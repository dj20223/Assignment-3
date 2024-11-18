import java.io.*;
import java.util.*;

public class CSVReader {
    public static List<Map<String, String>> readCSV(String filePath) {
        List<Map<String, String>> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine();
            if (headerLine == null) {
                throw new IllegalArgumentException("The CSV file is empty.");
            }

            // Splitting the header
            String[] headers = parseLine(headerLine);

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = parseLine(line);
                Map<String, String> product = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    product.put(headers[i], i < values.length ? values[i] : "");
                }
                products.add(product);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return products;
    }

    private static String[] parseLine(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);

            if (currentChar == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    currentToken.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (currentChar == ',' && !inQuotes) {
                tokens.add(currentToken.toString());
                currentToken.setLength(0);
            } else {
                currentToken.append(currentChar);
            }
        }

        tokens.add(currentToken.toString());
        return tokens.toArray(new String[0]);
    }
}