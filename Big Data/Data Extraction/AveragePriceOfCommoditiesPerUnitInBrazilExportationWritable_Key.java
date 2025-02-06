package br.puc.pr.TDE02.atv05;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Key implements WritableComparable<AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Key> {

    String unitType;
    String year;
    String category;

    public AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Key() {
    }

    public AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Key(String unitType, String year, String category) {
        this.unitType = unitType;
        this.year = year;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Key that = (AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Key) o;
        return Objects.equals(unitType, that.unitType) &&
                Objects.equals(year, that.year) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitType, year, category);
    }


    @Override
    public int compareTo(AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Key o) {
        return Integer.compare(this.hashCode(), o.hashCode());
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(unitType);
        dataOutput.writeUTF(year);
        dataOutput.writeUTF(category);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        unitType = dataInput.readUTF();
        year = dataInput.readUTF();
        category = dataInput.readUTF();
    }

    @Override
    public String toString() {
        return "AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Key{" +
                "unitType='" + unitType + '\'' +
                ", year='" + year + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
