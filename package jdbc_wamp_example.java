public class Student {     private int id;     private String name;     private String course; 
 
    public Student(int id, String name, String course) { 
        this.id = id;         this.name = name;         this.course = course; 
    } 
 
    public int getId() { return id; }     public String getName() { return name; }     public String getCourse() { return course; } 
 
    public void setId(int id) { this.id = id; }     public void setName(String name) { this.name = name; }     public void setCourse(String course) { this.course = course; } 
 
    @Override     public String toString() {         return "Student{id=" + id + ", name='" + name + "', course='" + course + "'}"; 
    } 
} 
package jdbc_wamp_example; 
import java.sql.*; import java.util.ArrayList; import java.util.List; 
 
public class StudentDAO { 
 
    public static void addStudent(String name, String course) { 
        String sql = "INSERT INTO student (name, course) VALUES (?, ?)";         try (Connection conn = DBConnect.getConnection();              PreparedStatement stmt = conn.prepareStatement(sql)) {             stmt.setString(1, name);             stmt.setString(2, course);             stmt.executeUpdate(); 
            System.out.println("Student added.");         } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
    } 
 
    public static List<Student> getAllStudents() {         List<Student> list = new ArrayList<>();         String sql = "SELECT * FROM student";         try (Connection conn = DBConnect.getConnection(); 
             Statement stmt = conn.createStatement();              ResultSet rs = stmt.executeQuery(sql)) {             while (rs.next()) {                 list.add(new Student(                         rs.getInt("id"),                         rs.getString("name"),                         rs.getString("course") 
                )); 
            } 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        }         return list; 
    } 
 
    public static void updateStudent(int id, String name, String course) {         String sql = "UPDATE student SET name=?, course=? WHERE id=?";         try (Connection conn = DBConnect.getConnection();              PreparedStatement stmt = conn.prepareStatement(sql)) {             stmt.setString(1, name);             stmt.setString(2, course);             stmt.setInt(3, id);             stmt.executeUpdate(); 
            System.out.println("Student updated.");         } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
    } 
 
    public static void deleteStudent(int id) { 
        String sql = "DELETE FROM student WHERE id=?";         try (Connection conn = DBConnect.getConnection();              PreparedStatement stmt = conn.prepareStatement(sql)) { 
            stmt.setInt(1, id);             stmt.executeUpdate(); 
            System.out.println("Student deleted."); 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
    } 
} 
