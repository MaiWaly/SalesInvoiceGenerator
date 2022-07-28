package Controller;

import Model.InvoiceHeader;
import Model.InvoiceLine;
import Model.InvoiceLineTableModel;
import View.MainFrame;
import View.NewHeaderDialog;
import View.NewLineDialog;
import java.awt.AWTEventMulticaster;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controller implements ActionListener, ListSelectionListener {

    private MainFrame frame;
    private NewHeaderDialog newHeaderDialog;
    private NewLineDialog newLineDialog;

    public Controller(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
//File Actions
            case "Load" ->
                loadFile();
            case "Save" ->
                saveFile();
//Headers Actions
            case "Create New Invoice" ->
                createNewInvoice();
            case "Create New Header Ok" ->
                createNewInvoiceOk();
            case "Create New Header Cancel" ->
                createNewInvoiceCancel();
            case "Delete Invoice" ->
                deleteInvoice();
//Lines Actions
            case "Create New Line" ->
                createNewLine();
            case "Create New Line Ok" ->
                createNewLineOk();
            case "Create New Line Cancel" ->
                createNewLineCancel();
            case "Delete Line" ->
                deleteLine();

        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedRow = frame.getInvHeaderTb().getSelectedRow();
        if (selectedRow != -1) {
            InvoiceHeader selectedHeader = frame.getInvoiceHeaderList().get(selectedRow);
            ArrayList<InvoiceLine> lines = selectedHeader.getLines();
            InvoiceLineTableModel invLineModel = new InvoiceLineTableModel(lines);
            frame.getInvLineTb().setModel(invLineModel);
            int invoiceID = frame.getInvoiceHeaderList().get(selectedRow).getInvoiceNo();
            String invoiceDate = frame.getInvoiceHeaderList().get(selectedRow).getInvoiceDate();
            String customerName = frame.getInvoiceHeaderList().get(selectedRow).getCustomerName();
            double invoiceTotal = frame.getInvoiceHeaderList().get(selectedRow).getInvoiceTotal();
            frame.invoiceNoLb.setText(String.valueOf(invoiceID));
            frame.invoiceDateLb.setText(String.valueOf(invoiceDate));
            frame.customerNameLb.setText(String.valueOf(customerName));
            frame.totalLb.setText(String.valueOf(invoiceTotal));
            System.out.println(String.valueOf("changevale" + this.frame));
            invLineModel.fireTableDataChanged();
        }
    }

    private void loadFile() {
        try {
            JFileChooser fc = new JFileChooser();
            int result = fc.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                String headerStrPath = headerFile.getAbsolutePath();
                Path headerPath = Paths.get(headerStrPath);
                List<String> headerLines = Files.lines(headerPath).collect(Collectors.toList());
                ArrayList<InvoiceHeader> invoiceHeaderList = new ArrayList<>();
                for (String headLine : headerLines) {
                    String[] parts = headLine.split(",");
                    int id = Integer.parseInt(parts[0]);
                    InvoiceHeader invoiceHeader = new InvoiceHeader(id, parts[1], parts[2]);
                    invoiceHeaderList.add(invoiceHeader);
                }
                System.out.println("Loaaaaaaaaaaad");

                result = fc.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String lineStrPath = fc.getSelectedFile().getAbsolutePath();
                    Path linePath = Paths.get(lineStrPath);
                    List<String> lineLines = Files.lines(linePath).collect(Collectors.toList());

                    for (String lineLine : lineLines) {
                        String[] parts = lineLine.split(",");
                        int invId = Integer.parseInt(parts[0]);
                        double price = Double.parseDouble(parts[2]);
                        int count = Integer.parseInt(parts[3]);
                        InvoiceHeader invHeader = getInvoiceHeaderById(invoiceHeaderList, invId);

                        InvoiceLine invoiceLine = new InvoiceLine(parts[0], count, price, invHeader);
                        invHeader.getLines().add(invoiceLine);
                    }
                    frame.setInvoiceHeaderList(invoiceHeaderList);
                    frame.getInvHeaderTb().setRowSelectionInterval(0, 0);

                }
                System.out.println(String.valueOf("Load" + this.frame));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private InvoiceHeader getInvoiceHeaderById(ArrayList<InvoiceHeader> invoices, int id) {
        for (InvoiceHeader invoice : invoices) {
            if (invoice.getInvoiceNo() == id) {
                return invoice;
            }
        }
        return null;
    }

    private void saveFile() {
        ArrayList<InvoiceHeader> headersDataList = frame.getInvoiceHeaderList();
        String headersStr = "";
        String linesStr = "";
        for (InvoiceHeader h : headersDataList) {
            String headerCsv = h.getCsvHeaderFile();
            headersStr += headerCsv + "\n";

            for (InvoiceLine l : h.getLines()) {
                String lineCsv = l.getCsvLineFile();
                linesStr += lineCsv + "\n";
            }
        }
        //Headers save File
        try {
            JFileChooser fc = new JFileChooser();
            int selectedoption = fc.showSaveDialog(frame);
            if (selectedoption == JFileChooser.APPROVE_OPTION);
            File headerDataFile = fc.getSelectedFile();
            FileWriter headerFileW = new FileWriter(headerDataFile);
            headerFileW.write(headersStr);
            headerFileW.flush();
            headerFileW.close();

            //Lines save File
            selectedoption = fc.showSaveDialog(frame);
            if (selectedoption == JFileChooser.APPROVE_OPTION);
            File lineDataFile = fc.getSelectedFile();
            FileWriter lineFileW = new FileWriter(lineDataFile);
            lineFileW.write(linesStr);
            lineFileW.flush();
            lineFileW.close();
        }
catch( Exception ex){


    }
}
 

           
    
//Headers Actions

    private void createNewInvoice() {

        newHeaderDialog = new NewHeaderDialog(frame);
        newHeaderDialog.setVisible(true);
    }

    private void createNewInvoiceOk() {

        String data = newHeaderDialog.getDateTxt().getText();
        String customerName = newHeaderDialog.getCustomerNameTxt().getText();
        int index = frame.getHeaderNextId();
        InvoiceHeader newHeader = new InvoiceHeader(index, data, customerName);
        frame.getInvoiceHeaderList().add(newHeader);
        frame.getHeaderTableModel().fireTableDataChanged();
        frame.getInvHeaderTb().setRowSelectionInterval(index - 1, index - 1);

        newHeaderDialog.setVisible(false);
        newHeaderDialog.dispose();
        newHeaderDialog = null;
    }

    private void createNewInvoiceCancel() {
        newHeaderDialog.setVisible(false);
        newHeaderDialog.dispose();
        newHeaderDialog = null;

    }

    private void deleteInvoice() {
        int selectedRow = frame.getInvHeaderTb().getSelectedRow();

        if (selectedRow != -1) {
            frame.getInvoiceHeaderList().remove(selectedRow);
            frame.getHeaderTableModel().fireTableDataChanged();
            frame.getInvHeaderTb().setRowSelectionInterval(0, 0);

        }
    }

//Lines Actions
    private void createNewLine() {
        newLineDialog = new NewLineDialog(frame);
        newLineDialog.setVisible(true);

        //NewHeaderDialog newLine = new NewHeaderDialog(frame);
    }

    private void createNewLineOk() {
        String ItemName = newLineDialog.getItemNameTxt().getText();
        double itemCount = Double.parseDouble(newLineDialog.getItemCountTxt().getText());
        int itemPrice = Integer.parseInt(newLineDialog.getItemPriceTxt().getText());
        int selectedHeader = frame.getInvHeaderTb().getSelectedRow();
        if (selectedHeader != -1) {
            InvoiceHeader header = frame.getInvoiceHeaderList().get(selectedHeader);
            InvoiceLine line = new InvoiceLine(ItemName, itemPrice, itemCount, header);
            header.getLines().add(line);
            InvoiceLineTableModel model = (InvoiceLineTableModel) frame.getInvLineTb().getModel();
            model.fireTableDataChanged();
            frame.getHeaderTableModel().fireTableDataChanged();
            frame.getInvHeaderTb().setRowSelectionInterval(selectedHeader, selectedHeader);
        }

        newLineDialog.setVisible(false);
        newLineDialog.dispose();
        newLineDialog = null;
    }

    private void createNewLineCancel() {
        newLineDialog.setVisible(false);
        newLineDialog.dispose();
        newLineDialog = null;
    }

    private void deleteLine() {

        int selectedHeaderindx = frame.getInvHeaderTb().getSelectedRow();
        int selectedRow = frame.getInvLineTb().getSelectedRow();
        if (selectedHeaderindx != -1 && selectedRow != -1) {
            InvoiceHeader selectedHeaderData = frame.getInvoiceHeaderList().get(selectedHeaderindx);
            selectedHeaderData.getLines().remove(selectedRow);
            InvoiceLineTableModel lineTbModel = new InvoiceLineTableModel(selectedHeaderData.getLines());
            frame.getInvLineTb().setModel(lineTbModel);
            lineTbModel.fireTableDataChanged();
            frame.getHeaderTableModel().fireTableDataChanged();
            frame.getInvHeaderTb().setRowSelectionInterval(selectedHeaderindx, selectedHeaderindx);
            frame.getInvLineTb().setRowSelectionInterval(0, 0);
        }

    }

}
