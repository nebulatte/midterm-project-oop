public abstract class Item {
    
    private String id;
    private String name;
    private int quantity;
    private float price;

    public Item(String id, String name, int quantity, float price) {
        this.id = formatId(id);
        this.name = formatName(name);
        this.quantity = quantity;
        this.price = price;
    }

    // GETTERS
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public float getPrice() {
        return price;
    }

    // SETTERS
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    // ABSTRACT METHOD
    public abstract String getCategory();

    // FORMATTING
    private String formatId(String id) {
        return id.toUpperCase();
    }
    private String formatName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

}
