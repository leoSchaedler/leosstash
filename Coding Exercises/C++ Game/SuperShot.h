#pragma once

#include "AbstractGO.h"
#include "DebugSquare.h";

class Alien;

class SuperShot : public AbstractGO
{
private:
	std::shared_ptr<Alien> mira;

	DebugSquare debug;

public:
	SuperShot();
	virtual void setup(ofVec2f pos) override;
	virtual void update(float s) override;
	virtual void draw() const override;
	virtual void hit(const std::shared_ptr<GameObject> other) override;
	bool finished() const override;
};

