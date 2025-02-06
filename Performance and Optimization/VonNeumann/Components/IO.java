package Components;

import java.io.PrintStream;

public class IO {

    private final PrintStream out;

    public IO (PrintStream out) {
        this.out = out;
    }

    public void Output (String string){
        out.println(string);
    }
}
