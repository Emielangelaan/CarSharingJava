package carsharing.customers;

enum CustomerMenuEnum {
    RENT_A_CAR,
    RETURN_A_RENTED_CAR,
    MY_RENTED_CAR;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).replace('_', ' ').toLowerCase();
    }
}
