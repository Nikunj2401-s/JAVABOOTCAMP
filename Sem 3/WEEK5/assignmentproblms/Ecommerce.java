package WEEK5.assignmentproblms;

import java.time.LocalDateTime;
import java.util.*;

// ---------------------- Immutable Product ----------------------
final class Product {
    private final String productId;
    private final String name;
    private final String category;
    private final String manufacturer;
    private final double basePrice;
    private final double weight;
    private final String[] features;
    private final Map<String, String> specifications;

    private Product(String productId,
                    String name,
                    String category,
                    String manufacturer,
                    double basePrice,
                    double weight,
                    String[] features,
                    Map<String, String> specifications) {
        if (productId == null || productId.isBlank()) throw new IllegalArgumentException("Invalid productId");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Invalid name");
        if (basePrice < 0 || weight < 0) throw new IllegalArgumentException("Invalid price/weight");

        this.productId = productId;
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.basePrice = basePrice;
        this.weight = weight;
        this.features = (features == null) ? new String[0] : Arrays.copyOf(features, features.length);
        this.specifications = (specifications == null) ? new HashMap<>() : new HashMap<>(specifications);
    }

    // Factory methods
    public static Product createElectronics(String id, String name, double price, double weight) {
        return new Product(id, name, "Electronics", "GenericBrand", price, weight,
                new String[]{"1 year warranty"}, Map.of("Voltage", "220V"));
    }

    public static Product createClothing(String id, String name, double price, double weight) {
        return new Product(id, name, "Clothing", "GenericBrand", price, weight,
                new String[]{"Washable"}, Map.of("Material", "Cotton"));
    }

    public static Product createBooks(String id, String name, double price, double weight) {
        return new Product(id, name, "Books", "GenericPublisher", price, weight,
                new String[]{"Paperback"}, Map.of("Language", "English"));
    }

    // Getters (defensive copies)
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getManufacturer() { return manufacturer; }
    public double getBasePrice() { return basePrice; }
    public double getWeight() { return weight; }
    public String[] getFeatures() { return Arrays.copyOf(features, features.length); }
    public Map<String, String> getSpecifications() { return new HashMap<>(specifications); }

    // Final tax calculation
    public final double calculateTax(String region) {
        double rate = switch (region) {
            case "US" -> 0.07;
            case "EU" -> 0.20;
            case "IN" -> 0.18;
            default -> 0.10;
        };
        return basePrice * rate;
    }

    @Override
    public String toString() {
        return "Product{" + name + ", $" + basePrice + ", " + category + "}";
    }
}

// ---------------------- Customer ----------------------
class Customer {
    private final String customerId;
    private final String email;
    private final String accountCreationDate;
    private String name;
    private String phoneNumber;
    private String preferredLanguage;

    public Customer(String customerId, String email, String name) {
        this.customerId = Objects.requireNonNull(customerId);
        this.email = Objects.requireNonNull(email);
        this.accountCreationDate = LocalDateTime.now().toLocalDate().toString();
        this.name = name;
        this.preferredLanguage = "EN";
    }

    // Getters/setters
    public String getCustomerId() { return customerId; }
    public String getEmail() { return email; }
    public String getAccountCreationDate() { return accountCreationDate; }
    public String getName() { return name; }
    public void setName(String name) { if (name != null && !name.isBlank()) this.name = name; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String lang) { this.preferredLanguage = lang; }

    // Privacy controls
    String getCreditRating() { return "GOOD"; } // package-private
    public String getPublicProfile() { return "Customer{" + name + ", member since " + accountCreationDate + "}"; }

    @Override
    public String toString() { return "Customer{" + customerId + ", " + name + "}"; }
}

// ---------------------- ShoppingCart ----------------------
class ShoppingCart {
    private final String cartId;
    private final String customerId;
    private final List<Object> items;
    private double totalAmount;
    private int itemCount;

