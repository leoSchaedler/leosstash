package pucpr;

public interface TableModel {

    int getRowCount();
    int getColumnCount();
    Object getValueAt(int row, int col);
    String getFormat(int col);
    String getHeader();
}
