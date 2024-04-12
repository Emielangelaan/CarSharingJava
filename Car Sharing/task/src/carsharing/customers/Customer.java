package carsharing.customers;

import carsharing.Entity;

public class Customer implements Entity {
    private int id = 0;
    private String name;
    private int rentedCarId = 0;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Customer(int id, String name, int rentedCarId) {
        this.id = id;
        this.name = name;
        this.rentedCarId = rentedCarId;
    }
    public Customer(String name) {
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
        return this.rentedCarId;
    }
    @Override
    public void setForeignId(int foreignId) {
        this.rentedCarId = foreignId;
    }
}
