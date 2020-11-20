package ui.bank;

import framework.common.Account;
import framework.common.AccountService;
import framework.common.AccountServiceImpl;
import framework.common.AccountType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

/**
 * A basic JFC based application.
 */
public class BankFrm extends JFrame {
    /****
     * init variables in the object
     ****/
    String accountnr, clientName, street, city, zip, state, accountType, clientType, amountDeposit;
    boolean newaccount;
    private DefaultTableModel model;
    private JTable JTable1;
    private JScrollPane JScrollPane1;
    BankFrm myframe;
    private Object rowdata[];

    public BankFrm() {
        myframe = this;

        setTitle("Bank Application.");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setSize(575, 310);
        setVisible(false);
        JPanel1.setLayout(null);
        getContentPane().add(BorderLayout.CENTER, JPanel1);
        JPanel1.setBounds(0, 0, 575, 310);
		/*
		/Add five buttons on the pane 
		/for Adding personal account, Adding company account
		/Deposit, Withdraw and Exit from the system
		*/
        JScrollPane1 = new JScrollPane();
        model = new DefaultTableModel();
        JTable1 = new JTable(model);
        model.addColumn("AccountNr");
        model.addColumn("Name");
        model.addColumn("City");
        model.addColumn("P/C");
        model.addColumn("Ch/S");
        model.addColumn("Amount");
        rowdata = new Object[8];
        newaccount = false;

        JPanel1.add(JScrollPane1);
        JScrollPane1.setBounds(12, 92, 444, 160);
        JScrollPane1.getViewport().add(JTable1);
        JTable1.setBounds(0, 0, 420, 0);
//        rowdata = new Object[8];

        JButton_PerAC.setText("Add personal account");
        JPanel1.add(JButton_PerAC);
        JButton_PerAC.setBounds(24, 20, 192, 33);
        JButton_CompAC.setText("Add company account");
        JButton_CompAC.setActionCommand("jbutton");
        JPanel1.add(JButton_CompAC);
        JButton_CompAC.setBounds(240, 20, 192, 33);
        JButton_Deposit.setText("Deposit");
        JPanel1.add(JButton_Deposit);
        JButton_Deposit.setBounds(468, 104, 96, 33);
        JButton_Withdraw.setText("Withdraw");
        JPanel1.add(JButton_Withdraw);
        JButton_Addinterest.setBounds(448, 20, 106, 33);
        JButton_Addinterest.setText("Add interest");
        JPanel1.add(JButton_Addinterest);
        JButton_Withdraw.setBounds(468, 164, 96, 33);

        JButton_Exit.setText("Exit");
        JPanel1.add(JButton_Exit);
        JButton_Exit.setBounds(468, 248, 96, 31);
        // lineBorder1.setRoundedCorners(true);
        // lineBorder1.setLineColor(java.awt.Color.green);
        //$$ lineBorder1.move(24,312);

        // Added for billing report
        JButton_GenBill.setText("Monthly bills");
        JButton_GenBill.setActionCommand("jbutton");
        JPanel1.add(JButton_GenBill);
        JButton_GenBill.setBounds(468, 210, 100, 33);
        ////////////

        JButton_PerAC.setActionCommand("jbutton");

        SymWindow aSymWindow = new SymWindow();
        this.addWindowListener(aSymWindow);
        SymAction lSymAction = new SymAction();
        JButton_Exit.addActionListener(lSymAction);
        JButton_PerAC.addActionListener(lSymAction);
        JButton_CompAC.addActionListener(lSymAction);
        JButton_Deposit.addActionListener(lSymAction);
        JButton_Withdraw.addActionListener(lSymAction);
        JButton_Addinterest.addActionListener(lSymAction);

        JButton_GenBill.addActionListener(lSymAction);//added for Billing Report

        reloadData();//added for testing
    }

    /*****************************************************
     * The entry point for this application.
     * Sets the Look and Feel to the System Look and Feel.
     * Creates a new JFrame1 and makes it visible.
     *****************************************************/
    static public void main(String args[]) {
        try {
            // Add the following code if you want the Look and Feel
            // to be set to the Look and Feel of the native system.

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
            }

            //Create a new instance of our application's frame, and make it visible.
            (new BankFrm()).setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }

    JPanel JPanel1 = new JPanel();
    JButton JButton_PerAC = new JButton();
    JButton JButton_CompAC = new JButton();
    JButton JButton_Deposit = new JButton();
    JButton JButton_Withdraw = new JButton();
    JButton JButton_Addinterest = new JButton();
    JButton JButton_Exit = new JButton();

    JButton JButton_GenBill = new JButton(); // Added for Billing Report

    void exitApplication() {
        try {
            this.setVisible(false);    // hide the Frame
            this.dispose();            // free the system resources
            System.exit(0);            // close the application
        } catch (Exception e) {
        }
    }

    class SymWindow extends java.awt.event.WindowAdapter {
        public void windowClosing(WindowEvent event) {
            Object object = event.getSource();
            if (object == BankFrm.this)
                BankFrm_windowClosing(event);
        }
    }

    void BankFrm_windowClosing(WindowEvent event) {
        // to do: code goes here.

        BankFrm_windowClosing_Interaction1(event);
    }

    void BankFrm_windowClosing_Interaction1(WindowEvent event) {
        try {
            this.exitApplication();
        } catch (Exception e) {
        }
    }

