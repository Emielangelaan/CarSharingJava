package carsharing.cars;


import carsharing.Entity;

public class Car implements Entity {
    private int id = 0;
    private String name;
    private int companyId = 0;

    public Car(int id, String name, int companyId) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
    }
    public Car(String name, int companyId) {
        this.name = name;
        this.companyId = companyId;
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
        return this.companyId;
    }
    @Override
    public void setForeignId(int foreignId) {
        this.companyId = foreignId;
    }
}
