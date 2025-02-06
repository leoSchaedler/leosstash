public class XXX {
    public static void main(String[] args) {
        /*long t0 = System.nanoTime();
        int c0 = 0;
        while (c0 < 5000000) {
            System.out.print("x");
            c0 += 1;
        }
        long t1 = System.nanoTime() - t0;
        System.out.println(t1/1e9);
        long t2 = System.nanoTime();
        for(int i=0; i<5000000; ++i) {
            System.out.print("x");
        }
        long t3 = System.nanoTime() - t2;
        System.out.println(t1/1e9);
        System.out.println(t3/1e9);*/
        long t0  = System.nanoTime();
        double resultado = 1;
        for(long i =1;i<5e9; i++){
            resultado += Math.pow(-1,i)/(2*i+1);
        }
        System.out.println(4*resultado);

        long o = System.nanoTime() -t0;
        System.out.println(o/1e9);
    }
}
