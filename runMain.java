import java.util.Scanner;
import java.util.ArrayList;
public class runMain{
   public static void main(String [] args) {
      Scanner scan = new Scanner(System.in);
      boolean loop = true, storageLoop =true;
      int itemCount =0;
      
      ArrayList<Items> itemsList = new ArrayList<>();  
      History history = new History ();    
      
      while (loop) {
         loop = false;
         try {                
            System.out.println("  Welcome to storagez!");
            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ \n");
            System.out.print("[1] INVENTORY\n[2] MARKET\n[3] STORAGE HISTORY\nCHOOSE A MODE: ");
            int modeChoice = scan.nextInt();
            scan.nextLine();
            
            if (modeChoice ==1) {
               do {        
                  storageLoop = false;               
                  System.out.print("\n[1] ADD ITEM/s\n[2] UPDATE ITEM/s\n[3] VIEW INVENTORY\n[4] TOTAL VALUE OF ITEMS\n[5] BACK\nCHOOSE AN OPTION: ");   
                  int menuOption = scan.nextInt();
                  scan.nextLine();
                  
                  if (menuOption == 1) {
                     System.out.print("\nNo. of items to add: ");
                     int n = scan.nextInt();
                     scan.nextLine();
                     for (int i=0; i<n; i++) {
                        System.out.print("Item #" + (i+1) + " name: ");
                        String name = scan.nextLine();  
                       
                        System.out.print("Item #" + (i+1) + " price: ");
                        double price = scan.nextDouble(); 
                     
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
                     System.out.print("\nItem/s successfully added to storage!");
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
                                 System.out.println("ITEM NAME UPDATED SUCCESSFULLY.");
                                 break;
                           
                              case 2:
                                 System.out.print("Enter new price: ");
                                 double newPrice = scan.nextDouble();
                                 double oldPrice = item.getPrice();
                                 item.setPrice(newPrice);  
                                 history.priceChangeRecord(item.getName(), oldPrice, newPrice);
                                 System.out.println("ITEM PRICE UPDATED SUCCESSFULLY.");
                                 break;
                           
                              case 3:
                                 System.out.print("Enter new quantity: ");
                                 int newQuantity = scan.nextInt();
                                 int oldQuantity = item.getQuantity();
                                 if (newQuantity > oldQuantity) {
                                    history.stockIncreaseRecord(item.getName(), newQuantity - oldQuantity, item.getPrice());
                                 } else {
                                    history.stockDecreaseRecord(item.getName(), oldQuantity - newQuantity, item.getPrice());
                                 }
                                 item.setQuantity(newQuantity);  
                                 System.out.println("ITEM QUANTITY UPDATED SUCCESSFULLY.");
                                 break;
                           
                              case 4:
                                 scan.nextLine();
                                 System.out.print("Enter new name: ");
                                 newName = scan.nextLine();
                                 item.setName(newName);
                              
                                 System.out.print("Enter new price: ");
                                 newPrice = scan.nextDouble();
                                 item.setPrice(newPrice);
                              
                                 System.out.print("Enter new quantity: ");
                                 newQuantity = scan.nextInt();
                                 item.setQuantity(newQuantity);
                              
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
                        System.out.println("No items in the inventory.");
                        loop = true;
                     }else{
                        System.out.println("\nINVENTORY DETAILS:"); 
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
                  
                  
                  else if (menuOption == 4) {}
                  else if (menuOption == 5) {loop = true;}
                  else {
                     System.out.println("Error In the input! Please try again..\n");
                     loop = true;
                  }
               }while(storageLoop);
            }
            
            if (modeChoice == 3) {
               history.displayHistory();
               storageLoop = runMain.backOrChangeMode();
               if (!storageLoop) {
                  loop = true;
               }
            }           
         }                   
         catch (Exception  e) {
            System.out.println("\nAN ERROR OCCURRED.. TRY AGAIN? [y/n]: ");
            scan.nextLine();
            char tryAgain = scan.next().charAt(0);
         
            if (tryAgain == 'y' || tryAgain == 'Y') {
               loop = true;
               scan.nextLine();
            }else {
               scan.close();
            }   
         }
      }//end sa while loop
     
     
         
   }
   
   public static boolean backOrChangeMode() {
      Scanner scan = new Scanner(System.in);
      boolean loopActivate = false;
      System.out.print("\n[1] BACK\n[2] CHANGE MODE\nChoose an option: ");
      int choice = scan.nextInt();
      scan.nextLine();
                  
      if (choice == 1) {
         loopActivate = true;
      }
      else if (choice == 2) {
         loopActivate = false;
      } 
      return loopActivate; 
   }
   
   
   
   
}