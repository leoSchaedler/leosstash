import javax.swing.*;
import java.util.ArrayList;


public class Operations {


    public Operations() {
    }

    public void mediaArray(int[] x) {
        int acc = 0;
        for (int i = 0; i < x.length; i++) {
            acc = acc + x[i];
        }
        System.out.println(acc / x.length);
    }

    public int modaArray(int[] x) {
        int freq = 0;
        int aux = 0;
        int moda = 0;


        for (int i = 0; i < x.length; i++) {
            freq = 0;

            for (int j = 0; j < x.length; j++) {
                if (x[i] == x[j])
                    freq++;

            }
            if (freq > aux) {
                aux = freq;
                moda = x[i];
            } else if (freq == aux && x[i] != moda) {
                System.out.print("Não existe moda unica");
                return 0;

            }

        }
        return moda;
    }
}


