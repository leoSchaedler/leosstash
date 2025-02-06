import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import javax.swing.JComponent;


public class Ser extends JComponent
{
    private static final int RAIO_INICIAL = 5;
    private static final int FATOR_MAXIMO_DE_AUMENTO_LINEAR = 3;
    private static final int RAIO_MAXIMO = 15;
    private static final int MAXIMO_CONTADOR_FISSAO = 80;
    private static final Random gerador_aleatorio;
    private int x;
    private int y;
    private int raio;
    private Color cor;
    private Territorio territorio;
    private boolean movendo_para_direita;
    private boolean movendo_para_baixo;
    private boolean engolido;
    private boolean fissionando;
    private int contador_fissao;
    
    public Ser(final Color cor, final Territorio territorio) {
        this.cor = cor;
        this.territorio = territorio;
        this.raio = 5;
        this.engolido = false;
        this.fissionando = false;
        this.contador_fissao = 0;
        final int max_x = territorio.getWidth();
        final int max_y = territorio.getHeight();
        final int altura;
        final int largura = altura = this.raio * 2;
        this.x = Ser.gerador_aleatorio.nextInt(max_x - largura + 1);
        this.y = Ser.gerador_aleatorio.nextInt(max_y - altura + 1);
        final double borda = Math.random();
        if (borda < 0.25) {
            this.y = 0;
            this.movendo_para_baixo = true;
            this.movendo_para_direita = (Math.random() < 0.5);
        }
        else if (borda < 0.5) {
            this.y = max_y - altura;
            this.movendo_para_baixo = false;
            this.movendo_para_direita = (Math.random() < 0.5);
        }
        else if (borda < 0.75) {
            this.x = 0;
            this.movendo_para_direita = true;
            this.movendo_para_baixo = (Math.random() < 0.5);
        }
        else {
            this.x = max_x - largura;
            this.movendo_para_direita = false;
            this.movendo_para_baixo = (Math.random() < 0.5);
        }
    }
    
    public Ser() {
    }
    
    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(this.cor);
        int altura;
        int largura = altura = this.raio * 2;
        g2d.fillOval(this.x, this.y, largura, altura);
        if (this.fissionando) {
            g2d.setColor(Color.RED);
            final int d = this.raio * (80 - this.contador_fissao) / 80;
            largura = (altura = (this.raio - d) * 2);
            g2d.fillOval(this.x + d, this.y + d, largura, altura);
        }
    }
    
    public void mover() {
        final int max_x = this.territorio.getWidth();
        final int max_y = this.territorio.getHeight();
        final int altura;
        final int largura = altura = this.raio * 2;
        if (this.movendo_para_direita) {
            ++this.x;
            if (this.x > max_x - largura) {
                this.x = max_x - largura;
                this.movendo_para_direita = false;
            }
        }
        else {
            --this.x;
            if (this.x < 0) {
                this.x = 0;
                this.movendo_para_direita = true;
            }
        }
        if (this.movendo_para_baixo) {
            ++this.y;
            if (this.y > max_y - altura) {
                this.y = max_y - altura;
                this.movendo_para_baixo = false;
            }
        }
        else {
            --this.y;
            if (this.y < 0) {
                this.y = 0;
                this.movendo_para_baixo = true;
            }
        }
    }
    
    public boolean engolido() {
        return this.engolido;
    }
    
    public boolean nao_engolido() {
        return !this.engolido;
    }
    
    public void marcar_engolido() {
        this.engolido = true;
    }
    
    public boolean em_colisao(final Ser outro_ser) {
        boolean colisao = false;
        final int x1 = this.x + this.raio;
        final int y1 = this.y + this.raio;
        final int x2 = outro_ser.x + outro_ser.raio;
        final int y2 = outro_ser.y + outro_ser.raio;
        final double distancia_entre_centros = Math.sqrt(Math.pow(x2 - x1, 2.0) + Math.pow(y2 - y1, 2.0));
        if (distancia_entre_centros <= this.raio + outro_ser.raio) {
            colisao = true;
        }
        return colisao;
    }
    
    public void fundir(final Ser outro_ser) {
        boolean este_engole = false;
        if (this.raio > outro_ser.raio) {
            este_engole = true;
        }
        else if (this.raio < outro_ser.raio) {
            este_engole = false;
        }
        else if (this.cor == outro_ser.cor) {
            este_engole = (Math.random() < 0.5);
        }
        else if (this.cor == Color.GREEN) {
            este_engole = true;
        }
        else if (outro_ser.cor == Color.GREEN) {
            este_engole = true;
        }
        else {
            este_engole = (Math.random() < 0.5);
            if (este_engole) {
                this.cor = Color.GREEN;
            }
            else {
                outro_ser.cor = Color.GREEN;
            }
        }
        if (este_engole) {
            this.engolir(outro_ser);
        }
        else {
            outro_ser.engolir(this);
        }
    }
    
    public void engolir(final Ser outro_ser) {
        this.raio += outro_ser.raio;
        outro_ser.marcar_engolido();
    }
    
    public void verificar_tamanho() {
        if (this.raio > 15) {
            this.fissionando = true;
            ++this.contador_fissao;
        }
    }
    
    public boolean deve_fissionar() {
        return this.contador_fissao == 80;
    }
    
    Ser fissionar() {
        this.raio /= 2;
        this.fissionando = false;
        this.contador_fissao = 0;
        final Ser s = new Ser();
        s.raio = this.raio;
        s.territorio = this.territorio;
        s.engolido = false;
        s.fissionando = false;
        s.contador_fissao = 0;
        s.movendo_para_baixo = !this.movendo_para_baixo;
        s.movendo_para_direita = !this.movendo_para_direita;
        final int max_x = this.territorio.getWidth();
        final int max_y = this.territorio.getHeight();
        final int altura;
        final int largura = altura = this.raio * 2;
        if (this.movendo_para_direita) {
            s.x = this.x - largura;
            if (s.x < 0) {
                s.x = max_x - largura;
            }
        }
        else {
            s.x = this.x + largura;
            if (s.x > max_x - largura) {
                s.x = 0;
            }
        }
        if (this.movendo_para_baixo) {
            s.y = this.y - altura;
            if (s.y < 0) {
                s.y = max_y - altura;
            }
        }
        else {
            s.y = this.y + altura;
            if (s.y > max_y - altura) {
                s.y = 0;
            }
        }
        if (this.cor == Color.GREEN) {
            this.cor = Color.BLUE;
            s.cor = Color.YELLOW;
        }
        else {
            s.cor = Color.GREEN;
        }
        return s;
    }
    
    @Override
    public Rectangle getBounds() {
        final int diametro = this.raio * 2;
        return new Rectangle(this.x, this.y, diametro, diametro);
    }
    
    public boolean engole(final Racional racional) {
        boolean resultado = false;
        if (racional.getBounds().intersects(this.getBounds()) && !this.engolivel()) {
            resultado = true;
        }
        return resultado;
    }
    
    public boolean engolivel() {
        return this.cor == Color.GREEN;
    }
    
    public boolean nao_engolivel() {
        return this.cor != Color.GREEN;
    }
    
    public int pontos() {
        return this.raio * this.raio / 10;
    }
    
    public boolean nao_fissionando() {
        return !this.fissionando;
    }
    
    static {
        gerador_aleatorio = new Random();
    }
}
