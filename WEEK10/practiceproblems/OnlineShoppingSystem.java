package WEEK10.practiceproblems;

import java.util.*;

// ---------------------------
// Product Class
// ---------------------------
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void showDetails() {
        System.out.println("Product: " + name + " | Price: ₹" + price);
    }
}

// ---------------------------
// Order Class (Composition → owns Products)
// ---------------------------
class Order {
    private int orderId;
    private String date;
    private List<Product> products;

    public Order(int orderId, String date) {
        this.orderId = orderId;
        this.date = date;
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println(product.getName() + " added to Order " + orderId);
    }

    public void showOrderDetails() {
        System.out.println("\nOrder ID: " + orderId + " | Date: " + date);
        System.out.println("Products in this order:");
        for (Product p : products) {
            p.showDetails();
        }
    }

    // Composition: If order is deleted, its products also are removed
    public void cancelOrder() {
        products.clear();
        System.out.println("Order " + orderId + " cancelled and all products removed.");
    }
}

// ---------------------------
// Customer Class (Association → places Orders)
// ---------------------------
class Customer {
    private String name;
    private String email;
    private List<Order> orders;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        orders = new ArrayList<>();
    }

    public void placeOrder(Order order) {
        orders.add(order);
        System.out.println(name + " placed Order " + order);
    }

    public void showCustomerDetails() {
        System.out.println("\nCustomer: " + name + " | Email: " + email);
        System.out.println("Orders placed:");
        for (Order o : orders) {
            o.showOrderDetails();
        }
    }
}

// ---------------------------
// Main Class
// ---------------------------
public class OnlineShoppingSystem {
    public static void main(String[] args) {

        // Create Products
        Product product1 = new Product("Laptop", 55000);
        Product product2 = new Product("Mouse", 700);
        Product product3 = new Product("Keyboard", 1500);

        // Create Orders (composition → owns products)
        Order order1 = new Order(101, "17-Oct-2025");
        order1.addProduct(product1);
        order1.addProduct(product2);

        Order order2 = new Order(102, "18-Oct-2025");
        order2.addProduct(product3);

        // Create Customer (association → has orders)
        Customer customer1 = new Customer("Amit", "amit@gmail.com");
        customer1.placeOrder(order1);
        customer1.placeOrder(order2);

        // Show all relationships and details
        customer1.showCustomerDetails();

        // Demonstrate composition (delete order)
        order1.cancelOrder();
    }
}
