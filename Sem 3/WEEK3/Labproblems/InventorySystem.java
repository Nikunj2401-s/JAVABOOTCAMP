package javabootcamp.WEEK3.Labproblems;

class Supplier {
    int supplierId;
    String name;

    Supplier(int supplierId, String name) {
        this.supplierId = supplierId;
        this.name = name;
    }

    void displaySupplier() {
        System.out.println("Supplier ID: " + supplierId + " | Name: " + name);
    }
}

class Product {
    int productId;
    String productName;
    int quantity;
    Supplier supplier;   // Relationship: Product has a Supplier

    Product(int productId, String productName, int quantity, Supplier supplier) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.supplier = supplier;
    }

    void displayProduct() {
        System.out.println("Product ID: " + productId + " | Name: " + productName + 
                           " | Quantity: " + quantity + 
                           " | Supplied by: " + supplier.name);
    }
}

public class InventorySystem {
    public static void main(String[] args) {
        // Create suppliers
        Supplier s1 = new Supplier(101, "ABC Traders");
        Supplier s2 = new Supplier(102, "XYZ Distributors");

        // Create products array
        Product[] products = new Product[3];
        products[0] = new Product(1, "Laptop", 10, s1);
        products[1] = new Product(2, "Smartphone", 25, s2);
        products[2] = new Product(3, "Printer", 5, s1);

        // Display all products
        System.out.println("=== Product Inventory ===");
        for (int i = 0; i < products.length; i++) {
            products[i].displayProduct();
        }

        // Display suppliers
        System.out.println("\n=== Suppliers ===");
        s1.displaySupplier();
        s2.displaySupplier();
    }
}
