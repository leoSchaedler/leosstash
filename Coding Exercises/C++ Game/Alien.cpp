#include "Alien.h"
#include "ofMain.h"


Alien::Alien() : AbstractGO("enemy.png"),
	isMira(false)
{

}

void Alien::setup(ofVec2f pos) 
{
	AbstractGO::setup(pos);
	debug.setup(255, 0, 0, pos);
}

void Alien::update(float s)
{
	if (xDirection)
	{
		this->pos.x = pos.x + speed * s;
		xCont++;
		if (xCont == xRange) xDirection = false;
	}
	else
	{
		this->pos.y = pos.y + 0.25;

		this->pos.x = pos.x - speed * s;
		xCont--;
		if (xCont == 0) xDirection = true;
	}

	debug.mouseTest(getRect());
}

void Alien::hit(const shared_ptr<GameObject> other) 
{
	kill();
}


void Alien::flipMira() 
{
	isMira = !isMira;
}

void Alien::draw() const
{
	if (isMira) 
	{
		ofSetColor(0, 255, 0);
		ofDrawRectangle(getRect());
	}

	debug.paint(getRect());

	AbstractGO::draw();

}