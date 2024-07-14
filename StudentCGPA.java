
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentInfoApp extends JFrame implements ActionListener {

	private JTextField nameField, usnField, ageField, addressField, sgpa1Field, sgpa2Field, sgpa3Field, sgpa4Field, categoryField;
    private JButton computeButton, doneButton, displayButton;
    private JTextArea displayArea;
    private ArrayList<String> studentCollection;

    public StudentInfoApp() {

    	setTitle("Student Information App");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(12, 2));


        nameField = new JTextField(20);
        usnField = new JTextField(20);
        ageField = new JTextField(20);
        addressField = new JTextField(20);
        sgpa1Field = new JTextField(20);
        sgpa2Field = new JTextField(20);
        sgpa3Field = new JTextField(20);
        sgpa4Field = new JTextField(20);
        categoryField = new JTextField(20);

        computeButton = new JButton("Compute");
        doneButton = new JButton("Done");
        displayButton = new JButton("Display");

        displayArea = new JTextArea(10, 50);
        displayArea.setEditable(false);


        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("USN:"));
        add(usnField);
        add(new JLabel("Age:"));
        add(ageField);
        add(new JLabel("Address:"));
        add(addressField);
        add(new JLabel("SGPA Semester 1:"));
        add(sgpa1Field);
        add(new JLabel("SGPA Semester 2:"));
        add(sgpa2Field);
        add(new JLabel("SGPA Semester 3:"));
        add(sgpa3Field);
        add(new JLabel("SGPA Semester 4:"));
        add(sgpa4Field);
        add(new JLabel("Category:"));
        add(categoryField);
        add(computeButton);
        add(doneButton);
        add(displayButton);
        add(new JScrollPane(displayArea));


        computeButton.addActionListener(this);
        doneButton.addActionListener(this);
        displayButton.addActionListener(this);


        studentCollection = new ArrayList<>();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == computeButton) {
            try {
                float sgpa1 = Float.parseFloat(sgpa1Field.getText());
                float sgpa2 = Float.parseFloat(sgpa2Field.getText());
                float sgpa3 = Float.parseFloat(sgpa3Field.getText());
                float sgpa4 = Float.parseFloat(sgpa4Field.getText());


                float cgpa = (sgpa1 + sgpa2 + sgpa3 + sgpa4) / 4;
                JOptionPane.showMessageDialog(this, "Computed CGPA: " + cgpa, "Result", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == doneButton) {
            try {
                String name = nameField.getText();
                String usn = usnField.getText();
                int age = Integer.parseInt(ageField.getText());
                String address = addressField.getText();
                float sgpa1 = Float.parseFloat(sgpa1Field.getText());
                float sgpa2 = Float.parseFloat(sgpa2Field.getText());
                float sgpa3 = Float.parseFloat(sgpa3Field.getText());
                float sgpa4 = Float.parseFloat(sgpa4Field.getText());
                String category = categoryField.getText();


                if (name.isEmpty() || usn.isEmpty() || address.isEmpty() || category.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (age <= 0) {
                    JOptionPane.showMessageDialog(this, "Age must be a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                String studentDetails = String.format("Name: %s, USN: %s, Age: %d, Address: %s, SGPA1: %.2f, SGPA2: %.2f, SGPA3: %.2f, SGPA4: %.2f, Category: %s",
                        name, usn, age, address, sgpa1, sgpa2, sgpa3, sgpa4, category);
                studentCollection.add(studentDetails);
                JOptionPane.showMessageDialog(this, "Student details stored.", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == displayButton) {

        	StringBuilder allDetails = new StringBuilder();
            for (String details : studentCollection) {
                allDetails.append(details).append("\n");
            }
            displayArea.setText(allDetails.toString());
        }
    }

    public static void main(String[] args) {
        new StudentInfoApp();
    }
}
