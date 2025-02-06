package br.puc.pr.TDE02.atv04;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class AverageOfCommodityValuesPerYearWriteble implements WritableComparable<AverageOfCommodityValuesPerYearWriteble> {

    private double price;
    private long count;

    public AverageOfCommodityValuesPerYearWriteble(double price, long count) {
        this.price = price;
        this.count = count;
    }

    public AverageOfCommodityValuesPerYearWriteble() {
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeDouble(price);
        dataOutput.writeLong(count);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        price = dataInput.readDouble();
        count = dataInput.readLong();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AverageOfCommodityValuesPerYearWriteble that = (AverageOfCommodityValuesPerYearWriteble) o;
        return price == that.price &&
                count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, count);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public int compareTo(AverageOfCommodityValuesPerYearWriteble o) {
        if (this.hashCode() < o.hashCode())
            return -1;
        else if (this.hashCode() > o.hashCode())
            return +1;
        return 0;
    }
}
