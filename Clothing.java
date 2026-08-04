public class Clothing extends Item{

    private final String category = "Clothing";

    public Clothing(String id, String name, int quantity, float price) {
        super(id, name, quantity, price);
    }

    @Override
    public String getCategory() {
        return category;
    }

}
