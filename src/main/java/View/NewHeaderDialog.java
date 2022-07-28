/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewHeaderDialog extends JDialog {

    private JTextField customerNameTxt;
    private JTextField dateTxt;
    private JLabel customerNameLb;
    private JLabel dateLb;
    private JButton okBtn;
    private JButton cancelBtn;

    public NewHeaderDialog(MainFrame mainFrame) {

        customerNameLb = new JLabel("   Customer Name");
        customerNameTxt = new JTextField(22);
        dateLb = new JLabel("    Date");
        dateTxt = new JTextField(22);
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        
        okBtn.setActionCommand("Create New Header Ok");
        cancelBtn.setActionCommand("Create New Header Cancel");
        
        
        okBtn.addActionListener(mainFrame.getController());
        cancelBtn.addActionListener(mainFrame.getController());
        
        
        setLayout(new GridLayout(3, 2));
        
        
        add(customerNameLb);
        add(customerNameTxt);
        add(dateLb);
        add(dateTxt);
        add(okBtn);
        add(cancelBtn);
        pack();

    }

    public JTextField getCustomerNameTxt() {
        return customerNameTxt;
    }

    public JTextField getDateTxt() {
        return dateTxt;
    }

}
