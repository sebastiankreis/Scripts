//
#ifdef WIN32
#include <windows.h>
#endif

#include <sstream>
#include <stdio.h>     // Standard Header For Most Programs
#include <stdlib.h>    // Additional standard Functions (exit() for example)
#include <GL/gl.h>     // The GL Header File
#include <GL/glu.h>    // The GL Utilities (GLU) header
#include <GL/glut.h>   // The GL Utility Toolkit (Glut) Header
#include <pic.h>
#include "rc_spline.h"
#include <iostream>

rc_Spline g_Track;

int g_iMenuId;

#define WINDOW_WIDTH 640
#define WINDOW_HEIGHT 480

vector<Vec3f> catmullPoints;

int pictureNum;

const GLfloat gravity = -9.8;
const GLfloat minSpeed = 10;
const GLfloat maxSpeed = 100;
GLfloat maxHeight;
GLfloat cameraMass = 50;
GLfloat cameraSpeed = 100;
GLfloat catmullIncrement = 0.01f;
GLfloat timeIncrement = 1;
GLfloat time = 0;

GLuint trackDisplayList;
GLuint frontTextureId;
GLuint backTextureId;
GLuint leftTextureId;
GLuint rightTextureId;
GLuint topTextureId;
GLuint bottomTextureId;

Vec3f mousePos (0.0f,0.0f,0.0f);
Vec3f cameraPos (0.0f, 0.0f, 0.0f);
Vec3f nextPos(0.0f,0.0f,0.0f);
Vec3f trueUp(0.0f,0.0f,0.0f);
Vec3f globalUp(0.0f, 0.0f, 1.0f);

/* OpenGL callback declarations 
definitions are at the end to avoid clutter */

void InitGL ( GLvoid );
void doIdle();
void display ( GLvoid );
void keyboardfunc (unsigned char key, int x, int y) ;
void menufunc(int value);
void mouseidle(int x, int y);
void renderSkybox(void);
void initLighting(void);
int physics(Vec3f point, int index);
int bisection(int uMin, int uMax, GLfloat length);
Vec3f catmull(Vec3f a, Vec3f b, Vec3f c, Vec3f d, GLfloat t);
void saveScreenshot(char* filename);


inline GLfloat iterpolate(GLfloat a, GLfloat b, GLfloat c, GLfloat d, GLfloat t) 
{ 
	return (0.5f*( (2*b) +
		     ((-a + c)*t) +
		     ((2*a -5*b + 4*c - d)*t*t) +
	             (((-a + 3*b - 3*c +d)*t*t*t))
			 ));
}

Vec3f catmull(Vec3f a, Vec3f b, Vec3f c, Vec3f d, GLfloat t)
{
	return Vec3f(   iterpolate(a.x(), b.x(), c.x(), d.x(), t),
			     iterpolate(a.y(), b.y(), c.y(), d.y(), t),
			     iterpolate(a.z(), b.z(), c.z(), d.z(), t)
					);					
}


void loadTexture (char *filename, GLuint &textureID)
{
	Pic *pBitMap = pic_read(filename, NULL);
	
	if(pBitMap == NULL)
	{
		printf("Could not load the texture file\n");
		exit(1);
	}

	glEnable(GL_TEXTURE_2D); // Enable texturing
	glGenTextures(1, &textureID); // Obtain an id for the texture
	
	glBindTexture(GL_TEXTURE_2D, textureID); // Set as the current texture

	/* set some texture parameters */
	glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR );
	glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR_MIPMAP_LINEAR );
	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_S,GL_CLAMP);
	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_T,GL_CLAMP);

	/* Load the texture into OpenGL as a mipmap. !!! This is a very important step !!! */
	gluBuild2DMipmaps(GL_TEXTURE_2D, GL_RGB, pBitMap->nx, pBitMap->ny, GL_RGB, GL_UNSIGNED_BYTE, pBitMap->pix);
	glDisable(GL_TEXTURE_2D);
	pic_free(pBitMap); // now that the texture has been copied by OpenGL we can free our copy
}

