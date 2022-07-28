package Model;

import java.util.ArrayList;


public class InvoiceHeader {

    private int invoiceNo;
    private String invoiceDate;
    private String customerName;

    ArrayList<InvoiceLine> lines;

    public InvoiceHeader() {
    }

    public InvoiceHeader(int invoiceNo, String invoiceDate, String customerName) {
        this.invoiceNo = invoiceNo;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getInvoiceTotal() {
        double total = 0;
        for (InvoiceLine line : getLines()) {
            total += line.getLineTotal();
        }
        return total;
    }

    public ArrayList<InvoiceLine> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }

    @Override
    public String toString() {
        return "InvoiceHeader{" + "invoiceNo=" + invoiceNo + ", invoiceDate=" + invoiceDate + ", customerName=" + customerName + '}';
    }
public String getCsvHeaderFile(){


return invoiceNo + "," + invoiceDate +"," + customerName;
        }
    
}
