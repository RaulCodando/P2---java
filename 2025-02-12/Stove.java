package Principal;

public class Stove extends Product{
    int burners;

    public Stove(int burners, String brand, int price){
        super(brand, price);
        this.burners = burners;
    }

    public int getBurners() {
        return burners;
    }
}
