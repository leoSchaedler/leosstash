import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.Iterator;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.Component;
import javax.swing.JFrame;
import java.util.ArrayList;
import javax.swing.JPanel;


public class Territorio extends JPanel
{
    private static final int X_MAXIMO_INICIAL = 400;
    private static final int Y_MAXIMO_INICIAL = 300;
    private static int ALTURA_BARRA_TITULO;
    private static int TEMPO_UM_PASSO;
    private static int MINIMO_SERES;
    private static int MAXIMO_GERACOES;
    private static int TAMANHO_MEIA_GERACAO_INICIAL;
    private static int TAMANHO_MEIA_GERACAO_NOVA;
    private static int TEMPO_MAXIMO_DE_OCIOSIDADE;
    private int tempo_sem_engolir;
    private int numero_geracao;
    private ArrayList<Ser> seres;
    private Racional racional;
    private boolean jogando;
    private JFrame frame;
    
    public Territorio(final String nome) {
        this.tempo_sem_engolir = 0;
        this.numero_geracao = 0;
        this.jogando = true;
        this.racional = new Racional(this, 400, 300);
        this.seres = new ArrayList<Ser>();
        final KeyListener listener = new LeitorSetas(this.racional);
        this.addKeyListener(listener);
        this.setFocusable(true);
        (this.frame = new JFrame(nome)).add(this);
        this.frame.setSize(400, 300 + Territorio.ALTURA_BARRA_TITULO);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(3);
    }
    
    private void criar_ser(final Color cor) {
        final Ser ser = new Ser(cor, this);
        this.seres.add(ser);
    }
    
    public void jogar() {
        try {
            Thread.sleep(1000L);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.criar_primeira_geracao();
        while (this.jogando) {
            this.verificar_ociosidade();
            for (final Ser s : this.seres) {
                s.mover();
            }
            this.verificar_colisoes_entre_seres();
            this.verificar_tamanhos_dos_seres();
            this.verificar_racional();
            this.verificar_minimo_de_seres();
            this.repaint();
            try {
                Thread.sleep(Territorio.TEMPO_UM_PASSO);
                this.tempo_sem_engolir += Territorio.TEMPO_UM_PASSO;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.gameOver();
    }
    
    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (final Ser s : this.seres) {
            s.paint(g);
        }
        if (this.racional != null) {
            this.racional.paint(g);
        }
        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", 1, 16));
        g2d.drawString("Gera\u00e7\u00e3o: " + String.valueOf(this.numero_geracao) + "/" + Territorio.MAXIMO_GERACOES, 10, 30);
        g2d.drawString("Ociosidade: " + String.valueOf((Territorio.TEMPO_MAXIMO_DE_OCIOSIDADE - this.tempo_sem_engolir) / 100), 10, 60);
    }
    
    private void verificar_colisoes_entre_seres() {
        boolean colisao_detectada = true;
        while (colisao_detectada) {
            colisao_detectada = false;
            for (int i = 0; i < this.seres.size(); ++i) {
                if (this.seres.get(i).nao_engolido()) {
                    for (int j = i + 1; j < this.seres.size(); ++j) {
                        if (this.seres.get(j).nao_engolido() && this.seres.get(i).em_colisao(this.seres.get(j))) {
                            this.seres.get(i).fundir(this.seres.get(j));
                            colisao_detectada = true;
                        }
                    }
                }
            }
            this.remover_engolidos();
        }
    }
    
    private void remover_engolidos() {
        int i = 0;
        while (i < this.seres.size()) {
            if (this.seres.get(i).engolido()) {
                this.seres.remove(i);
                this.tempo_sem_engolir = 0;
            }
            else {
                ++i;
            }
        }
    }
    
    private void verificar_tamanhos_dos_seres() {
        for (final Ser s : this.seres) {
            s.verificar_tamanho();
        }
        for (int quantidade_inicial = this.seres.size(), i = 0; i < quantidade_inicial; ++i) {
            if (this.seres.get(i).deve_fissionar()) {
                final Ser s2 = this.seres.get(i).fissionar();
                this.seres.add(s2);
            }
        }
    }
    
    private void verificar_racional() {
        for (final Ser s : this.seres) {
            if (s.engole(this.racional)) {
                this.jogando = false;
            }
        }
        if (this.jogando) {
            boolean engoliu_algum = false;
            for (final Ser s2 : this.seres) {
                if (this.racional.engole(s2)) {
                    s2.marcar_engolido();
                    engoliu_algum = true;
                }
            }
            if (engoliu_algum) {
                this.remover_engolidos();
            }
            else if (this.numero_geracao < Territorio.MAXIMO_GERACOES) {
                this.racional.aumentar_tempo_sem_engolir(Territorio.TEMPO_UM_PASSO);
                if (this.racional.morto_de_fome()) {
                    this.jogando = false;
                }
            }
            if (this.jogando && this.seres.size() < 2) {
                if (this.seres.size() == 1) {
                    if (this.seres.get(0).nao_fissionando() && this.seres.get(0).nao_engolivel()) {
                        this.racional.vencer();
                        this.jogando = false;
                    }
                }
                else {
                    this.racional.vencer();
                    this.jogando = false;
                }
            }
        }
    }
    
    private void gameOver() {
        String mensagem;
        if (this.racional.vencedor()) {
            mensagem = "Parab\u00e9ns! (" + this.racional.pontos() + " pontos)";
        }
        else {
            mensagem = "Voc\u00ea foi engolido! (" + this.racional.pontos() + " pontos)";
        }
        JOptionPane.showMessageDialog(this, mensagem, "Game Over", 0);
    }
    
    private void verificar_minimo_de_seres() {
        if (this.seres.size() < Territorio.MINIMO_SERES && this.numero_geracao < Territorio.MAXIMO_GERACOES) {
            this.nova_geracao();
        }
    }
    
    private void verificar_ociosidade() {
        if (this.tempo_sem_engolir >= Territorio.TEMPO_MAXIMO_DE_OCIOSIDADE) {
            if (this.numero_geracao < Territorio.MAXIMO_GERACOES) {
                this.tempo_sem_engolir = 0;
                this.nova_geracao();
            }
            else {
                this.jogando = false;
                this.racional.vencer();
            }
        }
    }
    
    private void criar_primeira_geracao() {
        this.numero_geracao = 1;
        for (int i = 0; i < Territorio.TAMANHO_MEIA_GERACAO_INICIAL; ++i) {
            this.criar_ser(Color.BLUE);
        }
        for (int i = 0; i < Territorio.TAMANHO_MEIA_GERACAO_INICIAL; ++i) {
            this.criar_ser(Color.YELLOW);
        }
    }
    
    private void nova_geracao() {
        ++this.numero_geracao;
        for (int i = 0; i < Territorio.TAMANHO_MEIA_GERACAO_NOVA; ++i) {
            this.criar_ser(Color.BLUE);
        }
        for (int i = 0; i < Territorio.TAMANHO_MEIA_GERACAO_NOVA; ++i) {
            this.criar_ser(Color.YELLOW);
        }
    }
    
    static {
        Territorio.ALTURA_BARRA_TITULO = 20;
        Territorio.TEMPO_UM_PASSO = 20;
        Territorio.MINIMO_SERES = 3;
        Territorio.MAXIMO_GERACOES = 3;
        Territorio.TAMANHO_MEIA_GERACAO_INICIAL = 4;
        Territorio.TAMANHO_MEIA_GERACAO_NOVA = 3;
        Territorio.TEMPO_MAXIMO_DE_OCIOSIDADE = 10000;
    }
}
