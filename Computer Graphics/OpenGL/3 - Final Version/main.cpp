
#include <windows.h>         // Para MS Windows
#include <GL/freeglut.h>    // GLUT, inclui glu.h and gl.h
#include <math.h>
//Transformações
#include <fstream>		 // Biblioteca do arquivo
#include <iostream>
using std::cout;
using std::endl;
using std::fstream;
using std::ios;


/* Variáveis Globais */

// Título
char title[] = "Trabalho CSG3D - Leonardo Ribeiro Schaedler";


// Manuseio dos objetos
int selected = 0;
int numObjects;
int ObjectList[10];				// Lista de Objetos, 10 é o número máximo
int x[10], y[10], z[10];	   // Coordenadas dos objetos
float r[10], g[10], b[10];    // Cores dos objetos
float grow[10];              // Variável para aumentar tamanho
int xcam, ycam, zcam;       // Variáveis para mover a camera


// Booleanas para alteração de visibilidade dos objetos
bool showCTS = true;
bool showES = true;
bool showTS = true;
bool showDTS = true;
bool showBCS = true;
bool showCS = true;
bool showCNS = true;
bool showT = true;


// Ângulo para projeção e funções do mouse
GLfloat angleV, fAspect;


// Constante PI para elaboração de alguns objetos
const float pi = 3.14159265359f;

// Ângulos para circulação na cena
GLfloat angle = 0.0f, angle1 = 0.0f;


// Funções e variáveis para cálculo da normal

float normal[3];

void ReduceToUnit(float vector[3])
{
	float length;
	// Calculate the length of the vector		
	length = (float)sqrt((vector[0] * vector[0]) +
		(vector[1] * vector[1]) +
		(vector[2] * vector[2]));
	// Keep the program from blowing up by providing an exceptable
	// value for vectors that may calculated too close to zero.
	if (length == 0.0f)
		length = 1.0f;
	// Dividing each element by the length will result in a
	// unit normal vector.
	vector[0] /= length;
	vector[1] /= length;
	vector[2] /= length;
}

// Pontos p1, p2 e p3 especificados em ordem horária
void calcNormal(float v[3][3], float out[3])
{
	float v1[3], v2[3];
	static const int x = 0;
	static const int y = 1;
	static const int z = 2;

	// Calculate two vectors from the three points
	v1[x] = v[0][x] - v[1][x];
	v1[y] = v[0][y] - v[1][y];
	v1[z] = v[0][z] - v[1][z];

	v2[x] = v[1][x] - v[2][x];
	v2[y] = v[1][y] - v[2][y];
	v2[z] = v[1][z] - v[2][z];

	// Take the cross product of the two vectors to get
	// the normal vector which will be stored in out
	out[x] = v1[y] * v2[z] - v1[z] * v2[y];
	out[y] = v1[z] * v2[x] - v1[x] * v2[z];
	out[z] = v1[x] * v2[y] - v1[y] * v2[x];

	// Normalize the vector (shorten length to one)
	ReduceToUnit(out);
}

// Funções para criação de objetos
void cubo(float a) {
	glBegin(GL_TRIANGLE_STRIP);
	float v[4][3] = { {-a / 2, a / 2, a / 2},{-a / 2, -a / 2, a / 2}, {a / 2, a / 2, a / 2}, {a / 2, -a / 2, a / 2 } };
	calcNormal(v, normal);
	glNormal3fv(normal);
	glVertex3fv(v[0]);
	glVertex3fv(v[1]);
	glVertex3fv(v[2]);
	glVertex3fv(v[3]);
	float w[4][3] = { {a / 2, a / 2, -a / 2},{a / 2, -a / 2, -a / 2}, {-a / 2, a / 2, -a / 2}, {-a / 2, -a / 2, -a / 2} };
	calcNormal(w, normal);
	glNormal3fv(normal);
	glVertex3fv(w[0]);
	glVertex3fv(w[1]);
	glVertex3fv(w[2]);
	glVertex3fv(w[3]);
	float x[2][3] = { {-a / 2, a / 2, a / 2},{-a / 2, -a / 2, a / 2 }};
	calcNormal(x, normal);
	glNormal3fv(normal);
	glVertex3fv(x[0]);
	glVertex3fv(x[1]);
	glEnd();
	glBegin(GL_TRIANGLE_STRIP);
	float y[4][3] = { {-a / 2, a / 2, -a / 2},{-a / 2, a / 2, a / 2}, {a / 2, a / 2, -a / 2}, {a / 2, a / 2, a / 2} };
	calcNormal(y, normal);
	glNormal3fv(normal);
	glVertex3fv(y[0]);
	glVertex3fv(y[1]);
	glVertex3fv(y[2]);
	glVertex3fv(y[3]);
	glEnd();
	glBegin(GL_TRIANGLE_STRIP);
	float z[4][3] = { {a / 2, -a / 2, -a / 2},{a / 2, -a / 2, a / 2}, {-a / 2, -a / 2, -a / 2}, {-a / 2, -a / 2, a / 2 } };
	calcNormal(v, normal);
	glNormal3fv(normal);
	glVertex3fv(z[0]);
	glVertex3fv(z[1]);
	glVertex3fv(z[2]);
	glVertex3fv(z[3]);
	glEnd();
}

