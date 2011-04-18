import urllib2 as urllib
import re
import sys


class Episode(object):
	def __init__(self, title, epNumber, season):
		self.title = title
		self.season = season
		self.episode = epNumber
	def __repr__(self):
		return "Season {0} - Episode {1} - {2}".format(self.season,self.episode,self.title)
			
class EpParser(object):
	def __init__(self, showTitle):
		self.title = showTitle.replace(' ','').strip()
		self.url = "http://epguides.com/" + self.title
		self.episodeList = []
		self.parseData()
			
	def __getData(self):
		try:
			request = urllib.urlopen( self.url )
			return request.readlines()
		except urllib.HTTPError as e:
			print 'HTTP Response: {0}'.format(e)
			exit()
		

	def parseData(self):
		data = self.__getData()
		
		pattern = r"""
			^			                # Start of the string
			(?:[\s]*?[\d]*\.?)		        # Number on list
			[\s]{2,}			        # Ignore whitespace
			(?P<season>[\d]*)		        # Season number
			-				        # Separator
			[\s]*				        # Optional whitespace
			(?P<episode>[\d]*)		        # Episode number
			[\s]{2,}			        # Ignore whitespace
			(?P<product>.+|)		        # Product number, or nothing
			[\s]{2,}				# Ignore whitespace
			(?P<airdate>[\w\s/]*?)	                # Air-date
			[\s]{2,}				# Ignore whitespace
			(?P<name>.*)			        # Episode name
			$					# End of line
			"""
		regex = re.compile(pattern, re.X|re.I )

		for line in data:
			info = regex.match(line)
			if info is not None:
				n = info.group('name')
				e = info.group('episode')
				s = info.group('season')
				n = re.sub('<.*?>', '', n).strip()
				self.episodeList.append( Episode(n,e,s) )
				
				
if __name__ == '__main__':
	if len(sys.argv)== 1:
		title = raw_input('Enter show title: ')
	else:
		title = sys.argv[1]
	ep = EpParser(title)
	for eps in ep.episodeList:
		print "Season {0} - Episode {1} - {2}".format(eps.season, eps.episode, eps.title)
