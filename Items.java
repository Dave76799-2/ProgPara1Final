import java.util.ArrayList;
public class Items {

   String name;
   double price;
   int quantity; 
   
   public Items() {
      this.name = "";
      this.price = 0.0;
      this.quantity = 0;
   }
   
   
   public Items(String name, double price, int quantity) {
      this.name = name;
      this.price = price;
      this.quantity = quantity;
     
   }
   
   public void availableItems() {      
      System.out.println("Name: " + name + "\nPrice: Php " + price + " || QTY: " + quantity);      
   }
   
   public String getName() {
      return name;
   }
   
   public double getPrice() {
      return price;
   }
   
   public int getQuantity() {
      return quantity;
   }
   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }
   public void setName(String name) {
      this.name = name;
   }
   public void setPrice(double price) {
        this.price = price;
    }
    
   public double totalValue() {
      return this.price*this.quantity;
   }
   
   public int sell(int amount) {
   int itemsLeft =-10;
   if (amount > this.quantity) {
      System.out.println();
      System.out.println("Not enough items in the inventory!!");
   }
   else {
      itemsLeft = this.quantity - amount;
      System.out.println("Items sold!!");
   }
      return itemsLeft;
   }
   
}