    public ShoppingCart(String customerId) {
        this.cartId = "CART-" + UUID.randomUUID();
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    public boolean addItem(Object product, int quantity) {
        if (!(product instanceof Product)) return false;
        if (quantity <= 0) return false;
        Product p = (Product) product;
        for (int i = 0; i < quantity; i++) items.add(p);
        itemCount += quantity;
        totalAmount += (p.getBasePrice() * quantity) - calculateDiscount();
        return true;
    }

    private double calculateDiscount() {
        if (itemCount >= 5) return 10.0; // bulk discount
        return 0.0;
    }

    String getCartSummary() {
        return "Cart{" + cartId + ", items=" + itemCount + ", total=$" + totalAmount + "}";
    }

    public double getTotalAmount() { return totalAmount; }
    public int getItemCount() { return itemCount; }
    public String getCartId() { return cartId; }
}

// ---------------------- Orders & Processing ----------------------
class Order {
    private final String orderId;
    private final LocalDateTime orderTime;
    private final ShoppingCart cart;

    public Order(ShoppingCart cart) {
        this.orderId = "ORD-" + UUID.randomUUID();
        this.orderTime = LocalDateTime.now();
        this.cart = cart;
    }

    public String getOrderId() { return orderId; }
    public ShoppingCart getCart() { return cart; }
    @Override
    public String toString() { return "Order{" + orderId + ", items=" + cart.getItemCount() + "}"; }
}

class PaymentProcessor {
    private final String processorId;
    private final String securityKey;

    public PaymentProcessor(String id, String key) {
        this.processorId = id;
        this.securityKey = key;
    }

    public boolean processPayment(double amount) {
        return amount > 0; // demo: assume success
    }
}

class ShippingCalculator {
    private final Map<String, Double> shippingRates;

    public ShippingCalculator() {
        this.shippingRates = Map.of("US", 5.0, "EU", 15.0, "IN", 10.0);
    }

    public double calculate(String region, double weight) {
        return shippingRates.getOrDefault(region, 20.0) + (weight * 0.5);
    }
}

// ---------------------- ECommerceSystem ----------------------
final class ECommerceSystem {
    private static final Map<String, Object> productCatalog = new HashMap<>();

    public static boolean processOrder(Object order, Object customer) {
        if (!(order instanceof Order) || !(customer instanceof Customer)) return false;
        Order o = (Order) order;
        Customer c = (Customer) customer;

        PaymentProcessor pp = new PaymentProcessor("P1", "KEY123");
        if (!pp.processPayment(o.getCart().getTotalAmount())) return false;

        System.out.println("Order " + o.getOrderId() + " processed for " + c.getPublicProfile());
        return true;
    }

    // Inventory management
    public static void addProductToCatalog(Product p) {
        productCatalog.put(p.getProductId(), p);
    }

    public static void printCatalog() {
        System.out.println("Catalog: " + productCatalog.values());
    }
}
public class Ecommerce {
    public static void main(String[] args) {
        // Immutable products
        Product phone = Product.createElectronics("P1", "Smartphone", 500, 0.3);
        Product shirt = Product.createClothing("P2", "T-Shirt", 20, 0.2);
        Product book = Product.createBooks("P3", "Java Basics", 15, 0.5);

        ECommerceSystem.addProductToCatalog(phone);
        ECommerceSystem.addProductToCatalog(shirt);
        ECommerceSystem.addProductToCatalog(book);
        ECommerceSystem.printCatalog();

        // Customer
        Customer cust = new Customer("C1", "alice@example.com", "Alice");
        System.out.println(cust.getPublicProfile());

        // Cart
        ShoppingCart cart = new ShoppingCart(cust.getCustomerId());
        cart.addItem(phone, 1);
        cart.addItem(shirt, 3);
        cart.addItem(book, 1);
        System.out.println(cart.getCartSummary());

        // Order
        Order order = new Order(cart);
        boolean success = ECommerceSystem.processOrder(order, cust);
        System.out.println("Order success? " + success);
    }
}