void disco(float Raio) {
	glBegin(GL_TRIANGLE_FAN);
	glVertex3f(0.0f, 0.0f, 0.0f); //centro
	for (float angle = 0.0; angle < (2.0 * pi); angle += (2.0 * pi) / 8.0) {
		float x = Raio * sin(angle);
		float y = Raio * cos(angle);
		glVertex3f(x, y, 0.0);
	}
	glEnd();
}

void cone(float Raio, float H) {
	glBegin(GL_TRIANGLE_FAN); // BASE
	glVertex3f(0.0, 0.0, 0.0); //centro
	for (float angle = 0.0; angle < (2.0 * pi); angle += (2.0 * pi / 36.0))
	{
		float x = Raio * sin(angle);
		float y = Raio * cos(angle);
		glVertex3f(x, y, 0.0);
	}
	glEnd();
	glBegin(GL_TRIANGLE_FAN); // lateral
	glVertex3f(0.0, 0.0, H); //centro
	for (float angle = 0.0; angle < (2.0 * pi); angle += (2.0 * pi / 36.0))
	{
		float x = Raio * sin(angle);
		float y = Raio * cos(angle);
		glVertex3f(x, y, 0.0);
	}
	glEnd();

}



// Inicializa parâmetros de rendering
void initGL(void)
{
	angleV = 45;

	glClearColor(0.0f, 0.0f, 0.0f, 1.0f);                   // Deixa o background preto e opaco
	glClearDepth(1.0f);                                    // Deixa a profundidade do background na mais distante possível
	glEnable(GL_DEPTH_TEST);                              // Habilita o depth testing para o z-culling
	glDepthFunc(GL_LEQUAL);                              // Configura o tipo do depth-test
	glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);  // Correções de perspectiva

	GLfloat luzAmbiente[4] = { 0.2,0.2,0.2,1.0 };
	GLfloat luzDifusa[4] = { 0.7,0.7,0.7,1.0 };	       // "cor" 
	GLfloat luzEspecular[4] = { 1.0, 1.0, 1.0, 1.0 }; // "brilho" 
	GLfloat posicaoLuz[4] = { 0.0, 50.0, 50.0, 1.0 };

	GLfloat luzAmbiente1[4] = { 0.8,0.8,0.8,1.0 };
	GLfloat luzDifusa1[4] = { 0.2,0.2,0.2,1.0 };	    // "cor1" 
	GLfloat luzEspecular1[4] = { 0.2, 0.2, 0.2, 1.0 }; // "brilho1" 
	GLfloat posicaoLuz1[4] = { 0.0, -50.0, 0.0, 1.0 };

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

	// Define os parâmetros da luz de número 1
	glLightfv(GL_LIGHT1, GL_AMBIENT, luzAmbiente1);
	glLightfv(GL_LIGHT1, GL_DIFFUSE, luzDifusa1);
	glLightfv(GL_LIGHT1, GL_SPECULAR, luzEspecular1);
	glLightfv(GL_LIGHT1, GL_POSITION, posicaoLuz1);

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
	gluLookAt(0+xcam, 80+ycam, 200+zcam, 0+xcam, 0+ycam, 0+zcam, 0, 1, 0);
}


