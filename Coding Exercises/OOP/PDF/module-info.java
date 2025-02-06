public class Semaforo {


    int cor = 2;

    public void MudaProximaCor() {
        switch(cor) {
            case 2:
                cor = 0;
                break;
            case 0:
                cor = 1;
                break
            case 1:
                cor = 2;
                break;
        }
    }

    public void MudaCorFixa(int x) {
        if( x >= 0 && x <=3) {
            cor = x;
        }
        else {
            System.out.printIn("Valor Inválido")
        }
    }

    publix in MostraCor() {
        return cor;
    }
}