import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LeitorSetas implements KeyListener { 
	
 private B ser;
 
 public LeitorSetas(B ser) {
 this.ser = ser;
 }

 @Override
 public void keyTyped(KeyEvent e) { }

 @Override
 public void keyPressed(KeyEvent e) {
 ser.keyPressed(e);
 }

 @Override
 public void keyReleased(KeyEvent e) { }
 
}