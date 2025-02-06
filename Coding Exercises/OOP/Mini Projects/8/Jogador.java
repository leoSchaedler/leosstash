public class Jogador {

    private String nome;
    protected  int score;

    public Jogador(String nome,int score){
        this.nome = nome;
        this.score = score;
    }

    public void imprimir(){
        System.out.println(nome);
        System.out.println(score);

    }
}
