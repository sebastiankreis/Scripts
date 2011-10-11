# Dan Tracy
# PR5 Makefile

SRC = pr5.c pr_parse.c pr_table.c pr_signal.c
INC = $(SRC:.c=.h)
ALL = $(SRC) $(INC)

cc: $(ALL)
	echo "echo Welcome to pr5.init" > pr5.init
	cc -v -o pr5 $(SRC) 

gcc: $(ALL)
	echo "echo Welcome to pr5.init" > pr5.init
	gcc -Wall -Wextra -std=c99 -D_POSIX_C_SOURCE=200112L -D_XOPEN_SOURCE=600 -o pr5 $(SRC)

