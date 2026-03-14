package org.bda.act1_3;
import jakarta.persistence.*;;

@Entity
public class Shirt extends Clothing{
    private char size;
    private char colorCode;

    public Shirt(){}

    public Shirt(String description, Float price, char size, char colorCode){
    super(description, price);
    this.size = size;
    this.colorCode = colorCode;
    }

    public char getSize(){
        return size;
    }

    public char getColorCode(){
        return colorCode;
    }

    public void setSize(char size){
        this.size = size;
    }

    public void setColorCode(char colorCode) {
        this.colorCode = colorCode;
    }

    @Override
    public void displayInformation() {
        System.out.println("Shirt -> description: " + getDescription() +
                ", price: " + getPrice() +
                ", size: " + size +
                ", colorCode: " + colorCode);
    }
}
