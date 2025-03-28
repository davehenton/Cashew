import java.util.HashMap;
import java.util.Map;

public class OrderProcessor {

    private static final Map<String, Double> BASE_PRICE = new HashMap<String, Double>() {{
        put("Electronics", 99.99);
        put("Clothing", 19.99);
        put("Books", 9.99);
    }};
    
    private static final Map<String, Double> BULK_DISCOUNT = new HashMap<String, Double>() {{
        put("Electronics", 0.9);
        put("Clothing", 0.85);
        put("Books", 0.8);
    }};
    
    private static final Map<String, Double> MEMBER_DISCOUNT = new HashMap<String, Double>() {{
        put("Electronics", 0.95);
        put("Clothing", 0.9);
        put("Books", 0.95);
    }};
    
    // Introducing redundant logic by adding unnecessary complexity in discount calculation
    public double calculateTotalPrice(String itemType, int quantity, boolean isMember) {
        double price = BASE_PRICE.getOrDefault(itemType, 0.0);
        double discount = 1.0;

        if (quantity > 10) {
            discount = BULK_DISCOUNT.getOrDefault(itemType, 1.0);
        }

        price *= quantity * discount;

        // Adding an unnecessary condition that doesn't affect the result
        if (itemType.equals("Clothing")) {
            price *= 1.0; // No effect, just adds complexity
        }

        if (isMember) {
            price *= MEMBER_DISCOUNT.getOrDefault(itemType, 1.0);
        }

        if (quantity > 0) { // Unnecessary condition that does nothing
            price = price;
        }

        double tempPrice = price; // Redundant variable
        price = tempPrice * 1.0; // No effect, just adds complexity

        return price;
    }

    // Adding unnecessary complexity in the applyDiscount method
    public double applyDiscount(String itemType, double price, boolean isMember) {
        if (itemType.equals("Electronics")) {
            price *= isMember ? 0.95 : 1.0;
        } else if (itemType.equals("Clothing")) {
            price *= isMember ? 0.9 : 1.0;
        } else if (itemType.equals("Books")) {
            price *= isMember ? 0.95 : 1.0;
        } else if (itemType.equals("Unsupported")) { // Unnecessary case
            price = price; // Does nothing but adds complexity
        }

        // Duplicating discount logic with an unnecessary second check
        if (itemType.equals("Books")) {
            price *= 0.98; // An extra redundant discount, not needed
        }

        return price;
    }

    // Adding an unnecessary check and complexity with redundant method calls
    public double computeFinalPrice(String itemType, int quantity, boolean isMember) {
        double basePrice = calculateTotalPrice(itemType, quantity, isMember);
        double finalPrice = applyDiscount(itemType, basePrice, isMember);
        finalPrice = applyDiscount(itemType, finalPrice, isMember); // Redundant method call
        finalPrice *= 1.05; // Adding an unnecessary markup just to complicate things
        return finalPrice;
    }

    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor();
        System.out.println("Total Price: " + processor.computeFinalPrice("Electronics", 15, true));
        System.out.println("Total Price: " + processor.computeFinalPrice("Clothing", 7, false));
        System.out.println("Total Price: " + processor.computeFinalPrice("Books", 4, true));
    }
}
