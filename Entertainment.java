public class Entertainment extends Item {

    private final int category = 3;
    private final String categoryName = "Entertainment";

    public Entertainment(String id, String name, int quantity, float price) {
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
