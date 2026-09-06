public class Entertainment extends Item {

    private final int category = 3;
    private final float minPrice = 500;
    private final float maxPrice = 10000;

    public Entertainment(String id, String name, int quantity, float price) {
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
