
import javax.swing.*;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerInfoApp extends JFrame implements ActionListener {
	JTextField custnof,custnamef,custstatef,custcreditf;
	JButton submit,display,addnew;
	JFrame jf1,jf2;
	
	CustomerInfoApp(){
		jf1 = new JFrame();
		jf1.setTitle("Add Cusomter Details");
		jf1.setSize(600,600);
		jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf1.setLayout(new GridLayout(20,2));
		
		custnof = new JTextField(20);
		custnamef = new JTextField(20);
		custstatef = new JTextField(20);
		custcreditf = new JTextField(20);
		
		submit = new JButton("Submit");
		display = new JButton("Display");
		
		jf1.add(new JLabel("Customer NO:"));
		jf1.add(custnof);
		jf1.add(new JLabel("Customer Name:"));
		jf1.add(custnamef);
		jf1.add(new JLabel("Customer State:"));
		jf1.add(custstatef);
		jf1.add(new JLabel("Customer Credit Limit:"));
		jf1.add(custcreditf);
		jf1.add(submit);
		jf1.add(display);
		
		submit.addActionListener(this);
		display.addActionListener(this);
		
		jf1.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submit) {
			try {
				int custno = Integer.parseInt(custnof.getText());
				String custname = custnamef.getText();
				String custstate = custstatef.getText();
				int custcredit = Integer.parseInt(custcreditf.getText());
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms","root","myadmin123");
				PreparedStatement ps = con.prepareStatement("insert into cust values(?,?,?,?)");
				ps.setInt(1, custno);
				ps.setString(2, custname);
				ps.setString(3, custstate);
				ps.setInt(4, custcredit);
				ps.executeUpdate();
				
				JOptionPane.showMessageDialog(jf1, "Stored Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
			}
			catch(SQLException s) {
				System.out.println("Error");
			}
		}
		if(e.getSource() == display) {
			try {
				jf2 = new JFrame();
				jf2.setSize(600,600);
				jf2.setTitle("Customer Details");
				jf2.setLayout(new BorderLayout());
				jf2.setDefaultCloseOperation(EXIT_ON_CLOSE);
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms","root","myadmin123");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("Select * from cust");
				JTextArea textarea = new JTextArea();
				textarea.setEditable(false);
				while(rs.next()) {
					textarea.append("Customer No: "+ rs.getInt(1) + "\n");
					textarea.append("Customer Name: "+ rs.getString(2) + "\n");
					textarea.append("Customer State: "+ rs.getString(3) + "\n");
					textarea.append("Customer Credit Limit: "+ rs.getInt(4) + "\n");
					textarea.append("\n");
				}
				JScrollPane scroll = new JScrollPane(textarea);
				scroll.setPreferredSize(new Dimension(780,580));
				jf2.add(scroll,BorderLayout.CENTER);
				
			    addnew = new JButton("Add new");
				addnew.addActionListener(this);
				JPanel panel = new JPanel();
				panel.add(addnew);
				jf2.add(panel,BorderLayout.SOUTH);
				
				jf2.setVisible(true);

			}
			catch(SQLException s) {
				System.out.println("Error");
			}
		}
		if(e.getSource() == addnew) {
			jf1.setVisible(true);
			jf2.setVisible(false);
		}
	}
	public static void main(String[] args) {
		new CustomerInfoApp();
	}
}
