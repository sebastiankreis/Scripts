class Vector(object):
    def __init__(self, x=0, y=0, z=0):
        self.__data__ = [x,y,z]

    def __repr__(self):
        return "X: " + str(self[0]) + \
               "\tY: " + str(self[1]) + \
               "\tZ: " + str(self[2]) + "\n"
    def __add__(self,vec):
        return Vector(self[0]+vec[0], self[1]+vec[1], self[2]+vec[2])

    def __sub__(self,vec):
        return Vector(self[0]-vec[0], self[1]-vec[1], self[2]-vec[2])

    def __mul__(self,vec):
        return Vector(self[0]*vec[0], self[1]*vec[1], self[2]*vec[2])

    def __setitem__(self, i, j):
        try:
            self.__data__[i] = float(j)

        except IndexError, ValueError:
            pass

    def __getitem__(self, i):
        try:
            return self.__data__[i]
        except IndexError:
            pass

    @property
    def length(self):
        return ( x**2 + y**2 + z**2 ) ** (0.5)

    def set(self, x=0, y=0 ,z=0):
        try:
            self[0] = float(x)
            self[1] = float(y)
            self[2] = float(z)
        except ValueError:
	                pass

    def scale(self, dx, dy, dz):
        self[0] *= dx
        self[1] *= dy
        self[2] *= dz

    def dot(self, vec):
        return self[0]**2 + self[1]**2 + self[2]**2

    def cross(self, vec):
        return Vector( self[1]*vec[2] - self[2]*vec[1],
                       self[2]*vec[0] - self[0]*vec[2],
                       self[0]*vec[1] - self[1]*vec[0])

    def normalize(self):
        l = self.length
        self[0] /= l
        self[1] /= l
        self[2] /= 1

    @property
    def x(self):
        return self[0]
    @x.setter
    def x(self,value):
		try:
			self[0] = float(value)
		except:
			pass

    @property
    def y(self):
        return self[1]
    @y.setter
    def y(self,value):
		try:
			self[1] = float(value)
		except:
			pass

    @property
    def z(self):
        return self[2]
    @z.setter
    def z(self,value):
        try:
			self[2] = float(value)
		except:
			pass