// Funções de callback para eventos do teclado
void keyboard(unsigned char key, int x, int y)
{
	switch (key)
	{
	case '1':
		cout << "Aperte 1 para ligar/ desligar a renderizacao do primeiro objeto (conforme ordem inicial de renderizacao)..." << endl;
		if (showCTS)
			showCTS = FALSE;
		else
		showCTS = TRUE;
		break;
		
	case '2':
		cout << "Aperte 2 para ligar/ desligar a renderizacao do segundo objeto (conforme ordem inicial de renderizacao)..." << endl;
		if (showES)
			showES = FALSE;
		else
			showES = TRUE;
		break;

	case '3':
		cout << "Aperte 3 para ligar/ desligar a renderizacao do terceiro objeto (conforme ordem inicial de renderizacao)..." << endl;
		if (showTS)
			showTS = FALSE;
		else
			showTS = TRUE;
		break;
	case '4':
		cout << "Aperte 4 para ligar/ desligar a renderizacao do quarto objeto (conforme ordem inicial de renderizacao)..." << endl;
		if (showDTS)
			showDTS = FALSE;
		else
			showDTS = TRUE;
		break;
	case '5':
		cout << "Aperte 5 para ligar/ desligar a renderizacao do quinto objeto (conforme ordem inicial de renderizacao)..." << endl;
		if (showBCS)
			showBCS = FALSE;
		else
			showBCS = TRUE;
		break;
	case '6':
		cout << "Aperte 6 para ligar/ desligar a renderizacao do sexto objeto (conforme ordem inicial de renderizacao)..." << endl;
		if (showCS)
			showCS = FALSE;
		else
			showCS = TRUE;
		break;
	case '7':
		cout << "Aperte 7 para ligar/ desligar a renderizacao do setimo objeto (conforme ordem inicial de renderizacao)..." << endl;
		if (showCNS)
			showCNS = FALSE;
		else
			showCNS = TRUE;
		break;
	case '8':
		cout << "Aperte 8 para ligar/ desligar a renderizacao do oitavo objeto (conforme ordem inicial de renderizacao)..." << endl;
		if (showT)
			showT = FALSE;
		else
			showT = TRUE;
		break;
	case 'a':
		glEnable(GL_LIGHT0);
		cout << "Habilitou a luz de numero 0..." << endl;
		break;
	case 's':
		glDisable(GL_LIGHT0);
		cout << "Desabilitou a luz de numero 0..." << endl;
		break;
	case 'd':
		glEnable(GL_LIGHT1);
		cout << "Habilitou a luz de numero 1..." << endl;
		break;
	case 'f':
		glDisable(GL_LIGHT1);
		cout << "Desabilitou a luz de numero 1..." << endl;
		break;
	case 'r':
		r[selected] = r[selected] + 0.1;
		cout << "Aumentou a intensidade da cor vermelha no objeto selecionado..." << endl;
		if (selected == 0)
			glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
		break;
	case 'g':
		g[selected] = g[selected] + 0.1;
		cout << "Aumentou a intensidade da cor verde no objeto selecionado..." << endl;
		if (selected == 0)
			glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
		break;
	case 'b':
		b[selected] = b[selected] + 0.1;
		cout << "Aumentou a intensidade da cor azul no objeto selecionado..." << endl;
		if (selected == 0)
			glClearColor(0.0f, 0.0f, 1.0f, 1.0f);
		break;
	case 't':
		r[selected] = r[selected] - 0.1;
		cout << "Diminuiu a intensidade da cor vermelha no objeto selecionado..." << endl;
		if (selected == 0)
			glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		break;
	case 'h':
		g[selected] = g[selected] - 0.1;
		cout << "Diminuiu a intensidade da cor verde no objeto selecionado..." << endl;
		if (selected == 0)
			glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		break;
	case 'n':
		b[selected] = b[selected] - 0.1;
		cout << "Diminuiu a intensidade da cor azul no objeto selecionado..." << endl;
		if (selected == 0)
			glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		break;
	case 'p':
		setVisParam();
		cout << "Habilitou projecao perspectiva..." << endl;
		break;
	case 'o':
		glOrtho(0.5, 3, 0.5, 3, 0, 1.8);
		cout << "Habilitou projecao ortogonal..." << endl;
		break;
	case 'c':
		grow[selected]++;
		cout << "Aumentou o tamanho da figura selecionada..." << endl;
		break;
	case 'v':
		grow[selected]--;
		cout << "Diminuiu o tamanho da figura selecionada..." << endl;
		break;
	case ',':
		if (selected == 0)
		{
			zcam = zcam + 5;
			setVisParam();
			cout << "Moveu a camera para tras..." << endl;
		}
		else
		{
			z[selected]--;
			cout << "Moveu a figura para tras..." << endl;
		}
		break;
	case '.':
		if (selected == 0)
		{
			zcam = zcam - 5;
			setVisParam();
			cout << "Moveu a camera para frente..." << endl;
		}
		else
		{
			z[selected]++;
			cout << "Moveu a figura para frente..." << endl;
		}
		break;
	case '	':
		cout << " " << endl;
		cout << "--------- Trabalho de Construcao de Software Grafico 3D - Implementacao OpenGL - Leonardo Ribeiro Schaedler ---------" << endl;
		cout << " " << endl;
		cout << "Aperte TAB a qualquer momento para mostrar novamente essas instrucoes" << endl;
		cout << "Page UP, Page Down, Home e End rotacionam a cena" << endl;
		cout << "Utilize as setas direcionais ou do Numpad  e as teclas '<' e '>' para mover a camera pela cena " << endl;
		cout << "Clique esquerdo do mouse - zoom in" << endl;
		cout << "Clique direito do mouse - zoom out" << endl;
		cout << "Pressione a tecla 'o' para entrar em modo de projecao ortogonal e 'p' para projecao perspectiva" << endl;
		cout << "Pressione a tecla 'a' para ligar a primeira luz, 's' para desliga-la. Aperte 'd' para ligar a segunda luz e 'f' para desliga-la" << endl;
		cout << "Pressione de 1-8 para ligar/ desligar a renderizacao dos objetos" << endl;
		cout << "Pressione de F1-F8 para selecionar o objeto desejado, para parar de selecionar um objeto aperte a tecla Insert" << endl;
		cout << "	- Com algum objeto ja selecionado, utilize as setas direcionais ou do Numpad e as teclas '<' e '>' para move-lo na cena" << endl;
		cout << "	- Com algum objeto ja selecionado, utilize a tecla 'c' para aumentar o tamanho dele e a tecla 'v' para diminuir" << endl;
		cout << "	- Com algum objeto ja selecionado, utilize as teclas r, g, b para aumentar a intensidade das cores" << endl;
		cout << "	- Com algum objeto ja selecionado, utilize as teclas t, h, n para diminuir a intensidade das cores" << endl;
		cout << "	- As teclas r e t mudam a cor vermelha, g e h mudam a cor verde e b e n mudam a azul" << endl;
		cout << "		- Caso nenhum objeto esteja selecionado, as alteracoes de cor serao aplicadas ao fundo da cena" << endl;
		cout << "Abaixo um log de suas acoes:" << endl;
		cout << " " << endl;
		break;
	}
	glutPostRedisplay();

}


