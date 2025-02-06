package br.puc.pr.TDE02.atv03;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class MostCommercializedCommodityinSixPerFlowWriteble implements WritableComparable<MostCommercializedCommodityinSixPerFlowWriteble> {

    private long cont;
    private String commodity;

    public MostCommercializedCommodityinSixPerFlowWriteble(long cont, String commodity) {
        this.cont = cont;
        this.commodity = commodity;
    }

    public MostCommercializedCommodityinSixPerFlowWriteble() {
    }

    public long getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
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
        MostCommercializedCommodityinSixPerFlowWriteble that = (MostCommercializedCommodityinSixPerFlowWriteble) o;
        return cont == that.cont &&
                Objects.equals(commodity, that.commodity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cont, commodity);
    }

    @Override
    public int compareTo(MostCommercializedCommodityinSixPerFlowWriteble o) {
        if (this.hashCode() < o.hashCode())
            return -1;
        else if (this.hashCode() > o.hashCode())
            return +1;
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(cont);
        dataOutput.writeUTF(commodity);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        cont = dataInput.readLong();
        commodity = dataInput.readUTF();
    }
}
