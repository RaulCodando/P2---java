package Principal;

public class Product{
    private String brand;
    private double price;
    private ShoppingCart shoppingCart;

    public Product(String brand, double price){
        this.brand = brand;
        this.price = price;
        shoppingCart = null;
    }

    public String getBrand(){
        return brand;
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
