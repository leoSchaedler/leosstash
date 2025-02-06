package Components;

public class InvalidAddres extends Exception {
    private final int Addres;

    public InvalidAddres(int Addres) {
        this.Addres = Addres;
    }

    @Override
    public String toString() {
        return "Endereço " + Addres + " é inválido!";
    }
}