void processSpecialKeys(int key, int xx, int yy) {
	switch (key) {
	case GLUT_KEY_PAGE_UP:
		angle++;
		cout << "Rotacionou a cena para baixo..." << endl;
		break;
	case GLUT_KEY_PAGE_DOWN:
		angle1++;
		cout << "Rotacionou a cena para direita..." << endl;
		break;
	case GLUT_KEY_HOME:
		angle--;
		cout << "Rotacionou a cena para cima..." << endl;
		break;
	case GLUT_KEY_END:
		angle1--;
		cout << "Rotacionou a cena para esquerda..." << endl;
		break;
	case GLUT_KEY_LEFT:
		if (selected == 0)
		{
			cout << "Moveu a camera para a esquerda..." << endl;
			xcam = xcam - 5;
			setVisParam();
		}
		else
		{
			x[selected]--;
			cout << "Moveu a figura para a esquerda..." << endl;
		}
		break;
	case GLUT_KEY_RIGHT:
		if (selected == 0)
		{
			xcam = xcam + 5;
			setVisParam();
			cout << "Moveu a camera para a direita..." << endl;
		}
		else
		{
			x[selected]++;
			cout << "Moveu a figura para a direita..." << endl;
		}
		break;
	case GLUT_KEY_UP:
		if (selected == 0)
		{
			ycam = ycam + 5;
			setVisParam();
			cout << "Moveu a camera para cima.." << endl;
		}
		else
		{
			y[selected]++;
			cout << "Moveu a figura para cima..." << endl;
		}
		break;
	case GLUT_KEY_DOWN:
		if (selected == 0)
		{
			ycam = ycam - 5;
			setVisParam();
			cout << "Moveu a camera para baixo.." << endl;
		}
		else
		{
			y[selected]--;
			cout << "Moveu a figura para baixo..." << endl;
		}
		break;
	case GLUT_KEY_F1:
		selected = 1;
		cout << "Cubo Triangle Strip selecionado" << endl;
		break;
	case GLUT_KEY_F2:
		selected = 2;
		cout << "Esfera Solida selecionada" << endl;
		break;
	case GLUT_KEY_F3:
		selected = 3;
		cout << "Cone Triangle Strip selecionado" << endl;
		break;
	case GLUT_KEY_F4:
		selected = 4;
		cout << "Disco Triangle Strip selecionado" << endl;
		break;
	case GLUT_KEY_F5:
		selected = 5;
		cout << "Bule de Cha Solido selecionado" << endl;
		break;
	case GLUT_KEY_F6:
		selected = 6;
		cout << "Cubo Solido selecionado" << endl;
		break;
	case GLUT_KEY_F7:
		selected = 7;
		cout << "Cone Solido selecionado" << endl;
		break;
	case GLUT_KEY_F8:
		selected = 8;
		cout << "Toro Solido selecionado" << endl;
		break;
		break;
	case GLUT_KEY_INSERT:
		selected = 0;
		cout << "Nenhum objeto selecionado" << endl;
		break;
	}
	glutPostRedisplay(); // Funções para atualização da cena
	//setVisParam();   
}


