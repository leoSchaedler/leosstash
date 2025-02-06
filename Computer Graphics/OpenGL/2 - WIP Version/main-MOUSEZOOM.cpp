#include <windows.h>		// for MS Windows
#include <GL\freeglut.h>	// GLUT, include glu.h and gl.h

GLfloat angleV, fAspect;
GLfloat angle = 0.0f, angle1 = 0.0f;
char title[] = "OpenGL-PUC PR: Proje��o Perspectiva + Setas(ROTATE) + Click Mouse(ZOOM)";

// Fun��o callback chamada para fazer o desenho
void render(void)
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // Clear color and depth buffers
//	glMatrixMode(GL_MODELVIEW);			// To operate on model-view matrix
//	glLoadIdentity();					// Reset the model-view matrix

	glRotatef(angle, 1.0f, 0.0f, 0.0f);
	glRotatef(angle1, 0.0f, 1.0f, 0.0f);

	glColor3f(0.0f, 0.0f, 1.0f);

	// Desenha o teapot com a cor corrente (wire-frame)
	glutWireTeapot(50.0f);

	// Executa os comandos OpenGL
	glutSwapBuffers();
}

// Inicializa par�metros de rendering
void initGL(void)
{
	glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	angleV = 45;
}

// Fun��o usada para especificar o volume de visualiza��o
void setVisParam(void)
{
	// Especifica sistema de coordenadas de proje��o
	glMatrixMode(GL_PROJECTION);
	// Inicializa sistema de coordenadas de proje��o
	glLoadIdentity();

	// Especifica a proje��o perspectiva
	gluPerspective(angleV, fAspect, 0.1, 500);

	// Especifica sistema de coordenadas do modelo
	glMatrixMode(GL_MODELVIEW);
	// Inicializa sistema de coordenadas do modelo
	glLoadIdentity();

	// Especifica posi��o do observador e do alvo
	gluLookAt(0, 80, 200, 0, 0, 0, 0, 1, 0);
}

// Fun��o callback chamada quando o tamanho da janela � alterado 
void reshape(GLsizei w, GLsizei h)
{
	// Para previnir uma divis�o por zero
	if (h == 0) h = 1;

	// Especifica o tamanho da viewport
	glViewport(0, 0, w, h);

	// Calcula a corre��o de aspecto
	fAspect = (GLfloat)w / (GLfloat)h;

	setVisParam();
}

// Fun��o callback chamada para gerenciar eventos do mouse
void mouse(int button, int state, int x, int y)
{
	if (button == GLUT_LEFT_BUTTON)
		if (state == GLUT_DOWN) {  // Zoom-in
			if (angleV >= 10) angleV -= 5;
		}
	if (button == GLUT_RIGHT_BUTTON)
		if (state == GLUT_DOWN) {  // Zoom-out
			if (angleV <= 130) angleV += 5;
		}
	setVisParam();
	glutPostRedisplay();
}
// Keyboard ...
void processSpecialKeys(int key, int xx, int yy) {
	switch (key) {
	case GLUT_KEY_LEFT:
		angle1--;
		break;
	case GLUT_KEY_RIGHT:
		angle1++;
		break;
	case GLUT_KEY_UP:
		angle--;
		break;
	case GLUT_KEY_DOWN:
		angle++;
		break;
	}
}

// Programa Principal
int main(int argc, char** argv) {
	glutInit(&argc, argv);             // Initialize GLUT
	glutInitDisplayMode(GLUT_DOUBLE);  // Enable double buffered mode
	glutInitWindowSize(640, 480);      // Set the window's initial width & height
	glutInitWindowPosition(50, 50);    // Position the window's initial top-left corner
	glutCreateWindow(title);           // Create window with the given title
	glutDisplayFunc(render);           // Register callback handler for window re-paint event
	glutSpecialFunc(processSpecialKeys);  // Register callback handler for arrow keys (keyborad)
	glutReshapeFunc(reshape);          // Register callback handler for window re-size event
	glutMouseFunc(mouse);	           // Register callback handler for window mouse button event
	glutIdleFunc(render);		   // Register callback handler for window redraw (idle - doing nothing)
	initGL();
	glutMainLoop();
	return 0;
}