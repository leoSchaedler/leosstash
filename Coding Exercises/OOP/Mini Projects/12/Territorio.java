import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Territorio extends JPanel {


 private JFrame frame;
 private static final int ALTURA_BARRA_TITULO = 20;
 private int larguraJanela;
 private int alturaJanela;
 private static int maxSpawnSeres;
 private int minimoSeresNoTerritorio;
 private int ritmo = 1;
 private B jogador;
 private int pontosMaximos = 1000;
 private ArrayList<A> seres;
 private boolean jogando = true;
 private Jogador player;
 private Jogador joga = null;;

 public Territorio(String nome) {

 seres = new ArrayList<A>();
 jogador = new B(this,Color.BLUE);
 KeyListener listener = new LeitorSetas(jogador);
 addKeyListener(listener);
 setFocusable(true);


	Object[] options = { "Ok", "Sair","Pontuação Anterior"};
	Object a = JOptionPane.showOptionDialog(null, "Bem-vindo! \n Bouncy Balls e um jogo arcade " +
					"em que seu objetivo e consumir a maior quantidade de inimigos no tempo escolhido. \n A seguir algumas opcoes " +
					"serao mostradas para configuracao do jogo, boa sorte e se divirta! :)", "Bem-vindo",
			 JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			 null, options, options[0]);
	 String s = JOptionPane.showInputDialog("Qual o seu nome?");
	 player = new Jogador(s);

	 switch(a.toString()) {
		 case "0":
			 break;
		 case "1":
			 Runtime.getRuntime().exit(0);
			 break;
		 case "2":
			 try {
			 String x = JOptionPane.showInputDialog("Nome:");
		
			joga = player.abrir(x + ".jogador");
			JOptionPane.showMessageDialog(null, joga.toString());
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Jogador não encontrado");
		}
			 break;

	 }
 	Object[] resolucaoDoJogo = { "640 x 480 4:3", "800 x 600 4:3" ,"1280 x 720 16:9", "1600 x 900 16:9"};
 	Object x = JOptionPane.showOptionDialog(null, "Escolha o tamanho da tela, impacta diretamente em sua experiencia com o jogo.", "Setup",
		 JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		 null, resolucaoDoJogo , resolucaoDoJogo [0]);
	
	switch(x.toString()) {
	case "0":
		larguraJanela = 640;
		alturaJanela = 480;
		break;

	case "1":
		larguraJanela = 800;
		alturaJanela = 600;
		break;

	case "2":
		larguraJanela = 1280;
		alturaJanela = 720;
		break;

	case "3" :
		larguraJanela = 1600;
		alturaJanela = 900;
		break;
	}

	Object[] ritmoDoJogo = { "Devagar", "Mediano", "Rapido", "Frenetico"};
	Object y = JOptionPane.showOptionDialog(null, "Escolha o ritmo do jogo, impacta diretamente em sua experiencia com o jogo.", "Setup",
		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		null, ritmoDoJogo, ritmoDoJogo[0]);

	 switch(y.toString()) {
		 case "0":
		 	ritmo = 1;
			 break;

		 case "1":
			 ritmo = 2;
			 break;

		 case "2":
			 ritmo = 3;
			 break;

		 case "3" :
			 ritmo = 4;
			 break;
	 }

	Object[] inimigosNoJogo = { "Poucos", "Mediano", "Muitos", "Realmente muitos"};
	Object z = JOptionPane.showOptionDialog(null, "Escolha a quantidade de inimigos na tela, impacta diretamente em sua experiencia com o jogo.", "Setup",
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			null, inimigosNoJogo, inimigosNoJogo[0]);

	 switch(z.toString()) {
		 case "0":
			 maxSpawnSeres = 30;
			 minimoSeresNoTerritorio = 5;
			 break;

		 case "1":
			 maxSpawnSeres = 50;
			 minimoSeresNoTerritorio = 10;
			 break;

		 case "2":
			 maxSpawnSeres = 80;
			 minimoSeresNoTerritorio =20;
			 break;

		 case "3" :
			 maxSpawnSeres = 200;
			 minimoSeresNoTerritorio = 30;
			 break;
	 }

	Object[] tempoDoJogo = { "25 pts.", "50 pts.", "100 pts.", "1000 pts."};
	Object xx = JOptionPane.showOptionDialog(null, "Escolha a quantidade maxima de pontos, impacta diretamente em sua experiencia com o jogo.", "Setup",
			 JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			 null, tempoDoJogo, tempoDoJogo[0]);

	 switch(xx.toString()) {
		 case "0":
			 pontosMaximos = 25;
			 break;

		 case "1":
			 pontosMaximos = 50;
			 break;

		 case "2":
			 pontosMaximos = 100;
			 break;

		 case "3" :
			 pontosMaximos = 1000;
			 break;
	 }


 frame = new JFrame(nome); // cria um frame
 frame.add(this); // insere o território no frame
 frame.setSize(larguraJanela, alturaJanela + ALTURA_BARRA_TITULO ); // define as dimensões do frame
 frame.setVisible(true); // torna o frame visível
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // define como o frame é fechado


 }


 public void paint(Graphics g) {
	 super.paint(g);
	 Graphics2D g2d = (Graphics2D) g; // por motivos de compatibilidade com a AWT
	 g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			 RenderingHints.VALUE_ANTIALIAS_ON);
	 g2d.setColor(Color.BLACK); // define a cor em uso
	 g2d.setFont(new Font("Verdana", Font.BOLD, 20)); // define a fonte em uso
	 g2d.drawString("Pontuacao: " + String.valueOf(jogador.getPontuacao()), 10, 30);
	 if (jogador != null) jogador.paint(g);
	 for (A s: seres) s.paint(g);
	}
 

 public void jogar() {

     try {
         Thread.sleep(1000);
     }
     catch (Exception e) {
         e.printStackTrace();
     }

     criar_seres();

     while (jogando) {
         for (A s: seres) s.mover();
         verificar_colisoes_entre_seres();
         verificar_se_jogador_comeu();
         verificar_quantidade_de_seres();
         repaint();
         if(jogador.getPontuacao() == pontosMaximos)
        	 jogando = false;
         try {
             Thread.sleep(13);
         }
         catch ( Exception e )
         {
             e.printStackTrace();
         }
     }
     try {
    	 player.setPontuacao(jogador.getPontuacao());
		player.salvar(player.getNome() + ".jogador");
	} catch (IOException e) {
		e.printStackTrace();
	}
     game_over();
 }
	 
	 private void game_over() {
		 JOptionPane.showMessageDialog(this, "Parabens, voce venceu! \n Sua pontuacao foi: " + (jogador.getPontuacao()), "Fim de Jogo", JOptionPane.PLAIN_MESSAGE);
		
			
			
	 }
	 
	 
	 private void criar_seres() {
 	 	for (int j = 0; j < maxSpawnSeres; j++) {
			switch(ritmo) {
				case 1:
					A ser = new A(this, Color.RED);
					seres.add(ser);
					break;

				case 2:
					A ser1 = new A(this, Color.RED);
					A ser2 = new A(this, Color.RED);
					seres.add(ser1);
					seres.add(ser2);
					break;

				case 3:
					A ser3 = new A(this, Color.RED);
					A ser4 = new A(this, Color.RED);
					A ser5 = new A(this, Color.RED);
					seres.add(ser3);
					seres.add(ser4);
					seres.add(ser5);
					break;

				case 4:
					A ser6 = new A(this, Color.RED);
					A ser7 = new A(this, Color.RED);
					A ser8 = new A(this, Color.RED);
					A ser9 = new A(this, Color.RED);
					seres.add(ser6);
					seres.add(ser7);
					seres.add(ser8);
					seres.add(ser9);
					break;
			}

 	 	}

	 }

	private void verificar_quantidade_de_seres() {
		if(seres.size() < minimoSeresNoTerritorio)
			criar_seres();
	}


	    private void verificar_colisoes_entre_seres() {
	        boolean colisao_detectada = true;
	        while (colisao_detectada) {
	            colisao_detectada = false;
	            for (int i = 0; i < this.seres.size(); ++i) {
	                if (this.seres.get(i).nao_engolido()) {
	                    for (int j = i + 1; j < this.seres.size(); ++j) {
	                        if (this.seres.get(j).nao_engolido() && this.seres.get(i).em_colisao(this.seres.get(j))) {
	                            this.seres.get(i).colidir(this.seres.get(j));
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
	        while (i < seres.size()) {
	            if (seres.get(i).engolido()) {
	                seres.remove(i);
	            }
	            else
	            {
	                i++;
	            }
	        }
	    }
	    
	    private void verificar_se_jogador_comeu() {
	        if (this.jogando) {
	            boolean engoliu_algum = false;
	            for (final A s2 : this.seres) {
	   
	                if (this.jogador.engole(s2)) {
	                    s2.marcar_engolido();
	                    engoliu_algum = true;
	                }
	            }
	            if (engoliu_algum) {
	                this.remover_engolidos();
	            }
	    	}
 		}

}
 