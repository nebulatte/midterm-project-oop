public class Electronics extends Item {

    private final int category = 2;
    private final String categoryName = "Electronics";

    public Electronics(String id, String name, int quantity, float price) {
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
