/* CMPSC 311 Project 5 pr5.c
 * Name: Dan Tracy
 * Date: October 28, 2009
 * Email: djt5019@psu.edu
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <signal.h>
#include <dirent.h>
#include <sys/wait.h>

#include "pr_table.h"
#include "pr_parse.h"
#include "pr_signal.h"
#include "pr5.h"

char* user;
char* path;
int   pr_debug	    = 0;
int   help_flag     = 0;	
int   verbose	    = 0;
int   interactive   = 0;
int   echo	    = 0;
int   write_history = 0;

extern char *optarg;
extern char **environ;
extern int optind;
extern int optopt;
extern int opterr;

static void usage(char* prog, int status);
static int process_loop(FILE* input, FILE* output);

int main(int argc, char *argv[])
{
	FILE*  input_file;
	FILE*  output_file;
	char*  init_file 	= "pr5.init";
	char*  write_file 	= "pr5.history";
	int    ch		= 0;
	int    exit_status 	= EXIT_SUCCESS;

	if( (user=getenv("USERNAME")) == NULL ) user=argv[0];

	/* Parse the command line */
	while( (ch = getopt(argc,argv,"rhiveds:w:")) != -1 )
	{
		switch(ch)
		{
			case 'h':
				usage( argv[0], EXIT_SUCCESS );
				break;
			case 'i':
				interactive = 1;
				break;
			case 'v':
				verbose = 1;
				break;
			case 'e':
				echo = 1;
				break;
			case 'd':
				pr_debug = 1;
				break;
			case 's':
				init_file = optarg;
				break;
			case 'w':
				write_history = 1;
				write_file = optarg;
				break;
			case 'r':
				write_history = 1;
				break;
			case '?':
			  	printf("%s: invalid option '%c'\n", argv[0], optopt);
			  	usage(argv[0], EXIT_FAILURE);
			  	break;
			case ':':
			  	printf("%s: invalid option '%c' (missing argument)\n", argv[0], optopt);
			  	usage(argv[0], EXIT_FAILURE);
			  	break;
			default:
				usage(argv[0], EXIT_FAILURE);
				break;		
		}	
	}

	/* Get the current working directory */
	path = malloc( PATH_SIZE );
	if( !path ) { printf("Malloc could not allocate space for the pathname\n"); exit(1); }
	
	if( getcwd(path, PATH_SIZE) == NULL )
	{
		printf("Could not retreive current working directory");
		if( (path = getenv("HOME")) == NULL)
			path = "\\";
	}

	/* Setup process table and the signal handlers*/
	pt_init( getppid() );
	setup_signals();

	/* If writing is enabled, open the output file */
	if(write_history)
		if( (output_file=fopen(write_file, "w+")) == NULL )
		   {  printf("The output file couldnt be created\n"); exit(1);  }

	/* If the init file isnt found just skip it*/
	if( (input_file=fopen(init_file,"r")) != NULL )
		{ exit_status = process_loop(input_file, output_file); }
	
	/* if nothing was specified then just exit*/
	if( optind == argc && interactive == 0 )
		exit(EXIT_SUCCESS);

	/* If there are no files or there is the - character read from stdin */
	if( argv[optind] == NULL || strcmp(argv[optind], "-") == 0 )
		exit_status = process_loop(stdin, output_file);


	else
	{
		int i;
		for(i = optind; i<argc; i++ )
		{
			if( (input_file=fopen(argv[i], "r")) == NULL )
			{
				printf("File %s could not be opened\n", argv[i]);
				continue;
			}
			else
			{
				printf("\nOpening File %s\n", argv[i]);
				printf("--------------------------------\n");
				exit_status = process_loop(input_file, output_file);
			}
		}
	}

	fclose(output_file);

	printf("\n\n");

	return exit_status;
}

static int process_loop( FILE* input, FILE* output )
{
	int status = 0;
	char cmdline[MAXLINE];
	int counter = 0;

	while ( !feof(input) )
	{
		if(interactive) printf("%s%%[%d]: ", user, ++counter);

		fgets(cmdline, MAXLINE, input);
		
		if( write_history ) fputs(cmdline, output);

		if ( strcmp(cmdline, "\n") == 0 )
			{ pt_cleanup();  --counter; continue; }

		if( echo ) printf("%s\n",cmdline);

		if ( cmdline == NULL)
			{ break; }

		/* Empty line, just check to see if any processes terminated and continue */
		

		status = parse(cmdline);

		if(pr_debug) printf("Parse Exit Status %d\n", status);

		pt_cleanup();

	}

	fclose(input);
	if(write_history)fputc('\n', output);
	
	printf("\n");
	return status;
}

static void usage(char *prog, int status)
{
  if (status == EXIT_SUCCESS)
  {
      printf("Usage: %s [-h] [-i] [-v] [-e] [-s f] file1 file2 ....\n", prog);
      printf("    -h      help\n");
      printf("    -i      interactive mode\n");
      printf("    -v      verbose mode\n");
      printf("    -d      debug mode\n");
      printf("    -e      echo commands before execution\n");
      printf("    -s f    use startup file f, default pr5.init\n");
      printf("    -w f    write commands inputted to file f\n");
      printf("    -r      writes commands to default file [pr5.history]\n");
  }
  else
  {
      fprintf(stderr, "%s: Try '%s -h' for usage information.\n", prog, prog);
  }

  exit(status);
}
