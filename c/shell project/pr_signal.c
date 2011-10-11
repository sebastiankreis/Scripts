/* CMPSC 311 Project 5 pr_signal.c
 * Name: Dan Tracy
 * Date: October 28, 2009
 * Email: djt5019@psu.edu
 */

#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>

#include "pr5.h"
#include "pr_signal.h"
#include "pr_table.h"

extern int pr_debug;
extern int verbose;

int install_signal(int sig, sighandler_t func)
{
	int error;
	struct sigaction sigact;
	int ret;

	sigact.sa_handler = func;
	sigemptyset(&sigact.sa_mask);
	sigact.sa_flags = SA_RESTART;

	ret = sigaction(sig, &sigact, NULL);
	error = (ret == -1);

	if( error )
	{
		fprintf(stderr, "install_signal_handler(%d) failed: %s\n", sig, strerror(errno));
	}

	return (error ? -1 : 0);
}

void setup_signals()
{
	install_signal( SIGCHLD, SIGCHLD_handler);
	install_signal( SIGINT, SIGINT_handler);
}

void SIGCHLD_handler( int sig )
{
	if( sig == SIGCHLD )
	{
		pt_cleanup();
		if(pr_debug || verbose) printf("\n%s Message: Child Signal Received\n",__func__);
	}
	else
	{
		fprintf(stderr, "%s Error: Critical Error receiving SIGCHLD, exiting\n", __func__);
		exit(EXIT_FAILURE);
	}
}

void SIGINT_handler( int sig )
{
	if( sig == SIGINT)
	{
		pid_t fg_process = pt_get_fg_process();

		if( fg_process == -1 )
		{
			if(pr_debug) printf("\n%s Message: Caught SIGINT, no FG process to terminate.\n", __func__);
			return;
		}
		else
		{
			printf("\n%s Message: Killing PID = %d\n", __func__, (int)fg_process);
			pt_update(fg_process, 3);
			kill(fg_process, SIGINT);
			return;
		}	
	}
	else
	{
		fprintf(stderr, "\n%s Error: Critical Error receiving SIGINT, exiting\n", __func__);
		exit(1);
	}
}
