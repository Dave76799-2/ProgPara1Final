import java.util.Scanner;
import java.util.ArrayList;
public class runMain{
   public static void main(String [] args) {
      Scanner scan = new Scanner(System.in);
      boolean loop = true, storageLoop =true, historyLoop =false;
      int itemCount =0;
   
      ArrayList<Items> itemsList = new ArrayList<>();    
      History history = new History ();  
      
      while (loop) {
         loop = false;
         double totalValue =0;
         try {                
            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _");
            System.out.println("||  Welcome To Storagez!   ||");
            System.out.println("||_ _ _ _ _ _ _ _ _ _ _ _ _||");
            System.out.println();
            System.out.print("[1] INVENTORY\n[2] MARKET\n[3] STORAGE HISTORY\nCHOOSE A MODE: ");
            int modeChoice = scan.nextInt();
            scan.nextLine();
            
            if (modeChoice ==1) {
               do {        
                  storageLoop = false;
                  System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _");
                  System.out.println("||      Storage Mode       ||");
                  System.out.println("||_ _ _ _ _ _ _ _ _ _ _ _ _|| \n");               
                  System.out.print("[1] ADD ITEM/s\n[2] UPDATE ITEM/s\n[3] VIEW INVENTORY\n[4] TOTAL VALUE OF ITEMS\n[5] BACK\nCHOOSE AN OPTION: ");   
                  int menuOption = scan.nextInt();
                  scan.nextLine();
                  
                  if (menuOption == 1) {
                     System.out.print("\nNo. of items to add: ");
                     int n = scan.nextInt();
                     scan.nextLine();
                     for (int i=0; i<n; i++) {
                        System.out.print("Item #" + (i+1) + " name: ");
                        String name = scan.nextLine();  
                       
                        System.out.print("Item #" + (i+1) + " price: Php ");
                        double price = scan.nextDouble(); 
                        scan.nextLine();
                        System.out.print("Item #" + (i+1) + " quantity: ");
                        int quantity = scan.nextInt();   
                        scan.nextLine();
                     
                        Items item = new Items(name, price, quantity);
                        itemsList.add(item);
                        itemCount++;
                        System.out.println();
                        
                        history.stockIncreaseRecord(name, quantity, price);
                        System.out.println();
                     }
                     System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
                     System.out.println("||   Item/s successfully added to storage!   ||");
                     System.out.println("||_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _||\n");                
                     storageLoop = runMain.backOrChangeMode();//Back or Change Mode na choice
                     if (storageLoop == false) {
                        loop = true;
                     }
                               
                  }
                  else if (menuOption == 2) { //
                     System.out.print("Enter item name to update: "); 
                     String updateName = scan.nextLine();
                  
                     boolean found = false;
                  
                     for (int i = 0; i < itemsList.size(); i++) {
                        Items item = itemsList.get(i);
                        if (item.getName().equalsIgnoreCase(updateName)) {  
                           found = true;
                           System.out.println("\nItem found: " + "\nName: " + item.getName() + "\nPrice: " + item.getPrice() + "\nQuantity: " + item.getQuantity());
                        
                           System.out.println("\nWhat would you like to update?\n[1] Name\n[2] Price\n[3] Quantity\n[4] Update all elements");
                           int updateOption = scan.nextInt();                 
                           switch (updateOption) {
                              case 1:
                                 scan.nextLine();
                                 System.out.print("Enter new name: ");
                                 String newName = scan.nextLine();
                                 item.setName(newName);  
                                 System.out.println();
                                 System.out.println("ITEM NAME UPDATED SUCCESSFULLY.");
                                 break;
                           
                              case 2:
                                 System.out.print("Enter new price: ");
                                 double newPrice = scan.nextDouble();
                                 scan.nextLine();
                                 double oldPrice = item.getPrice();
                                 item.setPrice(newPrice);  
                                 history.priceChangeRecord(item.getName(), oldPrice, newPrice);
                                 System.out.println();
                                 System.out.println("ITEM PRICE UPDATED SUCCESSFULLY.");
                                 break;
                           
                              case 3:
                                 System.out.print("Enter new quantity: ");
                                 int newQuantity = scan.nextInt();
                                 scan.nextLine();
                                 int oldQuantity = item.getQuantity();
                                 if (newQuantity > oldQuantity) {
                                    history.stockIncreaseRecord(item.getName(), newQuantity - oldQuantity, item.getPrice());
                                 } else {
                                    history.stockDecreaseRecord(item.getName(), oldQuantity - newQuantity, item.getPrice());
                                 }
                                 item.setQuantity(newQuantity);  
                                 System.out.println();
                                 System.out.println("ITEM QUANTITY UPDATED SUCCESSFULLY.");
                                 break;
                           
                              case 4:
                                 String oldName = item.getName();
                                 oldPrice = item.getPrice();
                                 oldQuantity = item.getQuantity();
                                 
                                 scan.nextLine();
                                 System.out.print("Enter new name: ");
                                 newName = scan.nextLine();
                                 item.setName(newName);
                              
                                 System.out.print("Enter new price: ");
                                 newPrice = scan.nextDouble();
                                 item.setPrice(newPrice);
                              
                                 System.out.print("Enter new quantity: ");
                                 newQuantity = scan.nextInt();
                                 scan.nextLine();
                                 item.setQuantity(newQuantity);
                                 
                                 history.updateAllRecord(oldName, oldPrice, oldQuantity, newName, newPrice, newQuantity);
                                 System.out.println();
                                 System.out.println("ALL ITEM DETAILS UPDATED SUCCESSFULLY.");
                                 break;
                           
                              default:
                                 System.out.println("INVALID INPUT! No updates made.");
                                 break;
                           }
                           break; 
                        }
                     }
                     if (!found) {
                        System.out.println("\nITEM NOT FOUND!");
                     }
                     storageLoop = runMain.backOrChangeMode();//Back or Change Mode na choice
                     if (storageLoop == false) {
                        loop = true;
                     }
                  }
                  
                  else if (menuOption == 3) {
                     if (itemsList.isEmpty()) {
                        System.out.println("\nNo items in the inventory.");
                        storageLoop = runMain.backOrChangeMode();//Back or Change Mode na choice
                        if (storageLoop == false) {
                           loop = true;
                        }
                     
                     }else{
                        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _");
                        System.out.println("||    INVENTORY DETAILS    ||");
                        System.out.println("||_ _ _ _ _ _ _ _ _ _ _ _ _|| \n");       
                        for (Items item : itemsList) {
                           item.availableItems(); // Display tanan item nga na-register                     
                           System.out.println();
                        }
                        storageLoop = runMain.backOrChangeMode();//Back or Change Mode na choice
                        if (storageLoop == false) {
                           loop = true;
                        }
                     }
                  }
                  
                  
                  else if (menuOption == 4) {
                     for (Items item : itemsList) {
                        totalValue += item.totalValue();
                     }
                     System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _");
                     System.out.println("||     <<TOTAL VALUE>>     ||");
                     System.out.println("||_ _ _ _ _ _ _ _ _ _ _ _ _|| \n");
                     System.out.println("Php " + totalValue);
                     storageLoop = runMain.backOrChangeMode();//Back or Change Mode na choice
                     if (storageLoop == false) {
                        loop = true;
                     }
                  
                  }
                  else if (menuOption == 5) {loop = true;}
                  else {
                     System.out.println("Error In the input! Please try again..\n");
                     loop = true;
                  }
               }while(storageLoop);
            }
            else if (modeChoice == 2) {
               String[] items = new String [itemCount];
               
               System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _");
               System.out.println("||       Market Mode       ||");
               System.out.println("||_ _ _ _ _ _ _ _ _ _ _ _ _|| ");  
               if (itemsList.isEmpty()) { 
                  System.out.println("No available items in the Market yet.\n"); 
                  loop = true;
               } else { 
                  System.out.println();
                  System.out.println("ITEMS AVAILABLE:"); 
                  for (Items item : itemsList) { 
                     item.availableItems();                      
                     System.out.println();
                  }
                  
                  System.out.println("[1] SELL");
                  System.out.println("[2] BACK");  
                  System.out.print("CHOOSE AN OPTION: "); 
                  int option = scan.nextInt();
                  scan.nextLine();
                  
                  if (option == 1) {
                     System.out.println("------------------------------------");
                     System.out.print("Name of the item to sell?: ");
                     String sellName = scan.nextLine();
                     
                     boolean found = false;
                     
                     for (int i = 0; i < itemsList.size(); i++) {
                        Items item = itemsList.get(i);
                        if (item.getName().equalsIgnoreCase(sellName)) {
                           found = true; 
                           System.out.print("How many " + '"' + sellName + "/s" + '"' + " to be sold: ");
                           int sellQty = scan.nextInt();
                           scan.nextLine();
                           
                           int newQuantity = item.sell(sellQty);
                           
                           if (newQuantity >= 0) {
                              item.setQuantity(newQuantity);
                              
                               double totalAmount = sellQty * item.price;
                               history.addSaleRecord(sellName, sellQty, totalAmount, item.price);
                           }
                           System.out.println();                   
                           System.out.println("Name: " + sellName + "\nPrice: Php " + item.price + " || QTY: " + item.quantity);        
                           
                           loop = true;
                        } else {
                           System.out.println("\nITEM NOT FOUND!");
                           loop = true;
                        }
                     }
                  } else if (option == 2) { 
                     loop = true;
                  }
               }
            }
            
            if (modeChoice == 3) {
               history.displayHistory();     
               historyLoop = runMain.historyChangeMode();
               if (historyLoop) {
                  loop = true;
               }
            }           
            
         }                   
         catch (Exception  e) {
            System.out.println("\nAN ERROR OCCURRED.. ");      
            do {
            
               System.out.print("TRY AGAIN? [y/n]: ");
               scan.nextLine();
               char c = scan.next().charAt(0);
            
            
               if (c == 'Y' || c == 'y') {
                  c = 'y';
                  loop = true;
                  break;
               }else if (c == 'n' || c == 'N') {
                  c ='n';
                  loop = false;
                  break;
               }else {}
            
            }while (true);            
            
         }//end sa while loop
              
      }
   }
   
