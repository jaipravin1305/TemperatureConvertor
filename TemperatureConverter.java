import java.util.Scanner;

public class TemperatureConverter {

    public static void main(String[] args) {
        // Initialize the scanner for user input
        Scanner scanner = new Scanner(System.in);
        
        // Display a welcome message and prompt user for input
        System.out.println("Welcome to the Temperature Converter!");
        
        while (true) {
            // Get temperature value from the user
            System.out.print("\nEnter the temperature value to convert: ");
            double tempValue = getValidTemperature(scanner);
            
            // Get source and target scales from the user
            System.out.print("Enter the source temperature scale (C for Celsius, F for Fahrenheit, K for Kelvin): ");
            char sourceScale = getValidScale(scanner);
            System.out.print("Enter the target temperature scale (C for Celsius, F for Fahrenheit, K for Kelvin): ");
            char targetScale = getValidScale(scanner);
            
            // Convert the temperature based on the source and target scales
            double convertedTemp = convertTemperature(tempValue, sourceScale, targetScale);
            
            // Display the result
            System.out.printf("The converted temperature is: %.2f %s\n", convertedTemp, getScaleName(targetScale));
            
            // Ask if the user wants to continue
            System.out.print("\nWould you like to convert another temperature? (Y/N): ");
            char continueChoice = scanner.next().charAt(0);
            
            if (continueChoice == 'N' || continueChoice == 'n') {
                System.out.println("Exiting the Temperature Converter. Goodbye!");
                break;
            }
        }

        // Close the scanner
        scanner.close();
    }

    // Function to handle valid temperature input
    private static double getValidTemperature(Scanner scanner) {
        double tempValue = 0;
        boolean valid = false;
        
        while (!valid) {
            if (scanner.hasNextDouble()) {
                tempValue = scanner.nextDouble();
                valid = true;
            } else {
                System.out.println("Invalid input! Please enter a valid numeric temperature value.");
                scanner.next(); // Consume the invalid input
            }
        }

        return tempValue;
    }

    // Function to handle valid scale input (C, F, or K)
    private static char getValidScale(Scanner scanner) {
        char scale = ' ';
        boolean valid = false;
        
        while (!valid) {
            scale = scanner.next().toUpperCase().charAt(0);  // Convert to uppercase to allow case-insensitive input
            if (scale == 'C' || scale == 'F' || scale == 'K') {
                valid = true;
            } else {
                System.out.println("Invalid scale! Please enter a valid scale (C for Celsius, F for Fahrenheit, K for Kelvin).");
            }
        }
        
        return scale;
    }

    // Function to perform temperature conversion based on source and target scales
    private static double convertTemperature(double tempValue, char sourceScale, char targetScale) {
        double result = tempValue;
        
        // Convert the input temperature to Celsius first
        if (sourceScale == 'F') {
            result = (tempValue - 32) * 5 / 9; // Fahrenheit to Celsius
        } else if (sourceScale == 'K') {
            result = tempValue - 273.15; // Kelvin to Celsius
        }

        // Now convert from Celsius to the target scale
        if (targetScale == 'F') {
            result = (result * 9 / 5) + 32; // Celsius to Fahrenheit
        } else if (targetScale == 'K') {
            result = result + 273.15; // Celsius to Kelvin
        }

        return result;
    }

    // Function to return the full name of the temperature scale
    private static String getScaleName(char scale) {
        switch (scale) {
            case 'C': return "Celsius";
            case 'F': return "Fahrenheit";
            case 'K': return "Kelvin";
            default: return "Unknown";
        }
    }
}
