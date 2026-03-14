package org.bda.act1_3;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Queries {

    @Entity
    public static class Clothing{

        // Generación del ID
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        public Long id;

        public String description;
        public Float price;

        public Clothing(){}

        public Clothing(String description, Float price){
            this.description = description;
            this.price = price;
        }
    }

    @Entity
    public static class Customer{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        public Long customerID;

        public String name;
        public String address;

        public Customer(){}

        public Customer(String name, String address){
            this.name = name;
            this.address = address;
        }
    }


}
