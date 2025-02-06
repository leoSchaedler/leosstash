#include <vector>

#include "SuperShot.h"
#include "Alien.h"
#include "GameManager.h"

SuperShot::SuperShot() : AbstractGO("SuperShot.png")
{
}

void SuperShot::setup(ofVec2f pos)
{
	AbstractGO::setup(pos);

	mira = MANAGER.getRandom<Alien>();
	if (mira) mira->flipMira();

	debug.setup(255, 0, 0, pos);
}

void SuperShot::update(float s)
{
	float speed = 50.0f;

	if (mira && mira->finished())
	{
		mira = nullptr;
	}

	if (mira)
	{
		//x
		if (this->pos.x < mira->getPos().x)
		{
			this->pos.x += speed * s;
		}
		else if (this->pos.x > mira->getPos().x)
		{
			this->pos.x -= speed * s;
		}
		//y
		if (this->pos.y < mira->getPos().y)
		{
			this->pos.y += speed * s;
		}
		else if (this->pos.y > mira->getPos().y)
		{
			this->pos.y -= speed * s;
		}
	}

	this->pos.y += -speed * s;
	if (pos.y < 0)
	{
		kill();
	}

	debug.mouseTest(getRect());
	
}

void SuperShot::draw() const
{
	debug.paint(getRect());

	AbstractGO::draw();
}

void SuperShot::hit(const std::shared_ptr<GameObject> other)
{
	const auto alien = dynamic_pointer_cast<Alien>(other);

	if (alien)
	{
		if (mira) mira->flipMira();
		kill();
	}
}

bool SuperShot::finished() const
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
