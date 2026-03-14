package org.bda.act1_3;

import jakarta.persistence.*;

// TODO: Check
@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Clothing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;
    private Float price;

    // TODO: Check
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer orderedBy;

    // TODO: Check
    public Clothing(){}

    public Clothing(String description, Float price) {
        this.description = description;
        this.price = price;
    }

    public Long getId(){
        return id;
    }

    public String getDescription(){
        return description;
    } 

    public Float getPrice() {
        return price;
    }

    public Customer getOrderedBy() {
        return orderedBy;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setOrderedBy(Customer orderedBy) {
        this.orderedBy = orderedBy;
    }

    public void displayInformation() {
        System.out.println("ID: " + id + ", description: " + description + ", price: " + price);
    }
}
