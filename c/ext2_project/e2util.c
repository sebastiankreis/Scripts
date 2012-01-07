#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdint.h>
#include <time.h>
#include <errno.h>
#include <string.h>

#include "e2util.h"


// Switch all of the values in the superblock structure from ext2 little-endian
// to the host's byte order.
void byteswap_superblock(struct superblock *sb)
{
	SWAP_LE(sb->s_inodes_count, 32);
	SWAP_LE(sb->s_blocks_count, 32);
	SWAP_LE(sb->s_first_data_block, 32);
	SWAP_LE(sb->s_log_block_size, 32);
	SWAP_LE(sb->s_blocks_per_group, 32);
	SWAP_LE(sb->s_inodes_per_group, 32);
	SWAP_LE(sb->s_state, 16);
}

// Display a formatted output of the superblock parameters.
void print_superblock(struct superblock *sb)
{
	printf("Inodes: %u\n"
	       "Blocks: %u\n"
	       "First data block: %u\n"
	       "Block size: %u\n"
	       "Blocks/group: %u\n"
	       "Inodes/group: %u\n"
	       "State: %s\n",
			sb->s_inodes_count, sb->s_blocks_count,
			sb->s_first_data_block, blocksize(sb),
			sb->s_blocks_per_group, sb->s_inodes_per_group,
			sb->s_state == 1 ? "Clean" : "Dirty");
}

// Switch all of the values in the inode structure from ext2 little-endian to
// the host's byte order.
void byteswap_bgdesc(struct bgdesc *bg)
{
	SWAP_LE(bg->bg_block_bitmap, 32);
	SWAP_LE(bg->bg_inode_bitmap, 32);
	SWAP_LE(bg->bg_inode_table, 32);
}

// Switch all of the values in the inode structure from ext2 little-endian to
// the host's byte order.
void byteswap_inode(struct inode *i)
{
	int j;

	SWAP_LE(i->i_mode, 16);
	SWAP_LE(i->i_uid, 16);
	SWAP_LE(i->i_size, 32);
	SWAP_LE(i->i_atime, 32);
	SWAP_LE(i->i_ctime, 32);
	SWAP_LE(i->i_mtime, 32);
	SWAP_LE(i->i_dtime, 32);
	for (j = 0; j < 12; j++)
		SWAP_LE(i->i_block_d[j], 32);
	SWAP_LE(i->i_block_1i, 32);
	SWAP_LE(i->i_block_2i, 32);
	SWAP_LE(i->i_block_3i, 32);
}

// Display a formatted output of the inode parameters.
void print_inode(struct inode *i)
{
	time_t t;

	printf("Mode: %o\n"
	       "User ID: %u\n"
	       "Size: %u\n",
			i->i_mode, i->i_uid, i->i_size);
	t = i->i_atime;
	printf("Access time: %s", ctime(&t));
	t = i->i_ctime;
	printf("Change time: %s", ctime(&t));
	t = i->i_mtime;
	printf("Modification time: %s", ctime(&t));
	t = i->i_dtime;
	printf("Deletion time: %s", ctime(&t));
	printf("First direct block: %u\n", i->i_block_d[0]);
}

// Print out all the data in the file represented by a certain inode.
// Return 0 on success, 1 on error.
int print_inode_data(struct superblock *sb, struct inode *i)
{
	int fullblocks = i->i_size / blocksize(sb);
	int j;
	char *block;

	block = malloc(blocksize(sb));
	if (block == NULL)
		return 1;

	for (j = 0; j < fullblocks; j++) {
		if (get_inode_block(sb, i, j, block))
			return 1;
		if (fwrite(block, blocksize(sb), 1, stdout) != 1)
			return 1;
	}
	if (i->i_size % blocksize(sb)) {
		if (get_inode_block(sb, i, j, block))
			return 1;
		if (fwrite(block, i->i_size % blocksize(sb), 1, stdout) != 1)
			return 1;
	}

	free(block);
}

// Switch all of the values in an indirect block from ext2 little-endian to the
// host's byte order.
void byteswap_iblock(struct superblock *sb, char *block)
{
	int i;
	uint32_t *entry = (uint32_t *) block;
	for (i = 0; i < blocksize(sb) / 4; i++)
		SWAP_LE(entry[i], 32);
}

// Returns the block size of the filesystem
int blocksize(struct superblock *sb)
{
	return 1024 << sb->s_log_block_size;
}

// --- end provided code --- //

