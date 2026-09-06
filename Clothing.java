public class Clothing extends Item {

    private final int category = 1;
    private final String categoryName = "Clothing";

    public Clothing(String id, String name, int quantity, float price) {
        super(id, name, quantity, price);
    }

    @Override
    public int getCategory() {
        return category;
    }
    @Override
    public String getCategoryName() {
        return categoryName;
    }

}
