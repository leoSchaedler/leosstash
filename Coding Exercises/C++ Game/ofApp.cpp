#include "ofApp.h"
#include <iostream>

#include "Ship.h"
#include "Alien.h"
#include "Shot.h"
#include "GameManager.h"


//--------------------------------------------------------------
void ofApp::setup(){
	//background set
	this->background.load("background.jpg");
	this->background.setAnchorPercent(0, 0);
	this->background.resize(ofGetScreenWidth(), ofGetScreenHeight());

	int xShip = 500;
	int yShip = 730;

	auto ship = std::make_shared<Ship>();
	ship->setup(ofVec2f(xShip, yShip));
	MANAGER.add(ship);

	for (int i = 0; i < 8; i++)
	{
		for (int j = 0; j < 4; j++)
		{
			auto alien = std::make_shared<Alien>();
			alien->setup(ofVec2f(140 + 100 * i, 115 + 100 * j));
			MANAGER.add(alien);
		}
	}

	MANAGER.setup();
}

//--------------------------------------------------------------
void ofApp::update(){
	MANAGER.update();
}

//--------------------------------------------------------------
void ofApp::draw(){
	ofSetColor(255, 255, 255);
	this->background.draw(0, 0);
	MANAGER.draw();
}

//--------------------------------------------------------------
void ofApp::keyPressed(int key){

}

//--------------------------------------------------------------
void ofApp::keyReleased(int key){

}

//--------------------------------------------------------------
void ofApp::mouseMoved(int x, int y ){

}

//--------------------------------------------------------------
void ofApp::mouseDragged(int x, int y, int button){

}

//--------------------------------------------------------------
void ofApp::mousePressed(int x, int y, int button){

}

//--------------------------------------------------------------
void ofApp::mouseReleased(int x, int y, int button){

}

//--------------------------------------------------------------
void ofApp::mouseEntered(int x, int y){

}

//--------------------------------------------------------------
void ofApp::mouseExited(int x, int y){

}

//--------------------------------------------------------------
void ofApp::windowResized(int w, int h){

}

//--------------------------------------------------------------
void ofApp::gotMessage(ofMessage msg){

}

//--------------------------------------------------------------
void ofApp::dragEvent(ofDragInfo dragInfo){ 

}
