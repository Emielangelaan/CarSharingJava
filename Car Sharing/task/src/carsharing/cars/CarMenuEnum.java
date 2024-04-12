package carsharing.cars;

enum CarMenuEnum {
    CAR_LIST,
    CREATE_A_CAR;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).replace('_', ' ').toLowerCase();
    }
}
