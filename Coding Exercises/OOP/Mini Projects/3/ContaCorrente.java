public class ContaCorrente {

    private int numero;
    private double saldo;

    public ContaCorrente(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;

    }

    public void depositar(double valor) {
        saldo = saldo + valor;

    }

    public void retirar(double valor) {
        assert (valor <= saldo);
        saldo = saldo - valor;
    }


    public void imprimir() {
        System.out.printf("\n A conta %d possui %. 2f de saldo na conta corrente \n", numero, saldo);
        }
    }





