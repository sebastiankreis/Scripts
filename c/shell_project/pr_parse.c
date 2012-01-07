/* CMPSC 311 Project 5 parse.c
 * Name: Dan Tracy
 * Date: October 28, 2009
 * Email: djt5019@psu.edu
 */

#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>
#include <sys/wait.h>
#include <signal.h>
#include <dirent.h>

#include "pr5.h"
#include "pr_parse.h"
#include "pr_table.h"
#include "pr_signal.h"

extern char** environ;
extern int    pr_debug;      // debug mode
extern int    verbose;       // verbose mode
extern char*  path;          // Path of the current dir
int count;

int parse(char* line)
{
	int bg   		= 0;
	int argc 		= 0;
	int exit_status		= EXIT_SUCCESS;
	char* argv[MAXARGS];
	char* buffer;

	buffer = strtok(line, " \t\n");
 
	while ( buffer != NULL )
	{
		argv[ argc++ ] = buffer;
		buffer = strtok( NULL, " \t\n" );
	}

	argv[argc] = NULL;
	count = argc;

	/* check if the last character is an ampersand */
	if(  strcmp(argv[argc-1], "&") == 0 ) 
	{
		argv[ --argc ] = '\0'; /* remove the ampersand */
		bg = 1; 
		if( pr_debug) printf("%s Message: Background enabled for %s\n", __func__, argv[0]);
	}
	
	if(pr_debug || verbose)printf("%s Message: Argument = %s\n", __func__, argv[0]);
	if(pr_debug)printf("%s Message: Arg Count= %d\n", __func__, argc);

	if( (exit_status = builtin( argv, argc )) != 0 )
		{ exit_status = execute_line(argv, bg); }

	return exit_status;
}

int execute_line( char** argv, int bg )
{
	int ret = EXIT_SUCCESS;

	fflush(stdin);
	fflush(stdout);
	
	if(verbose || pr_debug) printf("%s Message: Creating child process for %s\n",__func__,argv[0]);

	pid_t pid = fork();

	/* Child failed */
	if( pid < 0 )
	{
		fprintf(stderr, "%s Critical Error: Fork has failed\n", __func__);
		exit(EXIT_FAILURE);
	}
	/* Child process */
	else if (pid == 0)
	{
		sleep(1);
		if (execvp(argv[0], argv) == -1)
		{
			printf("%s: failed: %s\n", argv[0], strerror(errno));
			_exit(EXIT_FAILURE);
		}
		if(pr_debug||verbose) printf("PID[%d]: %s has finished running\n", getpid(),argv[0]);
		_exit(EXIT_SUCCESS);
	}
	/* Parent process */
	else if (pid > 0)
	{
		if(pr_debug) printf("%s Message: Adding %s to process table\n", __func__, argv[0] );
		
		pt_insert(pid, getppid(), bg);
		if( verbose||pr_debug) pt_print_processes(__func__);
		if( bg == 0 )
		{
			if(pr_debug) printf("%s message: Updating PID = %d\n\n", __func__,(int)pid);

			if (waitpid(pid, &ret, 0) == -1)
			{
		  		printf("%s: failed: %s\n", argv[0], strerror(errno));
		  		exit(EXIT_FAILURE);
			}

			pt_update(pid, 2);
		}
		else
		{
			if(pr_debug) printf("%s message: Updating PID = %d\n\n", __func__,(int)pid);
			pt_update(pid, 1);
			if(pr_debug||verbose) printf("\n%s Message: pid = %d\n process %s - executed in background\n", __func__, pid, argv[0]);
		}
	}

	return ret;
}


int builtin(char** arg, int argc)
{
	if (strcmp(arg[0], "exit") == 0 || strcmp(arg[0],"quit") == 0)
		{ exit(0); }
	
	if (strcmp(arg[0], "&") == 0)
		{ return 1; }

	if (strcmp(arg[0], "pjobs") == 0)
		{ pt_print_processes("Running Processes"); return 0;}

	if (strcmp(arg[0], "echo") == 0)
	{ 
		int i; 
	
		for( i = 1; i < argc; i++ )
			printf(" %s", arg[i]);

		printf("\n"); 
		return 0;
	}

	if (strcmp(arg[0], "dir") == 0 )
	{
		printf("%s\n", path);
		return 0;
	}

	if (strcmp(arg[0], "cdir") == 0 )
	{
		char* dir;

		if( argc == 1 )
		{
			/*If no path was provided default to the HOME, if that dosent exist then goto / */
			dir = getenv("HOME");

			if( !dir ) dir = "/";

			if( chdir(dir) < 0 )
				printf("Path dosent exist\n");

			getcwd(path, PATH_SIZE);

			return 0;
		}
		else
			dir = arg[1];

		if( dir[0] != '/' )
		{
			strcat(path,"/");
			strcat(path,dir);
		}

		if( chdir(path) < 0 )
			printf("Path dosent exist\n");

		getcwd(path, PATH_SIZE);
		printf("%s\n", path);

		return 0;
	}

	if (strcmp(arg[0],"setenv") == 0 )
	{
		if( argc != 2 ) return 0;

		if( putenv( arg[1] ) < 0 )
		{ fprintf(stderr, "Error adding %s to environment list\n", arg[1]); return 0; }
	
		return 0;
	}

	if (strcmp(arg[0], "unsetenv") == 0 )
	{
		if( argc != 2 ) return 0;

		unsetenv(arg[1]);
		return 0;
	}

	if (strcmp(arg[0], "penv") == 0 )
	{
		int i = 0;

		while( environ[i] != NULL )
			printf("%s\n", environ[i++]);

		return 0;
	}

	if (strcmp(arg[0], "set") == 0 )
	{
		if( argc != 3 ) return 0;

		int opt = atoi(arg[2]);

		if( opt>1 || opt<0 )
		{ printf("Please specify either on[1] or off[0]\n"); return 0; }

		if( strcmp(arg[1],"verbose") == 0 )
			verbose = opt;
		else if( strcmp(arg[1],"debug") == 0 )
			pr_debug = opt;


		return 0;
			 
	}

	if (strcmp(arg[0], "help") == 0)
	{ 
		printf("Help Command Listing\n");
		printf("--------------------\n");
		printf("exit                 -- Exits the shell\n");
		printf("quit                 -- Exits the shell\n");
		printf("pjobs                -- Prints Active Jobs, Shell is always first\n");
		printf("echo                 -- Prints whatever is on the command line\n");
		printf("dir                  -- Prints the current working directory\n");
		printf("cdir [path]          -- Changes the directory to path\n");
		printf("setenv [var=val]     -- Sets var = val\n");
		printf("penv                 -- Prints all environment variables\n");
		printf("unsetenv [var]       -- Unsets var\n");
		printf("set debug [on|off]   -- Sets debug mode on or off\n");
		printf("set verbose[on|off]  -- Sets verbose mode on or off\n");
		printf("help                 -- Prints this message\n");

		return 0;
	}

	return 1;
};

