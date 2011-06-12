#!/usr/bin/env python
# Author: Dan Tracy
# Name: moveFile.py
# Date: Jan 5, 2011
# intended for python 2.7

import os
import zipfile
from itertools import iterzip
from collections import OrderedDict

comicExt = {'zip':'cbz','rar':'cbr'}
def renameExts( path = None, extensions =comicExt, recurseIntoFolders=False ):
        """ Renames the files in the path given with the dictionary of extensions"""
        if path is None or extensions is None:
                return None

        for f in os.listdir(path):
                name,ext = os.path.splitext(f)
                name = os.path.join(path,name)

                if os.path.isdir(name) and recurseIntoFolders:
                        renameExts( name, extensions, True)
                else:
                        #strip out the preceding dot from the filename
                        ext = ext.replace('.','')
                        if ext in extensions:
                                f = os.path.join(path, f)
                                name = name + '.' + extensions[ext]
                                os.rename(f, os.path.join(path,name))
              

def renameFiles( path=None, names = None, testRun=False ):
	if path is None or names is None:
			raise Exception
 
	# We will sort dictionary with respect to key value by
	# removing all punctuation then adding each entry into
	# a ordered dictionary to preserve sorted order.  We do
	# this to insure the file list is in somewhat of a natural
	# ordering, otherwise we will have misnamed files
		
	files = os.listdir(path)
	clean = map(__removePunc, files)
	cleanFiles = dict(izip(clean,files))
	cleanFiles = orderedDictSort(cleanFiles)
		
	for f,n in izip(cleanFiles.iterkeys(),names):
		fileName = cleanFiles[f]
		_, ext   = os.path.splitext(f)
		newName  = n.strip() + ext

		fileName = os.path.join(path, fileName)
		newName  = os.path.join(path, newName)

		print ("OLD: {0}".format(fileName))
		print ("NEW: {0}".format(newName))
		print ""
				
		if not testRun: os.rename(fileName,newName)
	   
				

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
				

def __removePunc(title):
	import string
	name,ext = os.path.splitext(title)
	'''Remove any punctuation and whitespace from the title'''
	exclude = set(string.punctuation)
	name = ''.join(ch for ch in name if ch not in exclude)
	return name+ext

def orderedDictSort(dictionary):
	keys = dictionary.keys()
	keys.sort()
	d = zip(keys, map(dictionary.get,keys))
	return OrderedDict(d)
