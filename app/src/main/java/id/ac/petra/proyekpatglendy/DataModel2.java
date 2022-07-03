package id.ac.petra.proyekpatglendy;

public class DataModel2 {
    int id;
    String name;
    String price;

    public DataModel2(int id, String name, String price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public DataModel2(String name, int id, String price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DataModel2(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
