#include "DebugSquare.h"

void DebugSquare::setup(int r, int g, int b, ofVec2f pos)
{
	this->r = r;
	this->g = g;
	this->b = b;
	this->pos = pos;
	this->click = false;
}

void DebugSquare::paint(ofRectangle rect) const
{
	if (click)
	{
		ofSetColor(this->r, this->g, this->b);
		ofDrawRectangle(rect);
	}
}

bool DebugSquare::clicked()
{
	this->click = !click;
	return click;
}

void DebugSquare::mouseTest(ofRectangle rect)
{
	ofRectangle mouse(ofGetMouseX(), ofGetMouseY(), 10, 10);
	if ((ofGetKeyPressed('q') || ofGetKeyPressed('Q') || ofGetKeyPressed(OF_KEY_LEFT_COMMAND)) && rect.inside(mouse))
	{
		clicked();
	}
}
