package org.bda.act1_3;

import jakarta.persistence.Entity;

@Entity
public class Sock extends Clothing {

    private char colorCode;

    public Sock() {}

    public Sock(String description, Float price, char colorCode) {
        super(description, price);
        this.colorCode = colorCode;
    }

    public char getColorCode() {
        return colorCode;
    }

    public void setColorCode(char colorCode) {
        this.colorCode = colorCode;
    }

    @Override
    public void displayInformation() {
        System.out.println("Sock -> description: " + getDescription() +
                ", price: " + getPrice() +
                ", colorCode: " + colorCode);
    }
}