import java.awt.Color;
import java.awt.*;
import javax.swing.JComponent;


public abstract class C extends JComponent{
	
	 protected Territorio territorio;
	 protected Color cor;
	 protected int x = 40;
	 protected int y = 50;

	 
	 
	 public C(Territorio territorio, Color cor) {
	 this.territorio = territorio;
	 this.cor = cor;
	 }

	public abstract void paint(Graphics g);

	public abstract Rectangle getBounds();

	}
	    
	    
	    
	    


