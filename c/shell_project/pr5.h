#ifndef PR5_H
#define PR5_H

/* CMPSC 311 Project 5 pr5.h
 * Name: Dan Tracy
 * Date: October 28, 2009
 * Email: djt5019@psu.edu
 */


#include <limits.h>

#ifdef _POSIX_C_SOURCE
/* use the minimal value for maximal portability; see APUE Fig. 2.8, 2.10, 2.11 */

#define MAXLINE        _POSIX_MAX_INPUT
#define PATH_SIZE        _POSIX_PATH_MAX
#define MAX_CHILDREN    _POSIX_CHILD_MAX
#define MAXARGS 128
#define ENV_SIZE 256

#else
/* use the default value for this system */

#define MAXLINE        MAX_INPUT
#define PATH_SIZE        PATH_MAX
#define MAX_CHILDREN    CHILD_MAX

/* Alternative versions can be obtained from
 *   #include <unistd.h>
 *   sysconf(_SC_LINE_MAX)
 *   sysconf(_SC_CHILD_MAX)
 */

#endif

/*----------------------------------------------------------------------------*/

#endif

