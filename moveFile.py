#Author: Dan Tracy
#Name: moveFile.py
#Date: Jan 5, 2011
#intended for python 2.7

def renameExts( path = None, extensions = None ):
	""" Renames the files in the path given with the dictionary of extensions"""
	if path is None:
		raise Exception("Empty path passed to function")
    if extensions is None or type(extensions) is not dict:
        raise Exception("Invalid type for extensions dictionary")

	#try not to mangle the namespace as much
	import os
	
	for f in os.listdir(path):
		name,ext = os.path.splitext(f)
		name = os.path.join(path,name)
		#strip out the preceding dot from the filename
		ext = ext.replace('.','')
		
		if ext in extensions:
			f = os.path.join(path, f)
			name = name + '.' + extensions[ext]

			try:
				os.rename(f, name)
			except Exception as ex:
				print ex
	
	
def zipdir(path=None, archiveName = "test.zip"):
	if path is None:
		raise Exception("Empty path passed to function")
		
	import zipfile, os
	
	parent = os.getcwd()
	os.chdir( path )
	#So we dont accidentally include the zipfile we create in our file list
	files = os.listdir(path)
	
	with zipfile.ZipFile(archiveName, mode='w', compression=zipfile.ZIP_DEFLATED) as z:
		for f in files:
			z.write(f)
			
	os.chdir(parent)
	
