#include "AbstractGO.h"

AbstractGO::AbstractGO(const string& imgName)
	: imgName(imgName), dead(false)
{
}

void AbstractGO::setup(ofVec2f pos)
{
	this->pos = pos;

	this->imgH = 90;
	this->imgW = 90;
	
	img.load(imgName);
	img.setAnchorPercent(0.5f, 0.5f);
	img.resize(imgW, imgH);
}

void AbstractGO::update(float s)
{
}

void AbstractGO::draw() const
{
	ofSetColor(255, 255, 255, 255);
	img.draw(pos);
}

ofVec2f AbstractGO::getPos() const {
	return pos;
}

ofRectangle AbstractGO::getRect() const
{
	float hw = img.getWidth() / 2.0f;
	float hh = img.getHeight() / 2.0f;

	ofRectangle rect(pos.x - hw, pos.y - hh, imgW, imgH);
	return rect;
}

bool AbstractGO::finished() const
{
	return dead;
}

void AbstractGO::kill()
{
	dead = true;
}

void AbstractGO::hit(const std::shared_ptr<GameObject> other)
{
}

AbstractGO::~AbstractGO()
{
}