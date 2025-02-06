public class Senior extends Profissional {

    private double prêmio;

    public Senior(String nome, int score, double prêmio, double capital) {
        super(nome, score, capital);
        this.prêmio = prêmio;

    }

    public void imprimir() {
        super.imprimir();
        System.out.println(prêmio);
    }

    public void ganhar(int p){
        super.ganhar(p);
        prêmio = prêmio * 2;
    }

    public void perder(int p){
        super.ganhar(p);
        prêmio = prêmio / 2;
    }


}
