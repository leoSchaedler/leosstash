import java.awt.*;
import java.awt.event.KeyEvent;

public class B extends C{


	private int pontuacao = 0;
    private final int diametro = 40;


	public B(Territorio territorio, Color cor) { super(territorio, cor); }
	
	 public void paint(Graphics g) {
		 Graphics2D g2d = (Graphics2D) g; // compatibilidade com a AWT
		 g2d.setColor(cor);
		 g2d.fillOval(x, y, diametro, diametro); // desenha um círculo
		 }


	 public void keyPressed(final KeyEvent e) {
	        final int max_x = this.territorio.getWidth() - diametro;
	        final int max_y = this.territorio.getHeight() - diametro;
	        if (e.getKeyCode() == 37) {
	            this.x -= 10;
	            if (this.x < 0) {
	                this.x = 0;
	            }
	        }
	        else if (e.getKeyCode() == 39) {
	            this.x += 10;
	            if (this.x > max_x) {
	                this.x = max_x;
	            }
	        }
	        else if (e.getKeyCode() == 38) {
	            this.y -= 10;
	            if (this.y < 0) {
	                this.y = 0;
	            }
	        }
	        else if (e.getKeyCode() == 40) {
	            this.y += 10;
	            if (this.y > max_y) {
	                this.y = max_y;
	            }
	        }
	    }


    @Override
    public Rectangle getBounds() {
    return new Rectangle(this.x, this.y, diametro, diametro);
}

    public int getPontuacao() {
        return pontuacao;
    }

    public boolean engole(final A ser) {
    boolean resultado = false;
    if (ser.getBounds().intersects(this.getBounds()) ) {
        this.pontuacao ++;
        resultado = true;
        }
    
    
    return resultado;
    
    }




}
	
	


