package util;

public class Address {
    /*
                24
    |-----------X-------------|
    00000000000 0000000 000000
                  R       W
                  128 LINHAS (2^7)
     */

    private final Integer K;

    private final Integer t;    //11b               -|
    private final Integer r;    //07b -> 128         |  24b
    private final Integer w;    //06b               -|

    private final Integer s;

    private final Integer fullAddress;

    public Address(Integer address, Integer cacheLineSize, Integer byteSize) {

        StringBuilder binaryAddress = new StringBuilder(Integer.toBinaryString(address));
        if (binaryAddress.length() < byteSize)
            while (binaryAddress.length() < byteSize)
                binaryAddress.insert(0, "0");

        Integer inBits = Integer.parseInt(binaryAddress.toString(), 2);

        this.fullAddress = address;
        this.K = cacheLineSize;
        this.w = extractW(inBits);
        this.r = extractR(inBits);
        this.t = extractT(inBits);
        this.s = extractS(inBits);
    }

    public Integer getR() {
        return r;
    }

    public Integer getW() {
        return w;
    }

    public Integer getT() {
        return t;
    }

    public Integer getS() {
        return s;
    }

    public Integer getFullAddress() {
        return fullAddress;
    }

    public Integer getK() {
        return K;
    }

    private Integer extractW(Integer requestInBits) {
        return requestInBits & (K - 1);
    }

    private Integer extractS(Integer requestInBits) {
        int aux = requestInBits & 0b111111111111111111000000;
        return aux >> 18;
    }

    private Integer extractR(Integer requestInBits) {
        int aux = requestInBits & 0b000000000001111111000000;
        return aux >> 6;
    }

    private Integer extractT(Integer requestInBits) {
        int aux = requestInBits & 0b111111111110000000000000;
        return aux >> 13;
    }

    public static void createMemoryTest(Integer address){
        Address a = new Address(address, 64, 24);
        System.out.println("Creating Address: " + address);
        System.out.printf("value of 'w (6)': %s%n", a.getW());
        System.out.printf("value of 'r (7)': %s%n", a.getR());
        System.out.printf("value of 't (11)': %s%n", a.getT());
        System.out.println();
    }

}
