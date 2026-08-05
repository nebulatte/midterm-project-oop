import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {

    final ArrayList<Item> items = new ArrayList<>();

    // ====================================================================================================================

    public void addItem(Scanner scanner) {
        Display.headerAddItem();
        String enteredCategory = Validators.validateString(scanner, "Enter the category: ", "[a-zA-Z0-9]+", "Invalid input. Enter a valid category.");
        String checkedCategory = findCategory(enteredCategory);

        if(checkedCategory == null) {
            System.out.printf("Category '%s' does not exist!%n", enteredCategory);
            return;
        }

        if(enteredCategory.equalsIgnoreCase("clothing")) {
            addItemDetails(scanner, enteredCategory, 200.0f, 3000.0f);
        }
        else if(enteredCategory.equalsIgnoreCase("electronics")) {
            addItemDetails(scanner, enteredCategory, 500.0f, 100000.0f);
        }
        else if(enteredCategory.equalsIgnoreCase("entertainment")) {
            addItemDetails(scanner, enteredCategory, 100.0f, 3000.0f);
        }
        System.out.println("Item added successfully!");
    }

    // addItem() HELPER METHODS

    private void addItemDetails(Scanner scanner, String category, float minPrice, float maxPrice) {
        String id = Validators.validateString(scanner, "Enter the ID: ", "[a-zA-Z0-9]+", "Invalid input. Enter a valid id.");
        String name = Validators.validateString(scanner, "Enter the name: ", "[a-zA-Z ]+", "Invalid input. Enter a valid name.");
        int quantity = Validators.validateInt(scanner, "Enter the quantity: ", 1, 100, "Invalid input. Enter a valid quantity [1-100].");
        float price = Validators.validateFloat(scanner, "Enter the price: ", minPrice, maxPrice, String.format("Invalid input. Enter a valid price [%.2f-%.2f].", minPrice, maxPrice));

        if(category.equalsIgnoreCase("clothing")) {
            items.add(new Clothing(id, name, quantity, price));
        } else if(category.equalsIgnoreCase("electronics")) {
            items.add(new Electronics(id, name, quantity, price));
        } else if(category.equalsIgnoreCase("entertainment")) {
            items.add(new Entertainment(id, name, quantity, price));
        }
    }
    
    // ====================================================================================================================

    public void updateItem(Scanner scanner) {
        Display.headerUpdateItem();
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
        100.0f, 100000.0f, "Invalid input. Enter a valid price [100-100000].");

        currentItem.setPrice(newPrice);
        System.out.printf("%s's price has been changed from %.2f to %.2f!%n", itemName, oldPrice, newPrice);
    }
    private void updateItemQuantity(Scanner scanner, Item currentItem) {
        String itemName = currentItem.getName();
        int oldQuantity = currentItem.getQuantity();
        int newQuantity = Validators.validateInt(scanner, "Enter the item's new quantity: ", 
        0, 100, "Invalid input. Enter a valid quantity [0-100].");

        currentItem.setQuantity(newQuantity);
        System.out.printf("%s's quantity has been changed from %d to %d!%n", itemName, oldQuantity, newQuantity);
    }

    // ====================================================================================================================

    public void removeItem(Scanner scanner) {
        Display.headerRemoveItem();
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

    // ====================================================================================================================

    public void displayItemsByCategory(Scanner scanner) {
        Display.headerDisplayItemsByCategory();
        String enteredCategory = Validators.validateString(scanner, "Enter the category: ", "[a-zA-Z0-9]+", "Invalid input. Enter a valid category.");
        String checkedCategory = findCategory(enteredCategory);

        if(checkedCategory == null) {
            System.out.printf("Category '%s' does not exist!%n", enteredCategory);
            return;
        }

        Display.tableHeader();
        for(int i = 0; i < items.size(); i++) {
            Item currentItem = items.get(i);
            if(currentItem.getCategory().equalsIgnoreCase(enteredCategory)) {
                System.out.printf(Display.tableFormat(), currentItem.getId(), 
                currentItem.getName(), currentItem.getQuantity(), currentItem.getPrice());
            }
        }
    }

    // ====================================================================================================================

    public void displayAllItems() {
        Display.headerDisplayAllItems();
        Display.tableWithCategoryHeader();
        for(int i = 0; i < items.size(); i++) {
            Item currentItem = items.get(i);
            System.out.printf(Display.tableWithCategoryFormat(), currentItem.getId(), 
            currentItem.getName(), currentItem.getQuantity(), currentItem.getPrice(), currentItem.getCategory());
        }
    }

    // ====================================================================================================================

    public void searchItem(Scanner scanner) {
        Display.headerSearchItem();
        String enteredId = Validators.validateString(scanner, "Enter the ID: ", "[a-zA-Z0-9]+", "Invalid input. Enter a valid id.");
        Item currentItem = findItemById(enteredId);
        
        if(currentItem == null) {
            System.out.println("Item not found!");
            return;
        }
        
        Display.tableWithCategoryHeader();
        System.out.printf(Display.tableWithCategoryFormat(), currentItem.getId(), 
        currentItem.getName(), currentItem.getQuantity(), currentItem.getPrice(), currentItem.getCategory());
    }

    // ====================================================================================================================

    public void sortItems(Scanner scanner) {
        Display.headerSortItems();
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
    
    // ====================================================================================================================

    public void displayLowStockItems() {
        Display.headerDisplayLowStockItems();
        Display.tableWithCategoryHeader();
        final int lowStockLimit = 5;
        for(int i = 0; i < items.size(); i++) {
            Item currentItem = items.get(i);
            if(currentItem.getQuantity() <= lowStockLimit) {
                System.out.printf(Display.tableWithCategoryFormat(), currentItem.getId(), 
                currentItem.getName(), currentItem.getQuantity(), currentItem.getPrice(), currentItem.getCategory());
            }
        }
    }

    // GENERAL HELPER METHODS ================================================================================================

    private String findCategory(String searchCategory) {
        boolean isExistingCategory = searchCategory.equalsIgnoreCase("Clothing") 
                                || searchCategory.equalsIgnoreCase("Electronics") 
                                || searchCategory.equalsIgnoreCase("Entertainment");
        if(!isExistingCategory) {
            return null;
        }
        return searchCategory;
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