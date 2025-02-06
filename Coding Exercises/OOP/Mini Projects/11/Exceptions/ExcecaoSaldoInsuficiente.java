package Exceptions;

public class ExcecaoSaldoInsuficiente extends Exception {

    public ExcecaoSaldoInsuficiente(String msg) {
        super(msg);
    }

    public ExcecaoSaldoInsuficiente() {
        super();
    }
}
