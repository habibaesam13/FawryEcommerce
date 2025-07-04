
# E-Commerce Checkout System (Java)

A simple object-oriented Java console application that simulates a mini e-commerce checkout system with the following features:

---

## ✨ Features

- Define various products with name, price, and quantity
- Handle **expirable** and **non-expirable** products
- Support **shippable** products with weight-based shipping fees
- Add items to cart with quantity constraints
- Perform checkout with:
  - Subtotal calculation
  - Shipping cost calculation
  - Total amount deduction from customer balance
  - Shipment notice for applicable items
- Error handling for:
  - Expired products
  - Quantity exceeding stock
  - Empty cart
  - Insufficient balance
- User-friendly options to resolve product issues during checkout (update quantity, remove item, or cancel checkout)

---

### Running the App

```bash
javac Main.java
java Main

✅ Example Console Output
markdown
Copy
Edit
** Shipment notice **
2x Cheese 400g
1x Biscuits 700g
Total package weight 1.1kg

** Checkout receipt **
2x Cheese 400g 200
1x Biscuits 700g 150
1x Mobile Scratch Card 50
----------------------
Subtotal 400
Shipping 5.5
Amount 405.5
Remaining Balance: 594.5
---
### 📌 Notes
The app uses console interaction (via Scanner) to let users resolve product issues at checkout.

Shipping cost is calculated as 5 × totalWeight (customizable).

The system is modular and easy to extend with payment gateways, UI, or databases.

## 📸 Demo Screens

### ✅ 1. Successful Checkout
![1_success_checkout](https://github.com/user-attachments/assets/ce1da277-8cd0-48bd-82b4-f702800de740)
Displays checkout with subtotal, shipping, and updated balance.

---

### ❌ 2. Expired Productz

![2_expired_product](https://github.com/user-attachments/assets/6d38f40a-33ee-4c29-9b89-16021b1903dd)
Shows system preventing checkout of expired items.

---

### ⚠️ 3. Quantity Exceeds Stock

![3_quantity_exceeds](https://github.com/user-attachments/assets/fb172baf-803f-4ae5-a37d-65257a670c56)
Prompts user to update quantity, remove item, or cancel.

---

### 🧾 4. Insufficient Balance
![4 2_insufficient_balance](https://github.com/user-attachments/assets/78e2fff4-0962-4b0f-9615-296a85234598)
![4 1_insufficient_balance](https://github.com/user-attachments/assets/242d6f96-bc06-43e8-83c4-2d02ef6d93b0)
Alerts the user that they don’t have enough funds.

---

### 🚫 5. Empty Cart

![5_empty_cart](https://github.com/user-attachments/assets/a4e1082c-e81e-400f-8917-eb6581a88e64)
Prevents checkout when the cart is empty.

