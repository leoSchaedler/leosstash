package services;

import models.Empresa;

public class LoggerAccessService {

    private final Empresa empresa;

    public LoggerAccessService(Empresa empresa) {
        this.empresa = empresa;
    }

    private void logDefault(String symbol, String label) {
        System.out.printf
                ("%s %s [%s] %s%n", symbol, empresa.getNomeFuncionario(), empresa.getRazaoSocial(), label);
    }

    public void logBeginAccess() {
        logDefault("+", "acessou");
    }

    public void logTryAccess() {
        logDefault("", "tentando acessar");
    }

    public void logFinishAccess() {
        logDefault("-", "terminou acesso");
    }

}
