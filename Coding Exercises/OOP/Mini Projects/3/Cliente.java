public class Cliente {

    private String nome;
    private ContaCorrente contacorrente;

    public Cliente (String nome) {
        this.nome = nome;
    }

    public void ligar(int numero, double saldo) {

        contacorrente = new ContaCorrente(numero, saldo);
    }

    public void depositar(double valor) {
        contacorrente.depositar(valor);

    }

    public void retirar(double valor) {
        contacorrente.retirar(valor);

    }

    public void imprimir() {
        System.out.printf ("%s", nome);
        contacorrente.imprimir();
        System.out.println();

    }

}
