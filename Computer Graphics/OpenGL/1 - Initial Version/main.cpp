#include <windows.h>		// for MS Windows
#include <GL\freeglut.h>	// GLUT, include glu.h and gl.h
#include <fstream>		    // File library
#include <iostream>
using namespace std;

GLfloat angleV, fAspect;
GLfloat angle = 0.0f, angle1 = 0.0f;
int selected = 0;
int numObjects;
int ObjectList[10];         // Lista de Objetos, 10 é o número máximo
int x[10], y[10], z[10];	// coordenadas dos objetos
float r[10], g[10], b[10];  // cores dos objetos
char title[] = "OpenGL-PUC PR: Setas / Arquivo de Cena";

// Função para leitura do arquivo de cena
void DisplayFileRead(const char * fileName) // na versão 2015 (char * fileName)
{
	fstream inStream;
	inStream.open(fileName, ios::in); // abre o arquivo
	if (inStream.fail()) return;      //falha na abertura do arquivo
	cout << "Abertura do arquivo com sucesso ..." << endl;
	inStream >> numObjects;			  // lê primeira linha do arquivo, número de objetos 
	cout << numObjects << " Objetos na cena ..." << endl;
	for (int i = 1; i <= numObjects; i++) { // leitura das demais linhas, ID dos objetos, posição e cor
		inStream >>ObjectList[i]>>x[i]>>y[i]>>z[i]>>r[i]>>g[i]>>b[i];  
	}
	inStream.close();				// fecha o arquivo
}


// Função callback chamada para fazer o desenho
void render(void)
{
//	DisplayFileRead("df.txt");  // se estiver aqui, lê a cada redesenho da tela (dá para alterar o arquivo em tempo de execução)
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // limpa a tela e o buffer de profundidade
	glPushMatrix();
	glRotatef(angle, 1.0f, 0.0f, 0.0f);
	glRotatef(angle1, 0.0f, 1.0f, 0.0f);

	for (int i = 1; i <= numObjects; i++) {
		switch (ObjectList[i]){
		case 1: //cubo
			glPushMatrix();
			glColor3f(r[i], g[i], b[i]);
			glTranslatef(x[i], y[i], z[i]);
			glutSolidCube(10);
			glPopMatrix();
			break;
		case 2: //esfera
			glPushMatrix();
			glColor3f(r[i], g[i], b[i]);
			glTranslatef(x[i], y[i], z[i]);
			glutSolidSphere(10, 24, 24);
			glPopMatrix();
			break;
		case 3: //cone
			glPushMatrix();
			glColor3f(r[i], g[i], b[i]);
			glTranslatef(x[i], y[i], z[i]);
			glutSolidCone(10, 20, 24, 24);
			glPopMatrix();
			break;
		}
	}

	glPopMatrix();

	glutSwapBuffers();
}

// Inicializa parâmetros de rendering
void initGL(void)
{
	angleV = 45;

	glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // Set background color to black and opaque
	glClearDepth(1.0f);                   // Set background depth to farthest
	glEnable(GL_DEPTH_TEST);   // Enable depth testing for z-culling
	glDepthFunc(GL_LEQUAL);    // Set the type of depth-test
	glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);  // Nice perspective corrections

	GLfloat luzAmbiente[4] = { 0.2,0.2,0.2,1.0 };
	GLfloat luzDifusa[4] = { 0.7,0.7,0.7,1.0 };	   // "cor" 
	GLfloat luzEspecular[4] = { 1.0, 1.0, 1.0, 1.0 };// "brilho" 
	GLfloat posicaoLuz[4] = { 0.0, 50.0, 50.0, 1.0 };

	// Capacidade de brilho do material
	GLfloat especularidade[4] = { 1.0,1.0,1.0,1.0 };
	GLint especMaterial = 60;

	// Especifica que a cor de fundo da janela será preta
	glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

	// Habilita o modelo de colorização de Gouraud
	glShadeModel(GL_SMOOTH);

	// Define a refletância do material 
	glMaterialfv(GL_FRONT, GL_SPECULAR, especularidade);
	// Define a concentração do brilho
	glMateriali(GL_FRONT, GL_SHININESS, especMaterial);

	// Ativa o uso da luz ambiente 
	glLightModelfv(GL_LIGHT_MODEL_AMBIENT, luzAmbiente);

	// Define os parâmetros da luz de número 0
	glLightfv(GL_LIGHT0, GL_AMBIENT, luzAmbiente);
	glLightfv(GL_LIGHT0, GL_DIFFUSE, luzDifusa);
	glLightfv(GL_LIGHT0, GL_SPECULAR, luzEspecular);
	glLightfv(GL_LIGHT0, GL_POSITION, posicaoLuz);

	// Habilita a definição da cor do material a partir da cor corrente
	glEnable(GL_COLOR_MATERIAL);
	//Habilita o uso de iluminação
	glEnable(GL_LIGHTING);
	// Habilita a luz de número 0
	glEnable(GL_LIGHT0);
}

// Função usada para especificar o volume de visualização
void setVisParam(void)
{
	// Especifica sistema de coordenadas de projeção
	glMatrixMode(GL_PROJECTION);
	// Inicializa sistema de coordenadas de projeção
	glLoadIdentity();

	// Especifica a projeção perspectiva
	gluPerspective(angleV, fAspect, 0.1, 500);

	// Especifica sistema de coordenadas do modelo
	glMatrixMode(GL_MODELVIEW);
	// Inicializa sistema de coordenadas do modelo
	glLoadIdentity();

	// Especifica posição do observador e do alvo
	gluLookAt(0, 80, 200, 0, 0, 0, 0, 1, 0);
}

// Função callback chamada quando o tamanho da janela é alterado 
void reshape(GLsizei w, GLsizei h)
{
	// Para previnir uma divisão por zero
	if (h == 0) h = 1;

	// Especifica o tamanho da viewport
	glViewport(0, 0, w, h);

	// Calcula a correção de aspecto
	fAspect = (GLfloat)w / (GLfloat)h;

	setVisParam();
}

// Função callback chamada para gerenciar eventos do mouse
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
	glutPostRedisplay();  // redraw
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
	glutPostRedisplay();   // redraw
}

// Programa Principal
int main(int argc, char** argv) {
	DisplayFileRead("df.txt");              // se estiver aqui, lê somente uma vez
	cout << "Setas - rotaciona a cena" << endl;
	cout << "Mouse Click - zoom" << endl;
	glutInit(&argc, argv);					// Initialize GLUT
	glutInitDisplayMode(GLUT_DOUBLE);		// Enable double buffered mode
	glutInitWindowSize(640, 480);			// Set the window's initial width & height
	glutInitWindowPosition(50, 50);			// Position the window's initial top-left corner
	glutCreateWindow(title);				// Create window with the given title
	glutDisplayFunc(render);				// Regster callback for render function
	glutSpecialFunc(processSpecialKeys);	// Register callback handler for arrow keys (keyborad)
	glutReshapeFunc(reshape);				// Register callback for window resize event
	glutMouseFunc(mouse);					// Register callback for mouse event
//	glutIdleFunc(render);					// no animation
	initGL();								// Initialization function
	glutMainLoop();							// event loop
	return 0;
}