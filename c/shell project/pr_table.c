/* CMPSC 311 Project 5 pr_table.c
 * Name: Dan Tracy
 * Date: October 28, 2009
 * Email: djt5019@psu.edu
 */

/*----------------------------------------------------------------------------*/

#include <stdio.h>
#include <sys/types.h>
#include <stdlib.h>

#include "pr5.h"
#include "pr_table.h"

typedef struct pr_process_link { 
  pid_t pid;  
  pid_t ppid;
  int   state;
  int   exit_status;
  int   background;
  struct pr_process_link* next;
} process_t;

process_t* process_table = NULL;
process_t* last_node 	= NULL; 
/* the last_node pointer will point to the last element in the list, for fast link addition */

#define STATE_NONE       0
#define STATE_RUNNING    1
#define STATE_TERMINATED 2

static char *state[] = { "none", "running", "terminated" };

/*----------------------------------------------------------------------------*/

void pt_print_processes(const char * const caller)
{
	process_t* temp = process_table; 
	printf("  process table, printed by %s\n", caller);
	printf("    pid     parent_pid     state   status         BG/FG\n");
	
	while( temp )
	{
		char* bg = (temp->background) ? "FG" : "BG";

		printf("%8d   %8d   %10s   0x%08x      %s\n",
			(int)temp -> pid,
			(int)temp -> ppid,
			state[temp -> state],
			temp -> exit_status,
			bg);

		temp = temp->next;
	}
}

void pt_init(pid_t pid)
{
	if( process_table == NULL)
	{ 
		process_table 		   = malloc( sizeof(process_t) );
		process_table->pid 	   = pid;
		process_table->ppid 	   = 0;
		process_table->state 	   = STATE_NONE;
		process_table->exit_status = 0;
		process_table->background  = 0;
		process_table->next 	   = NULL;

		last_node = process_table;
	}
	else 
		if(pr_debug) printf("%s warning: Process Table already initalized - skipping instruction\n",__func__);
   
	if (pr_debug) pt_print_processes(__func__);
}

void pt_insert(pid_t pid, pid_t ppid, int bg)
{
	process_t* temp = malloc( sizeof(process_t) );
	
	if( temp == NULL )
	{
		printf("%s: Error allocating new process node\n",__func__);
		exit(EXIT_FAILURE);
	}

	temp->pid 		= pid;
	temp->ppid 		= ppid;
	temp->state 		= STATE_RUNNING;
	temp->exit_status 	= 0;
	temp->background	= bg;
	temp->next 		= NULL;
   
	last_node->next 	= temp;
	last_node 		= temp;
}

pid_t pt_get_fg_process()
{
	process_t* temp = process_table;
	
	while( temp )
	{
		if( temp->background == 1)
			return temp->pid;
		temp=temp->next;
	}
	return -1;
}

void pt_update(pid_t pid, int status)
{
	process_t* temp = process_table;

	while ( temp )
	{
		if (temp->pid == pid)
		{
			temp->state = STATE_TERMINATED;
			temp->exit_status = status;
			break;
		}
	
		temp = temp->next;
	}

	if( temp == NULL ) if(pr_debug) printf("%s: process %d was not found\n",__func__, pid);
}

void pt_remove(pid_t pid)
{
	process_t* temp = process_table;
	
	while ( temp )
	{
		if(temp->next == NULL && temp->pid != pid) 
		{ 
			printf("%s: process %d was not found\n" , __func__, pid); 
			break; 
		}
	
		if (temp->next->pid == pid)
		{
			process_t* x = temp->next->next;
			free(temp->next);
			temp->next = x;
			last_node = temp;
			break;
		}
	
		temp = temp->next;
	}
}

void pt_cleanup()
{
	process_t* temp = process_table;

	while( temp )
	{
		if( temp -> state == STATE_TERMINATED )
			pt_remove( temp->pid );
		temp = temp->next;
	}
}
/*----------------------------------------------------------------------------*/

