package org.bda.act1_3;

import jakarta.persistence.*;

@Entity
public class Hat extends Clothing{
    private char colorCode;

    public Hat(){}

    public Hat(String description, Float price, char colorCode) {
        super(description, price);
        this.colorCode = colorCode;
    }

    public char getColorCode(){
        return colorCode;
    }

    public void setColorCode(){
        this.colorCode = colorCode;
    }

    @Override
    public void displayInformation(){
        System.out.println("Hat -> description: " + getDescription() +
                ", price: " + getPrice() +
                ", colorCode: " + colorCode);
    }
}
