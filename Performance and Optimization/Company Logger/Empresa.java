package models;

import services.LoggerAccessService;
import services.RandomTimerService;


public abstract class Empresa extends Thread {

    protected final LoggerAccessService loggerAccessService;

    protected final String razaoSocial;

    protected final String nomeFuncionario;


    public Empresa(String razaoSocial, String nomeFuncionario) {
        super(String.format("%s-%s", razaoSocial, nomeFuncionario));
        this.razaoSocial = razaoSocial;
        this.nomeFuncionario = nomeFuncionario;
        this.loggerAccessService = new LoggerAccessService(this);
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }


    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    protected void esperando() throws InterruptedException {
        Thread.sleep(RandomTimerService.getRandomTimeWaitGenerator());
    }

    protected void processando() throws InterruptedException {
        Thread.sleep(RandomTimerService.getRandomTimeProcessGenerator());
    }

    protected abstract void entrada();

    protected abstract void saida();

    @Override
    public void run() {
        try {
            esperando();

            loggerAccessService.logTryAccess();

            entrada();

            loggerAccessService.logBeginAccess();

            processando();

            saida();

            loggerAccessService.logFinishAccess();
        } catch (InterruptedException ignored) {
        }
    }

}
