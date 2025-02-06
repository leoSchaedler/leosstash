import util.Address;

public class Testes {

    public static void main(String[] args) {

        /*
        w -> 101010
        r -> 0101010
        t -> 10101010101
         */

//        Address a = Address.createAddress(Integer.parseInt("101010101010101010101010", 2));


        //Address tests
        for (int i = 3; i < 1000; i+=64)
            Address.createMemoryTest(i);

        Address address = new Address(10560325, 64, 24);
        System.out.printf("value of 'w (6)': %s%n", address.getW());
        System.out.printf("value of 'r (7)': %s%n", address.getR());
        System.out.printf("value of 't (11)': %s%n", address.getT());

    }

}
