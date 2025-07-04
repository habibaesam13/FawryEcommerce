public class Main {
    public static void main(String[] args) {
        try {
            // Define Products
            ShippableProduct cheese = new ShippableProduct(0.2);
            cheese.setName("Cheese 400g");
            cheese.setPrice(100);
            cheese.setQuantity(5);

            ShippableProduct biscuits = new ShippableProduct(0.7);
            biscuits.setName("Biscuits 700g");
            biscuits.setPrice(150);
            biscuits.setQuantity(5);

            NonExpirableProduct scratchCard = new NonExpirableProduct();
            scratchCard.setName("Mobile Scratch Card");
            scratchCard.setPrice(50);
            scratchCard.setQuantity(3);

            ExpirableProduct cheeseRomy = new ExpirableProduct("2030-01-01");
            cheeseRomy.setName("Romy Cheese");
            cheeseRomy.setPrice(100);
            cheeseRomy.setQuantity(1);

            // Setup Cart
            Cart cart = new Cart();
//            cart.addItem(cheese, 2);
//            cart.addItem(biscuits, 1);
//            cart.addItem(scratchCard, 1);
//            cart.addItem(cheeseRomy, 1);

            // Customer
            Customer customer = new Customer("Alice", 1000, cart);

            // Try Checkout
            customer.checkout();

        } catch (IllegalStateException | IllegalArgumentException e) {
            System.err.println("Checkout failed: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