int main ( int argc, char** argv )   // Create Main Function For Bringing It All Together
{
	// get the the filename from the commandline.
	if (argc!=2)
	{
		printf("usage: %s trackfilname\n", argv[0]);
		exit(1);
	}


	/* load the track, this routine aborts if it fails */
	g_Track.loadSplineFrom(argv[1]);

	/*** The following are commands for setting up GL      ***/
	/*** No OpenGl call should be before this sequence!!!! ***/

	/* Initialize glut */
	glutInit(&argc,argv);
	/* Set up window modes */
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	/* Set window position (where it will appear when it opens) */
	glutInitWindowPosition(0,0);
	/* Set size of window */
	glutInitWindowSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	/* Create the window */
	glutCreateWindow    ( "Dan Tracys CMPSC 458: Rollercoaster" ); // Window Title (argv[0] for current directory as title)
	
	/**** Call to our initialization routine****/
	InitGL ();
	
	glutDisplayFunc(display);
	glutIdleFunc(doIdle);
	
	g_iMenuId = glutCreateMenu(menufunc);
	glutSetMenu(g_iMenuId);
	glutAddMenuEntry("Quit",0);
	glutAttachMenu(GLUT_RIGHT_BUTTON);
		
	glutKeyboardFunc(keyboardfunc);
	glutPassiveMotionFunc(mouseidle);
	
	glutMainLoop( );

	return 0;
}

