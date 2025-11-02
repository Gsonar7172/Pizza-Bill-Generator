import java.util.Scanner;

public class Index {

    static final double ROMAN_PIZZA_PRICE = 100.0;
    static final double CHEESE_PIZZA_PRICE = 250.0;
    static final double VEGGIE_PIZZA_PRICE = 380.0;

    static final double CHEESE_TOPPING = 50.5;
    static final double OLIVES_TOPPING = 30.0;
    static final double MUSHROOMS_TOPPING = 45.2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("------ Pizza Bill Generator ------");

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Mobile Number: ");
        String mobile = sc.nextLine();

        if (mobile.length() != 10 || !mobile.matches("\\d+")) {
            System.out.println("❌ Invalid mobile number. Must be 10 digits.");
            return;
        }

        System.out.println("\nSelect Pizza Type:");
        System.out.println("1. Roman Pizza (Rs.100.00)");
        System.out.println("2. Cheese Pizza (Rs.250.00)");
        System.out.println("3. Veggie Pizza (Rs.380.00)");
        System.out.print("Enter choice (1-3): ");
        int pizzaChoice = sc.nextInt();

        double pizzaPrice = 0;
        String pizzaName = "";

        switch (pizzaChoice) {
            case 1:
                pizzaPrice = ROMAN_PIZZA_PRICE;
                pizzaName = "Roman Pizza";
                break;
            case 2:
                pizzaPrice = CHEESE_PIZZA_PRICE;
                pizzaName = "Cheese Pizza";
                break;
            case 3:
                pizzaPrice = VEGGIE_PIZZA_PRICE;
                pizzaName = "Veggie Pizza";
                break;
            default:
                System.out.println("❌ Invalid choice.");
                return;
        }

        System.out.print("Enter number of pizzas: ");
        int numPizzas = sc.nextInt();

        System.out.println("\nSelect Toppings (Y/N):");
        System.out.print("Extra Cheese (Rs.50.50)? ");
        boolean cheese = sc.next().equalsIgnoreCase("Y");

        System.out.print("Olives (Rs.30.00)? ");
        boolean olives = sc.next().equalsIgnoreCase("Y");

        System.out.print("Mushrooms (Rs.45.20)? ");
        boolean mushrooms = sc.next().equalsIgnoreCase("Y");

        double toppingsPrice = 0;
        if (cheese) toppingsPrice += CHEESE_TOPPING;
        if (olives) toppingsPrice += OLIVES_TOPPING;
        if (mushrooms) toppingsPrice += MUSHROOMS_TOPPING;

        double subtotal = (pizzaPrice + toppingsPrice) * numPizzas;
        double tax = subtotal * 0.08;
        double total = subtotal + tax;

        System.out.println("\n------ BILL ------");
        System.out.println("Customer: " + name);
        System.out.println("Mobile: " + mobile);
        System.out.println("Pizza: " + pizzaName);
        System.out.println("Quantity: " + numPizzas);
        System.out.println("Toppings Cost: Rs." + toppingsPrice);
        System.out.println("Subtotal: Rs." + subtotal);
        System.out.println("Tax (8%): Rs." + tax);
        System.out.println("Total: Rs." + total);
        System.out.println("\n✅ Thanks for visiting!");
    }
}
