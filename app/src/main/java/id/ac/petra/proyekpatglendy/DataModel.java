package id.ac.petra.proyekpatglendy;

public class DataModel {
    int id;
    String city;
    String street;

    public DataModel(int id, String city, String street) {
        this.id = id;
        this.city = city;
        this.street = street;
    }

    public DataModel(String city, int id, String street){
        this.id = id;
        this.city = city;
        this.street = street;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DataModel(){

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
