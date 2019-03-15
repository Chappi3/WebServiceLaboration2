package se.iths.tonny.webservicelaboration2.model;

public class Whiskey {

    private long id;
    private String name;
    private int age;
    private double price;

    public Whiskey() {
        id = 0;
    }

    public Whiskey(long id, String name, int age, double price) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Whiskey other = (Whiskey) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "Whiskey [id=" + id + ", name=" + name + ", age=" + age
                + ", price=" + price + "]";
    }
}
