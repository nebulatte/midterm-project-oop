import java.util.Scanner;

public class Validators {
    
    public static int allowIntOnly(Scanner scanner) {
        String line;
        int number = 0;
        boolean valid = false;
        do { 
            try {
                line = scanner.nextLine().trim();
                number = Integer.parseInt(line);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter an integer: ");
            }
        } while (!valid);
        return number;
    }

    public static float allowFloatOnly(Scanner scanner) {
        String line;
        float number = 0;
        boolean valid = false;
        do { 
            try {
                line = scanner.nextLine().trim();
                number = Float.parseFloat(line);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a float: ");
            }
        } while (!valid);
        return number;
    }

    public static String allowStringOnly(Scanner scanner) {
        String string = "";
        boolean valid = false;
        do {
            string = scanner.nextLine().trim();
            if (string.isEmpty()) {
                System.out.println("Invalid input. Input cannot be empty.");
            } else {
                valid = true;
            }
        } while(!valid);
        return string;
    }

    // =====================================================================================================================

    public static int validateInt(Scanner scanner, String prompt, int min, int max, String errorMessage) {
        int number;
        boolean valid = false;
        do {
            System.out.print(prompt);
            number = allowIntOnly(scanner);
            if(number >= min && number <= max) {
                valid = true;
            } else {
                System.out.println(errorMessage);
            }
        } while (!valid);
        return number;
    }

    public static float validateFloat(Scanner scanner, String prompt, float min, float max, String errorMessage) {
        float number;
        boolean valid = false;
        do {
            System.out.print(prompt);
            number = allowFloatOnly(scanner);
            if(number >= min && number <= max) {
                valid = true;
            } else {
                System.out.println(errorMessage);
            }
        } while (!valid);
        return number;
    }

    public static String validateString(Scanner scanner, String prompt, String format, String errorMessage) {
        String string;
        boolean valid = false;
        do {
            System.out.print(prompt);
            string = allowStringOnly(scanner);
            if(string.matches(format)) {
                valid = true;
            } else {
                System.out.println(errorMessage);
            }
        } while (!valid);
        return string;
    }

}
