import java.util.Arrays;

public class Pilha {

    private int topo = -1;
    private int[] dado;
    private int MAX;

    public Pilha(int max) {
        this.MAX=max;
        this.dado= new int[max];
    }
    public int[] underflowtest(Pilha p) {
        if(p.pilhavazia(p)==true) {
            return p.dado;
        }
        else {
            return dado;
        }
    }
    public boolean pilhavazia(Pilha p) {
        if(p.topo==-1) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean pilhacheia(int[]dado, int max) {
        if(dado.length==topo) {
            return true;
        }
        else {
            return false;
        }
    }
    public void empilhar(Pilha p, int n) {
        if(!pilhacheia(p.dado,p.MAX)) {
            p.topo++;
            p.dado[p.topo]=n;
            //p.retornardados(p);
        }
        else {
            System.out.println("Pilha cheia");
        }
    }
    public void desempilhar(Pilha p) {
        if(!p.pilhavazia(p)) {
            p.dado[p.topo]=0;
            p.topo--;
            //p.retornardados(p);
        }
        else {
            System.out.println("Pilha vazia");
        }
    }
    public void achartopo(Pilha p) {
        System.out.println("O topo e "+p.dado[p.topo]);
    }
    public void retornardados(Pilha p) {
        System.out.println( Arrays.toString(p.dado));
    }
    public void converte(int valor) {
        Pilha aa = new Pilha(100);
        System.out.println("Voce esta convertendo o valor "+valor);
        int temp=valor;
        int resto;
        while(temp!=0) {
            resto= temp%2;
            aa.empilhar(aa,resto);
            temp=(int)temp/2;
        }
        int[] resultado = new int[aa.topo+1];
        int salvartopoinicial = aa.topo;
        int i =0;
        while(i<salvartopoinicial) {
            resultado[i]= aa.dado[aa.topo];
            aa.desempilhar(aa);
            i++;
        }
        System.out.println(Arrays.toString(resultado));
    }
}
