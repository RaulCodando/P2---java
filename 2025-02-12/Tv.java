package Principal;

public class Tv extends Product{
    int inches;

    public Tv(int inches, String brand, int price){
        super(brand, price);
        this.inches = inches;
    }

    public int getInches() {
        return inches;
    }
}
