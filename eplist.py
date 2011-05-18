import urllib2 as urllib
import re
import string

#output display format
#Season - Episode Number - Episode Title
display = "Season {0} - Episode {1} - {2}"

class Episode(object):
	def __init__(self, title, epNumber, season):
		self.title = title
		self.season = season
		self.episode = epNumber
	def __repr__(self):
                global display
		return display.format(self.season,self.episode,self.title)
			
class EpParser(object):
	def __init__(self, showTitle):
		self.title = self.__prepareTitle(showTitle.lower())
		self.url = "http://epguides.com/" + self.title
		self.episodeList = []
		self.parseData()

	def __prepareTitle(self, title):
                '''Remove any punctuation and whitespace from the title'''
		exclude = set(string.punctuation)
		title = ''.join(ch for ch in title if ch not in exclude)
		title = title.split()
		if title[0] == 'the': title.remove('the')
		return ''.join(title)
		
	def __getData(self):
		try:
			request = urllib.urlopen( self.url )
			return request.readlines()
		except urllib.HTTPError as e:
                        print '{0}'.format(e)
			if e.getcode == 404:
                                print 'Show {0} was not found, please check your spelling.'.format(self.title)
			exit()
		except urllib.URLError as e:                        
                        print '{0}'.format(e)
		

	def parseData(self):
                '''Requests the title's webpage from the URL, parses information,
                   and populates the episodeList'''
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
				s = int(info.group('season'))
				n = re.sub('<.*?>', '', n).strip()
				self.episodeList.append( Episode(n,e,s) )
				

def main():

	import argparse
	import sys

	parser = argparse.ArgumentParser()
	parser.add_argument('title', help="The title of the show")
	parser.add_argument('-s','--season', default=-1, type=int, help="The specific season to search for")
	namespace = parser.parse_args()

	title = namespace.title
	season= namespace.season
	
	ep = EpParser(title)
	results = ep.episodeList

	header  = "Show: {0}\n------" + ('-'*len(title))
	if season != -1:
		results = filter(lambda x: x.season == season, results)

	print header.format(title)
	for eps in results:
		print eps


if __name__ == '__main__':
	main()
	
