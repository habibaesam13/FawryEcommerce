import java.util.List;
import java.util.ArrayList;
public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
//        if (quantity > product.getQuantity()) {
//            throw new IllegalArgumentException("Requested quantity exceeds available stock.\t product: "+product.getName()+"\tthe available in stock is: "+product.getQuantity());
//        }
        CartItem item = new CartItem(product, quantity);
        items.add(item);
    }

    public double getSubtotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }
    public List<Shippable> getShippableItems() {
        List<Shippable> shippables = new ArrayList<>();
        for (CartItem item : items) {
            Product product = item.getProduct();
            if (product instanceof Shippable) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippables.add((Shippable) product);
                }
            }
        }
        return shippables;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public void clear(){
        items.clear();
    }
}
