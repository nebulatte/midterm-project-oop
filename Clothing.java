public class Clothing extends Item {

    private final int category = 1;
    private final float minPrice = 200;
    private final float maxPrice = 5000;

    public Clothing(String id, String name, int quantity, float price) {
        super(id, name, quantity, price);
    }

    @Override
    public int getCategory() {
        return category;
    }
    @Override
    public float getMinPrice() {
        return minPrice;
    }
    @Override
    public float getMaxPrice() {
        return maxPrice;
    }

}
