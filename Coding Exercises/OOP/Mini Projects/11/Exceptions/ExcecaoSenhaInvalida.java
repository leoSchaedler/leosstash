package Exceptions;

public class ExcecaoSenhaInvalida extends ExcecaoAutenticacao {

    public ExcecaoSenhaInvalida(String msg) {
        super(msg);
    }

    public ExcecaoSenhaInvalida() {
        super();
    }
}
