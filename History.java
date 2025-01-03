//This is a separate java file that can be called in the main file to record the storage history such as the addidtion and decrease of stocks, and the records of the sold stocks.
import java.util.ArrayList;

public class History {

   public static class Record {
      String type, name, details; 
      double price;
      
      public Record(String recordType, String recordName, String recordDetails, double recordPrice) {
         type = recordType;
         name = recordName;
         details = recordDetails;
         price = recordPrice;
      }

      //this displays the record in a formatted way.
      public void recordDisplay() {
         System.out.print("_________________");
         System.out.print("\nUpdated History: \n");
         System.out.println("Type: " + type);
         System.out.println("Item: " + name);
         System.out.println("Price: Php " + price);
         System.out.println("Details: " + details);
         System.out.println();
      }
   }

   private ArrayList<Record> historyRecord; //this stores all the history records.

   public History() {
      historyRecord = new ArrayList<>();
   }

   //this adds the record of the sales done in the market.
   public void addSaleRecord(String name, int quantity, double totalAmount, double price) {
      String details = "Sold " + quantity + " units. Total: Php " + totalAmount;
      historyRecord.add(new Record("Sale", name, details, price));
   }

   //this adds a record when a product has its price changed.
   public void priceChangeRecord(String name, double oldPrice, double newPrice){
      String details = "Price changed from Php " + oldPrice + " to Php " + newPrice + ".\nNo items sold.";
      historyRecord.add(new Record("Price Change", name, details, newPrice));
      
   }

   //this records all the changes in the quantity, the increase and the decrease of the said product.
   public void quantityChangeRecord(String name, int quantityChanged, double price) {
      String details = "Quantity changed by " + quantityChanged + " units.";
      historyRecord.add(new Record("Quantity Change", name, details, price));
      
   }

   //records the stock that has been added.
   public void stockIncreaseRecord(String name, int quantityAdded, double price) {
      String details = "Added " + quantityAdded + " units in the inventory.\nNo items sold.";
      historyRecord.add(new Record("Stock Added", name, details, price));
   }

   //records the stock that has been decreased/removed in the current storage inventory.
   public void stockDecreaseRecord(String name, int quantityRemoved, double price) {
      String details = "Stock decreased by " + quantityRemoved + " units.\nNo items sold.";
      historyRecord.add(new Record("Stock Decrease", name, details, price));
   }

   //this records the changing of all attributes of an item stored in the inventory.
   public void updateAllRecord(String oldName, double oldPrice, int oldQuantity, String newName, double newPrice, int newQuantity) {
      String details = "Updated all fields:\n\n" +
                     "Name: From" + oldName + " to " + newName + ";\n" +
                     "Price: From Php " + oldPrice + " to Php " + newPrice + ";\n" +
                     "Quantity: " + oldQuantity + "units to " + newQuantity + " units";
      historyRecord.add(new Record("Full Update", newName, details, newPrice));
}

   //this shows the history log, which sends a message if no such records have been done yet.
   public void displayHistory() {
      if (historyRecord.isEmpty()) {
         System.out.println("No history records available.");
      } else {
          System.out.println("___ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ___");
          System.out.println("||               STORAGE HISTORY             ||");
          System.out.println("||  (Added/Decreased Items and Sold Items)   ||");
          System.out.println("||_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _||");
         for (Record record : historyRecord) {
            record.recordDisplay();
         }
      }
   }
}
