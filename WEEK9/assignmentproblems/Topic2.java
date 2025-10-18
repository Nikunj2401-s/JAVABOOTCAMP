class Product {
    int productId;
    String productName;

    Product(int productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Same reference
        if (obj == null || getClass() != obj.getClass()) return false;
        Product p = (Product) obj;
        return this.productId == p.productId; // Logical comparison by ID
    }
}

public class Topic2 {
    public static void main(String[] args) {
        Product p1 = new Product(101, "Laptop");
        Product p2 = new Product(101, "Laptop");
        Product p3 = p1;

        System.out.println("p1 == p2 → " + (p1 == p2));          // false (different objects)
        System.out.println("p1.equals(p2) → " + p1.equals(p2));  // true (same productId)
        System.out.println("p1 == p3 → " + (p1 == p3));          // true (same reference)
    }
}
