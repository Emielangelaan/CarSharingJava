package carsharing.companies;


import carsharing.Entity;

public class Company implements Entity {
    private int id = 0;
    private String name;

    public Company(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Company(String name) {
        this.name = name;
    }

    @Override
    public int getId() {
        return this.id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public int getForeignId() {
        return 0;
    }
    @Override
    public void setForeignId(int foreignId) {}
}