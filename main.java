public class Main {
    public static void main(String[] args) {
        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Start the application
            StudentView view = new StudentView();
            view.showMenu();

        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found!");
        }
    }
}
