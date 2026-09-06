public class Electronics extends Item {

    private final int category = 2;
    private final float minPrice = 1000;
    private final float maxPrice = 100000;

    public Electronics(String id, String name, int quantity, float price) {
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
