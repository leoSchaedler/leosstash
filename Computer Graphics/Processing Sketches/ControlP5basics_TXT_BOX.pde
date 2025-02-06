import controlP5.*;
ControlP5 cp5;

String var1="____";
String var2="____";
String var3="____";

void setup() {
  size(400, 400);
  
  cp5 = new ControlP5(this);
  // create a texfield
  cp5.addTextfield("textA", 100, 100, 100, 20);
  cp5.addTextfield("textB", 100, 200, 100, 20); 
}

void draw() {
  background(128);
  text("concatenacao: "+var3, 100,300);
  var3 =(var1+var2);
}

// for every change (a textfield event confirmed with a return) in textfield text?,
// function text? will be invoked (neste caso textA e textB)
public void textA(String theValue) {
  var1 = theValue;
}
public void textB(String theValue) {
  var2 = theValue;
}