// Função de callback para eventos do mouse
void mouse(int button, int state, int x, int y)
{
	if (button == GLUT_LEFT_BUTTON)
		if (state == GLUT_DOWN) {  // Zoom-in
			if (angleV >= 10) angleV -= 5;
			cout << "Aumentou o Zoom" << endl;
		}
	if (button == GLUT_RIGHT_BUTTON)
		if (state == GLUT_DOWN) {  // Zoom-out
			if (angleV <= 130) angleV += 5;
			cout << "Diminuiu o Zoom" << endl;
		}
	setVisParam();
	glutPostRedisplay();
}


// Função para leitura do arquivo de cena
void DisplayFileRead(const char* fileName) // na versão 2015 (char * fileName)
{
	fstream inStream;
	inStream.open(fileName, ios::in); // abre o arquivo
	if (inStream.fail()) return;      //falha na abertura do arquivo
	cout << "Abertura do arquivo com sucesso ..." << endl;
	inStream >> numObjects;			  // lê primeira linha do arquivo, número de objetos 
	cout << numObjects << " Objetos na cena ..." << endl;
	for (int i = 1; i <= numObjects; i++) { // leitura das demais linhas, ID dos objetos, posição e cor
		inStream >> ObjectList[i] >> x[i] >> y[i] >> z[i] >> r[i] >> g[i] >> b[i] >> grow[i];
	}
	inStream.close();				 // fecha o arquivo
}


