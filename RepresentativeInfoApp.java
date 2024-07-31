
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RepresentativeInfoApp extends JFrame implements ActionListener {
    JFrame jf1, jf2;
    JTextField repnof, repnamef, repstatef, repcomissionf, repratef;
    JButton submit, display, addNew;

    RepresentativeInfoApp() {
        jf1 = new JFrame();
        jf1.setTitle("Details");
        jf1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf1.setSize(600, 600);
        jf1.setLayout(new GridLayout(20, 2));

        repnof = new JTextField(20);
        repnamef = new JTextField(20);
        repstatef = new JTextField(20);
        repcomissionf = new JTextField(20);
        repratef = new JTextField(20);

        submit = new JButton("Submit");
        display = new JButton("Display");

        jf1.add(new JLabel("Representative NO: "));
        jf1.add(repnof);
        jf1.add(new JLabel("Representative Name: "));
        jf1.add(repnamef);
        jf1.add(new JLabel("Representative State: "));
        jf1.add(repstatef);
        jf1.add(new JLabel("Representative Commission: "));
        jf1.add(repcomissionf);
        jf1.add(new JLabel("Representative Rate: "));
        jf1.add(repratef);
        jf1.add(submit);
        jf1.add(display);

        submit.addActionListener(this);
        display.addActionListener(this);

        jf1.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            try {
                int repno = Integer.parseInt(repnof.getText());
                String repname = repnamef.getText();
                String repstate = repstatef.getText();
                float repcomission = Float.parseFloat(repcomissionf.getText());
                float reprate = Float.parseFloat(repratef.getText());

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "myadmin123");
                PreparedStatement ps = con.prepareStatement("insert into rep values(?,?,?,?,?)");
                ps.setInt(1, repno);
                ps.setString(2, repname);
                ps.setString(3, repstate);
                ps.setFloat(4, repcomission);
                ps.setFloat(5, reprate);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(jf1, "Stored Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                con.close();
            } catch (SQLException s) {
                s.printStackTrace();
                System.out.println("SQL Error");
            }
        }
        if (e.getSource() == display) {
            try {
                jf2 = new JFrame();
                jf2.setTitle("Details");
                jf2.setDefaultCloseOperation(EXIT_ON_CLOSE);
                jf2.setSize(800, 600); 
                jf2.setLayout(new BorderLayout()); 

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "myadmin123");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("Select * from rep");

                JTextArea textArea = new JTextArea();
                textArea.setFont(new Font("Arial", Font.PLAIN, 14)); 
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);

                while (rs.next()) {
                    textArea.append("Representative NO: " + rs.getInt(1) + "\n");
                    textArea.append("Representative Name: " + rs.getString(2) + "\n");
                    textArea.append("Representative State: " + rs.getString(3) + "\n");
                    textArea.append("Representative Commission: " + rs.getFloat(4) + "\n");
                    textArea.append("Representative Rate: " + rs.getFloat(5) + "\n");
                    textArea.append("\n");
                }

                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(780, 580)); 
                jf2.add(scrollPane, BorderLayout.CENTER); 

                addNew = new JButton("Add New");
                addNew.addActionListener(this);
                JPanel buttonPanel = new JPanel();
                buttonPanel.add(addNew);
                jf2.add(buttonPanel, BorderLayout.SOUTH); 

                con.close();
                
                jf2.setVisible(true);
            } catch (SQLException s) {
                s.printStackTrace();
                System.out.println("SQL Error");
            }
        }
        if (e.getSource() == addNew) {
            jf2.setVisible(false); 
            jf1.setVisible(true); 
        }
    }

    public static void main(String[] args) {
        new RepresentativeInfoApp();
    }
}
