package ui.bank;
/*
		A basic implementation of the JDialog class.
*/

import framework.common.AccountService;
import framework.common.AccountServiceImpl;
import ui.ccard.CardFrm;

import javax.swing.*;
import java.awt.*;

public class JDialogGenBill extends JDialog
{
    String billstring;
    
    CardFrm parentframe = new CardFrm();
    
	public JDialogGenBill(Frame parent)
	{
		super(parent);
		
		// This code is automatically generated by Visual Cafe when you add
		// components to the visual environment. It instantiates and initializes
		// the components. To modify the code, only use code syntax that matches
		// what Visual Cafe can generate, or Visual Cafe may be unable to back
		// parse your Java file into its visual environment.
		//{{INIT_CONTROLS
		getContentPane().setLayout(null);
		setSize(405,367);
		setVisible(false);
		getContentPane().add(JScrollPane1);
		JScrollPane1.setBounds(24,24,358,240);
		//JScrollPane1.getViewport().add(JTextField1);
		//JTextField1.setBounds(0,0,355,237);
		JScrollPane1.getViewport().add(JTextArea1);
		JTextArea1.setBounds(0,0,355,237);
		JButton_OK.setText("OK");
		JButton_OK.setActionCommand("OK");
		getContentPane().add(JButton_OK);
		JButton_OK.setBounds(156,276,96,24);

		// generate the string for the monthly bill

		AccountService service = new AccountServiceImpl();
		billstring = "";
		service.printBankStatement().forEach((String rec) -> {
			JTextArea1.append(rec);
		});
		//}}
	
		//{{REGISTER_LISTENERS
		SymAction lSymAction = new SymAction();
		JButton_OK.addActionListener(lSymAction);
		//}}
	}

	public JDialogGenBill()
	{
		this((Frame)null);
	}



	//{{DECLARE_CONTROLS
	JScrollPane JScrollPane1 = new JScrollPane();
	JTextField JTextField1 = new JTextField();
	JTextArea JTextArea1 = new JTextArea();
	JButton JButton_OK = new JButton();
	//}}


	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == JButton_OK)
				JButtonOK_actionPerformed(event);
		}
	}

	void JButtonOK_actionPerformed(java.awt.event.ActionEvent event)
	{
		dispose();
	}
}
