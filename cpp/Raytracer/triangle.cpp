#include "triangle.h"

//constructor given  center, radius, and material
triangle::triangle(Vec3f p0, Vec3f p1, Vec3f p2, float tx0, float tx1, float tx2, float ty0, float ty1, float ty2, int m, scene* s) : rtObject(s)  
{
	point0 = p0;
	point1 = p1;
	point2 = p2;
	texX0 = tx0;
	texX1 = tx1;
	texX2 = tx2;
	texY0 = ty0;
	texY1 = ty1;
	texY2 = ty2;
	matIndex = m;
	myScene = s;
	alpha = point1 - point0;
	beta  = point2 - point0;
}

float triangle::testIntersection(Vec3f eye, Vec3f dir)
{
	//see the book/slides for a description of how to use Cramer's rule to solve
	//for the intersection(s) of a line and a plane, implement it here and
	//return the minimum distance (if barycentric coordinates indicate it hit
	//the triangle) otherwise 9999999
	
	Vec3f edge1 =  alpha;
	Vec3f edge2 =  beta;

	Vec3f pvec;
	Vec3f::Cross3(pvec,dir, edge2);
	
	float det = edge1.Dot3(pvec);
	
	// no intersection
	if(det > (-1 * EPSILON) && det < EPSILON)
            return MISS;
	
	float invDet = 1/det;
	Vec3f tvec = eye-point0;
	
	float u = tvec.Dot3(pvec) * invDet;
	
	if(u < 0 || u > 1)
		return MISS;
	
	Vec3f qvec;
	Vec3f::Cross3(qvec,tvec, edge1);
        
	float v = dir.Dot3(qvec) * invDet;
	
	if(v < 0 || (u + v) > 1)
		return MISS;          
      
	float dist = edge2.Dot3(qvec) * invDet;
	
	if(dist < 0)
		return MISS;
	
	return dist;
}

Vec3f triangle::getNormal(Vec3f eye, Vec3f dir)
{
	//construct the barycentric coordinates for the plane
	Vec3f bary1 = alpha;
	Vec3f bary2 = beta;

	//cross them to get the normal to the plane
	//note that the normal points in the direction given by right-hand rule
	//(this can be important for refraction to know whether you are entering or leaving a material)
	Vec3f normal;
	Vec3f::Cross3(normal,bary1,bary2);
	normal.Normalize();

	return normal;
}

Vec3f triangle::getTextureCoords(Vec3f eye, Vec3f dir)
{
	//find alpha and beta (parametric distance along barycentric coordinates)
	//use these in combination with the known texture surface location of the vertices
	//to find the texture surface location of the point you are seeing
	Vec3f coords;

	Vec3f X_a = alpha;
	Vec3f Y_a = beta;

	

	return coords;
}