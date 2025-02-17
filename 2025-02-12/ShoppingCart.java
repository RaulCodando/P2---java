package Principal;

import java.util.ArrayList;

public class ShoppingCart{
    private int costumerID;
    private ArrayList<Product> productList = new ArrayList<Product>();

    public ShoppingCart(int costumerID){
        this.costumerID = costumerID;
    }

    public void addProduct(Product produto){
        if(produto.getShoppingCart() != null){
            throw new IllegalArgumentException("Esse produto já está em um carrinho.");
        }
        productList.add(produto);
        produto.setShoppingCart(this);
    }

    public void removeProduct(Product produto){
        productList.remove(produto);
    }

    public String getContents(){
        String contents = "Listed products: \n";
        for(Product produto : productList){
            contents += "Nome: " + produto.getBrand() + "\n";
            contents += "Preço: " + produto.getPrice() + ";\n\n";
        }
        return contents;
    }

    public int getCostumerID(){
        return costumerID;
    }

    public int getItemCount(){
        return productList.size();
    }

    public double getTotalPrice(){
        double totalPrice = 0;
        for (Product produto : productList) {
            totalPrice += produto.getPrice();
        }
        return totalPrice;
    }
}
