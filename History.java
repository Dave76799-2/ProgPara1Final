import java.util.ArrayList;

public class History {

   public static class Record {
      String type; 
      String name;
      String details; 
   
      public Record(String recordType, String recordName, String recordDetails) {
         type = recordType;
         name = recordName;
         details = recordDetails;
      }
   
      public void recordDisplay() {
         System.out.println("Type: " + type);
         System.out.println("Item: " + name);
         System.out.println("Details: " + details);
         System.out.println();
      }
   }

   private ArrayList<Record> historyRecord;

   public History() {
      historyRecord = new ArrayList<>();
   }

   public void addSaleRecord(String name, int quantity, double totalAmount) {
      String details = "Sold " + quantity + " units. Total: Php " + totalAmount;
      historyRecord.add(new Record("Sale", name, details));
   }

   public void stockIncreaseRecord(String name, int quantityAdded) {
      String details = "Stock added: " + name + " by " + quantityAdded + " units.\nNo items sold.";
      historyRecord.add(new Record("Stock Added", name, details)   );
   }
    
   public void stockDecreaseRecord(String name, int quantityRemoved) {
      String details = "Stock decreased by " + quantityRemoved + " units.";
      historyRecord.add(new Record("Stock Decrease", name, details));
   }

   public void displayHistory() {
      if (historyRecord.isEmpty()) {
         System.out.println("No history records available.");
      } else {
         System.out.println("\nHISTORY (Stocks added/Items sold):\n");
         for (Record record : historyRecord) {
            record.recordDisplay();
         }
      }
   }
}
