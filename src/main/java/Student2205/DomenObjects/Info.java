package Student2205.DomenObjects;

public class Info {
    private String city;
    private String street;
    private String house;


    public Info(String city, String street, String house) {
        this.city = city;
        this.street = street;
        this.house = house;
    }

    public Info() {
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

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
}
