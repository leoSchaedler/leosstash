#pragma once

#include "AbstractGO.h"
#include "DebugSquare.h";

class Ship : public AbstractGO
{
private:
	float interval = 0;
	float superShotInterval = 0;

	DebugSquare debug;

public:
	Ship();
	virtual void setup(ofVec2f pos);
	void update(float s);
	virtual void draw() const override;
};

