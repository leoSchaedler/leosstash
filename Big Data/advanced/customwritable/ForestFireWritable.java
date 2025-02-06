package advanced.customwritable;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class ForestFireWritable implements WritableComparable<ForestFireWritable> {

    private double temp, vento;

    public ForestFireWritable() {
    }

    public ForestFireWritable(double temp, double vento) {
        this.temp = temp;
        this.vento = vento;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getVento() {
        return vento;
    }

    public void setVento(double vento) {
        this.vento = vento;
    }

    @Override
    public int compareTo(ForestFireWritable o) {
        if (this.hashCode() < o.hashCode())
            return -1;
        else if(this.hashCode() > o.hashCode())
            return +1;
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeDouble(temp);
        dataOutput.writeDouble(vento);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        temp = dataInput.readDouble();
        vento = dataInput.readDouble();
    }

    @Override
    public String toString() {
        return "ForestFireWritable{" +
                "temp=" + temp +
                ", vento=" + vento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForestFireWritable that = (ForestFireWritable) o;
        return Double.compare(that.temp, temp) == 0 &&
                Double.compare(that.vento, vento) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(temp, vento);
    }
}
