package carsharing;

public class Main {
    public static void main(String[] args) {
        new InitializeDb();
        Menu menu = new LoginMenu();
        menu.openMenu();
    }
}