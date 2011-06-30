#Author: Dan Tracy
#!/usr/bin/env python

#Name: texSynth.py
#Date: Dec 3, 2010

import sys
from random import randint as rand

try:
    import cv
except ImportError:
    sys.exit("OpenCV for Python is not found")


class Synth:
    ## Synth Constructor
    def __init__(self, textureFilename, texton, size, outFile):
        self.texture = self.loadTexture(textureFilename)
        self.image = cv.CreateImage(size,cv.IPL_DEPTH_8U, 3)
        cv.SetZero(self.image)
        self.textonSize = texton
        self.size = size
        self.outputFile = outFile
        self.imageTextons = []
        self.textureTextons=[]

        for u in xrange(0, self.image.height, self.textonSize[0]):
            for v in xrange(0, self.image.width, self.textonSize[1]):
                self.imageTextons.append( self.getTexton(self.image,u,v) )
                self.textureTextons.append( self.getTexton(self.texture,u,v) )
				              
    ## Retrieve a textonSize by textonSize square from the image
    def getTexton(self, image, x=0, y=0):
        x  = max( x - self.textonSize[0], 0)
        y  = max( y - self.textonSize[1], 0)
        dx = min( x + self.textonSize[0], image.width)
        dy = min( y + self.textonSize[1], image.height)
        return image[x:dx, y:dy]

    def copyTexture(self, image, tex):
        ''' Copy the texture tex subrectangle to the image subrectangle'''
        for u in xrange(tex.height):
            for v in xrange(tex.width):
                image[u,v] = tex[u,v]
                
    ## Hardcore quilting action
    def quilt(self):
        #random texture texton for the upper left corner
        i = rand(0, self.texture.height)
        j = rand(0, self.texture.width)
        textureTexton = self.getTexton( self.texture, i, j)
        imageTexton = self.imageTextons[0]
        self.copyTexture(imageTexton, textureTexton)

        for tex in self.imageTextons[1:]:
            previousTexton = textureTexton
            imageTexton = tex
            textureTexton = self.searchTexture(previousTexton)
            #textureTexton = self.blend(textureTexton,previousTexton)
            self.copyTexture(imageTexton, textureTexton)
            
    ## This utilizes my distance metric, I use a relative L2 norm to compare two overlapping regions
    def searchTexture(self, tex):
        overlap = self.textonSize[0] / 3
        texCut = self.getCut(tex, tex.width - overlap, 0, tex.width, tex.height)

        minSSD = 1000
        index = (0,0)
        currentTile = tex

        for candidate in self.textureTextons:
            candCut= self.getCut(candidate,0,0,overlap,candidate.height)
            result = cv.Norm(texCut, candCut, cv.CV_RELATIVE_L2)

            if minSSD > result:
                index = (u,v)
                minSSD = result

        return self.getTexton(self.texture, index[0], index[1])


    def blend(self, newTexton, prevTexton):
        pass
    
    ## Due to a bug in OpenCV (Ticket 528) I had to implement this kludge.
    def getCut(self, image, xBegin, yBegin, xEnd, yEnd):
        dx = abs(xEnd-xBegin)
        dy = abs(yEnd-yBegin)

        s = cv.CreateMat(dy, dx, image.type)
        for u in xrange(s.height):
            for v in xrange(s.width):
                s[u,v] = image[u+yBegin,v+xBegin]

        return s


    def loadTexture(self, filename):
        tex = None
        try:
            tex = cv.LoadImage(filename, cv.CV_LOAD_IMAGE_COLOR)
        except Exception:
            print "Texture {0} could not be loaded".format(filename)
            exit(1)
        finally:
            return tex

    ## I choose to save the images as PNGs to try to avoid some
    ## of the compression artifacts from JPEG
    def saveImage(self):
        cv.SaveImage(self.outputFile, self.image)


if __name__ == '__main__':
    import argparse
    import sys
    
    parser = argparse.ArgumentParser(description="Texture Synthesis Program")
    parser.add_argument('-i','--input-file', required=True, type=str,
                        metavar='f', help='Input image filename')
    
    parser.add_argument('-t','--texton-size', type=int, default=5,
                        metavar='n', help="default is 5 pixels")
    
    parser.add_argument('-o','--output-file', type=str, default='output.png',
                        metavar='f', help="Output image filename")
    
    parser.add_argument('-s','--output-size', type=int, default=256,
                        metavar='n', help="default is 256 pixels")

    namespace = parser.parse_args()
    texFile = namespace.input_file
    texSize = namespace.texton_size
    size    = namespace.output_size
    outFile = namespace.output_file
    
    generatedImage = Synth( texFile, (texSize,texSize), (size,size), outFile )
    generatedImage.quilt()
    generatedImage.saveImage()