   public static boolean backOrChangeMode() {
      Scanner scan = new Scanner(System.in);
      boolean loopActivate = false, escapeLoop = false;
      
     
      do {
         try{  
            escapeLoop = true;
            System.out.println("[1] BACK");
            System.out.println("[2] CHANGE MODE");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();
            
            if (choice == 1) {
               loopActivate = true;
            }
            else if (choice == 2) {
               loopActivate = false;
            }
            else {
               System.out.println();
               System.out.print("Invalid input! Try again:");
               escapeLoop = false;
            }
         }catch (Exception e) {
            System.out.println();
            System.out.println("INVALID INPUT.. ");
            System.out.println();
            escapeLoop = false;
            scan.nextLine();
         }
      }while(escapeLoop != true);
      
      return loopActivate; 
   }
   
   public static boolean historyChangeMode() {
   
   
      Scanner scan = new Scanner(System.in);
      boolean loopActivate = false;
      do {
         try{  
            loopActivate = true;
            System.out.println("[1] BACK to mode menu"); 
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();
            
            if (choice == 1) {
               loopActivate = true;
            }
            else {
               System.out.println();
               System.out.println("INVALID INPUT.. ");
               loopActivate = false;
            }
         }catch (Exception e) {
            System.out.println();
            System.out.println("INVALID INPUT.. ");
            System.out.println();
            loopActivate = false;
            scan.nextLine();
         }
      }while(loopActivate != true);
      return loopActivate; 
   }
   
   
   
}
