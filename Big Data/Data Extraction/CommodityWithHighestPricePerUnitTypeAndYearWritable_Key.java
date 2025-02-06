package br.puc.pr.TDE02.atv06;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class CommodityWithHighestPricePerUnitTypeAndYearWritable_Key implements WritableComparable<CommodityWithHighestPricePerUnitTypeAndYearWritable_Key> {

    private String unitType;
    private String year;

    public CommodityWithHighestPricePerUnitTypeAndYearWritable_Key(String unitType, String year) {
        this.unitType = unitType;
        this.year = year;
    }

    public CommodityWithHighestPricePerUnitTypeAndYearWritable_Key() {
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommodityWithHighestPricePerUnitTypeAndYearWritable_Key that = (CommodityWithHighestPricePerUnitTypeAndYearWritable_Key) o;
        return Objects.equals(unitType, that.unitType) &&
                Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitType, year);
    }

    @Override
    public String toString() {
        return "CommodityWithHighestPricePerUnitTypeAndYearWritable_Key{" +
                "unitType='" + unitType + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    @Override
    public int compareTo(CommodityWithHighestPricePerUnitTypeAndYearWritable_Key o) {
        return Integer.compare(this.hashCode(), o.hashCode());
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(unitType);
        dataOutput.writeUTF(year);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        unitType = dataInput.readUTF();
        year = dataInput.readUTF();
    }
}
