import logging
import logging.config
import logging.handlers
import atexit

from os.path import join, abspath
from datetime import datetime

from Constants import RESOURCEPATH

_logger = None
def get_logger():
    """Returns the global logger instance"""
    global _logger
    
    if _logger is None:
        p = abspath(join(RESOURCEPATH,'logger.conf'))
    
        logging.config.fileConfig( abspath(join(RESOURCEPATH,'logger.conf')))
        _logger = logging.getLogger()
        
        logPath = join(RESOURCEPATH, 'output.log')       
        
        fileHandler = logging.handlers.RotatingFileHandler(logPath, maxBytes=2**20, backupCount=3)
        fileHandler.setFormatter( logging.Formatter('%(levelname)s | %(module)s.%(funcName)s - "%(message)s"') )
        fileHandler.setLevel( logging.DEBUG)
        
        _logger.addHandler(fileHandler)
                     
        _logger.debug("APPLICATION START: {}".format(datetime.now()))
        atexit.register( _closeLogs )
     
    return _logger
    
def _closeLogs():
    """Properly shutdown the loggers"""
    _logger.debug("APPLICATION END: {}".format(datetime.now()))
    logging.shutdown()