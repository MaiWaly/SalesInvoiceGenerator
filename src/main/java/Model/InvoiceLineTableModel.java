/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mai
 */
public class InvoiceLineTableModel extends AbstractTableModel {

    private String[] colsNames = {"ID", "Item Name", "Item Price", "Count", "Total"};
    private ArrayList<InvoiceLine> data;

    public InvoiceLineTableModel(ArrayList<InvoiceLine> data) {
        this.data = data;
    }

    public ArrayList<InvoiceLine> getData() {
        return data;
    }

    public InvoiceLineTableModel() {
        data = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return colsNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine invLine = data.get(rowIndex);

        switch (columnIndex) {
            case 0 -> {
                return invLine.getInvHeader().getInvoiceNo();
            }
            case 1 -> {
                return invLine.getItemName();
            }
            case 2 -> {
                return invLine.getItemPrice();
            }
            case 3 -> {
                return invLine.getItemCount();
            }
            case 4 -> {
                return invLine.getLineTotal();
            }

        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colsNames[column];
    }

}
