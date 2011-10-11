#ifndef PR_TABLE_H
#define PR_TABLE_H

/* CMPSC 311 Project 5 ptable.h
 * Name: Dan Tracy
 * Date: October 28, 2009
 * Email: djt5019@psu.edu
 */


/*----------------------------------------------------------------------------*/

#include <sys/types.h>

extern int pr_debug;

/* initalize the process table */
void pt_init(pid_t pid);

/* will iterate through the process table printing each one */
void pt_print_processes(const char * const caller);

/* Common linked list functions */
void pt_insert(pid_t pid, pid_t ppid, int bg);
void pt_update(pid_t pid, int status);
void pt_remove(pid_t pid);

/* Remove any terminated processes */
void pt_cleanup();

/* Return the process currently running in the foreground*/
/* We say the shell itself is running in the background*/
pid_t pt_get_fg_process();

/*----------------------------------------------------------------------------*/

#endif
