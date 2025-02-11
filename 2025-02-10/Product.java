public class Product{
    private String name;
    private double price;
    private ShoppingCart shoppingCart;

    public Product(String name, double price){
        this.name = name;
        this.price = price;
        shoppingCart = null;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public ShoppingCart getShoppingCart(){
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
