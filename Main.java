import java.util.Scanner;

public class Main {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        Inventory inventory = new Inventory();
        int choice;

        while(running) {
            Display.menu();
            choice = Validators.validateInt(scanner, "Enter your choice: ", 1, 9, "Enter a valid choice [1-9]: ");
            System.out.println();
            switch (choice) {
                case 1 -> inventory.addItem(scanner);
                case 2 -> inventory.updateItem(scanner);
                case 3 -> inventory.removeItem(scanner);
                case 4 -> inventory.displayItemsByCategory(scanner);
                case 5 -> inventory.displayAllItems();
                case 6 -> inventory.searchItem(scanner);
                case 7 -> inventory.sortItems(scanner);
                case 8 -> inventory.displayLowStockItems();
                case 9 -> { System.out.println("Closing the program...");
                            running = false;}
            }
            System.out.println();
        }

        scanner.close();
        
    }
    
}
