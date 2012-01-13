.SILENT:

SOURCES = printsb.c carve.c custominode.c find2i.c isfree.c printbg.c printblk.c printidata.c printinode.c
OBJS = $(SOURCES:.c=.o)

.PHONY: all clean

all: $(SOURCES)

e2util.o:
	gcc -c e2util.c

$(SOURCES): e2util.o
	gcc $@ $< -o $(@:.c=)

clean:
	$(RM) $(SOURCES:.c=)
	$(RM) e2util.o