void InitGL ( GLvoid )
{
    glHint( GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
	glViewport (0.0f,0.0f, WINDOW_WIDTH, WINDOW_HEIGHT);
	glMatrixMode( GL_PROJECTION );
	glLoadIdentity();
	gluPerspective(100.0f, (float)WINDOW_WIDTH/(float)WINDOW_HEIGHT, 0.1f, 2000.0f);
	glMatrixMode(GL_MODELVIEW);

	GLuint trackTexture;
	loadTexture("texture/front.jpg",frontTextureId);
	loadTexture("texture/back.jpg",backTextureId);
	loadTexture("texture/up.jpg",topTextureId);
	loadTexture("texture/down.jpg",bottomTextureId);
	loadTexture("texture/left.jpg",leftTextureId);
	loadTexture("texture/right.jpg",rightTextureId);
	loadTexture("texture/track.jpg", trackTexture);

	glClearColor(0.0f, 0.0f, 0.0f, 0.75f);				// Black Background

	initLighting();

	Vec3f ptA, ptB, ptC, ptD;
	Vec3f pos(0,0,0);
	Vec3f lastPos(0,0,0);

	// Iterates through the control points and performs the catmull rom spline on them then
	// stores the position (not offset) in the catmullPoints vector
	int index = 0;
	for(pointVectorIter itr = g_Track.points().begin()+3; itr != g_Track.points().end(); index++)
	{	
		switch(index)
		{
			case 0:
				ptA = *(g_Track.points().end()-3); 
				ptB = *(g_Track.points().end()-2); 
				ptC = *(g_Track.points().end()-1); 
				ptD = *g_Track.points().begin();
				break;
			case 1:
				ptA = *(g_Track.points().end()-2); 
				ptB = *(g_Track.points().end()-1); 
				ptC = *(g_Track.points().begin()); 
				ptD = *(g_Track.points().begin()+1);
				break;
			case 2:
				ptA = *(g_Track.points().end()-1); 
				ptB = *(g_Track.points().begin()); 
				ptC = *(g_Track.points().begin()+1); 
				ptD = *(g_Track.points().begin()+2);
				break;
			default:
				ptA = *(itr-3); 
				ptB = *(itr-2); 
				ptC = *(itr-1); 
				ptD = *itr;
				break;
		}

		float t = 0.0f;
		while(t <= 1.0f )
		{
			Vec3f temp = catmull(ptA,ptB,ptC,ptD,t);	
			pos += temp;
			//if(pos.y() > maxHeight) maxHeight = pos.y();
			catmullPoints.push_back(pos);

			t += catmullIncrement;
		}

		if(index>2)
			++itr;
	}


	// Draw the points in displayList and setup lighting and materialfv
	GLfloat reflection[] = { 0.8f, 0.8f, 0.8f, 1.0f };
	glMaterialfv(GL_FRONT, GL_SPECULAR, reflection); 
	glMaterialf(GL_FRONT, GL_SHININESS, 50.0); 
	
	trackDisplayList = glGenLists(1);
	glNewList(trackDisplayList, GL_COMPILE);

	glEnable(GL_TEXTURE_2D);
	glBindTexture(GL_TEXTURE_2D, trackTexture);

	glBegin(GL_QUADS);
	for(pointVectorIter itr = catmullPoints.begin(); itr != catmullPoints.end(); ++itr)
	{
		lastPos = pos;
		pos = *itr;

		//glVertex3f(pos.x(), pos.y(), pos.z() );
		glVertex3f( pos.x()-1, pos.y()+1, pos.z() );
		glTexCoord2d(0,0);

		glVertex3f( lastPos.x()-1, lastPos.y()+1, lastPos.z());
		glTexCoord2d(1,0);

		glVertex3f( lastPos.x(), lastPos.y()+1, lastPos.z());
		glTexCoord2d(1,1);

		glVertex3f( pos.x(), pos.y()+1, pos.z() );
		glTexCoord2d(0,1);
	}

	glEnd();
	glEndList();
}

void display ( void )
{
	/* Clear buffers */
	glLoadIdentity();
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	glViewport(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);			

	glMatrixMode(GL_PROJECTION);					
	glLoadIdentity();									

	gluPerspective(100.0f,(GLfloat)WINDOW_WIDTH/(GLfloat)WINDOW_HEIGHT,0.1f,5000.0f);

	glMatrixMode(GL_MODELVIEW);
	renderSkybox();
	glLoadIdentity();
	glTranslatef(0.5f,-2.0f,0.0f);
	
	//gluLookAt(cameraPos[0],cameraPos[1],cameraPos[2],0,0,0,0,1,0);
	gluLookAt(cameraPos[0],cameraPos[1],cameraPos[2],
			  nextPos[0],nextPos[1],nextPos[2],
			  trueUp[0],trueUp[1],trueUp[2]);

	glEnable(GL_LIGHTING);

	glCallList(trackDisplayList);
	
	glDisable(GL_LIGHTING);
	glutSwapBuffers();
}

void cameraCallback(int junk)
{
	//Iterator through the catmull position points and the corresponding index of the iterator
	static pointVectorIter itr = catmullPoints.begin();
	//static int index = 0;

	//Once i get the camera working correctly the physics will be implemented
	//but for now im still trying to figure out how to stop it from flipping
	//index = physics(*itr,index);
	cameraPos = *itr;
	
	((itr + 1) == catmullPoints.end()) ? (itr=catmullPoints.begin()) : itr+=1;
	nextPos = *itr;

	Vec3f rightVec;
	rightVec.Cross3(rightVec, globalUp, (nextPos-cameraPos) );
	rightVec.Normalize();

	trueUp.Cross3(trueUp, rightVec, nextPos);
	trueUp.Normalize();
 
	time += timeIncrement;

}


void keyboardfunc (unsigned char key, int x, int y) {

	if(key == 'p' || key == 'P')
	{
		std::ostringstream o;
		string name = "Picture-";
		char n[512]; 
		
		o << name << pictureNum << ".jpg";

		name = o.str();

		strcpy_s(n,512,name.c_str());

		saveScreenshot(n);
		pictureNum++;
	}
	/* User pressed quit key */
	if(key == 'w' || key == 'W')
		cameraPos.Set( cameraPos[0] + 1, cameraPos[1], cameraPos[2] );
	if(key == 's' || key == 'S')
		cameraPos.Set( cameraPos[0] - 1, cameraPos[1], cameraPos[2] );
	if(key == 'a' || key == 'A')
		cameraPos.Set( cameraPos[0], cameraPos[1] +1, cameraPos[2] );
	if(key == 'd' || key == 'D')
		cameraPos.Set( cameraPos[0], cameraPos[1] -1, cameraPos[2] );
	if(key == 'r' || key == 'R')
		cameraPos.Set( cameraPos[0], cameraPos[1] , cameraPos[2]-1 );
	if(key == 'e' || key == 'E')
		cameraPos.Set( cameraPos[0], cameraPos[1] , cameraPos[2]+1 );
	if(key == 'q' || key == 'Q' || key == 27) {
		exit(0);
	}
}
/* Function that GL runs once a menu selection has been made.
* This receives the number of the menu item that was selected
*/
void menufunc(int value)
{
	switch (value)
	{
	case 0:
		exit(0);
		break;
	}
}


void mouseidle(int x, int y)
{
  int diffx = x - mousePos[0]; //check the difference between the current x and the last x position
  int diffy = y - mousePos[0]; //check the difference between the current y and the last y position

  mousePos.Set(x,y,0);
}


void doIdle()
{
	glutPostRedisplay();
	glutTimerFunc(cameraSpeed, cameraCallback,0);

}

void renderSkybox(void)
{	
	static GLfloat r = 2000.0f;

	glDisable(GL_LIGHTING);

	glLoadIdentity();

	glColor4f(1.0f,1.0f,1.0f,1.0f);
 
	glPushMatrix();
 
	glTranslatef(0.0f, 0.0f, 0.0f);

	glBindTexture(GL_TEXTURE_2D,frontTextureId); // select which texture to use 
	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_S,GL_CLAMP);
	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_T,GL_CLAMP);
	
	glRotatef(cameraPos[0],0,1,0);
	glRotatef(cameraPos[1],1,0,0);
	glBegin(GL_QUADS);
	
	//set color
	glColor3f(1.0, 1.0, 1.0);
	
	//* Set the texture coordinates */
	glTexCoord2f(0, 0); glVertex3f(  r, -r, -r );
	glTexCoord2f(1, 0); glVertex3f( -r, -r, -r );
	glTexCoord2f(1, 1); glVertex3f( -r,  r, -r );
	glTexCoord2f(0, 1); glVertex3f(  r,  r, -r );

  //* End the object */
	glEnd();

    glBindTexture(GL_TEXTURE_2D,leftTextureId); // select which texture to use 
	glBegin(GL_QUADS);
	glColor3f(1.0, 1.0, 1.0);
	
	glTexCoord2f(0, 0); glVertex3f(  r, -r,  r );
	glTexCoord2f(1, 0); glVertex3f(  r, -r, -r );
	glTexCoord2f(1, 1); glVertex3f(  r,  r, -r );
	glTexCoord2f(0, 1); glVertex3f(  r,  r,  r );
	glEnd();

    glBindTexture(GL_TEXTURE_2D,backTextureId); // select which texture to use 
	glBegin(GL_QUADS);
	glColor3f(1.0, 1.0, 1.0);
	glTexCoord2f(0, 0); glVertex3f( -r, -r,  r );
	glTexCoord2f(1, 0); glVertex3f(  r, -r,  r );
	glTexCoord2f(1, 1); glVertex3f(  r,  r,  r );
	glTexCoord2f(0, 1); glVertex3f( -r,  r,  r );
	glEnd();

     glBindTexture(GL_TEXTURE_2D,rightTextureId); // select which texture to use 
	 glBegin(GL_QUADS);
	 glColor3f(1.0, 1.0, 1.0);
	 glTexCoord2f(0, 0); glVertex3f( -r, -r, -r );
	 glTexCoord2f(1, 0); glVertex3f( -r, -r,  r );
	 glTexCoord2f(1, 1); glVertex3f( -r,  r,  r );
	 glTexCoord2f(0, 1); glVertex3f( -r,  r, -r );
	 glEnd();
     
	 glBindTexture(GL_TEXTURE_2D,topTextureId); // select which texture to use 
	 glBegin(GL_QUADS);
	 glColor3f(1.0, 1.0, 1.0);
	 glTexCoord2f(0, 1); glVertex3f( -r,  r, -r );
	 glTexCoord2f(0, 0); glVertex3f( -r,  r,  r );
	 glTexCoord2f(1, 0); glVertex3f(  r,  r,  r );
	 glTexCoord2f(1, 1); glVertex3f(  r,  r, -r );
	 glEnd();

     glBindTexture(GL_TEXTURE_2D,bottomTextureId); // select which texture to use 
	 glBegin(GL_QUADS);
	 glColor3f(1.0, 1.0, 1.0);
	 glTexCoord2f(0, 0); glVertex3f( -r, -r, -r );
	 glTexCoord2f(0, 1); glVertex3f( -r, -r,  r );
	 glTexCoord2f(1, 1); glVertex3f(  r, -r,  r );
	 glTexCoord2f(1, 0); glVertex3f(  r, -r, -r );
	 glEnd();
	 
	 glDisable(GL_TEXTURE_2D);
	 glPopMatrix();
	 glEnable(GL_LIGHTING);
}



