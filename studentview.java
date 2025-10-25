import java.util.List;
import java.util.Scanner;

public class StudentView {
    private StudentController controller;
    private Scanner sc;

    public StudentView() {
        controller = new StudentController();
        sc = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n===== STUDENT MANAGEMENT MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    System.out.println("👋 Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 5);
    }

    private void addStudent() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();

        Student s = new Student(0, name, dept, marks);
        controller.addStudent(s);
    }

    private void viewAllStudents() {
        List<Student> list = controller.getAllStudents();
        if (list.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("\n=== STUDENT LIST ===");
            for (Student s : list) {
                System.out.println(s);
            }
        }
    }

    private void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter new Name: ");
        String name = sc.nextLine();
        System.out.print("Enter new Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter new Marks: ");
        double marks = sc.nextDouble();

        Student s = new Student(id, name, dept, marks);
        controller.updateStudent(s);
    }

    private void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();
        controller.deleteStudent(id);
    }
}
