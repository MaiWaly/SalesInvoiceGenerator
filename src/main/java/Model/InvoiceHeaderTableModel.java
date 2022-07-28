package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class InvoiceHeaderTableModel extends AbstractTableModel {

    private ArrayList<InvoiceHeader> invHeader;
    private String[] colsNames = {"ID", "Customer Name", "Date", "Total"};

    @Override
    public String getColumnName(int column) {
        return colsNames[column]; 
    }

    public InvoiceHeaderTableModel(ArrayList<InvoiceHeader> invHeader) {
        this.invHeader = invHeader;
    }

    @Override
    public int getRowCount() {
        return invHeader.size();
    }

    @Override
    public int getColumnCount() {
        return colsNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        InvoiceHeader header = invHeader.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return header.getInvoiceNo();

            case 1:
                return header.getCustomerName();
            case 2:
                return header.getInvoiceDate();
            case 3:
                return header.getInvoiceTotal();
        }
        return null;
    }

  

}
