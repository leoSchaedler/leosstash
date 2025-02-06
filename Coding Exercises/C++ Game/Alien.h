#pragma once

#include "AbstractGO.h";
#include "DebugSquare.h";

class Alien : public AbstractGO
{
private:
	int xRange = 40;
	int speed = 30;
	bool xDirection = true;
	int xCont = 0;

	bool isMira;

	DebugSquare debug;

public:
	Alien();
	virtual void setup(ofVec2f pos);
	virtual void draw() const override;
	virtual void hit(const std::shared_ptr<GameObject> other) override;
	void update(float s);
	void flipMira();
};

