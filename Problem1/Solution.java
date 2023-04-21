import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static void seedOriginalList(OriginalList oList) {
        Stock seedStock = new Stock();
        seedStock.Name = "apple";
        seedStock.Price = 100;
        oList.addStock(seedStock);
        seedStock = new Stock();
        seedStock.Name = "tesla";
        seedStock.Price = 50;
        oList.addStock(seedStock);
        seedStock = new Stock();
        seedStock.Name = "google";
        seedStock.Price = 10;
        oList.addStock(seedStock);
        seedStock = new Stock();
        seedStock.Name = "nasa";
        seedStock.Price = 200;
        oList.addStock(seedStock);
    }
    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        //initialise lists and seed
        OriginalList oList = new OriginalList();
        seedOriginalList(oList);
        PortfolioList pList = new PortfolioList(oList);
        Scanner sc = new Scanner(System.in);
        System.out.println("Plese Select an operation:");
        System.out.println("1. Add Stock to original List");
        System.out.println("2. Add Stock to Portfolio List");
        System.out.println("3. Display the original List");
        System.out.println("4. Display the Portfolio List");
        System.out.println("5. Purchase the Portfolio List");
        System.out.println("6. Sell the Portfolio List");;
        while(true){
            String mainChoice = sc.nextLine();
            // sc.close();
            switch(mainChoice){
                case "1": 
                String oStockName = sc.nextLine();
                String oStockPrice = sc.nextLine();
                Stock oStockEntry = new Stock();
                oStockEntry.Name = oStockName;
                oStockEntry.Price = Double.parseDouble(oStockPrice);
                oList.addStock(oStockEntry);
		System.out.println("Added Stock to original List. Enter coice for main menu.");;
                break;
                case "2": 
                    String pStockName = sc.nextLine();
                    String pStockQty = sc.nextLine();
                    pList.addPortfolioStock(pStockName, Integer.parseInt(pStockQty));
		    System.out.println("Portfolio Stock added. Enter coice for main menu.");;
                break;
                case "3": 
                    oList.displayList();
		    System.out.println("Enter coice for main menu.");;
                break;
                case "4": 
                    pList.displayList();
		    System.out.println("Enter coice for main menu.");;
                break;
                case "5": 
                    String purStockName = sc.nextLine();
                    String purStockQty = sc.nextLine();
                    pList.purchaseStock(purStockName, Integer.parseInt(purStockQty));
		    System.out.println("Portfolio Stock added. Enter coice for main menu.");;
                break;
                case "6": 
                    String sellStockName = sc.nextLine();
                    String sellStockQty = sc.nextLine();
                    pList.sellStock(sellStockName, Integer.parseInt(sellStockQty));
		    System.out.println("Portfolio Stock sold. Enter coice for main menu.");;
                break;
                default:
                    System.out.println("Invalid Main Menu choice, please try again.");
                break;
            }
        }
    }
}

class Stock{
    public String Name;
    public double Price;
}

class UserPortfolioStock extends Stock{
    int Qty;
    double TotalStockPrice;
}

class OriginalList{
    ArrayList<Stock> originalList = new  ArrayList<Stock>();
    int maxCap = 1000;
    int actualCap;
    private Stock getStock(Stock s){
        Stock res = null;
        for(Stock o :originalList ){
            if(o.Name.equals(s.Name)){
                res = o;
                break;
            }
        }
        return res;
    }
    public void addStock(Stock s){
        if(actualCap + 1 > maxCap){
            System.out.println("Stock Cant be purchased.");
            return;
        }
        Stock existingStock = getStock(s);
        if(existingStock != null){
            System.out.println("Stock is already added to the original List.");
            return;
        }
        originalList.add(s);
        actualCap++;
    }
    public void displayList(){
        for(Stock o :originalList ){
            System.out.println("Stock Name: "+ o.Name);
            System.out.println("Stock Price: "+ o.Price);
        }
    }
}

class PortfolioList{
    PortfolioList(){}
    PortfolioList(OriginalList oList){
        this.oList = oList;
    }
    private OriginalList oList;
    ArrayList<UserPortfolioStock> portfolioList = new  ArrayList<UserPortfolioStock>();
    int maxCap = 1000;
    int actualCap;
    private Stock checkOlistForStock(String name){
        Stock res = null;
        for(Stock o :oList.originalList ){
            if(o.Name.equals(name)){
                res = o;
                break;
            }
        }
        return res;
    }
    private UserPortfolioStock checkPortfolioListForStock(String name){
        UserPortfolioStock res = null;
        for(UserPortfolioStock o :portfolioList ){
            if(o.Name.equals(name)){
                res = o;
                break;
            }
        }
        return res;
    }
    public void addPortfolioStock(String name, int qty){
        Stock oStock = checkOlistForStock(name);
        if(oStock == null){
            System.out.println("Invalid stock. Add operation cannot be performed.");
            return;
        }
        if(actualCap + qty > maxCap){
            System.out.println("Stock Cant be purchased.");
            return;
        }
        UserPortfolioStock pStock = checkPortfolioListForStock(name);
        if(pStock != null){
            pStock.Qty = pStock.Qty + qty;
            pStock.TotalStockPrice = pStock.Price * pStock.Qty;
	    actualCap += qty;
            return;
        }
        
        pStock = new UserPortfolioStock();
        pStock.Price = oStock.Price;
        pStock.Name=  oStock.Name;
        pStock.Qty= qty;
        pStock.TotalStockPrice = pStock.Price * pStock.Qty;
	actualCap += qty;
	portfolioList.add(pStock);
	
    }
    public void purchaseStock(String name, int qty){
        Stock oStock = checkOlistForStock(name);
        if(oStock == null){
            System.out.println("Invalid stock. Purchade operation cannot be performed.");
            return;
        }
        if(actualCap + qty > maxCap){
            System.out.println("Stock Cant be purchased.");
            return;
        }
        UserPortfolioStock pStock = checkPortfolioListForStock(name);
        if(pStock != null){
            pStock.Qty = pStock.Qty + qty;
            pStock.TotalStockPrice = pStock.Price * pStock.Qty;
	    actualCap += qty;
            return;
        }
        
        pStock = new UserPortfolioStock();
        pStock.Price = oStock.Price;
        pStock.Name=  oStock.Name;
        pStock.Qty= qty;
        pStock.TotalStockPrice = pStock.Price * pStock.Qty;
	portfolioList.add(pStock);
	actualCap += qty;
    }
    
    public void sellStock(String name, int qty){
        Stock oStock = checkOlistForStock(name);
        if(oStock == null){
            System.out.println("Invalid stock. Purchade operation cannot be performed.");
            return;
        }
        UserPortfolioStock pStock = checkPortfolioListForStock(name);

        if(pStock == null){
             System.out.println("Stock dosent exist.");
            return;
        }
        
        if(actualCap < qty || pStock.Qty < qty ){
            System.out.println("Invalid Stock Quantity.");
            return;
        }
	if(pStock.Qty == qty){
	    portfolioList.remove(pStock);
            return;
        }
        pStock.Qty= pStock.Qty - qty;
        pStock.TotalStockPrice = pStock.Price * pStock.Qty;
	actualCap -= qty;
    }
    
    public void displayList(){
        for(UserPortfolioStock p :portfolioList ){
            System.out.println("Stock Name: "+ p.Name);
            System.out.println("Stock Price: "+ p.Price);
            System.out.println("Stock Quantity: "+ p.Qty);
            System.out.println("Stock Total Price: "+ p.TotalStockPrice);
        }
    }
}
