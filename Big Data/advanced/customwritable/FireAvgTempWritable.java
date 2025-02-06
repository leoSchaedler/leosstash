package advanced.customwritable;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class FireAvgTempWritable implements WritableComparable<FireAvgTempWritable>  {

    private long ocorrencia;
    private double somaTemp;

    public FireAvgTempWritable() {
    }

    public FireAvgTempWritable(long ocorrencia, double somaTemp) {
        this.ocorrencia = ocorrencia;
        this.somaTemp = somaTemp;
    }

    public double getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(long ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public double getSomaTemp() {
        return somaTemp;
    }

    public void setSomaTemp(long somaTemp) {
        this.somaTemp = somaTemp;
    }

    @Override
    public String toString() {
        return "FireAvgTempWritable{" +
                "ocorrencia=" + ocorrencia +
                ", somaTemp=" + somaTemp +
                '}';
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(ocorrencia);
        dataOutput.writeDouble(somaTemp);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        ocorrencia = dataInput.readLong();
        somaTemp = dataInput.readDouble();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FireAvgTempWritable that = (FireAvgTempWritable) o;
        return ocorrencia == that.ocorrencia &&
                Double.compare(that.somaTemp, somaTemp) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ocorrencia, somaTemp);
    }

    @Override
    public int compareTo(FireAvgTempWritable o) {
        if (this.hashCode() < o.hashCode())
            return -1;
        else if (this.hashCode() > o.hashCode())
            return +1;
        return 0;
    }
}
