#pragma once

#include "ofMain.h";

class DebugSquare
{
private:
	//color
	int r;
	int g;
	int b;

	bool click;
	ofVec2f pos;

public:
	void setup(int r, int g, int b, ofVec2f pos);
	void paint(ofRectangle rect) const;
	bool clicked();
	void mouseTest(ofRectangle rect);
};

