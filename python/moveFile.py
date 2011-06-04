#Author: Dan Tracy
#!/usr/bin/env python
# Name: moveFile.py
# Date: Jan 5, 2011
# intended for python 2.7

import os
import zipfile

def renameExts( path = None, extensions = {'zip':'cbz','rar':'cbr'}, recurseIntoFolders=False ):
        """ Renames the files in the path given with the dictionary of extensions"""
        if path is None:
                raise Exception("Empty path passed to function")
        if extensions is None or type(extensions) is not dict:
                raise Exception("Invalid type for extensions dictionary")
 
        for f in os.listdir(path):
                name,ext = os.path.splitext(f)
                name = os.path.join(path,name)

                if os.path.isdir(name+ext) and recurseIntoFolders:
                        renameExts( name+ext, extensions, True)
                else:
                        #strip out the preceding dot from the filename
                        ext = ext.replace('.','')
                        if ext in extensions:
                                f = os.path.join(path, f)
                                name = name + '.' + extensions[ext]
                                try:
                                        os.rename(f, os.path.join(path,name))
                                except Exception as ex:
                                        print ex

def renameFiles( path=None, names = None ):
        if path is None or names is None:
                raise Exception

        files = os.listdir(path)
        for f,n in zip(files,names):
                _,ext = os.path.splitext(f)
                n = n.strip() + ext
                n = os.path.join(path,n)
                f = os.path.join(path,f)
                print f
                print n
                print ""
                os.rename(f,n)
       
                

def zipDirs(topPath = None, ext = 'zip'):
        topPath = os.path.realpath(topPath)
        parent = os.getcwd()
        os.chdir(topPath)

        def __zipdir(path=None, parent=None, archiveName = "test.zip"):
                files = os.listdir(path)
                with zipfile.ZipFile(archiveName, mode='w', compression=zipfile.ZIP_DEFLATED) as z:
                        os.chdir(path)
                        for f in files:
                                z.write(f)
                        os.chdir(parent)
        
        for dirs in os.listdir(topPath):
                name = dirs
                dirs = os.path.realpath(dirs)
                if os.path.isdir(dirs):
                        print "Zipping {0}".format(dirs)
                        __zipdir(dirs, topPath, dirs+'.'+ext)

        os.chdir(parent)
                

                 
