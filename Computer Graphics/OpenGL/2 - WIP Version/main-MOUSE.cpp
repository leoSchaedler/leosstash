#include <windows.h>  // for MS Windows
#include <GL\freeglut.h>  // GLUT, include glu.h and gl.h
#include <math.h>

/* Global variables */
char title[] = "OpenGL-PUC PR (MOUSE 1)";
GLfloat nRange = 200;
// camera, mouse ... 
float deltaAngle = 0.0f;
int xOrigin = -1;
float angle = 0.0; // angle of rotation for the camera direction
float lx = 0.0f, lz = -1.0f; // actual vector representing the camera's direction
float x = 0.0f, z = 5.0f; // XZ position of the camera

void cubo(float a)
{
	glShadeModel(GL_FLAT);
	glBegin(GL_TRIANGLE_STRIP);
	glColor3f(1.0f, 0.0f, 0.0f); // red
	glVertex3f(-a / 2, a / 2, a / 2);
	glVertex3f(-a / 2, -a / 2, a / 2);
	glColor3f(0.0f, 0.0f, 1.0f); // blue
	glVertex3f(a / 2, a / 2, a / 2);
	glVertex3f(a / 2, -a / 2, a / 2);
	glColor3f(1.0f, 1.0f, 1.0f); // white
	glVertex3f(a / 2, a / 2, -a / 2);
	glVertex3f(a / 2, -a / 2, -a / 2);
	glColor3f(1.0f, 1.0f, 0.0f); // magenta
	glVertex3f(-a / 2, a / 2, -a / 2);
	glVertex3f(-a / 2, -a / 2, -a / 2);
	glColor3f(1.0f, 0.0f, 1.0f); // cyan
	glVertex3f(-a / 2, a / 2, a / 2);
	glVertex3f(-a / 2, -a / 2, a / 2);
	glEnd();

	glBegin(GL_TRIANGLE_STRIP);
	glColor3f(0.5f, 0.5f, 0.5f); // grey
	glVertex3f(-a / 2, a / 2, -a / 2);
	glVertex3f(-a / 2, a / 2, a / 2);
	glVertex3f(a / 2, a / 2, -a / 2);
	glVertex3f(a / 2, a / 2, a / 2);
	glEnd();

	glBegin(GL_TRIANGLE_STRIP);
	glColor3f(1.0f, 0.5f, 0.0f); // orange
	glVertex3f(a / 2, -a / 2, -a / 2);
	glVertex3f(a / 2, -a / 2, a / 2);
	glVertex3f(-a / 2, -a / 2, -a / 2);
	glVertex3f(-a / 2, -a / 2, a / 2);
	glEnd();
}

/* Initialize OpenGL Graphics */
void initGL() {
	glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // Set background color to black and opaque
	glClearDepth(1.0f);                   // Set background depth to farthest
	glEnable(GL_DEPTH_TEST);   // Enable depth testing for z-culling
	glDepthFunc(GL_LEQUAL);    // Set the type of depth-test
	glShadeModel(GL_SMOOTH);   // Enable smooth shading
	glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);  // Nice perspective corrections
}
// mouse callbacks 
void mouseButton(int button, int state, int x, int y) {
	// only start motion if the left button is pressed
	if (button == GLUT_LEFT_BUTTON) {
		// when the button is released
		if (state == GLUT_UP) {
			angle += deltaAngle;
			xOrigin = -1;
		}
		else {// state = GLUT_DOWN
			xOrigin = x;
		}
	}
}
void mouseMove(int x, int y) {
	// this will only be true when the left button is down
	if (xOrigin >= 0) {

		// update deltaAngle
		deltaAngle = (x - xOrigin) * 0.001f;

		// update camera's direction
		lx = sin(angle + deltaAngle);
		lz = -cos(angle + deltaAngle);
	}
}

/* Handler for window-repaint event. Called back when the window first appears and
whenever the window needs to be re-painted. */
void render() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // Clear color and depth buffers
	glMatrixMode(GL_MODELVIEW);			// To operate on model-view matrix
	glLoadIdentity();					// Reset the model-view matrix

	// Set the camera								   
	gluLookAt(x, 1.0f, z, x + lx, 1.0f, z + lz, 0.0f, 1.0f, 0.0f);

	cubo(100);

	glutSwapBuffers();  // Swap the front and back frame buffers (double buffering)
}

/* Handler for window re-size event. Called back when the window first appears and
whenever the window is re-sized with its new width and height */
void reshape(GLsizei w, GLsizei h) {
	if (h == 0) h = 1;
	// Especifica as dimensões da Viewport
	glViewport(0, 0, w, h);
	// Inicializa o sistema de coordenadas
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	if (w <= h)
		glOrtho(-nRange, nRange, -nRange * h / w, nRange*h / w, -nRange, nRange);
	else
		glOrtho(-nRange * w / h, nRange*w / h, -nRange, nRange, -nRange, nRange);
}

/* Main function: GLUT runs as a console application starting at main() */
int main(int argc, char** argv) {
	glutInit(&argc, argv);            // Initialize GLUT
	glutInitDisplayMode(GLUT_DOUBLE); // Enable double buffered mode
	glutInitWindowSize(640, 480);     // Set the window's initial width & height
	glutInitWindowPosition(50, 50);   // Position the window's initial top-left corner
	glutCreateWindow(title);          // Create window with the given title
	glutDisplayFunc(render);          // Register callback handler for window re-paint event
	glutReshapeFunc(reshape);         // Register callback handler for window re-size event
	glutMouseFunc(mouseButton);	  // Register callback handler for window mouse button event
	glutMotionFunc(mouseMove);        // Register callback handler for window mouse move event
	initGL();                         // Our own OpenGL initialization
	glutMainLoop();                   // Enter the infinite event-processing loop
	return 0;
}