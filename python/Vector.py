# author: Dan Tracy
''' A simple vector class with basic functions'''

class Vector(object):
    ''' The vector class'''
    def __init__(self, x=0.0, y=0.0, z=0.0):
        '''Create a new vector <x,y,z> with default values of zero'''
        self.x = float(x)
        self.y = float(y)
        self.z = float(z)

    def __repr__(self):
        ''' Returns the string representation of the vector'''
        return "X: {0}\tY: {1}\tZ: {2}".format(self.x, self.y, self.z)
        
    def __add__(self, vec):
        '''Returns the sum of two vectors in a new vector
           ex: <1,2,3> + <1,1,1> = <2,3,4>'''
        return Vector(self.x + vec.x, self.y + vec.y, self.z + vec.z)

    def __sub__(self, vec):
        '''Returns a new vector with the difference between vectors
           ex: <3,3,3> - <2,2,2> = <1,1,1>'''
        return Vector(self.x - vec.x, self.y - vec.y, self.z - vec.z)

    def __mul__(self, vec):
        '''Returns a new vector with each element multiplied in position
           ex: <2,2,2> * <2,3,4> = <4,6,8>'''
        return Vector(self.x * vec.x, self.y * vec.y, self.z * vec.z)

    def length(self):
        '''Returns the euclidean length of the vector'''
        return ( self.x**2 + self.y**2 + self.z**2 ) ** (0.5)

    def set(self, x=0, y=0, z=0):
        '''Updates the vector with new values'''
        try:
            self.x = float(x)
            self.y = float(y)
            self.z = float(z)
        except ValueError:
            pass

    def scale(self, dx, dy, dz):
        '''Scales the vector by the values given'''
        self.x *= dx
        self.y *= dy
        self.z *= dz

    def dot(self, vec):
        '''Returns the dot product of two vectors
           ex: <1,2,3> o <2,2,2> = 12 '''
        return sum(self * vec)

    def cross(self, vec):
        ''' Returns the cross product of two vectors in a new vector
            ex: <1,0,0> x <0,1,0> = <0,0,1> '''
        return Vector( self.y * vec.z - self.z * vec.y, 
                       self.z * vec.x - self.x * vec.z, 
                       self.x * vec.y - self.y * vec.x)

    def normalize(self):
        ''' Scales the vectors components such that the length is now 1'''
        length = 1/self.length()
        self.scale( length, length, length)