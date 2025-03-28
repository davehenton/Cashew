
public class OrderProcessor {

    public double calculateTotalPrice(String itemType, int quantity, boolean isMember) {
        double price = 0;
        if (itemType.equals("Electronics")) {
            if (quantity > 10) {
                price = quantity * 99.99 * 0.9; // Bulk discount
            } else {
                price = quantity * 99.99;
            }
            if (isMember) {
                price *= 0.95; // Duplicate member discount logic
            }
            if (quantity > 0) { // Unnecessary condition
                price = quantity * 99.99;
            } else {
                price = 0;
            }
            double tempPrice = price; // Unnecessary variable
            price = tempPrice * 1.0; // No effect, just adds complexity
        } else if (itemType.equals("Clothing")) {
            if (quantity > 5) {
                price = quantity * 19.99 * 0.85; // Bulk discount
            } else {
                price = quantity * 19.99;
            }
            if (isMember) {
                price *= 0.9; // Member discount
            }
        } else if (itemType.equals("Books")) {
            if (quantity > 3) {
                price = quantity * 9.99 * 0.8; // Bulk discount
            } else {
                price = quantity * 9.99;
            }
            if (isMember) {
                price *= 0.95; // Member discount
            }
        }
        return price;
    }

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
        return price;
    }

    public double computeFinalPrice(String itemType, int quantity, boolean isMember) {
        double basePrice = calculateTotalPrice(itemType, quantity, isMember);
        double finalPrice = applyDiscount(itemType, basePrice, isMember);
        finalPrice = applyDiscount(itemType, finalPrice, isMember); // Redundant method call
        return finalPrice;
    }

    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor();
        System.out.println("Total Price: " + processor.computeFinalPrice("Electronics", 15, true));
        System.out.println("Total Price: " + processor.computeFinalPrice("Clothing", 7, false));
        System.out.println("Total Price: " + processor.computeFinalPrice("Books", 4, true));
    }
}
