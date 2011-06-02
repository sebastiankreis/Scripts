#!/usr/bin/env python

import urllib2 as urllib
import re
import string
from contextlib import closing

try:
	import sqlite3 as sql
except ImportError:
	sql = None

#output display format
#Season - Episode Number - Episode Title
display = "Season {0} - Episode {1} - {2}"
verbose = False

class Episode(object):
	def __init__(self, title, epNumber, season):
		self.title = title
		self.season = season
		self.episode = epNumber
		
	def __repr__(self):
		global display
		return display.format(self.season,self.episode,self.title)
			
class EpParser(object):
	def __init__(self, showTitle, cache=None):
		''' Proper title is used for the database/url while the display
		title is used for error messages/display purposes'''
		global verbose
		self.properTitle = self._prepareTitle(showTitle.lower())
		self.displayTitle = showTitle
		
		self.url = "http://epguides.com/" + self.properTitle
		self.episodeList = []
		self.cache = cache
		self.verbose = verbose

	def _prepareTitle(self, title):
		'''Remove any punctuation and whitespace from the title'''
		
		exclude = set(string.punctuation)
		title = ''.join(ch for ch in title if ch not in exclude)
		title = title.split()
		if title[0] == 'the': title.remove('the')
		return ''.join(title)

	def parseData(self):
		if self.cache is not None:
			self.episodeList = self._parseCacheData()

		# The show was found in the database
		if self.episodeList != []:
			if self.verbose: print "Show found in database"
			self.cache.close()
			return 
		if self.verbose: print "Show not found in database, polling web"
		self.episodeList = self._parseHTMLData()

		if self.cache is not None:
			if self.verbose: print "Adding show to the database"
			self.cache.addShow( self.properTitle, self.episodeList )
			self.cache.close()
			
	def _parseCacheData(self):
		showId = self.cache.getShowId(self.properTitle)
		
		if showId == -1: return []

		return self.cache.getEpisodes(showId)
		 

	def _getHTMLData(self):
		with closing(urllib.urlopen( self.url )) as request:
			if request.getcode() == 200:
				return request.readlines()
			
	def _parseHTMLData(self):
		data = self._getHTMLData()
		episodes = []
		pattern = r"""
			^		        # Start of the string
			(?:[\s]*?[\d]*\.?)	# Number on list
			[\s]{2,}		# Ignore whitespace
			(?P<season>[\d]*)	# Season number
			-			# Separator
			[\s]*			# Optional whitespace
			(?P<episode>[\d]*)	# Episode number
			[\s]{2,}		# Ignore whitespace
			(?P<product>.+|)	# Product number, or nothing
			[\s]{2,}		# Ignore whitespace
			(?P<airdate>[\w\s/]*?)	# Air-date
			[\s]{2,}		# Ignore whitespace
			(?P<name>.*)		# Episode name
			$			# End of line
			"""
		regex = re.compile(pattern, re.X|re.I )

		for line in data:
			info = regex.match(line)
			if info is not None:
				n = info.group('name')
				e = info.group('episode')
				s = int(info.group('season'))
				n = re.sub('<.*?>', '', n).strip()
				episodes.append( Episode(n,e,s) )
		return episodes

class Cache(object):
	def __init__(self, recreate=False, db="episodes.db"):
		self.connection = sql.connect(db)
		self.cursor = self.connection.cursor()
		if recreate == True:
			print "Making a new cache"
			self.cursor.executescript( '''
				DROP TABLE episodes;
				DROP TABLE shows;

				CREATE TABLE shows (	
					sid INTEGER PRIMARY KEY, 
					title TEXT
					);

				CREATE TABLE episodes ( 
					eid INTEGER PRIMARY KEY, 
					sid INTEGER, eptitle TEXT, 
					season INTEGER, 
					showNumber INTEGER
			);''')

	def close(self):
		self.cursor.close()
		self.connection.commit()
		self.connection.close()

	def getShowId(self, showTitle):
		title = (showTitle,)
		self.cursor.execute("SELECT sid FROM shows WHERE\
					title=?", title)
		result = self.cursor.fetchone()
		if result is not None:
			return result[0]
		else:
			return -1

	def getEpisodes(self, showId):
		sid = (showId,)
		self.cursor.execute(
			"SELECT eptitle, shownumber, season FROM episodes\
			 WHERE sid=?",sid)
		
		result = self.cursor.fetchall()
		eps = []
		
		if result is not None:
			for episode in result:
				eps.append( Episode(*episode) )

		return eps

		
	def addShow(self, showTitle, episodes):
		title = (showTitle,)
		self.cursor.execute("INSERT INTO shows values (NULL, ?)", title)
		showId = self.getShowId(showTitle)
		for e in episodes:
			show = (showId,e.title, e.season, e.episode,)
			self.cursor.execute(
				"INSERT INTO episodes values (NULL, ?, ?, ?, ?)", show)
	
def main():
	import argparse
	import sys

	parser = argparse.ArgumentParser(description="Episode Parser")

	parser.add_argument('title',
			    help="The title of the show")
	
	parser.add_argument('-s','--season', default=-1, type=int, metavar='n',
			    help="The specific season to search for")
	
	parser.add_argument('-d','--display-header', action="store_true",
			    help="Display the header at the top of the output")
	
	parser.add_argument('-v', '--verbose', action="store_true",
			    help="Be verbose")
	
	parser.add_argument('-n', '--nocache', action="store_true",
			    help="Disallow the program to load the episode database")

	parser.add_argument('-r', '--recreatecache', action="store_true",
			    help="Will recreate the cache from scratch, be sure you want to")
	
	namespace = parser.parse_args()
	
	title   = namespace.title
	season  = namespace.season
	newCache= namespace.recreatecache

	global verbose
	verbose = namespace.verbose

	if sql is None:
		cache = None
	else:
		cache = Cache(recreate=newCache)
		
	ep = EpParser(title, cache)
	ep.parseData()
	results = ep.episodeList

	if namespace.display_header or namespace.verbose:
		print ""
		print "Show: {0}".format(title)
		print "Number of episodes: {0}".format(len(results))
		print "Number of seasons: {0}".format( results[-1].season )
		print "-" * 30
		print ""

	if season != -1:
		results = filter(lambda x: x.season == season, results)

	currSeason = results[0].season
	for eps in results:
		if currSeason != eps.season:
			print ""
		print eps
		currSeason = eps.season


if __name__ == '__main__':
	main()	
