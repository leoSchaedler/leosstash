import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class LeitorSetas implements KeyListener
{
    private Racional racional;
    
    public LeitorSetas(final Racional racional) {
        this.racional = racional;
    }
    
    @Override
    public void keyTyped(final KeyEvent e) {
    }
    
    @Override
    public void keyPressed(final KeyEvent e) {
        this.racional.keyPressed(e);
    }
    
    @Override
    public void keyReleased(final KeyEvent e) {
    }
}
