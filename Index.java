import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.text.*;  

public class Index extends JFrame {  // Class name now starts with a capital letter

    // Prices for pizza types
    static final double ROMAN_PIZZA_PRICE = 100.0;
    static final double CHEESE_PIZZA_PRICE = 250.0;
    static final double VEGGIE_PIZZA_PRICE = 380.0;

    // Prices for toppings
    static final double CHEESE_TOPPING = 50.5;
    static final double OLIVES_TOPPING = 30.0;
    static final double MUSHROOMS_TOPPING = 45.2;

    // GUI components
    private Choice pizzaChoice;
    private JTextField numOfPizzas, customerName, customerMobile;
    private JCheckBox cheeseTopping, olivesTopping, mushroomsTopping;
    private JButton calculateButton;
    private JTextArea billArea;

    public Index() {
        setTitle("Pizza Bill Generator");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(new Color(255, 239, 186));

        // ---- Customer details panel ----
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createTitledBorder("Customer Details"));

        JLabel nameLabel = new JLabel("Customer Name:");
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(nameLabel, gbc);

        customerName = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(customerName, gbc);

        JLabel mobileLabel = new JLabel("Customer Mobile Number:");
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(mobileLabel, gbc);

        customerMobile = new JTextField(10);
        gbc.gridx = 1;
        formPanel.add(customerMobile, gbc);

        // Allow only digits, max 10 characters
        ((AbstractDocument) customerMobile.getDocument()).setDocumentFilter(new MobileNumberFilter());
        add(formPanel);

        // ---- Pizza selection ----
        JPanel pizzaPanel = new JPanel(new GridBagLayout());
        pizzaPanel.setBackground(Color.WHITE);
        pizzaPanel.setBorder(BorderFactory.createTitledBorder("Pizza Selection"));

        JLabel pizzaLabel = new JLabel("Select your pizza type:");
        gbc.gridx = 0; gbc.gridy = 0;
        pizzaPanel.add(pizzaLabel, gbc);

        pizzaChoice = new Choice();
        pizzaChoice.add("Roman Pizza (Rs.100.00)");
        pizzaChoice.add("Cheese Pizza (Rs.250.00)");
        pizzaChoice.add("Veggie Pizza (Rs.380.00)");
        gbc.gridx = 1;
        pizzaPanel.add(pizzaChoice, gbc);

        JLabel numPizzasLabel = new JLabel("Enter the number of pizzas:");
        gbc.gridx = 0; gbc.gridy = 1;
        pizzaPanel.add(numPizzasLabel, gbc);

        numOfPizzas = new JTextField(5);
        gbc.gridx = 1;
        pizzaPanel.add(numOfPizzas, gbc);
        add(pizzaPanel);

        // ---- Toppings ----
        JPanel toppingsPanel = new JPanel(new GridBagLayout());
        toppingsPanel.setBackground(Color.WHITE);
        toppingsPanel.setBorder(BorderFactory.createTitledBorder("Select Toppings"));

        cheeseTopping = new JCheckBox("Extra Cheese (Rs.50.50)");
        gbc.gridx = 0; gbc.gridy = 0;
        toppingsPanel.add(cheeseTopping, gbc);

        olivesTopping = new JCheckBox("Olives (Rs.30.00)");
        gbc.gridy = 1;
        toppingsPanel.add(olivesTopping, gbc);

        mushroomsTopping = new JCheckBox("Mushrooms (Rs.45.20)");
        gbc.gridy = 2;
        toppingsPanel.add(mushroomsTopping, gbc);
        add(toppingsPanel);

        // ---- Button ----
        JPanel buttonPanel = new JPanel();
        calculateButton = new JButton("Calculate Bill");
        buttonPanel.add(calculateButton);
        add(buttonPanel);

        // ---- Bill area ----
        billArea = new JTextArea(10, 40);
        billArea.setEditable(false);
        billArea.setBackground(Color.WHITE);
        billArea.setForeground(Color.BLACK);
        billArea.setFont(new Font("Courier New", Font.BOLD, 14));
        billArea.setBorder(BorderFactory.createTitledBorder("Your Bill"));
        add(billArea);

        // ---- Button action ----
        calculateButton.addActionListener(e -> generateBill());
    }

    // ---- Mobile number validation ----
    class MobileNumberFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (string.matches("[0-9]{1}") && fb.getDocument().getLength() < 10) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text.matches("[0-9]{1}") && fb.getDocument().getLength() - length + text.length() <= 10) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    // ---- Bill generation ----
    private void generateBill() {
        String name = customerName.getText().trim();
        String mobile = customerMobile.getText().trim();

        if (name.isEmpty() || mobile.isEmpty()) {
            billArea.setText("Please enter valid customer details!");
            return;
        }
        if (mobile.length() != 10 || !mobile.matches("[0-9]+")) {
            billArea.setText("Mobile number must be exactly 10 digits.");
            return;
        }

        int pizzaChoiceIndex = pizzaChoice.getSelectedIndex();
        double pizzaPrice = 0.0;
        switch (pizzaChoiceIndex) {
            case 0: pizzaPrice = ROMAN_PIZZA_PRICE; break;
            case 1: pizzaPrice = CHEESE_PIZZA_PRICE; break;
            case 2: pizzaPrice = VEGGIE_PIZZA_PRICE; break;
        }

        int numPizzas;
        try {
            numPizzas = Integer.parseInt(numOfPizzas.getText());
        } catch (NumberFormatException e) {
            billArea.setText("Please enter a valid number for pizzas.");
            return;
        }

        double toppingsPrice = 0.0;
        if (cheeseTopping.isSelected()) toppingsPrice += CHEESE_TOPPING;
        if (olivesTopping.isSelected()) toppingsPrice += OLIVES_TOPPING;
        if (mushroomsTopping.isSelected()) toppingsPrice += MUSHROOMS_TOPPING;

        double subtotal = (pizzaPrice + toppingsPrice) * numPizzas;
        double tax = subtotal * 0.08;
        double total = subtotal + tax;

        billArea.setText("");
        billArea.append("------ Pizza Bill ------\n");
        billArea.append("Customer: " + name + "\n");
        billArea.append("Mobile: " + mobile + "\n\n");
        billArea.append("Pizza Type: " + getPizzaName(pizzaChoiceIndex) + "\n");
        billArea.append("Number of Pizzas: " + numPizzas + "\n");
        billArea.append("Pizza Price: Rs." + pizzaPrice + "\n");
        billArea.append("Toppings: Rs." + toppingsPrice + "\n");
        billArea.append("Subtotal: Rs." + subtotal + "\n");
        billArea.append("Tax (8%): Rs." + tax + "\n");
        billArea.append("Total: Rs." + total + "\n\n");
        billArea.append("Thanks For Visiting!\n");
    }

    private String getPizzaName(int choice) {
        switch (choice) {
            case 0: return "Roman Pizza";
            case 1: return "Cheese Pizza";
            case 2: return "Veggie Pizza";
            default: return "Unknown";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Index app = new Index();
            app.setVisible(true);
        });
    }
}