// Retrieve the interesting parts of the superblock and store it in the struct.
// Return 0 on success, 1 on error.
int get_superblock(FILE *f, struct superblock *out)
{
	errno = 0;
	// Save the file so other functions can use it
	out->file = f;

	// Move to byte 1024, the start of the superblock
	fseek(f, 1024,SEEK_SET);

	fread(&out->s_inodes_count, 4, 1, f);
	fread(&out->s_blocks_count, 4, 1, f);

	fseek(f, 12, SEEK_CUR);
	fread(&out->s_first_data_block, 4, 1, f);

	fread(&out->s_log_block_size, 4 ,1, f);

	fseek(f, 4, SEEK_CUR);
	fread(&out->s_blocks_per_group, 4, 1, f);

	fseek(f, 4, SEEK_CUR);
	fread(&out->s_inodes_per_group, 4, 1, f);
	
	fseek(f, 32, SEEK_CUR);
	fread(&out->s_state, 2, 1, f);

	// Convert the superblock from little-endian format to whatever the
	// host system is.  Leave this at the end of get_superblock.
	byteswap_superblock(out);

	if(errno != 0)
		return 1;

	return 0;
}

// Fetch the data from the specified block into the provided buffer.
// Return 0 on success, 1 on error.
int get_block_data(struct superblock *sb, int blk, char *out)
{

	if(blk < 0 || blk > sb->s_blocks_count)
		return 1;	

	size_t offset = (blocksize(sb) * blk) + 1024;
	size_t size = blocksize(sb);
	errno = 0;

	fseek(sb->file, offset, SEEK_SET);

	size_t i;
	for( i = 0; i < size; ++i)
	{
		out[i] = getc(sb->file);
	}

	out[size+1] = '\0';

	if(errno != 0)
		return 1;

	return 0;
}

// Write the data from the specified block to standard output.
// Return 0 on success, 1 on error.
int print_block_data(struct superblock *sb, int blk)
{
	int size = blocksize(sb);
	char* buffer = (char*) malloc( size + 1);
	errno = 0;

	if(!buffer)
		return 1;

	if( get_block_data(sb, blk, buffer) )
		return 1;

	size_t i;
	for(i=0; i<(size+1);++i)
		printf("%c",buffer[i]);

	free(buffer);

	if(errno != 0)
		return 1;

	return 0;
}

// Return the number of the block group that a certain block belongs to.
int bg_from_blk(struct superblock *sb, int blk)
{
	return blk / sb->s_blocks_per_group;
}

// Return the index of a block within its block group.
int blk_within_bg(struct superblock *sb, int blk)
{
	return blk % sb->s_blocks_per_group;
}

// Return the number of the block group that a certain inode belongs to.
int bg_from_ino(struct superblock *sb, int ino)
{
	return  (ino - 1) / sb->s_inodes_per_group;
}

// Return the index of an inode within its block group
int ino_within_bg(struct superblock *sb, int ino)
{
	return (ino -1 ) % sb->s_inodes_per_group;
}

// Retrieve information from the block group descriptor table.
// Return 0 on success, 1 on error.
int get_bgdesc(struct superblock *sb, int bg, struct bgdesc *out)
{
	errno = 0;
	int index = (blocksize(sb) * 2) + (32 * bg);
	fseek(sb->file, index, SEEK_SET);

	int numBytesRead = 0;
	numBytesRead += fread(&out->bg_block_bitmap, sizeof(uint32_t),1, sb->file);
	numBytesRead += fread(&out->bg_inode_bitmap, sizeof(uint32_t),1, sb->file);
	numBytesRead += fread(&out->bg_inode_table,  sizeof(uint32_t),1, sb->file);

	// Convert the block info from little-endian format to whatever the
	// host system is.  Leave this at the end of get_bgdesc.
	byteswap_bgdesc(out);

	return 0;
}

// Retrieve information from an inode (file control block).
// Return 0 on success, 1 on error.
int get_inode(struct superblock *sb, int ino, struct inode *out)
{	
	int inode_index  = ino_within_bg(sb, ino);
	int inode_group  = bg_from_ino(sb,ino);
	
	struct bgdesc bg_info;
	if( get_bgdesc(sb, inode_group, &bg_info) )
		return 1;

	int inode_table_start = (bg_info.bg_inode_table * blocksize(sb));
	int inode_table_entry = inode_table_start + (inode_index * 128);

	fseek(sb->file, inode_table_entry, SEEK_SET);
	
	fread(&out->i_mode,  sizeof(out->i_mode),  1, sb->file);
	fread(&out->i_uid,   sizeof(out->i_uid),   1, sb->file);
	fread(&out->i_size,  sizeof(out->i_size),  1, sb->file);
	fread(&out->i_atime, sizeof(out->i_atime), 1, sb->file);
	fread(&out->i_ctime, sizeof(out->i_ctime), 1, sb->file);
	fread(&out->i_mtime, sizeof(out->i_mtime), 1, sb->file);
	fread(&out->i_dtime, sizeof(out->i_dtime), 1, sb->file);

	fseek(sb->file, 16, SEEK_CUR);

	fread(&out->i_block_d, sizeof(uint32_t), 12, sb->file);
	fread(&out->i_block_1i, sizeof(out->i_block_1i), 1, sb->file);
	fread(&out->i_block_2i, sizeof(out->i_block_2i), 1, sb->file);
	fread(&out->i_block_3i, sizeof(out->i_block_3i), 1, sb->file);

//	int i;
//	for (i=0; i<12; ++i)
//		printf("0x%x\n", out->i_block_d[i]);
	
	// Convert the inode from little-endian format to whatever the host
	// system is.  Leave this at the end of get_inode.
	byteswap_inode(out);

	return 0;
}

