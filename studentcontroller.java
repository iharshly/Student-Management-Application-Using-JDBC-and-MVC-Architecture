import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentController {

    private static final String URL = "jdbc:mysql://localhost:3306/your_database_name"; // <-- change this
    private static final String USER = "your_username"; // <-- change this
    private static final String PASSWORD = "your_password"; // <-- change this

    // Establish database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // CREATE operation
    public void addStudent(Student student) {
        String sql = "INSERT INTO Student (Name, Department, Marks) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getDepartment());
            pstmt.setDouble(3, student.getMarks());

            pstmt.executeUpdate();
            System.out.println("✅ Student added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ operation
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Student";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("StudentID"),
                        rs.getString("Name"),
                        rs.getString("Department"),
                        rs.getDouble("Marks")
                );
                students.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    // UPDATE operation
    public void updateStudent(Student student) {
        String sql = "UPDATE Student SET Name=?, Department=?, Marks=? WHERE StudentID=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getDepartment());
            pstmt.setDouble(3, student.getMarks());
            pstmt.setInt(4, student.getStudentID());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Student updated successfully!");
            } else {
                System.out.println("❌ No student found with ID: " + student.getStudentID());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE operation
    public void deleteStudent(int studentID) {
        String sql = "DELETE FROM Student WHERE StudentID=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentID);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Student deleted successfully!");
            } else {
                System.out.println("❌ No student found with ID: " + studentID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
