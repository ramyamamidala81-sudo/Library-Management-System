import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LibraryService {

    public void addBook(String title, String author, int quantity) {

        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO books(title, author, quantity) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, quantity);

            ps.executeUpdate();

            System.out.println("Book Added Successfully");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void viewBooks() {

    try {
        Connection con = DBConnection.getConnection();

        String query = "SELECT * FROM books";

        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        System.out.println("\nBooks List");
        System.out.println("-----------------------------");

        while(rs.next()) {

            System.out.println(
                    rs.getInt("book_id") + " | " +
                    rs.getString("title") + " | " +
                    rs.getString("author") + " | " +
                    rs.getInt("quantity")
            );
        }

    } catch(Exception e) {
        e.printStackTrace();
    }
    }
    public void searchBook(String title) {

    try {
        Connection con = DBConnection.getConnection();

        String query = "SELECT * FROM books WHERE title = ?";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, title);

        ResultSet rs = ps.executeQuery();

        if(rs.next()) {

            System.out.println(
                    rs.getInt("book_id") + " | " +
                    rs.getString("title") + " | " +
                    rs.getString("author") + " | " +
                    rs.getInt("quantity")
            );

        } else {
            System.out.println("Book Not Found");
        }

    } catch(Exception e) {
        e.printStackTrace();
    }
    }
    public void issueBook(int bookId) {
    try {
        Connection con = DBConnection.getConnection();

        String query = "UPDATE books SET quantity = quantity - 1 " + "WHERE book_id=? AND quantity > 0";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, bookId);

        int rows = ps.executeUpdate();

        if (rows > 0)
            System.out.println("Book Issued Successfully");
        else
            System.out.println("Book Not Available");

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    public void deleteBook(int id) {
    try {
        Connection con = DBConnection.getConnection();

        String query = "DELETE FROM books WHERE book_id=?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, id);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Book Deleted Successfully");
        } else {
            System.out.println("Book Not Found");
        }

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    public void returnBook(int id) {
    try {
        Connection con = DBConnection.getConnection();

        String query = "UPDATE books SET quantity = quantity + 1 WHERE book_id=?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, id);

        int rows = ps.executeUpdate();

        if (rows > 0)
            System.out.println("Book Returned Successfully");
        else
            System.out.println("Book Not Found");

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
