# -*- coding: utf-8 -*-
# author:  Dan Tracy
# program: poll_sources.py

import sys

from os import listdir

from Utils import WEBSOURCESPATH
from Episode import getLogger

def locate_show(title):
    '''Polls the web sources looking for the show'''
    episodes = []
    modules = []

    sys.path.append(WEBSOURCESPATH)
    ## This will import all the modules within the web_sources directory so that we
    ## can easily plug in new sources for finding episode information
    mods = filter( lambda x: x.endswith('py') and not x.startswith('__'),  listdir(WEBSOURCESPATH))
    
    for m in mods:
        getLogger().info("Importing web resource {}".format(m[:-3]))
        x =  __import__(m[:-3]) 
        modules.append(x)
    
    getLogger().info("Searching for {}".format(title))
    
    for source in modules:
        getLogger().info( "Polling {0}".format(source.__name__) )
            
        episodes = source.poll(title)
        
        if episodes:
            getLogger().info( "LOCATED {0}".format(title) )
            break
        getLogger().info( "Unable to locate {0} at {1}".format(title, source.__name__) )

    if not episodes:
        getLogger().info("Unable to locate the show: " + title)
        return []
        
    return filter(lambda x: x.episode > 0, episodes)