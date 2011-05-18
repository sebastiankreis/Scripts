#!/usr/bin/sh

SAVE_GAME="World2"
SAVE_DIR="/cygdrive/c/Users/Dan/AppData/Roaming/.minecraft/saves/"
BACKUP_DEST="/cygdrive/c/Users/Dan/Dropbox/Minecraft_Backup"
ARCHIVE_NAME="World2-$(date +%m-%d-%Y).tar.gz"
NUM_BACKUPS=1
NUM_FILES=$(ls -tr ${BACKUP_DEST} | wc -l)

if [ -f $BACKUP_DEST/$ARCHIVE_NAME ]
	then echo "Archive already exists"
	exit
fi

cd $SAVE_DIR
tar czf $ARCHIVE_NAME $SAVE_GAME
mv $ARCHIVE_NAME $BACKUP_DEST

cd $BACKUP_DEST

if [[ $NUM_BACKUPS < $NUM_FILES ]];
	then
		echo "Removing old backup: $(ls -tr $BACKUP_DEST | head -n 1)"
		rm -i $"$(ls -tr | head -n 1)"
fi

echo "Backup sucessful"
