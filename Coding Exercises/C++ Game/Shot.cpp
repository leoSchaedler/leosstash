#include "Shot.h"
#include "Alien.h";

Shot::Shot() : AbstractGO("bullet.png")
{
}

void Shot::setup(ofVec2f pos)
{
	AbstractGO::setup(pos);
	debug.setup(255, 0, 0, pos);
}

void Shot::update(float s)
{
	this->pos.y -= 500.0f * s;
	if (pos.y < 0)
	{
		kill();
	}

	debug.mouseTest(getRect());
}

void Shot::draw() const
{

	debug.paint(getRect());

	AbstractGO::draw();
}

void Shot::hit(const std::shared_ptr<GameObject> other)
{
	auto alien = std::dynamic_pointer_cast<Alien>(other);
	if (alien)
	{
		kill();
	}
}

bool Shot::finished() const
{
	if (dead)
	{
		return dead;
	}
	else 
	{
		return pos.y < 0;
	}
}