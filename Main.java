import java.util.*;

public class Main {
    public static double parsePrice(String priceStr) {
        if (priceStr == null || priceStr.isBlank()) {
            return 0.00; // Handle null or blank strings as 0.00
        }

        try {
            // Check for "Total price:" and return 0.00
            if (priceStr.equalsIgnoreCase("Total price:")) {
                return 0.00;
            }

            // If the price is a range (e.g., "10.99-20.99"), take the lower bound
            if (priceStr.contains("-")) {
                String[] range = priceStr.split("-");
                priceStr = range[0].trim(); // Use the first value in the range
            }

            // Remove all non-numeric characters except '.'
            priceStr = priceStr.replaceAll("[^0-9.]", "");

            if (priceStr.equals(".") || priceStr.isEmpty()) {
                return 0.0; // Invalid price format, return 0.0
            }

            String[] parts = priceStr.split("\\.");
            if (parts.length > 2) { // Too many decimal points indicate repetition
                // Take the first valid two segments (e.g., "6.94" from "6.946.94")
                priceStr = parts[0] + "." + parts[1];
            }

            // Ensure the string is not empty and matches a valid numeric pattern
            if (priceStr.isEmpty() || !priceStr.matches("[0-9]+(\\.[0-9]+)?")) {
                throw new NumberFormatException("Invalid price format: " + priceStr);
            }

            return Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            // Log the specific issue and return 0.00
            System.err.println("Error parsing price: " + priceStr);
            return 0.00;
        }
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        List<Map<String, String>> products = CSVReader.readCSV("amazon-product-data.csv");

        for (Map<String, String> product : products) {
            try {
                String productID = product.get("Uniq Id").trim();
                String name = product.get("Product Name").trim();
                String category = product.get("Category").trim();
                String priceStr = product.get("Price").trim();

                double price = parsePrice(priceStr); // Use the parsePrice method to clean and parse the price

                tree.insert(productID, name, category, price);

            } catch (Exception e) {
                System.err.println("Error processing product: " + product);
                e.printStackTrace(); // Log the error for debugging
            }
        }

        System.out.println("Search Results:");
        tree.displayNode(tree.search("bc178f33a04dbccefa95b165f8b56830"));
        tree.displayNode(tree.search("18018b6bc416dab347b1b7db79994afa"));
        tree.displayNode(tree.search("84fb43b933850dc05e57a162c5ba1702"));

        System.out.println("Insertion Results:");
        try {
            tree.insert("q23r7fv6gyber5ilaghysa", "Snowboard - All ages", "Outdoor Sports", 467.63);
            System.out.println("Product successfully inserted!");
        } catch (Exception e) {
            System.err.println("Error inserting product.");
        }
        //Duplicate insertion
        try {
            String duplicateID = "18018b6bc416dab347b1b7db79994afa";
            String duplicateName = "Guillow Airplane Design Studio with Travel Case Building Kit";
            String duplicateCategory = "Toys & Games | Hobbies | Models & Model Kits | Model Kits | Airplane & Jet Kits";
            double duplicatePrice = 28.91;
            tree.insert(duplicateID, duplicateName, duplicateCategory, duplicatePrice);
        } catch (Exception e) {
            System.err.println("Error processing duplicate product");
        }
    }
}
