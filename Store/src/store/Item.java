//Ghaida Alangari, 439017383, 374
package store;

public class Item {
    /* COMPLETE ITEM CLASS */
    private int itemNumber;
    private String itemName;
    private double itemPrice, itemCost;
    
    public Item(int in, String n, int ip){
        itemNumber = in;
        itemName = n;
        itemPrice = ip;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
    }
    public void print(){
        System.out.printf("\t\t- Number: %d\n\t\t- Name: %s\n\t\t- Price: %.2f SAR\n",itemNumber,itemName,itemPrice);
    }  

    public String toString1() {
        return "Item{" + "itemNumber = " + itemNumber + ", itemName = " + itemName + ", itemPrice = " + itemPrice + '}';
    }

    @Override
    public String toString() {
        return "Item{" + "itemNumber = " + itemNumber + ", itemName = " + itemName + '}';
    }
}
