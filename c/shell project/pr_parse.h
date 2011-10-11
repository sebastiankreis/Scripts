#ifndef PR_PARSE_H
#define PR_PARSE_H

/* CMPSC 311 Project 5 parse.h
 * Name: Dan Tracy
 * Date: October 28, 2009
 * Email: djt5019@psu.edu
 */

#include <string.h>

/*****************************************/

int parse( char* buf );
int execute_line( char** argv, int run_in_background);
int builtin( char** arg, int argc );

/*****************************************/
#endif
