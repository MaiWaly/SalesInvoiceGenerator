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

public class NewLineDialog extends JDialog {

    private JLabel itemNameLb;
    private JLabel itemCountLb;
    private JLabel itemPriceLb;

    private JTextField itemNameTxt;
    private JTextField itemCountTxt;
    private JTextField itemPriceTxt;

    private JButton okBtn;
    private JButton cancelBtn;

    public JTextField getItemNameTxt() {
        return itemNameTxt;
    }

    public JTextField getItemCountTxt() {
        return itemCountTxt;
    }

    public JTextField getItemPriceTxt() {
        return itemPriceTxt;
    }

    public NewLineDialog(MainFrame frame) {

        itemNameLb = new JLabel("   Item Name");
        itemCountLb = new JLabel("   Count");
        itemPriceLb = new JLabel("   Item Price");

        itemNameTxt = new JTextField(22);
        itemCountTxt = new JTextField(22);
        itemPriceTxt = new JTextField(22);

        okBtn = new JButton("Ok");
        cancelBtn = new JButton("Cancel");

        okBtn.setActionCommand("Create New Line Ok");
        okBtn.addActionListener(frame.getController());

        cancelBtn.setActionCommand("Create New Line Cancel");
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 2));

        add(itemNameLb);
        add(itemNameTxt);
        add(itemCountLb);
        add(itemCountTxt);
        add(itemPriceLb);
        add(itemPriceTxt);
        add(okBtn);
        add(cancelBtn);

        pack();

    }

}
