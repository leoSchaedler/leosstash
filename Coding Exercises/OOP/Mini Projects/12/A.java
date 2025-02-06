import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;


public class A extends C{


	    private static final Random gerador_aleatorio = new Random();
		private boolean movendo_para_direita;
	    private boolean movendo_para_baixo;
	    private boolean engolido;
	    private final int diametro = 10;


	    public A(Territorio territorio,Color cor) {
		super(territorio, cor);
		engolido = false;

		int max_x = territorio.getWidth();
		int max_y = territorio.getHeight();
		x = gerador_aleatorio.nextInt(max_x - diametro + 1);
		y = gerador_aleatorio.nextInt(max_y - diametro + 1);
	
	    double borda = Math.random();
        if (borda < 0.25)
        {
            // borda superior
            y = 0;
            movendo_para_baixo = true;
            movendo_para_direita = (Math.random() < 0.5);
        }
        else
        if (borda < 0.5)
        {
            // borda inferior
            y = max_y - diametro;
            movendo_para_baixo = false;
            movendo_para_direita = (Math.random() < 0.5);
        }
        else
        if (borda < 0.75)
        {
            // borda esquerda
            x = 0;
            movendo_para_direita = true;
            movendo_para_baixo = (Math.random() < 0.5);
        }
        else
        {
            // borda direita
            x = max_x - diametro;
            movendo_para_direita = false;
            movendo_para_baixo = (Math.random() < 0.5);
        }
    }
	    public void paint(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g; // compatibilidade com a AWT
	        g2d.setColor(Color.RED);
	        g2d.fillOval( x, y, diametro, diametro);
	    }

	    public void mover()
	    {
	        int max_x = territorio.getWidth();
	        int max_y = territorio.getHeight();

	        if (movendo_para_direita)
	        {
	            x++;
	            if (x > max_x - diametro)
	            {
	                x = max_x - diametro;
	                movendo_para_direita = false;
	            }
	        }
	        else // movendo para a esquerda
	        {
	            x--;
	            if (x < 0)
	            {
	                x = 0;
	                movendo_para_direita = true;
	            }
	        }
	        if (movendo_para_baixo)
	        {
	            y++;
	            if (y > max_y - diametro)
	            {
	                y = max_y - diametro;
	                movendo_para_baixo = false;
	            }
	        }
	        else // movendo para cima
	        {
	            y--;
	            if (y < 0)
	            {
	                y = 0;
	                movendo_para_baixo = true;
	            }
	        }
	    }

	    public boolean engolido() {
	        return engolido;
	    }

	    public boolean nao_engolido() {
	        return !engolido;
	    }

	    public void marcar_engolido() {
	        engolido = true;
	    }

	    public boolean em_colisao(A outro_ser) {
	        boolean colisao = false;
	        int x1 = this.x + this.diametro;
	        int y1 = this.y + this.diametro;
	        int x2 = outro_ser.x + this.diametro;
	        int y2 = outro_ser.y + this.diametro;
	        double distancia_entre_centros = Math.sqrt( Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2) );
	        if (distancia_entre_centros <= this.diametro + this.diametro)
	            colisao = true;
	        return colisao;
	    }

	    public void colidir(A outro_ser) {
			this.mover();
			outro_ser.mover();
	    }

	    public Rectangle getBounds() { return new Rectangle(x, y, diametro, diametro); }

				   
}


