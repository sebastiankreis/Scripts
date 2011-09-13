#include "sphere.h"

//constructor given  center, radius, and material
sphere::sphere(Vec3f c, float r, int m, scene* s) : rtObject(s)  
{
	center = c;
	radius = r;
	matIndex = m;
	myScene = s;
}

float sphere::testIntersection(Vec3f eye, Vec3f dir)
{
	//see the book for a description of how to use the quadratic rule to solve
	//for the intersection(s) of a line and a sphere, implement it here and
	//return the minimum positive distance or 9999999 if none
	
	float det;
	Vec3f p = center - eye;
	float b = p.Dot3(dir);
	
	det = b*b - p.Dot3(p) + radius*radius;
	
	if (det < EPSILON)
		return MISS;
	
	det= sqrt(det);

	float i1= b - det;
	float i2= b + det;

	// intersecting with ray?
	if(i2<0) return MISS;

	if(i1<0) i1=0;

	return min(i1,i2);

}

Vec3f sphere::getNormal(Vec3f eye, Vec3f dir)
{
	//once you can test for intersection,
	//simply add (distance * view direction) to the eye location to get surface location,
	//then subtract the center location of the sphere to get the normal out from the sphere
	Vec3f normal = center - dir;
	//dont forget to normalize
	normal.Normalize();

	return normal;
}

Vec3f sphere::getTextureCoords(Vec3f eye, Vec3f dir)
{
	//find the normal as in getNormal
	Vec3f normal = getNormal(eye,dir);

	//use these to find spherical coordinates
	Vec3f x(1,0,0); 
	Vec3f y(0,1,0);
	Vec3f z(0,0,1); 

	float u,v;
	float phi   = acos( normal.Dot3(z) );
	float theta = acos( normal.Dot3(x) / sin(phi) );

	v = (PI - phi)/PI;

	float n = normal.Dot3(y);

	if( n < 0 )
		u = theta / (2*PI);
	else
		u = 1 - theta / (2*PI);

	
	Vec3f coords(u,v,0);
	return coords;
}