    class SymAction implements java.awt.event.ActionListener {
        public void actionPerformed(ActionEvent event) {
            Object object = event.getSource();
            if (object == JButton_Exit)
                JButtonExit_actionPerformed(event);
            else if (object == JButton_PerAC)
                JButtonPerAC_actionPerformed(event);
            else if (object == JButton_CompAC)
                JButtonCompAC_actionPerformed(event);
            else if (object == JButton_Deposit)
                JButtonDeposit_actionPerformed(event);
            else if (object == JButton_Withdraw)
                JButtonWithdraw_actionPerformed(event);
            else if (object == JButton_Addinterest)
                JButtonAddinterest_actionPerformed(event);
            else if (object == JButton_GenBill)
                JButtonGenerateBill_actionPerformed(event);
        }
    }

    //When the Exit button is pressed this code gets executed
    //this will exit from the system
    void JButtonExit_actionPerformed(ActionEvent event) {
        System.exit(0);
    }

    void JButtonPerAC_actionPerformed(ActionEvent event) {
		/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object 
		 set the boundaries and show it 
		*/

        JDialog_AddPAcc pac = new JDialog_AddPAcc(myframe);
        pac.setBounds(450, 20, 300, 330);
        pac.show();

        if (newaccount) {
            // add row to table
            rowdata[0] = accountnr;
            rowdata[1] = clientName;
            rowdata[2] = city;
            rowdata[3] = "P";
            rowdata[4] = accountType;
            rowdata[5] = "0";
            model.addRow(rowdata);
            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
            newaccount = false;
        }
    }

    void JButtonCompAC_actionPerformed(ActionEvent event) {
		/*
		 construct a JDialog_AddCompAcc type object 
		 set the boundaries and 
		 show it 
		*/

        JDialog_AddCompAcc pac = new JDialog_AddCompAcc(myframe);
        pac.setBounds(450, 20, 300, 330);
        pac.show();

        if (newaccount) {
            // add row to table
            rowdata[0] = accountnr;
            rowdata[1] = clientName;
            rowdata[2] = city;
            rowdata[3] = "C";
            rowdata[4] = accountType;
            rowdata[5] = "0";
            model.addRow(rowdata);
            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
            newaccount = false;
        }

    }

    void JButtonDeposit_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) model.getValueAt(selection, 0);

            //Show the dialog for adding deposit amount for the current mane
            JDialog_Deposit dep = new JDialog_Deposit(myframe, accnr);
            dep.setBounds(430, 15, 275, 140);
            dep.show();

            // compute new amount
            if (amountDeposit != null) {
                double deposit = Double.parseDouble(amountDeposit);
                String samount = (String) model.getValueAt(selection, 5);
                double currentamount = Double.parseDouble(samount);
                double newamount = currentamount + deposit;
                model.setValueAt(String.format("%.2f", newamount), selection, 5);
            }
        }
    }

    void JButtonWithdraw_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) model.getValueAt(selection, 0);

            //Show the dialog for adding withdraw amount for the current mane
            JDialog_Withdraw wd = new JDialog_Withdraw(myframe, accnr);
            wd.setBounds(430, 15, 275, 140);
            wd.show();

            // compute new amount
            if (amountDeposit != null) {
                double deposit = Double.parseDouble(amountDeposit);
                String samount = (String) model.getValueAt(selection, 5);
                double currentamount = Double.parseDouble(samount);
                double newamount = currentamount - deposit;
                if (newamount > 0)
                    model.setValueAt(String.format("%.2f", newamount), selection, 5);
//                model.setValueAt(String.valueOf(newamount), selection, 5);
                else {
                    JOptionPane.showMessageDialog(JButton_Withdraw, " Account " + accnr + " : balance is less than: $" + String.valueOf(deposit) + " !", "Warning: The withdraw money must be less than balance", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    void JButtonAddinterest_actionPerformed(ActionEvent event) {
        JOptionPane.showMessageDialog(JButton_Addinterest, "Add interest to all accounts", "Add interest to all accounts", JOptionPane.WARNING_MESSAGE);
        AccountService service = new AccountServiceImpl();
        service.calculateInterest();
        reloadData();
    }

    // Added for billing report
    void JButtonGenerateBill_actionPerformed(java.awt.event.ActionEvent event) {
        JDialogGenBill billFrm = new JDialogGenBill();
        billFrm.setBounds(450, 20, 400, 350);
        billFrm.show();
    }

    void reloadData() {
        int size = model.getRowCount();
        for (int i = size - 1; i >= 0; i--)
            model.removeRow(i);

        AccountService service = new AccountServiceImpl();
        Collection<Account> col = service.getAllAccounts().stream().
                filter(account -> ((account.getAccountType().equals(AccountType.SAVING)) || (account.getAccountType().equals(AccountType.CHECKING))))
                .collect(Collectors.toList());
        col.forEach(account -> {
            rowdata[0] = account.getAccountNumber();
            rowdata[1] = account.getCustomer().getName();
            rowdata[2] = account.getCustomer().getCity();
            rowdata[3] = account.getCustomer().getBirthday() == null ? "C" : "P";
            rowdata[4] = account.getAccountType().equals(AccountType.SAVING) ? "S" : "Ch";
            rowdata[5] = String.format("%.2f", account.getBalance());;
//            String.format("%.2f", account.getBalance());
            model.addRow(rowdata);
        });
    }
}
