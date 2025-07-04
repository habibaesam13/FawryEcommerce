public abstract class Product {
    protected String name;
    protected double price;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    protected int quantity;


    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void reduceQuantity(int quantity){
        this.quantity -=quantity;
    }

    public boolean isExpired(){
        return false;
    }
}
