import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StoreInventory inv = new StoreInventory(0, 0);
        setUpInvenory(inv, in);
        Customer c = new Customer(1, "Name", "Email", "Addr");
        addProductToCart(c, inv, in);

        int choice= in.nextInt();
        switch (choice) {
            case 1:
                // add product to inventory
                addProductsToInventory(inv, in);
                inv.display();
                // display inventory
                break;

            case 2:
                // update product in inventory
                // display inventory
                break;

            case 3:
                // remove product from inventory
                // display inventory
                break;

            case 4:
                // add product to cart
                // display cart
                break;

            case 5:
                // remove product from cart
                // display cart
                break;

            case 6:
                // place order
                break;
        }

    }
    
    static void setUpInvenory(StoreInventory inv, Scanner in){
        int invCap = in.nextInt();
        in.nextLine();
        int invSize = in.nextInt();
        in.nextLine();
        inv = new StoreInventory(invCap, invSize);
        for(int i = 0; i< invSize; i++)addProductsToInventory(inv, in);
    }
    static void addProductsToInventory(StoreInventory inv, Scanner in){
        int id = in.nextInt();
        in.nextLine();
        String name = in.nextLine();
        double price = in.nextDouble();
        in.nextLine();
        int qty = in.nextInt();
        in.nextLine();
        inv.addProduct(new Product(id, name, price, qty));
    }
    
    static void addProductToCart( Customer c, StoreInventory inv, Scanner in){
        int lp = in.nextInt();
        in.nextLine();
        String name = in.nextLine();
        int qty = in.nextInt();
        in.nextLine();
        for(int i = 0; i<lp; i++)c.cart.addProduct(name, qty, inv);
        
        
    }
}


class StoreInventory{
    private ArrayList<Product> products;
    private int Capacity;
    private int Size;
    StoreInventory(int Capacity,int Size ){
        products = new ArrayList<Product>();
        this.Capacity = Capacity;
        this.Size = Size;
    }
    void addProduct(Product p){
        if(products.size()<Capacity)products.add(p); 
    }
    void display(){
        for(Product p : products){
            System.out.println(p.getName()+ ": " + p.getQty());
        }
    }
    Product getProuctByName(String name){
        for(Product p : products){
            if(p.getName().equals(name)) return p;
        }
        return null;
    }
    
}

class Product{
    private int Id;
    private String Name;
    private double Price;
    private int Qty;
    Product(int id, String name,  double price,int qty ){
        this.Id =   id;
        this.Name = name;
        this.Price = price;
        this.Qty = qty;
    }
    String getName(){
        return this.Name;
    }
    int getQty(){
        return this.Qty;
    }
    double getPrice(){
        return this.Price;
    }
}


class Customer{
    private int Id;
    private String Name;
    private String Email;
    private String Addr;
    Cart cart;
    Customer(int Id, String Name,  String Email,String Addr ){
        this.Id =   Id;
        this.Name = Name;
        this.Email = Email;
        this.Addr = Addr;
        this.cart = new Cart();
    }
}

class Cart{
    private static int MAX_CART_SIZE = 10;
    private ArrayList<Product> products;
    private double TotalcartAmount;
    Cart(){
        products = new ArrayList<Product>();
    }
    
    void addProduct(String name, int qty, StoreInventory inv){
        if(products.size() + 1 > MAX_CART_SIZE){
            System.out.println("Cart is full. Product can't be added to the cart.");
            return;
        }
        Product p =inv.getProuctByName(name);
        if(p == null){
            System.out.println("Product doesn't exist in inventory.");
            return;
        }
        products.add(new Product(Util.getRandom(), name,p.getPrice() , qty));
    }
}

class OrderedProuct{
    private Product product;
    private String date;
    private String time;
    OrderedProuct(Product product,String date,String time){
        this.date = date;
        this.time =  time;
        this.product = product; 
    }
}

class Order{
    private ArrayList<OrderedProuct> products;
    Order(){
        products = new ArrayList<OrderedProuct>();
    }
}

class Util{
    public static int getRandom(){
        return (int)(Math.random() *1000);
    }
}


