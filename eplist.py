import urllib2 as urllib
import re

class EpParser(object):
	def __init__(self, showTitle):
		self.title = showTitle.replace(' ','').strip()
		self.url = "http://epguides.com/" + self.title
		self.infoList = []
		
	class Show(object):
		def __init__(self, title, season, epNumber):
			self.title = title
			self.season = season
			self.episode = epNumber
			
	def __getData(self):
		request = urllib.urlopen( self.url )
		return request.readlines()

	def parseData(self):
		data = self.__getData()
		
		#taken from https://github.com/bertjwregeer/epguides/blob/master/epguides.py
		pattern = r"""
			^						# Start of the string
			(?:[\s]*?[\d]*\.?)		# Number on list
			[\s]{2,}				# Ignore whitespace
			(?P<season>[\d]*)		# Season number
			-						# Separator
			[\s]*					# Optional whitespace
			(?P<episode>[\d]*)		# Episode number
			[\s]{2,}				# Ignore whitespace
			(?P<product>.+|)		# Product number, or nothing
			[\s]{2,}				# Ignore whitespace
			(?P<airdate>[\w\s/]*?)	# Air-date
			[\s]{2,}				# Ignore whitespace
			(?P<name>.*)			# Episode name
			$						# End of line
			"""
		regex = re.compile(pattern, re.X|re.I )

		for line in data:
			info = regex.match(line)
			if info is not None:
				n = info.group('name')
				e = info.group('episode')
				s = info.group('season')
				n = re.sub('<.*?>', '', n).strip()
				self.infoList.append( Show(n,e,s) )