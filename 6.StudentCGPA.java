
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentCGPA extends JFrame implements ActionListener {
	JTextField namef,usnf,addf,sg1f,sg2f,sg3f,sg4f;
	JButton computeb,displayb,doneb;
	JTextArea displayA;
	ArrayList<String> studentCollection;
	
	StudentCGPA(){
		setTitle("Student Details");
		setSize(600,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(12,2));
		
		namef = new JTextField(20);
		usnf = new JTextField(20);
		addf = new JTextField(20);
		sg1f = new JTextField(20);
		sg2f = new JTextField(20);
		sg3f = new JTextField(20);
		sg4f = new JTextField(20);
		
		computeb = new JButton("Compute");
		doneb = new JButton("Done");
		displayb = new JButton("Display");
		
		displayA = new JTextArea(10,50);
		displayA.setEditable(false);
		
		add(new JLabel("Name: "));
		add(namef);
		add(new JLabel("USN: "));
		add(usnf);
		add(new JLabel("Address: "));
		add(addf);
		add(new JLabel("SGPA 1: "));
		add(sg1f);
		add(new JLabel("SGPA 2: "));
		add(sg2f);
		add(new JLabel("SGPA 3: "));
		add(sg3f);
		add(new JLabel("SGPA 4: "));
		add(sg4f);
		add(computeb);
		add(doneb);
		add(displayb);
		
		add(new JScrollPane(displayA));
		
		computeb.addActionListener(this);
		doneb.addActionListener(this);
		displayb.addActionListener(this);
		
		studentCollection = new ArrayList <String>();
		
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == computeb) {
			try {
				float sg1 = Float.parseFloat(sg1f.getText());
				float sg2 = Float.parseFloat(sg2f.getText());
				float sg3 = Float.parseFloat(sg3f.getText());
				float sg4 = Float.parseFloat(sg4f.getText());
				
				float cgpa = (sg1+sg2+sg3+sg4) / 4;
				
				if (sg1 > 10 || sg2 > 10 || sg3 > 10 || sg4 > 10) {
					JOptionPane.showMessageDialog(this,"SGPA should be less than 10","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(this,"CGPA ->> " + cgpa,"Result",JOptionPane.INFORMATION_MESSAGE);
			}
			catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(this,"Invalid NumberFormat","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == doneb) {
			try {
				String name = namef.getText();
				String usn = usnf.getText();
				String addr = addf.getText();
				float sg1 = Float.parseFloat(sg1f.getText());
				float sg2 = Float.parseFloat(sg2f.getText());
				float sg3 = Float.parseFloat(sg3f.getText());
				float sg4 = Float.parseFloat(sg4f.getText());
				
				if (name.isEmpty() || usn.isEmpty() || addr.isEmpty()) {
					JOptionPane.showMessageDialog(this,"All Fields Required","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String alldetails = String.format("Name: %s,USN: %s,Address: %s,SGPA 1: %.2f,SGPA 2: %.2f,SGPA 3: %.2f,SGPA 4: %.2f",name,usn,addr,sg1,sg2,sg3,sg4);
				studentCollection.add(alldetails);
				JOptionPane.showMessageDialog(this,"All Details Stored Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
			}
			catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(this,"Invalid NumberFormat","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == displayb) {
			StringBuilder details = new StringBuilder();
			for (String sd: studentCollection) {
				details.append(sd).append("\n");
				displayA.setText(details.toString());
			}
		}
	}
	public static void main(String[] args) {
		new StudentCGPA();
	}
}
