
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CustomerPurchaseApp extends JFrame implements ActionListener {
    private JTextField usernameField, passwordField, customerIdField, mobileField, itemIdField, quantityField;
    private JButton loginButton, checkCustomerButton, addItemButton, printButton;
    private JTextArea itemDetailsArea;
    private JComboBox<String> discountComboBox;

    private Map<String, String> customerData;
    private Map<String, String> itemData;
    private Map<String, Double> itemPrices;

    public CustomerPurchaseApp() {
        setTitle("Customer Purchase App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(11, 2, 10, 10));
        add(mainPanel, BorderLayout.CENTER);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        mainPanel.add(new JLabel("Username:"));
        mainPanel.add(usernameField);
        mainPanel.add(new JLabel("Password:"));
        mainPanel.add(passwordField);
        mainPanel.add(new JLabel("")); // Empty label for padding
        mainPanel.add(loginButton);

        customerIdField = new JTextField(20);
        mobileField = new JTextField(20);
        itemIdField = new JTextField(20);
        quantityField = new JTextField(20);
        checkCustomerButton = new JButton("Check Customer");
        addItemButton = new JButton("Add Item");
        printButton = new JButton("Print");
        itemDetailsArea = new JTextArea(10, 50);
        itemDetailsArea.setEditable(false);
        discountComboBox = new JComboBox<>(new String[]{"No Discount", "10% Discount", "20% Discount"});

        mainPanel.add(new JLabel("Customer ID:"));
        mainPanel.add(customerIdField);
        mainPanel.add(new JLabel("Mobile Number:"));
        mainPanel.add(mobileField);
        mainPanel.add(new JLabel("")); // Empty label for padding
        mainPanel.add(checkCustomerButton);
        mainPanel.add(new JLabel("Item ID:"));
        mainPanel.add(itemIdField);
        mainPanel.add(new JLabel("Quantity:"));
        mainPanel.add(quantityField);
        mainPanel.add(new JLabel("")); // Empty label for padding
        mainPanel.add(addItemButton);
        mainPanel.add(new JLabel("Discount:"));
        mainPanel.add(discountComboBox);
        mainPanel.add(new JLabel("")); // Empty label for padding
        mainPanel.add(printButton);
        mainPanel.add(new JLabel("Item Details:"));
        mainPanel.add(new JScrollPane(itemDetailsArea));

        loginButton.addActionListener(this);
        checkCustomerButton.addActionListener(this);
        addItemButton.addActionListener(this);
        printButton.addActionListener(this);

        customerData = new HashMap<>();
        itemData = new HashMap<>();
        itemPrices = new HashMap<>();

        customerData.put("123", "Alice");
        customerData.put("456", "Bob");
        itemData.put("101", "Pen");
        itemData.put("102", "Notebook");
        itemPrices.put("101", 1.5);
        itemPrices.put("102", 2.0);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if ("admin".equals(username) && "password".equals(password)) {
                JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == checkCustomerButton) {
            String customerId = customerIdField.getText();
            String mobile = mobileField.getText();

            if (customerData.containsKey(customerId)) {
                JOptionPane.showMessageDialog(this, "Customer exists: " + customerData.get(customerId), "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                customerData.put(customerId, mobile);
                JOptionPane.showMessageDialog(this, "New customer added: " + mobile, "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getSource() == addItemButton) {
            String itemId = itemIdField.getText();
            int quantity;

            try {
                quantity = Integer.parseInt(quantityField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (itemData.containsKey(itemId)) {
                String itemName = itemData.get(itemId);
                double itemPrice = itemPrices.get(itemId) * quantity;
                itemDetailsArea.append(itemName + " - Quantity: " + quantity + " - Total Cost: $" + itemPrice + "\n");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid item ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == printButton) {
            String discount = (String) discountComboBox.getSelectedItem();
            StringBuilder receipt = new StringBuilder("Receipt:\n");
            receipt.append(itemDetailsArea.getText());
            receipt.append("Discount: ").append(discount).append("\n");

            JOptionPane.showMessageDialog(this, receipt.toString(), "Receipt", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new CustomerPurchaseApp();
    }
}
