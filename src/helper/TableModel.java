package helper;

import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private String[] columns;
    private Object[][] rows;

    public TableModel() {
    }

    public TableModel(Object[][] data, String[] columnName) {
        this.rows = data;
        this.columns = columnName;
    }

    @Override
    public Class getColumnClass(int column) {
        // 3 is the index of the column image
        if (column == 3) {
            return Icon.class;
        } else {
            return getValueAt(0, column).getClass();
        }
    }

    @Override
    public int getRowCount() {
        return rows.length;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }
}
