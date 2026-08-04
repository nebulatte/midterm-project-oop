public class Display {
    
    public static void menu() {
        System.out.println("=======================================");
        System.out.println("      INVENTORY MANAGAMENT SYSTEM      ");
        System.out.println("=======================================");
        System.out.println("[1] Add Item");
        System.out.println("[2] Update Item");
        System.out.println("[3] Remove Item");
        System.out.println("[4] Display Items by Category");
        System.out.println("[5] Display All Items");
        System.out.println("[6] Search Item");
        System.out.println("[7] Sort Items");
        System.out.println("[8] Display Low Stock Items");
        System.out.println("[9] Exit");
    }

    public static void menuQuantityOrPrice() {
        System.out.println("Which attribute do you want to use?");
        System.out.println("[1] Quantity");
        System.out.println("[2] Price");
    }

    public static void menuAscendingOrDescending() {
        System.out.println("Which order do you want to use?");
        System.out.println("[1] Ascending");
        System.out.println("[2] Descending");
    }

    // HEADERS TO SHOW THE USER'S CURRENT ACTION
    public static void headerAddItem() {
        System.out.println("============ ADDING ITEM ============");
    }
    public static void headerUpdateItem() {
        System.out.println("============ UPDATING ITEM ============");
    }
    public static void headerRemoveItem() {
        System.out.println("============ REMOVING ITEM ============");
    }
    public static void headerDisplayItemsByCategory() {
        System.out.println("============ ITEMS BY CATEGORY ============");
    }
    public static void headerDisplayAllItems() {
        System.out.println("============ ALL ITEMS ============");
    }
    public static void headerSearchItem() {
        System.out.println("============ SEARCHING ITEM ============");
    }
    public static void headerSortItems() {
        System.out.println("============ SORTING ITEMS ============");
    }
    public static void headerDisplayLowStockItems() {
        System.out.println("============ LOW STOCK ITEMS ============");
    }

    // ====================================================================================================================

    public static void tableHeader() {
        System.out.printf("%-20s%-20s%-15s%-10s%n", "ID", "NAME", "QUANTITY", "PRICE");
    }
    public static String tableFormat() {
        return "%-20s%-20s%-15d%-10.2f%n";
    }

    public static void tableWithCategoryHeader() {
        System.out.printf("%-20s%-20s%-15s%-15s%-12s%n", "ID", "NAME", "QUANTITY", "PRICE", "CATEGORY");
    }
    public static String tableWithCategoryFormat() {
        return "%-20s%-20s%-15d%-15.2f%s%n";
    }

}
