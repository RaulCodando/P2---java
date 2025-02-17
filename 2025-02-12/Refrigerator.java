package Principal;

public class Refrigerator extends Product{
    int size;

    public Refrigerator(int size, String brand, int price){
        super(brand, price);
        this.size = size;
    }

    public int getSize(){
        return size;
    }
}
