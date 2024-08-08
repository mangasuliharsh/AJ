
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ItemPurchase extends JFrame implements ActionListener {
    JTextField itemIdField, quantityField, itemNameField, totalCostField;
    JButton computeButton, printButton;
    HashMap<String, Item> itemCatalog;

    ItemPurchase() {
        setTitle("Item Purchase");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Initializing text fields
        itemIdField = new JTextField(20);
        quantityField = new JTextField(20);
        itemNameField = new JTextField(20);
        totalCostField = new JTextField(20);

        // Initializing buttons0
        computeButton = new JButton("Compute");
        printButton = new JButton("Print");

        // Adding components to the frame
        add(new JLabel("Item ID: "));
        add(itemIdField);
        add(new JLabel("Quantity: "));
        add(quantityField);
        add(new JLabel("Item Name: "));
        add(itemNameField);
        add(new JLabel("Total Cost: "));
        add(totalCostField);
        add(computeButton);
        add(printButton);

        // Add action listeners
        computeButton.addActionListener(this);
        printButton.addActionListener(this);

        // Initialize item catalog with some items
        itemCatalog = new HashMap<>();
        itemCatalog.put("101", new Item("Pen", 10.0));
        itemCatalog.put("102", new Item("Notebook", 50.0));
        itemCatalog.put("103", new Item("Pencil", 5.0));

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == computeButton) {
            // Handling the Compute button click
            String itemId = itemIdField.getText();
            int quantity = Integer.parseInt(quantityField.getText());

            if (itemCatalog.containsKey(itemId)) {
                Item item = itemCatalog.get(itemId);
                double totalCost = item.getPrice() * quantity;
                itemNameField.setText(item.getName());
                totalCostField.setText(String.format("%.2f", totalCost));
            } else {
                JOptionPane.showMessageDialog(this, "Item not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == printButton) {
            // Handling the Print button click
            try {
                double totalCost = Double.parseDouble(totalCostField.getText());
                String[] discounts = {"No Discount", "10% Discount", "20% Discount"};
                String selectedDiscount = (String) JOptionPane.showInputDialog(this,"Select Discount","Discount Options",JOptionPane.QUESTION_MESSAGE,null,discounts,discounts[0]);

                double finalCost = totalCost;
                if ("10% Discount".equals(selectedDiscount)) {
                    finalCost *= 0.9;
                } else if ("20% Discount".equals(selectedDiscount)) {
                    finalCost *= 0.8;
                }

                JOptionPane.showMessageDialog(this, "Final Cost: " + String.format("%.2f", finalCost), "Final Cost", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Please compute total cost first.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new ItemPurchase();
    }

    // Inner class to represent an item
    class Item {
        private String name;
        private double price;

        Item(String name, double price) {
            this.name = name;
            this.price = price;
        }

        String getName() {
            return name;
        }

        double getPrice() {
            return price;
        }
    }
}
