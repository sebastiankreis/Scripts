class Move():

	@staticmethod
	def addExtensions( exts=[], extmapping={}, resetExts=False ):
		from os import path, walk, rename
		from glob import glob

		if resetExts:
			Move.__exts__ = None
			Move.__extmap__ = None

		Move.__exts__ = exts
		Move.__extmap__ = extmapping

	@staticmethod
	def renameExt( srcPath, recurseIntoFolders=False ):
		if Move.__extmap__ == {}:
			return

		if recurseIntoFolders:
			for root, dirs, files in os.walk(srcPath):
				for f in files:
					name,ext = os.path.splitext(f)

					if ext in Move.__extmap__:
						try:
							os.rename(f, name+Move.__extmap__[ext])
						except Exception, msg:
							print msg
		else:
			for f in glob.glob('*'):
				name,ext = os.path.splitext(f)
				if os.path.isfile(f) and ext in Move.__extmap__:
					os.rename(f, name+Move.__extmap__[ext])

	@staticmethod
	def move( srcPath, destPath ):
		for root, dirs, files in os.walk(srcPath):
			for f in files:
				name,extension = os.path.splitext(f)
				if extensions in Move.__exts__ or Move.__exts__ == []:
					try:
						shutil.move(f, destPath)
					except Exception as msg:
						print msgp
