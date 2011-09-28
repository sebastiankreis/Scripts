//Dan Tracy
//Heightmap Assignments
#ifdef WIN32
#include <windows.h>
#pragma warning(disable:4996) 
#endif
#include <math.h>
#include <stdio.h>    
#include <stdlib.h>   
#include <sstream>
#include <iostream>
#include <GL/gl.h>    
#include <GL/glu.h>    
#include <GL/glut.h>  
#include <pic.h>
using namespace std;

#define WINDOW_WIDTH 640
#define WINDOW_HEIGHT 480

int g_iMenuId;

GLfloat currentTranslation[3] = {50.5f, 10.0f, 150.0f};
GLfloat currentRotation[3] = {30.0f, 0.0f, 0.0f};
GLfloat currentScaling[3] = {1, 1, 1};
GLfloat currentCameraCoord[3] = {0.0f, 0.0f, 0.0f};
GLfloat currentCameraRotation[3] = {0.0f, 0.0f, 0.0f};
float camerascale = 0.25;

/* State of the mouse */
int mousePos[2] = {0, 0};
int leftMouseButtonState = 0;    /* 1 if pressed, 0 if not */
int middleMouseButtonState = 0;
int rightMouseButtonState = 0;

const int PI = 3.141592654f;

typedef enum { ROTATE, TRANSLATE, SCALE } CONTROLSTATE;
CONTROLSTATE currentControlState = SCALE;

Pic * sourceImage;

GLuint frontTextureId;
GLuint backTextureId;
GLuint leftTextureId;
GLuint rightTextureId;
GLuint topTextureId;
GLuint bottomTextureId;


int pictureNum = 0;

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


