public class Electronics extends Item{

    private final String category = "Electronics";

    public Electronics(String id, String name, int quantity, float price) {
        super(id, name, quantity, price);
    }

    @Override
    public String getCategory() {
        return category;
    }

}
