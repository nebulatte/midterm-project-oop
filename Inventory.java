import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {

    ArrayList<Item> items = new ArrayList<>();

    final float minPrice = 200.0f;
    final float maxPrice = 100000.0f;

    final int minQuantity = 0;
    final int maxQuantity = 100;

    final int lowStockLimit = 5;

    // [1] ====================================================================================================================

    public void addItem(Scanner scanner) {
        Display.headerAddItem();
        Display.categories();
        int enteredCategory = Validators.validateInt(scanner, "Enter the category: ", 1, 3, "Invalid input. Enter a valid number [1, 2, 3].");
        int checkedCategory = isValidCategory(enteredCategory);

        if(checkedCategory == -1) {
            System.out.printf("Category '%s' does not exist!%n", enteredCategory);
            return;
        }

        addItemDetails(scanner, checkedCategory);
        System.out.println("Item added successfully!");
    }

    // addItem() HELPER METHODS

    private void addItemDetails(Scanner scanner, int category) {

        String id = Validators.validateString(scanner, "Enter the ID: ", "[a-zA-Z0-9]+", "Invalid input. Enter a valid id.");
        String name = Validators.validateString(scanner, "Enter the name: ", "[a-zA-Z ]+", "Invalid input. Enter a valid name.");
        int quantity = Validators.validateInt(scanner, "Enter the quantity: ", minQuantity, maxQuantity, String.format("Invalid input. Enter a valid quantity [%d-%d].", minQuantity, maxQuantity));
        float price = Validators.validateFloat(scanner, "Enter the price: ", minPrice, maxPrice, String.format("Invalid input. Enter a valid price [%.2f-%.2f].", minPrice, maxPrice));
        
        switch(category) {
            case 1 -> items.add(new Clothing(id, name, quantity, price));
            case 2 -> items.add(new Electronics(id, name, quantity, price));
            case 3 -> items.add(new Entertainment(id, name, quantity, price));
        }

    }
    
    // [2] ====================================================================================================================

    public void updateItem(Scanner scanner) {
        Display.headerUpdateItem();
        if(items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        String enteredId = Validators.validateString(scanner, "Enter the ID: ", "[a-zA-Z0-9]+", "Invalid input. Enter a valid id.");
        Item currentItem = findItemById(enteredId);

        if(currentItem == null) {
            System.out.println("Item not found!");
            return;
        }

        System.out.println("Update the item's information:");
        System.out.println("[1] Price");
        System.out.println("[2] Quantity");
        int choice = Validators.validateInt(scanner, "Enter your choice: ", 1, 2, "Invalid input. Enter a valid number [1 or 2].");

        if(choice == 1) {
            updateItemPrice(scanner, currentItem);
        }
        else if(choice == 2) {
            updateItemQuantity(scanner, currentItem);
        }
    }

    // updateItem() HELPER METHODS

    private void updateItemPrice(Scanner scanner, Item currentItem) {
        String itemName = currentItem.getName();
        float oldPrice = currentItem.getPrice();
        float newPrice = Validators.validateFloat(scanner, "Enter the item's new price: ", 
        minPrice, maxPrice, String.format("Invalid input. Enter a valid price [%.2f-%.2f].", minPrice, maxPrice));

        currentItem.setPrice(newPrice);
        System.out.printf("%s's price has been changed from %.2f to %.2f!%n", itemName, oldPrice, newPrice);
    }
    private void updateItemQuantity(Scanner scanner, Item currentItem) {
        String itemName = currentItem.getName();
        int oldQuantity = currentItem.getQuantity();
        int newQuantity = Validators.validateInt(scanner, "Enter the item's new quantity: ", 
        minQuantity, maxQuantity, String.format("Invalid input. Enter a valid quantity [%d-%d].", minQuantity, maxQuantity));

        currentItem.setQuantity(newQuantity);
        System.out.printf("%s's quantity has been changed from %d to %d!%n", itemName, oldQuantity, newQuantity);
    }

    // [3] ====================================================================================================================

    public void removeItem(Scanner scanner) {
        Display.headerRemoveItem();
        if(items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        String enteredId = Validators.validateString(scanner, "Enter the ID: ", "[a-zA-Z0-9]+", "Invalid input. Enter a valid id.");
        Item currentItem = findItemById(enteredId);
        
        if(currentItem == null) {
            System.out.println("Item not found!");
            return;
        }
        
        String itemName = currentItem.getName();
        items.remove(currentItem);
        System.out.printf("Item '%s' has been removed from the inventory.%n", itemName);
    }

    // [4] ====================================================================================================================

    public void displayItemsByCategory(Scanner scanner) {
        Display.headerDisplayItemsByCategory();
        if(items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        Display.categories();
        int enteredCategory = Validators.validateInt(scanner, "Enter the category: ", 1, 3, "Invalid input. Enter a valid number [1, 2, 3].");
        int checkedCategory = isValidCategory(enteredCategory);

        if(checkedCategory == -1) {
            System.out.printf("Category '%d' does not exist!%n", enteredCategory);
            return;
        }
        // Displaying the table with the appropriate headers
        
        switch(enteredCategory) {
            case 1 -> Display.labelClothing();
            case 2 -> Display.labelElectronics();
            case 3 -> Display.labelEntertainment();
        }
        Display.tableHeader();
        for(int i = 0; i < items.size(); i++) {
            Item currentItem = items.get(i);
            if(currentItem.getCategory() == enteredCategory) {
                System.out.printf(Display.tableFormat(), currentItem.getId(), 
                currentItem.getName(), currentItem.getQuantity(), currentItem.getPrice());
            }
        }
    }

    // [5] ====================================================================================================================

    public void displayAllItems() {
        Display.headerDisplayAllItems();
        if(items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        Display.tableWithCategoryHeader();
        for(int i = 0; i < items.size(); i++) {
            Item currentItem = items.get(i);
            System.out.printf(Display.tableWithCategoryFormat(), currentItem.getId(), 
            currentItem.getName(), currentItem.getQuantity(), currentItem.getPrice(), currentItem.getCategoryName());
        }
    }

    // [6] ====================================================================================================================

    public void searchItem(Scanner scanner) {
        Display.headerSearchItem();
        if(items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        String enteredId = Validators.validateString(scanner, "Enter the ID: ", "[a-zA-Z0-9]+", "Invalid input. Enter a valid id.");
        Item currentItem = findItemById(enteredId);
        
        if(currentItem == null) {
            System.out.println("Item not found!");
            return;
        }
        
        Display.tableWithCategoryHeader();
        System.out.printf(Display.tableWithCategoryFormat(), currentItem.getId(), 
        currentItem.getName(), currentItem.getQuantity(), currentItem.getPrice(), currentItem.getCategoryName());
    }

    // [7] ====================================================================================================================

    public void sortItems(Scanner scanner) {
        Display.headerSortItems();
        if(items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        Display.menuQuantityOrPrice();
        int pickAttribute = Validators.validateInt(scanner, "Enter your choice: ", 1, 2, "Invalid input. Enter 1 or 2.");
        Display.menuAscendingOrDescending();
        int pickOrder = Validators.validateInt(scanner, "Enter your choice: ", 1, 2, "Invalid input. Enter 1 or 2.");
        
        boolean byQuantity = pickAttribute == 1;
        boolean isAscending = pickOrder == 1;

        bubbleSort(byQuantity, isAscending);
        
        displayAllItems();
    }

    // sortItems() HELPER METHODS

    private void bubbleSort(boolean byQuantity, boolean isAscending) {
        boolean swapped;
        do {
            swapped = false;
            for(int i = 0; i < items.size()-1; i++) {
                Item firstItem = items.get(i);
                Item secondItem = items.get(i+1);

                float firstValue = byQuantity? firstItem.getQuantity(): firstItem.getPrice();
                float secondValue = byQuantity? secondItem.getQuantity(): secondItem.getPrice();
                boolean outOfOrder = isAscending? firstValue > secondValue: firstValue < secondValue;
                if(outOfOrder) {
                    items.set(i, secondItem);
                    items.set(i+1, firstItem);
                    swapped = true;
                }
            }
        } while(swapped);
    }
    
    // [8] ====================================================================================================================

    public void displayLowStockItems() {
        Display.headerDisplayLowStockItems();
        if(items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        Display.tableWithCategoryHeader();
        
        for(int i = 0; i < items.size(); i++) {
            Item currentItem = items.get(i);
            if(currentItem.getQuantity() <= lowStockLimit) {
                System.out.printf(Display.tableWithCategoryFormat(), currentItem.getId(), 
                currentItem.getName(), currentItem.getQuantity(), currentItem.getPrice(), currentItem.getCategoryName());
            }
        }
    }

    // GENERAL HELPER METHODS ================================================================================================

    private int isValidCategory(int searchCategory) {
        if(searchCategory >= 1 && searchCategory <= 3) {
            return searchCategory;
        } else {
            return -1;
        }
    }

    private Item findItemById(String searchId) {
        if(items.isEmpty()) {
            System.out.println("There are no items available.");
        }
        for (int i = 0; i < items.size(); i++) {
            Item currentItem = items.get(i);
            if (currentItem.getId().equalsIgnoreCase(searchId)) {
                return currentItem;
            } 
        }
        return null;
    }

}