void initLighting(void)
{
	GLfloat ambience[] = {0.5f, 0.5f, 0.5f, 1.0f};
	GLfloat diffuse[] = { 0.8f, 0.8f, 0.8, 1.0f };
	GLfloat light[] = {1.0f, 1.0f, 1.0f, 1.0f };
	GLfloat position[] = {0.0f, 100.0f, 10.0f};
    
	glLightModelfv(GL_LIGHT_MODEL_AMBIENT, ambience);
	glLightfv(GL_LIGHT1, GL_AMBIENT, light);
    glLightfv(GL_LIGHT1, GL_DIFFUSE, diffuse);
    glLightfv(GL_LIGHT1, GL_POSITION, position);
    
	glEnable(GL_LIGHT1);
	glEnable(GL_LIGHTING);
	glDisable(GL_LIGHTING);
}

int physics(Vec3f point, int index)
{
	//Delta is 3 for movement
	cameraSpeed = cameraSpeed + gravity*point.y();
	if(cameraSpeed <= minSpeed) cameraSpeed=minSpeed;
	if(cameraSpeed >= maxSpeed) cameraSpeed=maxSpeed;
	GLfloat arclength = point.Length() + cameraSpeed*timeIncrement;
	int nextPoint = bisection(index, (index+3), arclength);
	
	return nextPoint;
}

int bisection(int uMin, int uMax, GLfloat length)
{
	while(true)
	{
		size_t u = (uMin+uMax)/2;

		if(u > catmullPoints.size())
			uMax = catmullPoints.size();
		if(u < catmullPoints.size())
			uMin = 0;

		int nextLen = catmullPoints.at(u).Length();

		if(nextLen-length < 1)
			return u;
	}
}

void saveScreenshot (char* filename)
{
	int i;
	Pic *in = NULL;

	if (filename == NULL)
		return;

	/* Allocate a picture buffer */
	in = pic_alloc(WINDOW_WIDTH, WINDOW_HEIGHT, 3, NULL);

	/* Loop over each row of the image and copy into the image */
	for (i=WINDOW_HEIGHT-1; i>=0; i--) {
		glReadPixels(0, WINDOW_HEIGHT-1-i, WINDOW_WIDTH, 1, GL_RGB,
			GL_UNSIGNED_BYTE, &in->pix[i*in->nx*in->bpp]);
	}

	/* Output the file */
	if (jpeg_write(filename, in)) {
	} else {
		printf("Error in Saving\n");
	}

	/* Free memory used by image */
	pic_free(in);
}
