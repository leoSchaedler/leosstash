#pragma once

#include "ofMain.h";
#include "GameObject.h";

class AbstractGO :public GameObject
{
protected:
	string imgName;
	ofVec2f pos;
	ofImage img;
	bool dead;

	float imgH;
	float imgW;

	virtual void kill();
public:
	AbstractGO(const string& imgName);

	//Proibindo cópia
	AbstractGO(const AbstractGO&) = delete;
	AbstractGO& operator = (const AbstractGO&) = delete;

	virtual void setup(ofVec2f pos);
	virtual void update(float s);
	virtual void draw() const;
	virtual bool finished() const;

	virtual ofVec2f getPos() const;
	virtual ofRectangle getRect() const;

	virtual void hit(const std::shared_ptr<GameObject> other);


	virtual ~AbstractGO();

};