void InitGL ( GLvoid )
{
	glEnable(GL_DEPTH_TEST);							// Enables Depth Testing
	glDepthFunc(GL_LEQUAL);

	glClearColor(0.0f, 0.0f, 0.0f, 0.5f);				// Black Background	

    glHint( GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
	glViewport (0.0f,0.0f, WINDOW_WIDTH, WINDOW_HEIGHT);
	glMatrixMode( GL_PROJECTION );
	glLoadIdentity();
	gluPerspective(100.0f, (float)WINDOW_WIDTH/(float)WINDOW_HEIGHT, 0.1f, 2000.0f);
	glMatrixMode(GL_MODELVIEW);

	//call our function to load a texture (you will have to do this for each texture you will load)
	loadTexture("texture/front.jpg",frontTextureId);
	loadTexture("texture/back.jpg",backTextureId);
	loadTexture("texture/up.jpg",topTextureId);
	loadTexture("texture/down.jpg",bottomTextureId);
	loadTexture("texture/left.jpg",leftTextureId);
	loadTexture("texture/right.jpg",rightTextureId);
  /*if you plan to use Display lists this is a good place to
    create them */
}

void renderSkybox(void)
{	static GLfloat r = 2000.0f;

	glDisable(GL_LIGHTING);

	glLoadIdentity();

	glColor4f(1.0f,1.0f,1.0f,1.0f);
 
	glPushMatrix();
 
	glTranslatef(0.0f, 0.0f, 0.0f);

	glRotatef(currentCameraCoord[0]/1.5,0,1,0);
	glRotatef(currentCameraCoord[1]/1.5,1,0,0);
	glEnable(GL_TEXTURE_2D);
	
	// Render the front texture
	glBindTexture(GL_TEXTURE_2D, frontTextureId);
	glBegin(GL_QUADS);
		glTexCoord2f(1.0, 0.0);	glVertex3f( r, r, -r); 
		glTexCoord2f(0.0, 0.0);	glVertex3f(-r, r, -r);
		glTexCoord2f(0.0, 1.0); glVertex3f(-r, -r,-r);
		glTexCoord2f(1.0, 1.0); glVertex3f( r, -r,-r);
	glEnd();

	// Render the back texture
	glBindTexture(GL_TEXTURE_2D, backTextureId);
	glBegin(GL_QUADS);
		glTexCoord2f(1.0, 0.0);	glVertex3f( r, r, r); 
		glTexCoord2f(0.0, 0.0);	glVertex3f(-r, r, r);
		glTexCoord2f(0.0, 1.0); glVertex3f(-r, -r,r);
		glTexCoord2f(1.0, 1.0); glVertex3f( r, -r,r);
	glEnd();
	
   // Render the top quad
   glBindTexture(GL_TEXTURE_2D, topTextureId);
   glBegin(GL_QUADS);
       glTexCoord2f(0.0, 1.0); glVertex3f( -r, r, -r );
       glTexCoord2f(1.0, 1.0); glVertex3f( r, r, -r );
       glTexCoord2f(1.0, 0.0); glVertex3f( r, r, r );
       glTexCoord2f(0.0, 0.0); glVertex3f( -r, r, r );
	glEnd();

	// Render the bottom quad
   glBindTexture(GL_TEXTURE_2D, bottomTextureId);
   glBegin(GL_QUADS);
       glTexCoord2f(0.0, 1.0); glVertex3f( -r, -r, -r );
       glTexCoord2f(1.0, 1.0); glVertex3f( r, -r, -r );
       glTexCoord2f(1.0, 0.0); glVertex3f( r, -r, r );
       glTexCoord2f(0.0, 0.0); glVertex3f( -r, -r, r );
	glEnd();

	// Render the right quad
	glBindTexture(GL_TEXTURE_2D, rightTextureId);
    glBegin(GL_QUADS);
       glTexCoord2f(0.0, 1.0); glVertex3f( r, -r, -r );
       glTexCoord2f(1.0, 1.0); glVertex3f( r, -r, r );
       glTexCoord2f(1.0, 0.0); glVertex3f( r, r, r );
       glTexCoord2f(0.0, 0.0); glVertex3f( r, r, -r );
	glEnd();

	glBindTexture(GL_TEXTURE_2D, leftTextureId);
    glBegin(GL_QUADS);
       glTexCoord2f(0.0, 1.0); glVertex3f( -r, -r, -r );
       glTexCoord2f(1.0, 1.0); glVertex3f( -r, -r, r );
       glTexCoord2f(1.0, 0.0); glVertex3f( -r, r, r );
       glTexCoord2f(0.0, 0.0); glVertex3f( -r, r, -r );
	glEnd();

	glDisable(GL_TEXTURE_2D);
	glPopMatrix();
	glEnable(GL_LIGHTING);
}

void renderHeightmap()
{
	glTranslatef(currentTranslation[0]-50.0f,-currentTranslation[1],-currentTranslation[2]);
	glRotatef(-30.0f, currentRotation[0], currentRotation[1], currentRotation[2]);
	glScalef(currentScaling[0], currentScaling[1], currentScaling[2]);
	
	glBegin(GL_POINTS);
	
	for(int x = 0; x < (sourceImage->nx); ++x)
	{
		for(int y = 0; y < (sourceImage->ny); ++y)
		{
			int red = PIC_PIXEL(sourceImage , x, y, 0);
			int green = PIC_PIXEL(sourceImage , x, y, 1);
			int blue = PIC_PIXEL(sourceImage , x, y, 2);
			int z = (red+green+blue)/32;

			glColor3f(0,1,0);
			glVertex3f(x, z, y); 
			
		}
	}
	glEnd();
}


void display ( void )   // Create The Display Function
{
	glLoadIdentity();
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	glViewport(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);			

	glMatrixMode(GL_PROJECTION);					
	glLoadIdentity();									

	gluPerspective(100.0f,(GLfloat)WINDOW_WIDTH/(GLfloat)WINDOW_HEIGHT,0.1f,5000.0f);

	glMatrixMode(GL_MODELVIEW);

	glLoadIdentity();
	glTranslatef(0.5f,-2.0f,0.0f);
	
	gluLookAt(mousePos[0],mousePos[1],0,0,0,0,0,1,0);
	
	renderSkybox();
	renderHeightmap();
	glutSwapBuffers();
}



/* Write a screenshot to the specified filename */
void saveScreenshot (char *filename)
{
  int i;
  Pic *in = NULL;

  if (filename == NULL)
    return;

  /* Allocate a picture buffer */
  in = pic_alloc(WINDOW_WIDTH, WINDOW_HEIGHT, 3, NULL);

  printf("File to save to: %s\n", filename);

  /* Loop over each row of the image and copy into the image */
  for (i=WINDOW_HEIGHT-1; i>=0; i--) {
    glReadPixels(0, WINDOW_HEIGHT-1-i, WINDOW_WIDTH, 1, GL_RGB,
                 GL_UNSIGNED_BYTE, &in->pix[i*in->nx*in->bpp]);
  }

  /* Output the file */
  if (jpeg_write(filename, in)) {
    printf("File saved Successfully\n");
  } else {
    printf("Error in Saving\n");
  }

  /* Free memory used by image */
  pic_free(in);
}

/* converts mouse drags into information about rotation/translation/scaling
 * This is run by GL whenever the mouse is moved and a mouse button is being
 * held down.
 */
void mousedrag(int x, int y)
{
  int mousePosChange[2] = {x-mousePos[0], y-mousePos[1]};

  /* Check which state we are in. */
  switch (currentControlState)
  {
    case TRANSLATE:
      if (leftMouseButtonState)
      {
        currentTranslation[0] += mousePosChange[0]*0.1;
        currentTranslation[1] += mousePosChange[1]*0.1;
      }
      if (middleMouseButtonState)
      {
        currentTranslation[2] += mousePosChange[1]*0.1;
      }
      break;
    case ROTATE:
      if (leftMouseButtonState)
      {
        currentRotation[0] += mousePosChange[1];
        currentRotation[1] += mousePosChange[0];
      }
      if (middleMouseButtonState)
      {
        currentRotation[2] += mousePosChange[1];
      }
      break;
    case SCALE:
      if (leftMouseButtonState)
      {
        currentScaling[0] *= 1.0+mousePosChange[0]*0.01;
        currentScaling[1] *= 1.0-mousePosChange[1]*0.01;
      }
      if (middleMouseButtonState)
      {
        currentScaling[2] *= 1.0-mousePosChange[1]*0.01;
      }
      break;
  }

  /* Update stored mouse position */
  mousePos[0] = x;
  mousePos[1] = y;
}

/* This function is called by GL when the mouse moves passively.
 * "Passively" means that no mouse button is being held down */
void mouseidle(int x, int y)
{
  int diffx = x - mousePos[0]; //check the difference between the current x and the last x position
  int diffy = y - mousePos[1]; //check the difference between the current y and the last y position

  mousePos[0] = x;
  mousePos[1] = y;

  currentCameraRotation[0] += (float) diffy; //set the xrot to xrot with the addition of the difference in the y position
  currentCameraRotation[1] += (float) diffx;// set the xrot to yrot with the addition of the difference in the x position

   currentCameraCoord[0] = x;
   currentCameraCoord[1] = y;
}



/* This is called by GL whenever a mouse button is pressed. */
void mousebutton(int button, int state, int x, int y)
{

  /* Check which button was pressed and update stored mouse state */
  switch (button)
  {
    case GLUT_LEFT_BUTTON:
      leftMouseButtonState = (state==GLUT_DOWN);
      break;
    case GLUT_MIDDLE_BUTTON:
      middleMouseButtonState = (state==GLUT_DOWN);
      break;
    case GLUT_RIGHT_BUTTON:
      rightMouseButtonState = (state==GLUT_DOWN);
      break;
  }

  /* Check what modifier keys (shift, ctrl, etc) are pressed and update the
   * control mode based off of which keys are pressed */
  switch(glutGetModifiers())
  {
    case GLUT_ACTIVE_CTRL:
      currentControlState = TRANSLATE;
      break;
    case GLUT_ACTIVE_SHIFT:
      currentControlState = SCALE;
      break;
    default:
      currentControlState = ROTATE;
      break;
  }

  /* Update stored mouse position */
  mousePos[0] = x;
  mousePos[1] = y;
}


void keyboard (unsigned char key, int x, int y) 
{
	if (key== 'd' || key == 'D' )
	{
		float yrotrad = (currentCameraCoord[1] / 180 * PI);
		currentCameraCoord[0] += float(cos(yrotrad)) * 0.2;
		currentCameraCoord[2] += float(sin(yrotrad)) * 0.2;
	}
	if (key=='a' || key == 'A')
	{
		float yrotrad = (currentCameraCoord[1] / 180 * PI);
		currentCameraCoord[0] -= float(cos(yrotrad)) * 0.2;
		currentCameraCoord[2] -= float(sin(yrotrad)) * 0.2;
	}
	if( key == 13 )
	{
		std::ostringstream o;
		string name = "Picture-";

		o << name << pictureNum << ".jpg";

		name = o.str();

		char n[512]; 
		strcpy(n,name.c_str());
		cout << n << endl;
		saveScreenshot(n);
		pictureNum++;

	}
	
	if(key == 'q' || key == 'Q' || key == 27) 
	{
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

/* This function is called by GL whenever it is idle, usually after calling
 * display.
 */
void doIdle()
{
  /* do some stuff... */

  /* make the screen update. */
   glutPostRedisplay(); 
}

// The ubiquituous main function.
int main ( int argc, char** argv )   // Create Main Function For Bringing It All Together
{
  
  if (argc<2)// if not specified, prompt for filename
  {
	char inputFile[999];
	printf("Input height file:");
	cin>>inputFile;
	sourceImage = jpeg_read(inputFile, NULL);
  }
  else //otherwise, use the name provided
  {
	/* Open jpeg (reads into memory) */
	sourceImage = jpeg_read(argv[1], NULL);
  }

  //this is an example of reading image data, you will use the pixel values to determine the height of the map at each node
  int red = PIC_PIXEL(sourceImage , 0, 0, 0);
  int green = PIC_PIXEL(sourceImage , 0, 0, 1);
  int blue = PIC_PIXEL(sourceImage , 0, 0, 2);
  cout<<"\nThe (r,g,b) value at the upper left corner is ("<<red<<","<<green<<","<<blue<<")\n";

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
  glutCreateWindow    ( "CMPSC 458: Heightmap" ); // Window Title (argv[0] for current directory as title)

  /**** Call to our initialization routine****/
  InitGL ();

  /* tells glut to use a particular display function to redraw */
  glutDisplayFunc(display);

  /** allow the user to quit using the right mouse button menu **/
  /* Set menu function callback */
  g_iMenuId = glutCreateMenu(menufunc);

  /* Set identifier for menu */
  glutSetMenu(g_iMenuId);

  /* Add quit option to menu */
  glutAddMenuEntry("Quit",0);

  /* Add any other menu functions you may want here.  The format is:
   * glutAddMenuEntry(MenuEntry, EntryIndex)
   * The index is used in the menufunc to determine what option was selected
   */

  /* Attach menu to right button clicks */
  glutAttachMenu(GLUT_RIGHT_BUTTON);

  /* Set idle function.  You can change this to call code for your animation,
   * or place your animation code in doIdle */
  glutIdleFunc(doIdle);

  /* callback for keyboard input */
  glutKeyboardFunc(keyboard);

  /* callback for mouse drags */
  glutMotionFunc(mousedrag);

  /* callback for idle mouse movement */
  glutPassiveMotionFunc(mouseidle);

  /* callback for mouse button changes */
  glutMouseFunc(mousebutton);


  glutMainLoop        ( );          // Initialize The Main Loop

  return 0;
}