package WEEK9.labproblems;

class Address {
    String city;

    Address(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return city;
    }
}

class Person implements Cloneable {
    String name;
    Address address;

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Shallow clone
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Deep clone
    protected Person deepClone() {
        return new Person(this.name, new Address(this.address.city));
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', address=" + address + "}";
    }
}

public class Inner {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address addr = new Address("Delhi");
        Person p1 = new Person("Ravi", addr);

        // Shallow copy
        Person shallow = (Person) p1.clone();

        // Deep copy
        Person deep = p1.deepClone();

        // Modify original
        p1.address.city = "Mumbai";

        System.out.println("Original: " + p1);
        System.out.println("Shallow Clone: " + shallow);
        System.out.println("Deep Clone: " + deep);
    }
}
