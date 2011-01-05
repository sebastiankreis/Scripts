Dan Tracy
RollerCoaster README
1-Late Day Used

The rollercoaster, for the most part, works.  The only major concern is the damn camera.  For whatever reason I cannot get it to stop flipping and going through the track.  Currently
I have the camera going point by point on the spline, whose coordinates are stored within the vector catmullPoints.  It is taking into consideration the current point for the camera 
position and the next point in the iteration as the look at vector.  It then performs the cross multiplication of the look vector and the global up, which is the Y vector(0,1,0), to get 
the right vector.  It is then crossing the right with the look at to get the true up but for whatever reason it still flips.

The rollercoaster first reads in the spline files using the functions provided for us.  It then iterates through the control points four at a time.  There is an additional switch statement to make sure that it processes the last three points and the first. It starts iterating on the third control point afterwards, since the first three will already have been processed.  It performs the catmull-rom spline interpolation on these four points and increments the current position vector with them. The new position vector is stored in the catmullPoints vector structure so the exact location of each point is already calculated and can be easily drawn or reffered to.

 begin()              end-3 end-2 end-1
+---+----------------+-----+-----+----+
| A |                | B   |  C  | D  |
+---+----------------+-----+-----+----+

A display list is compiled with a corny rail texture that I have found online, I believe I just typed in rollercoaster track texture into google and found one.  The majority of my time
was getting the scene to render correctly and to get the camera to actually move through the points.  This was the biggest struggle.  Currently I have it going through point by point since I couldnt figure out how to add acceleration and velocity to it.  There are some basic physics functions implemented at the end of the main file, but since I never got the camera working correctly in the first place I never got a chance to use them.  A big problem that I faced was a discontinutiy between the last point and the first point.  To fix this when iterating throught the points for creating the display list I take into consideration the current poition and the last position and draw a textured quad between them.  It makes a complete track but there is a hideous looking quad just behind the first point connected to the final point in a huge jump.  

After I got all of that working somewhat correctly, save for the camera, I started working on the lighting and shadowing.  I used the default setting for the shadowing (GL_SMOOTH_SHADING) and used a white light for the light source.  The diffuse is set to 50% color, 50% shininess, and a 80% reflection for the specular material settings.  All the tracks on the rollercoaster share the same lighting settings.  I referred to the OpenGL redbook a great while working on the lighting.

I have reused the skybox code from the heightmap project and still havent figured how to get rid of those prominent lines around the quads.  However this time around I did get the skybox to rotate correctly.  It now uses the cameras position to rotate the skybox around the camera when it is on the track.  It also disable lighting, draws the skybox, then re-enables lighting again.

All in all I have put a lot of work into this project to get it where it currently is.  I would estimate the better part of two weeks.  My biggest issues were camera manipulation, drawing the spline, and rendering a continious track.  I did as much as I could and got as much done as I can possibly do.  Despite all of the problems it was a pretty fun project.