// Retrieves the data from the nth data block of a certain inode.
// Return 0 on success, 1 on error.
int get_inode_block(struct superblock *sb, struct inode *i, uint32_t n, char *out)
{		
	uint32_t data_block;
	uint32_t blocks_per_indirection = (blocksize(sb)/sizeof(uint32_t));
	uint32_t bpi_2 = (blocks_per_indirection * blocks_per_indirection); 
	uint32_t bpi_3 = (blocks_per_indirection * bpi_2);

	if( n < 0 )
		return 1;

	if( n < 12 )
		data_block = i->i_block_d[n];

	else if( n < blocks_per_indirection ) //Single Indirection
	{	
		int index = n % blocks_per_indirection;
		
		uint32_t block_start = i->i_block_1i * blocksize(sb);
		uint32_t addr[blocks_per_indirection];

		//Seek to the index within the block of pointers
		fseek(sb->file, block_start, SEEK_SET);
		fread(addr, sizeof(uint32_t), blocks_per_indirection, sb->file);

		data_block = addr[index];
		
	}
 
	else if( n < bpi_2 ) //Double indirection
	{
		int index = n % bpi_2;

		uint32_t addr1[blocks_per_indirection];
		uint32_t addr2[blocks_per_indirection];

		uint32_t block_start = i->i_block_2i * blocksize(sb);

		fseek(sb->file, block_start, SEEK_SET);
		fread(addr1, sizeof(uint32_t), blocks_per_indirection, sb->file);

		
		uint32_t nextBlockStart = addr1[index] * blocksize(sb);
		fseek(sb->file, nextBlockStart, SEEK_SET);
		fread(addr2, sizeof(uint32_t), blocks_per_indirection, sb->file);


		index = n % blocks_per_indirection;

		data_block = addr2[index];	
	}

	else if( n < bpi_3 ) //Triple indirection
	{
		uint32_t addr[blocks_per_indirection];
		uint32_t block_start = i->i_block_3i * blocksize(sb);
		int index;
		
		fseek(sb->file, block_start, SEEK_SET);
		fread(addr, sizeof(uint32_t), blocks_per_indirection, sb->file);
		index = n % bpi_3;
		block_start = addr[index] * blocksize(sb);

		fseek(sb->file, block_start, SEEK_SET);
		fread(addr, sizeof(uint32_t), blocks_per_indirection, sb->file);
		index = n % bpi_2;
		block_start = addr[index] * blocksize(sb);	

		fseek(sb->file, block_start, SEEK_SET);
		fread(addr, sizeof(uint32_t), blocks_per_indirection, sb->file);
		index = n % blocks_per_indirection;
		data_block = addr[index];	
	}

	if(get_block_data(sb, data_block, out))
		return 1;

	byteswap_iblock(sb, out);
	return 0;
}

// Return 1 if a block is free, 0 if it is not, and -1 on error
int is_block_free(struct superblock *sb, int blk)
{
	if( blk < 0 || blk > sb->s_blocks_count)
		return -1;

	uint32_t block_group = bg_from_blk(sb,blk);
	uint32_t block_index = blk_within_bg(sb,blk);
	struct bgdesc groupInfo;

	if( get_bgdesc(sb, block_group, &groupInfo) )
		return -1;

	uint32_t bitmap = groupInfo.bg_block_bitmap;

	return (bitmap>>block_index) & 1;
}

// Return 1 if a block appears to be an indirect block, 0 if it does not, and
// -1 on error.
int looks_indirect(struct superblock *sb, char *block)
{
	char ptr1[33];
	char ptr2[33];

	strncpy(ptr1, block, 32);
	strncpy(ptr2, (block+32), 32);

	ptr1[33] = '\0';
	ptr2[33] = '\0';

	uint32_t p1, p2;

	p1 = strtoul(ptr1, NULL, 0);
	p2 = strtoul(ptr2, NULL, 0);

	if( p1 == 0 || p2 == 0 )
		return 0;

	else if( p2 - p1 == 1 )
		return 1;


	return -1;
}

// Return 1 if a block appears to be a doubly-indirect block, 0 if it does not,
// and -1 on error.
int looks_2indirect(struct superblock *sb, char *block)
{
	char ptr1[33];
	char ptr2[33];

	strncpy(ptr1, block, 32);
	strncpy(ptr2, (block+32), 32);

	uint32_t p1, p2;

	p1 = strtoul(ptr1, NULL, 0);
	p2 = strtoul(ptr2, NULL, 0);

	if( p1 == 0 || p2 == 0)
		return 0;


	if( looks_indirect(sb, ptr1) == 1 && looks_indirect(sb,ptr2) == 1 )
		return 1; 
	return 0;
}
