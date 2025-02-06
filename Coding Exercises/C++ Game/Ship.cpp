#include "GameManager.h"
#include "Ship.h"

#include "Shot.h"
#include "SuperShot.h"

Ship::Ship() : AbstractGO("jogador.png")
{
}

void Ship::setup(ofVec2f pos)
{
	AbstractGO::setup(pos);
	debug.setup(255, 0, 0, pos);
}

void Ship::update(float s)
{
	//Move
	float speed = 0;
	if (pos.x >= 100 && (ofGetKeyPressed('a') || ofGetKeyPressed('A') || ofGetKeyPressed(OF_KEY_LEFT)))
	{
		speed = -300;
	}
	else if (pos.x < 900 && (ofGetKeyPressed('d') || ofGetKeyPressed('D') || ofGetKeyPressed(OF_KEY_RIGHT)))
	{
		speed = 300;
	}
	pos.x += speed * s;

	//Tiro
	interval += s;
	if (ofGetKeyPressed(' ') && interval > 0.3)
	{
		interval = 0;
		auto shot = std::make_shared<Shot>();
		shot->setup(pos);
		MANAGER.add(shot);
	}

	//Super Tiro
	superShotInterval += s;
	if ((ofGetKeyPressed('f') || ofGetKeyPressed('F')) && superShotInterval > 1.0)
	{
		superShotInterval = 0;
		auto supershot = std::make_shared<SuperShot>();
		supershot->setup(pos);
		MANAGER.add(supershot);
	}

	debug.mouseTest(getRect());
}

void Ship::draw() const
{
	debug.paint(getRect());

	AbstractGO::draw();
}
