package Model;

public class InvoiceLine {

    private String itemName;
    private int itemCount;
    private double itemPrice;
    private InvoiceHeader invHeader;

    public InvoiceLine() {
    }

    public InvoiceLine(String itemName, int itemCount, double itemPrice, InvoiceHeader invHeader) {
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
        this.invHeader = invHeader;
    }

    public InvoiceLine(String itemName, int itemCount, double itemPrice) {
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
    }

    public InvoiceHeader getInvHeader() {
        return invHeader;
    }

    public void setInvHeader(InvoiceHeader invHeader) {
        this.invHeader = invHeader;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getLineTotal() {
        return itemCount * itemPrice;
    }

    @Override
    public String toString() {
        return "InvoiceLine{" + "itemName=" + itemName + ", itemCount=" + itemCount + ", itemPrice=" + itemPrice + ", invHeader=" + invHeader + "linetotal" + getLineTotal() + '}';
    }

    public String getCsvLineFile() {

        return invHeader.getInvoiceNo() + "," + itemName + "," + itemPrice + "," + itemCount;
    }

}
