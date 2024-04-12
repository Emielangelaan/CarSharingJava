package carsharing.companies;

enum CompanyMenuEnum {
    COMPANY_LIST,
    CREATE_A_COMPANY;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).replace('_', ' ').toLowerCase();
    }
}
