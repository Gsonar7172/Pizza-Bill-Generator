import javax.swing.*;  // Import Swing components like Jframe, Jlabel ,Jtextfield
import java.awt.*;  // Import AWT components for layout, grid layout and graphics
import java.awt.event.*;  // for event handling
import javax.swing.text.*;  // Import Swing text classes for text formatting for eg. mobile no.

public class PizzaBillGeneratorGUI extends JFrame {  // Define the class for the Pizza Bill Generator GUI that extends JFrame (generate window)

    // set Prices for different pizza types
    static final double ROMAN_PIZZA_PRICE = 100.0;
    static final double CHEESE_PIZZA_PRICE = 250.0;
    static final double VEGGIE_PIZZA_PRICE = 380.0;

    // set Prices for different pizza toppings
    static final double CHEESE_TOPPING = 50.5;
    static final double OLIVES_TOPPING = 30.0;
    static final double MUSHROOMS_TOPPING = 45.2;

    // Define GUI components
    private Choice pizzaChoice; //choice block for user
    private JTextField numOfPizzas, customerName, customerMobile;
    private JCheckBox cheeseTopping, olivesTopping, mushroomsTopping; //using checkbox user select multiple toping options
    private JButton calculateButton;
    private JTextArea billArea;

    public PizzaBillGeneratorGUI() {  // Constructor to set up the GUI
        setTitle("Pizza Bill Generator");  // Set the title of the window
        setSize(500, 600);  // Set the window size (500px width and 600px height)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Close the application when the window is closed
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));  // Set a vertical layout for components

        // Set background color of the window
        getContentPane().setBackground(new Color(255, 239, 186));

        // Create the main container for the form
        JPanel formPanel = new JPanel();  // Create a new panel for the form
        formPanel.setLayout(new GridBagLayout());  // Use GridBagLayout to align components properly
        GridBagConstraints gbc = new GridBagConstraints();  // Create a new GridBagConstraints object for layout
        formPanel.setBackground(new Color(255, 255, 255));  // Set the background color of the form to white
        formPanel.setBorder(BorderFactory.createTitledBorder("Customer Details"));  // Add a border with title to the form

        // Create and add customer name label and text field
        JLabel nameLabel = new JLabel("Customer Name:");  // Label for customer name
        gbc.gridx = 0;  // Set grid position
        gbc.gridy = 0;  // Set grid position
        gbc.anchor = GridBagConstraints.WEST;  // Align label to the left
        formPanel.add(nameLabel, gbc);  // Add label to the form panel

        customerName = new JTextField(20);  // Create a text field for entering the customer name
        gbc.gridx = 1;  // Set grid position
        formPanel.add(customerName, gbc);  // Add the text field to the form panel

        // Create and add customer mobile number label and text field
        JLabel mobileLabel = new JLabel("Customer Mobile Number:");  // Label for mobile number
        gbc.gridx = 0;  // Set grid position
        gbc.gridy = 1;  // Set grid position
        formPanel.add(mobileLabel, gbc);  // Add label to the form panel

        customerMobile = new JTextField(10);  // Create a text field for entering mobile number
        gbc.gridx = 1;  // Set grid position
        formPanel.add(customerMobile, gbc);  // Add the mobile number text field to the form panel

        // Add a filter to allow only 10-digit mobile numbers
        ((AbstractDocument) customerMobile.getDocument()).setDocumentFilter(new MobileNumberFilter());

        add(formPanel);  // Add the form panel to the main window

        // Create and set up pizza selection panel
        JPanel pizzaPanel = new JPanel();  // Create a panel for pizza selection
        pizzaPanel.setLayout(new GridBagLayout());  // Use GridBagLayout for pizza selection panel
        pizzaPanel.setBackground(new Color(255, 255, 255));  // Set background color to white
        pizzaPanel.setBorder(BorderFactory.createTitledBorder("Pizza Selection"));  // Set a border with title

        JLabel pizzaLabel = new JLabel("Select your pizza type:");  // Label for pizza type selection
        gbc.gridx = 0;  // Set grid position
        gbc.gridy = 0;  // Set grid position
        gbc.anchor = GridBagConstraints.WEST;  // Align to the left
        pizzaPanel.add(pizzaLabel, gbc);  // Add the label to the pizza panel

        pizzaChoice = new Choice();  // Create a drop-down menu for selecting pizza type
        pizzaChoice.add("Roman Pizza (Rs.100.00)");  // Add options for different pizzas
        pizzaChoice.add("Cheese Pizza (Rs.250.00)");
        pizzaChoice.add("Veggie Pizza (Rs.380.00)");
        gbc.gridx = 1;  // Set grid position
        pizzaPanel.add(pizzaChoice, gbc);  // Add the pizza choice drop-down to the pizza panel

        JLabel numPizzasLabel = new JLabel("Enter the number of pizzas:");  // Label for entering the number of pizzas
        gbc.gridx = 0;  // Set grid position
        gbc.gridy = 1;  // Set grid position
        pizzaPanel.add(numPizzasLabel, gbc);  // Add the label to the pizza panel

        numOfPizzas = new JTextField(5);  // Create a text field for entering the number of pizzas
        gbc.gridx = 1;  // Set grid position
        pizzaPanel.add(numOfPizzas, gbc);  // Add the text field to the pizza panel

        add(pizzaPanel);  // Add the pizza panel to the main window

        // Create and set up toppings selection panel
        JPanel toppingsPanel = new JPanel();  // Create a panel for toppings selection
        toppingsPanel.setLayout(new GridBagLayout());  // Use GridBagLayout for toppings selection panel
        toppingsPanel.setBackground(new Color(255, 255, 255));  // Set background color to white
        toppingsPanel.setBorder(BorderFactory.createTitledBorder("Select Toppings"));  // Set a border with title

        cheeseTopping = new JCheckBox("Extra Cheese (Rs.50.50)");  // Create a check box for extra cheese topping
        gbc.gridx = 0;  // Set grid position
        gbc.gridy = 0;  // Set grid position
        toppingsPanel.add(cheeseTopping, gbc);  // Add check box to the toppings panel

        olivesTopping = new JCheckBox("Olives (Rs.30.00)");  // Create a check box for olives topping
        gbc.gridy = 1;  // Set grid position
        toppingsPanel.add(olivesTopping, gbc);  // Add check box to the toppings panel

        mushroomsTopping = new JCheckBox("Mushrooms (Rs.45.20)");  // Create a check box for mushrooms topping
        gbc.gridy = 2;  // Set grid position
        toppingsPanel.add(mushroomsTopping, gbc);  // Add check box to the toppings panel

        add(toppingsPanel);  // Add the toppings panel to the main window

        // Create and set up button for calculating the bill
        JPanel buttonPanel = new JPanel();  // Create a panel for the button
        calculateButton = new JButton("Calculate Bill");  // Create a button with the label "Calculate Bill"
        buttonPanel.add(calculateButton);  // Add the button to the button panel
        add(buttonPanel);  // Add the button panel to the main window

        // Create and set up the area to display the bill
        billArea = new JTextArea(10, 40);  // Create a text area with 10 rows and 40 columns
        billArea.setEditable(false);  // Set the text area to be non-editable
        billArea.setBackground(new Color(255, 255, 255));  // Set background color to white
        billArea.setForeground(new Color(0, 0, 0));  // Set text color to black
        billArea.setFont(new Font("Courier New", Font.BOLD, 14));  // Set font style and size
        billArea.setBorder(BorderFactory.createTitledBorder("Your Bill"));  // Add a border with title "Your Bill"
        add(billArea);  // Add the bill area to the main window

        // Add an action listener for the calculate button
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {  // When the button is clicked, call the generateBill method
                generateBill();
            }
        });
    }

    // Method to filter mobile number input (only numeric and max 10 digits)
    class MobileNumberFilter extends DocumentFilter {
        @Override     //@Override ensures that the method is correctly overriding a method from the superclass.
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("[0-9]{1}")) {  // Allow only numeric input
                if (fb.getDocument().getLength() < 10) {  // Limit input to 10 digits
                    super.insertString(fb, offset, string, attr);
                }
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text.matches("[0-9]{1}")) {  // Allow only numeric input
                if (fb.getDocument().getLength() - length + text.length() <= 10) {  // Limit input to 10 digits
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        }
    }

    private void generateBill() {  // Method to generate and display the bill
        // Validate customer details
        String name = customerName.getText().trim();  // Get and trim customer name
        String mobile = customerMobile.getText().trim();  // Get and trim mobile number
        if (name.isEmpty() || mobile.isEmpty()) {  // If name or mobile is empty, display an error
            billArea.setText("Please enter valid customer details!");
            return;
        }

        // Validate mobile number (must be 10 digits)
        if (mobile.length() != 10 || !mobile.matches("[0-9]+")) {
            billArea.setText("Mobile number must be exactly 10 digits.");
            return;
        }

        int pizzaChoiceIndex = pizzaChoice.getSelectedIndex();  // Get the selected pizza type index
        double pizzaPrice = 0.0;  // Initialize pizza price

        // Determine the pizza price based on the selected pizza
        switch (pizzaChoiceIndex) {
            case 0: pizzaPrice = ROMAN_PIZZA_PRICE; break;
            case 1: pizzaPrice = CHEESE_PIZZA_PRICE; break;
            case 2: pizzaPrice = VEGGIE_PIZZA_PRICE; break;
        }

        // Get the number of pizzas
        int numPizzas;
        try {
            numPizzas = Integer.parseInt(numOfPizzas.getText());  // Parse the number of pizzas
        } catch (NumberFormatException e) {
            billArea.setText("Please enter a valid number for pizzas.");
            return;
        }

        // Calculate the total topping price
        double toppingsPrice = 0.0;
        if (cheeseTopping.isSelected()) toppingsPrice += CHEESE_TOPPING;
        if (olivesTopping.isSelected()) toppingsPrice += OLIVES_TOPPING;
        if (mushroomsTopping.isSelected()) toppingsPrice += MUSHROOMS_TOPPING;

        // Calculate the subtotal, tax, and total bill
        double subtotal = (pizzaPrice + toppingsPrice) * numPizzas;
        double tax = subtotal * 0.08;  // 8% tax
        double total = subtotal + tax;

        // Display the bill in the JTextArea
        billArea.setText("");  // Clear previous bill
        billArea.append("------ Pizza Bill ------\n");  // Display header
        billArea.append("Customer: " + name + "\n");  // Display customer name
        billArea.append("Mobile: " + mobile + "\n\n");  // Display customer mobile
        billArea.append("Pizza Type: " + getPizzaName(pizzaChoiceIndex) + "\n");  // Display pizza type
        billArea.append("Number of Pizzas: " + numPizzas + "\n");  // Display number of pizzas
        billArea.append("Pizza Price: Rs." + pizzaPrice + "\n");  // Display pizza price
        billArea.append("Toppings: Rs." + toppingsPrice + "\n");  // Display toppings price
        billArea.append("Subtotal: Rs." + subtotal + "\n");  // Display subtotal
        billArea.append("Tax (8%): Rs." + tax + "\n");  // Display tax
        billArea.append("Total: Rs." + total + "\n\n");  // Display total amount
        billArea.append("Thanks For Visiting!\n");  // Thank you message
    }

    // Method to get pizza name based on the choice
    private String getPizzaName(int choice) {
        switch (choice) {
            case 0: return "Roman Pizza";
            case 1: return "Cheese Pizza";
            case 2: return "Veggie Pizza";
            default: return "Unknown";
        }
    }

    public static void main(String[] args) {  // Main method to run the application
        // Create and show the Pizza Bill Generator GUI
        PizzaBillGeneratorGUI app = new PizzaBillGeneratorGUI();
        app.setVisible(true);  // Make the window visible
    }
}
