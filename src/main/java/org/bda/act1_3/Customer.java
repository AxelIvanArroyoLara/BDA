package org.bda.act1_3;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerID;

    private String name;
    private String address;

    // TODO: Check
    @OneToMany(mappedBy = "orderedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Clothing> orders = new ArrayList<>();

    public Customer() {}

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Clothing> getOrders() {
        return orders;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addOrder(Clothing clothing) {
        orders.add(clothing);
        clothing.setOrderedBy(this);
    }

    public void removeOrder(Clothing clothing) {
        orders.remove(clothing);
        clothing.setOrderedBy(null);
    }

    public void displayInformation() {
        System.out.println("CustomerID: " + customerID + ", name: " + name + ", address: " + address);
    }
}