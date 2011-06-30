# -*- coding: utf-8 -*-
# author:  Dan Tracy
# program: cache.py

import atexit
import os
try:
    import sqlite3
except ImportError:
    exit("sqlite3 not found")

from Utils import Episode

class Cache(object):
    ''' Our database logic class'''
    _sqlquery = '''
        CREATE TABLE shows (
            sid INTEGER PRIMARY KEY,
            title TEXT
        );
        
        CREATE TABLE episodes (
            eid INTEGER PRIMARY KEY,
            sid INTEGER,
            eptitle TEXT,
            season INTEGER,
            showNumber INTEGER,			
            FOREIGN KEY(sid) REFERENCES shows(sid)
        );'''
    
    def __init__(self, recreate=False, dbName="resources/episodes.db", verbose=False):
        
        if not os.path.exists(dbName):
            self.connection = sqlite3.connect(dbName)
            self.connection.executescript( Cache._sqlquery )
        else:
            self.connection = sqlite3.connect(dbName)
            
        self.verbose = verbose
        
        if recreate:
            if self.verbose:  print "Making a new cache"

            self.__executeQuery("DROP TABLE shows")
            self.__executeQuery("DROP TABLE episodes")
            
            self.connection.executescript( Cache._sqlquery )
            
            
        self.cursor = self.connection.cursor()
        
        #Make sure everything is utf-8
        self.connection.text_factory = lambda x: unicode(x, 'utf-8')
        atexit.register( self.close )

    def __executeQuery(self, query):
        try:
            self.connection.execute( query )
        except:
            pass

    def close(self):
        ''' Commits any changes to the database then closes connections to it'''
        self.cursor.close()
        self.connection.commit()
        self.connection.close()

    def getShowId(self, showTitle):
        ''' Polls the database for the shows title then returns its show id'''
        title = (showTitle, )
        self.cursor.execute("SELECT sid FROM shows WHERE title=?", title)
        result = self.cursor.fetchone()
        if result is not None:
            return result[0]
        else:
            return -1

    def getEpisodes(self, showId):
        ''' Using the show id return the shows associated with that id'''
        sid = (showId, )
        self.cursor.execute(
            "SELECT eptitle, shownumber, season FROM episodes\
             WHERE sid=?", sid)
        
        result = self.cursor.fetchall()
        eps = []
        
        if result is not None:
            for episode in result:
                eps.append( Episode(episode[0], episode[1], episode[2]) )

        return eps

        
    def addShow(self, showTitle, episodes):
        ''' If we find a show on the internet that is not in our database
        we can use this function to add it into our database for the future'''
        title = (showTitle, )
        self.cursor.execute("INSERT INTO shows values (NULL, ?)", title)
        showId = self.getShowId(showTitle)
        for eps in episodes:
            show = (showId, eps.title, eps.season, eps.episode,)
            self.cursor.execute(
                "INSERT INTO episodes values (NULL, ?, ?, ?, ?)", show)
   