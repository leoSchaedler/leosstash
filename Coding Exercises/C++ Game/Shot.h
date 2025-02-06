#pragma once

#include "AbstractGO.h";
#include "DebugSquare.h";

class Shot : public AbstractGO
{
private:
	DebugSquare debug;

public:
	Shot();
	virtual void setup(ofVec2f pos);
	void update(float s) override;
	virtual void draw() const override;
	virtual void hit(const std::shared_ptr<GameObject> other) override;
	bool finished() const override;
};

