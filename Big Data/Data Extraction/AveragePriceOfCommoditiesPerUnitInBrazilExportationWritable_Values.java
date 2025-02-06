package br.puc.pr.TDE02.atv05;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values implements WritableComparable<AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values> {

    private double price;
    private String commodity;
    private long cont;

    public AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values(double price, String commodity, long cont) {
        this.price = price;
        this.commodity = commodity;
        this.cont = cont;
    }

    public AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values() {
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

    public long getCont() {
        return cont;
    }

    public void setCont(long cont) {
        this.cont = cont;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values that = (AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values) o;
        return Double.compare(that.price, price) == 0 &&
                cont == that.cont &&
                Objects.equals(commodity, that.commodity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, commodity, cont);
    }

    @Override
    public String toString() {
        return "AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values{" +
                ", price=" + price +
                ", commodity='" + commodity + '\'' +
                ", cont=" + cont +
                '}';
    }

    @Override
    public int compareTo(AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values o) {
        return Integer.compare(this.hashCode(), o.hashCode());
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeDouble(price);
        dataOutput.writeUTF(commodity);
        dataOutput.writeLong(cont);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        price = dataInput.readDouble();
        commodity = dataInput.readUTF();
        cont = dataInput.readLong();
    }
}
