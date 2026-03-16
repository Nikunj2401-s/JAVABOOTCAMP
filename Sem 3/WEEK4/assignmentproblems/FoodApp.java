package WEEK4.assignmentproblems;

class Order {
    String item;
    int qty;
    int price;

    Order(String item, int qty, int price) {
        this.item = item;
        this.qty = qty;
        this.price = price;
    }

    int getTotal() {
        return qty * price;
    }

    void show() {
        System.out.println(qty + " x " + item + " @ " + price + " = " + getTotal());
    }
}

public class FoodApp {
    public static void main(String[] args) {
        Order o1 = new Order("Burger", 2, 120);
        Order o2 = new Order("Fries", 1, 80);
        Order o3 = new Order("Cola", 3, 50);

        o1.show();
        o2.show();
        o3.show();

        int total = o1.getTotal() + o2.getTotal() + o3.getTotal();
        System.out.println("Final Bill = " + total);
    }
}

