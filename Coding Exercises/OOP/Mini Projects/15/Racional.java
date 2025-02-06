import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JComponent;


public class Racional extends JComponent
{
    private static final int STEP = 10;
    private static final int LARGURA = 20;
    private static final int ALTURA = 20;
    private static final int BONUS_VITORIA = 20;
    private static final int TEMPO_MAXIMO_SEM_ENGOLIR = 5000;
    private static final int PUNICAO_TEMPO_SEM_ENGOLIR = 1;
    private static final int LIMITE_PONTUACAO_NEGATIVA = -10;
    private Territorio territorio;
    private int x;
    private int y;
    private int pontuacao;
    private int tempo_sem_engolir;
    private boolean vencedor;
    
    public Racional(final Territorio territorio, final int max_x, final int max_y) {
        this.pontuacao = 0;
        this.tempo_sem_engolir = 0;
        this.vencedor = false;
        this.territorio = territorio;
        this.x = (max_x - 20) / 2;
        this.y = (max_y - 20) / 2;
    }
    
    @Override
    public void paint(final Graphics g) {
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.MAGENTA);
        g2d.fillRect(this.x, this.y, 20, 20);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Verdana", 1, 12));
        g2d.drawString(String.valueOf(this.pontos()), this.x + 2, this.y + 20 - 2);
    }
    
    public void keyPressed(final KeyEvent e) {
        final int max_x = this.territorio.getWidth() - 20;
        final int max_y = this.territorio.getHeight() - 20;
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
        return new Rectangle(this.x, this.y, 20, 20);
    }
    
    public boolean engole(final Ser ser) {
        boolean resultado = false;
        if (ser.getBounds().intersects(this.getBounds()) && ser.engolivel()) {
            this.pontuacao += ser.pontos();
            this.tempo_sem_engolir = 0;
            resultado = true;
        }
        return resultado;
    }
    
    public void vencer() {
        this.pontuacao += 20;
        this.vencedor = true;
    }
    
    public boolean vencedor() {
        return this.vencedor;
    }
    
    public int pontos() {
        return this.pontuacao;
    }
    
    public void aumentar_tempo_sem_engolir(final int tempo) {
        this.tempo_sem_engolir += tempo;
        if (this.tempo_sem_engolir >= 5000) {
            this.tempo_sem_engolir %= 5000;
            --this.pontuacao;
        }
    }
    
    public boolean morto_de_fome() {
        return this.pontuacao <= -10;
    }
}
