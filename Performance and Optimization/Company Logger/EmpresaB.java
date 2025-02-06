package models;

import util.CSC;

public class EmpresaB extends Empresa {


    public EmpresaB(String razaoSocial, String nomeFuncionario) {
        super(razaoSocial, nomeFuncionario);
    }

    @Override
    protected synchronized void entrada() {
        try {
            CSC.EBMutex.acquire();

            if (CSC.EBCount == 0)
                CSC.empty.acquire();
            CSC.EBCount++;

            CSC.EBMutex.release();

            CSC.EBMultiplex.acquire();
        } catch (InterruptedException ignored) {
        }
    }

    @Override
    protected synchronized void saida() {
        try {
            CSC.EBMultiplex.release();

            CSC.EBMutex.acquire();
            CSC.EBCount--;
            if (CSC.EBCount == 0)
                CSC.empty.release();

            CSC.EBMutex.release();
        } catch (Exception ignored) {
        }
    }


}
