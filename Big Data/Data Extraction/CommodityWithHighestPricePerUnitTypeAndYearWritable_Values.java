package br.puc.pr.TDE02.atv06;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class CommodityWithHighestPricePerUnitTypeAndYearWritable_Values implements WritableComparable<CommodityWithHighestPricePerUnitTypeAndYearWritable_Values> {

    private String commodity;
    private double price;

    public CommodityWithHighestPricePerUnitTypeAndYearWritable_Values(String commodity, double price) {
        this.commodity = commodity;
        this.price = price;
    }

    public CommodityWithHighestPricePerUnitTypeAndYearWritable_Values() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommodityWithHighestPricePerUnitTypeAndYearWritable_Values that = (CommodityWithHighestPricePerUnitTypeAndYearWritable_Values) o;
        return Double.compare(that.price, price) == 0 &&
                Objects.equals(commodity, that.commodity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commodity, price);
    }

    @Override
    public String toString() {
        return "CommodityWithHighestPricePerUnitTypeAndYearWritable_Values{" +
                "commodity='" + commodity + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(CommodityWithHighestPricePerUnitTypeAndYearWritable_Values o) {
        return Integer.compare(this.hashCode(), o.hashCode());
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(commodity);
        dataOutput.writeDouble(price);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        commodity = dataInput.readUTF();
        price = dataInput.readDouble();
    }
}
