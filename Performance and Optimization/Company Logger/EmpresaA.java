package models;

import util.CSC;

public class EmpresaA extends Empresa {


    public EmpresaA(String razaoSocial, String nomeFuncionario) {
        super(razaoSocial, nomeFuncionario);
    }

    @Override
    protected synchronized void entrada() {
        try {
            CSC.EAMutex.acquire();

            if (CSC.EACount == 0)
                CSC.empty.acquire();
            CSC.EACount++;

            CSC.EAMutex.release();

            CSC.EAMultiplex.acquire();
        } catch (InterruptedException ignored){

        }
    }

    @Override
    protected synchronized void saida() {
        try {
            CSC.EAMultiplex.release();

            CSC.EAMutex.acquire();
            CSC.EACount--;
            if (CSC.EACount == 0)
                CSC.empty.release();

            CSC.EAMutex.release();
        } catch (Exception ignored){

        }
    }

}
