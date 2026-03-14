package org.bda.act1_3;

import jakarta.persistence.Entity;

@Entity
public class Pant extends Clothing {

    private Integer size;
    private char gender;
    private char colorCode;

    public Pant() {}

    public Pant(String description, Float price, Integer size, char gender, char colorCode) {
        super(description, price);
        this.size = size;
        this.gender = gender;
        this.colorCode = colorCode;
    }

    public Integer getSize() {
        return size;
    }

    public char getGender() {
        return gender;
    }

    public char getColorCode() {
        return colorCode;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setColorCode(char colorCode) {
        this.colorCode = colorCode;
    }

    @Override
    public void displayInformation() {
        System.out.println("Pant -> description: " + getDescription() +
                ", price: " + getPrice() +
                ", size: " + size +
                ", gender: " + gender +
                ", colorCode: " + colorCode);
    }
}