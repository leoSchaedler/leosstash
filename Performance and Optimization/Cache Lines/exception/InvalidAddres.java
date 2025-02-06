package exception;

import util.Address;

public class InvalidAddres extends Exception {
    private final int Addres;

    public InvalidAddres(int Address) {
        this.Addres = Address;
    }

    public InvalidAddres(Address Address) {
        this.Addres = Address.getR();
    }

    @Override
    public String toString() {
        return "Endereço " + Addres + " é inválido!";
    }
}
