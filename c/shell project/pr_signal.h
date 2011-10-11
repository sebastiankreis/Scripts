#ifndef PR_SIGNAL_H
#define PR_SIGNAL_H

/* CMPSC 311 Project 5 pr_signal.h
 * Name: Dan Tracy
 * Date: October 28, 2009
 * Email: djt5019@psu.edu
 */

#include <signal.h>

extern int pr_debug;

typedef void sighandler_t(int);

/* Installs the signal and handler, returns 0 if successfully and -1 otherwise */
int install_signal(int sig, sighandler_t func);

/* Signal Handlers*/
void SIGINT_handler(int sig);
void SIGCHLD_handler(int sig);

/* Associates each specified signal to its handler*/
void setup_signals();


#endif


