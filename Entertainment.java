public class Entertainment extends Item{

    private final String category = "Entertainment";

    public Entertainment(String id, String name, int quantity, float price) {
        super(id, name, quantity, price);
    }

    @Override
    public String getCategory() {
        return category;
    }

}
