#!/usr/bin/env python
# -*- coding: utf-8 -*-
# author:  Dan Tracy
# program: eplist.py

''' 
This command line program will take a T.V. show as input and
will return information about each episode, such as the title
season and number.  I use this program to help clean up my
TV show collection.  After the show has been found online it 
will be entered into a local databse, provided sqlite3 is available,
for faster lookup in the future.  You are able to filter the shows
by season along with other options on the command line interface.
'''

import argparse
import os
import sys

import Utils
from Parser import EpParser
from Cache import Cache
 
def main():
    
    ''' Our main function for our command line interface'''
    cmd = argparse.ArgumentParser(description="TV Show Information Parser")

    cmd.add_argument('title', 
        help="The title of the show")
      
    cmd.add_argument('-d', '--display-header', action="store_true", 
        help="Display the header at the top of the output")
    
    cmd.add_argument('-v', '--verbose', action="store_true", 
        help="Be verbose, enable additional output")
    
    cmd.add_argument('-s', '--season', default=-1, type=int, metavar='n', 
        help="The specific season to search for")

    group = cmd.add_mutually_exclusive_group(required=False)
  
    group.add_argument('-t', '--testrename', dest='testpath', metavar='p', 
        help="Will test rename the files in the path provided ")
    
    group.add_argument('-r', '--renamefiles', dest='pathname', metavar='p',
        help="Will rename the files in the path provided")


    
    namespace = cmd.parse_args()
    verbose   = namespace.verbose
    title     = namespace.title
    season    = namespace.season
    pathname  = namespace.pathname
    testpath  = namespace.testpath
    display   = namespace.display_header
    
    path      = pathname or testpath
    testRun   = testpath is not None
    rename    = testpath is not None or pathname is not None

    if path is not None and not os.path.exists(path):
        exit("ERROR - Path provided does not exist")

    cache = Cache( verbose=verbose )
    episodeParser = EpParser(title, cache, verbose=verbose)
    results = episodeParser.parseData()


    if rename:
        Utils.renameFiles(path, results, testRun)
        return


    if display or verbose and not rename:
        print "\nShow: {0}".format(title)
        print "Number of episodes: {0}".format(len(results))
        print "Number of seasons: {0}".format( results[-1].season )
        print "-" * 30

    
    # If the user specified a specific season we will filter our results
    # this also checks to make sure its a reasonable season number
    if 0 < season <= results[-1].season:
        results = [ x for x in results if x.season == season ]

    currSeason = results[0].season
    for eps in results:
        if currSeason != eps.season and (display or verbose) :
            print "\nSeason {0}".format(eps.season)
            print "----------"
        print eps.display()
        currSeason = eps.season


if __name__ == '__main__':
    main()	
