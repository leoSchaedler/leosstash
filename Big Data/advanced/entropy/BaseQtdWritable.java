package advanced.entropy;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class BaseQtdWritable implements WritableComparable<BaseQtdWritable>{

    private String texto;
    private long qtd;

    public BaseQtdWritable(String texto, long qtd) {
        this.texto = texto;
        this.qtd = qtd;
    }

    public BaseQtdWritable() {
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public long getQtd() {
        return qtd;
    }

    public void setQtd(long qtd) {
        this.qtd = qtd;
    }


    @Override
    public int compareTo(BaseQtdWritable o) {
        return Integer.compare(this.hashCode(), o.hashCode());
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(texto);
        dataOutput.writeLong(qtd);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        texto = dataInput.readUTF();
        qtd = dataInput.readLong();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseQtdWritable that = (BaseQtdWritable) o;
        return qtd == that.qtd &&
                Objects.equals(texto, that.texto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(texto, qtd);
    }
}