// Função para renderização
void render(void)
{
	//DisplayFileRead("df.txt");                         // se estiver aqui, lê a cada redesenho da tela (dá para alterar o arquivo em tempo de execução)
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // limpa a tela e o buffer de profundidade
	glPushMatrix();
	glRotatef(angle, 1.0f, 0.0f, 0.0f);
	glRotatef(angle1, 0.0f, 1.0f, 0.0f);

	for (int i = 1; i <= numObjects; i++) {
		switch (ObjectList[i]) {
		case 1: // Cubo Triangle Strip

			if (showCTS) 
			{
				glPushMatrix();
				glColor3f(r[i], g[i], b[i]);
				glTranslatef(x[i], y[i], z[i]);
				cubo(10 + grow[i]);
				glPopMatrix();
			}
			break;

		case 2: // Esfera Sólida

			if (showES)
			{
				glPushMatrix();
				glColor3f(r[i], g[i], b[i]);
				glTranslatef(x[i], y[i], z[i]);
				glutSolidSphere(10 + grow[i], 24 + grow[i], 24 + grow[i]);
				glPopMatrix();
			}
			break;

		case 3: // Cone Triangle Strip

			if (showTS)
			{
				glPushMatrix();
				glColor3f(r[i], g[i], b[i]);
				glTranslatef(x[i], y[i], z[i]);
				cone(10 + grow[i], 20 + grow[i]);
				glPopMatrix();
			}
			break;

		case 4: // Disco Triangle Strip

			if (showDTS)
			{
				glPushMatrix();
				glColor3f(r[i], g[i], b[i]);
				glTranslatef(x[i], y[i], z[i]);
				disco(10 + grow[i]);
				glPopMatrix();
			}
			break;
		case 5: // Bule de Chá Sólido

			if (showBCS)
			{
				glPushMatrix();
				glColor3f(r[i], g[i], b[i]);
				glTranslatef(x[i], y[i], z[i]);
				glutSolidTeapot(10 + grow[i]);
				glPopMatrix();
			}
			break;
		case 6: // Cubo Sólido

			if (showCS)
			{
				glPushMatrix();
				glColor3f(r[i], g[i], b[i]);
				glTranslatef(x[i], y[i], z[i]);
				glutSolidCube(10 + grow[i]);
				glPopMatrix();
			}
			break;
		case 7: // Cone Sólido

			if (showCNS)
			{
				glPushMatrix();
				glColor3f(r[i], g[i], b[i]);
				glTranslatef(x[i], y[i], z[i]);
				glutSolidCone(10 + grow[i], 10 + grow[i], 10 + grow[i], 10 + grow[i]);
				glPopMatrix();
			}
			break;
		case 8: // Toro Sólido

			if (showT)
			{
				glPushMatrix();
				glColor3f(r[i], g[i], b[i]);
				glTranslatef(x[i], y[i], z[i]);
				glutSolidTorus(1 + grow[i], 5 + grow[i], 5 + grow[i], 100 + grow[i]);
				glPopMatrix();
			}
			break;
		}

	}

	glPopMatrix();

	glutSwapBuffers();
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


// Função main para inicialização do GLUT como um app de console e chamadas de funções
int main(int argc, char** argv) {
	DisplayFileRead("df.txt");  // Caso a DisplayFile estiver aqui, lê somente uma vez a DF
	cout << " " << endl;
	cout << "--------- Trabalho de Construcao de Software Grafico 3D - Implementacao OpenGL - Leonardo Ribeiro Schaedler ---------" << endl;
	cout << " " << endl;
	cout << "Aperte TAB a qualquer momento para mostrar novamente essas instrucoes" << endl;
	cout << "Page UP, Page Down, Home e End rotacionam a cena" << endl;
	cout << "Utilize as setas direcionais ou do Numpad  e as teclas '<' e '>' para mover a camera pela cena " << endl;
	cout << "Clique esquerdo do mouse - zoom in" << endl;
	cout << "Clique direito do mouse - zoom out" << endl;
	cout << "Pressione a tecla 'o' para entrar em modo de projecao ortogonal e 'p' para projecao perspectiva" << endl;
	cout << "Pressione a tecla 'a' para ligar a primeira luz, 's' para desliga-la. Aperte 'd' para ligar a segunda luz e 'f' para desliga-la" << endl;
	cout << "Pressione de 1-8 para ligar/ desligar a renderizacao dos objetos" << endl;
	cout << "Pressione de F1-F8 para selecionar o objeto desejado, para parar de selecionar um objeto aperte a tecla Insert" << endl;
	cout << "	- Com algum objeto ja selecionado, utilize as setas direcionais ou do Numpad e as teclas '<' e '>' para move-lo na cena" << endl;
	cout << "	- Com algum objeto ja selecionado, utilize a tecla 'c' para aumentar o tamanho dele e a tecla 'v' para diminuir" << endl;
	cout << "	- Com algum objeto ja selecionado, utilize as teclas r, g, b para aumentar a intensidade das cores" << endl;
	cout << "	- Com algum objeto ja selecionado, utilize as teclas t, h, n para diminuir a intensidade das cores" << endl;
	cout << "	- As teclas r e t mudam a cor vermelha, g e h mudam a cor verde e b e n mudam a azul" << endl;
	cout << "		- Caso nenhum objeto esteja selecionado, as alteracoes de cor serao aplicadas ao fundo da cena" << endl;
	cout << "Abaixo um log de suas acoes:" << endl;
	cout << " " << endl;
	glutInit(&argc, argv);                         // Inicializa GLUT
	glutInitDisplayMode(GLUT_DOUBLE);             // Habilita modo double buffered 
	glutInitWindowSize(1280, 720);               // Configura o tamanho inicial da janela
	glutInitWindowPosition(50, 50);             // Posiciona a janela no topo esquerdo da tela
	glutCreateWindow(title);                   // Cria uma janela com o título dado
	glutDisplayFunc(render);                  // Cuida do registro da callback para evento de re-paint da janela
	glutReshapeFunc(reshape);                // Cuida do registro da callback para evento de re-size da janela
	glutKeyboardFunc(keyboard);             // Cuida do callback de eventos do teclado
	glutSpecialFunc(processSpecialKeys);   // Cuida do callback de eventos do teclado especiais (setas por exemplo)
	glutMouseFunc(mouse);	              // Cuida do callback de eventos do mouse
	initGL();							 // Função de inicialização da OpenGL
	glutMainLoop();						// Loop de eventos
	return 0;
}