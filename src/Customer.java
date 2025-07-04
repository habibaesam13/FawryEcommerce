import java.util.List;
import java.util.Scanner;
public class Customer {
    private String name;
    private double balance;
    private Cart cart;

    public Customer(String name, double balance, Cart cart) {
        this.name = name;
        this.balance = balance;
        this.cart = cart;
    }

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }



    public void checkout() {
        Scanner scanner = new Scanner(System.in);

        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Cannot proceed with checkout.");
            return;
        }

        // Step 1: Validate items and offer user options
        for (int i = 0; i < cart.getItems().size(); ) {
            CartItem item = cart.getItems().get(i);
            Product product = item.getProduct();

            // Check expiration
            if (product instanceof ExpirableProduct && product.isExpired()) {
                System.out.println("⚠ Product '" + product.getName() + "' is expired (Expiry: "
                        + ((ExpirableProduct) product).getExpiryDate() + ")");
                System.out.println("Do you want to [1] Remove this item or [2] Cancel checkout?");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    cart.getItems().remove(i);
                    continue;
                } else {
                    System.out.println("Checkout canceled.");
                    return;
                }
            }

            // Check quantity
            if (item.getQuantity() > product.getQuantity()) {
                System.out.println("⚠ Product '" + product.getName() + "' only has " + product.getQuantity() + " in stock.");
                System.out.println("You requested: " + item.getQuantity());
                System.out.println("Choose: [1] Update quantity, [2] Remove item, [3] Cancel checkout");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter new quantity (max " + product.getQuantity() + "): ");
                        int newQty = scanner.nextInt();
                        if (newQty <= product.getQuantity() && newQty > 0) {
                            item.setQuantity(newQty);
                        } else {
                            System.out.println("Invalid quantity. Removing item.");
                            cart.getItems().remove(i);
                            continue;
                        }
                        break;
                    case 2:
                        cart.getItems().remove(i);
                        continue;
                    case 3:
                        System.out.println("Checkout canceled.");
                        return;
                }
            }

            i++;
        }

        if (cart.isEmpty()) {
            System.out.println("All items were removed. Cart is empty.");
            return;
        }
        double subtotal;
        double shipping;
        double total;

        // Step 2: Calculate subtotal and shipping
        while (true) {
            subtotal = cart.getSubtotal();
            shipping = calculateShippingFees(cart.getShippableItems());
            total = subtotal + shipping;

            System.out.printf("Subtotal: %.2f | Shipping: %.2f | Total: %.2f | Your Balance: %.2f%n",
                    subtotal, shipping, total, balance);

            if (balance >= total) {
                break;
            }

            System.out.println("Your balance is not enough to complete the checkout.");
            System.out.println("Choose an option: [1] Update item quantity  [2] Remove item  [3] Cancel checkout");
            System.out.println("Cart Items:");
            for (int i = 0; i < cart.getItems().size(); i++) {
                CartItem item = cart.getItems().get(i);
                System.out.printf("[%d] %s x%d (%.2f)%n", i + 1,
                        item.getProduct().getName(), item.getQuantity(), item.getTotalPrice());
            }

            System.out.print("Select item number to modify: ");
            int selectedIndex = scanner.nextInt() - 1;

            if (selectedIndex < 0 || selectedIndex >= cart.getItems().size()) {
                System.out.println("Invalid selection.");
                continue;
            }

            CartItem selectedItem = cart.getItems().get(selectedIndex);
            System.out.println("Action: [1] Update quantity  [2] Remove item  [3] Cancel checkout");
            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    System.out.print("Enter new quantity (max " + selectedItem.getProduct().getQuantity() + "): ");
                    int newQty = scanner.nextInt();
                    if (newQty > 0 && newQty <= selectedItem.getProduct().getQuantity()) {
                        selectedItem.setQuantity(newQty);
                    } else {
                        System.out.println("Invalid quantity. No changes made.");
                    }
                    break;
                case 2:
                    cart.getItems().remove(selectedIndex);
                    break;
                case 3:
                    System.out.println("Checkout canceled.");
                    return;
            }

            if (cart.isEmpty()) {
                System.out.println("Cart is now empty. Checkout canceled.");
                return;
            }
        }


        // Step 3: Process payment
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }
        deductAmount(total);

        // Step 4: Ship
        List<Shippable> itemsToShip = cart.getShippableItems();
        if (!itemsToShip.isEmpty()) {
            new ShippingService().ship(itemsToShip);
        }

        // Step 5: Print receipt
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s %.0f%n", item.getQuantity(), item.getProduct().getName(), item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.1f%n", shipping);
        System.out.printf("Amount %.1f%n", total);
        System.out.printf("Remaining Balance: %.2f%n", balance);

        cart.clear();
    }


    private double calculateShippingFees(List<Shippable> items) {
        double totalWeight = 0;
        for (Shippable item : items) {
            totalWeight += item.getWeight();
        }
        return totalWeight * 5; // Example: $5 per unit of weight
    }

    public void deductAmount(double amount){
        this.balance-=amount;
    }
}
