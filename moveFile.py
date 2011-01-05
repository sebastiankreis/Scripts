#Author: Dan Tracy
#Name: moveFile.py
#Date: Jan 5, 2011


import os, glob, shutil

class Move():

        __exts__ = {}
        
	@staticmethod
	def addExtensions( exts=None, resetExts=False ):

		if resetExts:
			Move.__exts__ = {}

                if exts is None:
                        raise Exception("There were no extensions passed")
                
		if type(exts) is list:
                        for ext in exts:
                                Move.__exts__[str(ext)] = str(ext)
                elif type(exts) is str:
                        Move.__exts__[exts] = exts
                elif type(exts) is dict:
                        for ext,key in exts.items():
                                Move.__exts__[ext] = key
                else:
                        raise Exception("Illegal extension argument type")



	@staticmethod
	def renameExt( srcPath='.', parentPath=None, recurseIntoFolders=False ):
		if Move.__exts__ == {}:
			return

                os.chdir(srcPath)
                                
		for f in glob.glob('*'):
                        name,ext = os.path.splitext(f)

                        if os.path.isdir(f) and recurseIntoFolders:
                                Move.renameExt( os.path.abspath(f), srcPath )
                        elif os.path.isfile(f) and ext in Move.__exts__:
                                newname = name + Move.__exts__[ext]
                                os.rename(f, newname)

                if parentPath is not None:
                        os.chdir(parentPath)


	@staticmethod
	def move( destPath=None, srcPath='.', parentPath=None, recurseIntoFolders=False ):

                if Move.__exts__ == {}:
                        raise Exception("The list of extensions is empty")
                elif destPath is None:
                        raise Exception("There is no destination path defined")
                
                
                try:
                        os.chdir(srcPath)
                except Exception, msg:
                        print msg
                        return

                print os.getcwd()
                                
		for f in glob.glob('*'):
                        name,ext = os.path.splitext(f)

                        if os.path.isdir(f) and recurseIntoFolders:
                                Move.move( os.path.abspath(f), destPath, srcPath )
                        elif os.path.isfile(f) and ext in Move.__exts__:
                                try:
                                        shutil.move(f, destPath)
                                except Exception, msg:
                                        print msg

                if parentPath is not None:
                        os.chdir(parentPath)
