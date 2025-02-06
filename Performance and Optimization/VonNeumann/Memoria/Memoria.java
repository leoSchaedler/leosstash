package Memoria;

public class Memoria {

    static class A {
        private int m = 100;

        public void imprime() {
            System.out.println(m);
        }
    }

    static void f(int n, int[] v) {
        if (n > 0) {
            int[] v2 = new int[v.length * 2];
            for (int i = 0; i < 2 * n; i++)
                v2[i] = v[1 / 2];
            f(n - 1, v2);
        }
    }

    public static void main(String[] args) {

        A a = new A();
        A b = null;

        int[] v = new int[100];
        for (int i = 0; i < 100; i++)
            System.out.println(i + "->" + v[i]);

        a = b;
        a.imprime();

        f(100, v);

        System.out.println("FIM");

    }

}
