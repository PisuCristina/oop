package view;
import view.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepo {
    private List<Book> books;

    public void populateList(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/oop", "postgres", "lambor345");
            Statement st = conn.createStatement();
            String qs1 = "SELECT * FROM book";
            ResultSet resultSet=st.executeQuery(qs1);
            while(resultSet.next()) {
                Book tmp = new Book(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3),resultSet.getDouble(6),resultSet.getString(5),Integer.parseInt(resultSet.getString(4)));

                books.add(tmp);
            }
        } catch (SQLException e){
            System.out.println("Wrong: " + e.getMessage());
        }
    }

    public void searchBook(String searched){
        try{
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/oop", "postgres", "lambor345");
            Statement st = conn.createStatement();
            String query = "SELECT * FROM book WHERE title LIKE "+ "'%"+searched+"%'"+ "OR author LIKE "+"'%"+searched+"%'"+ "OR category LIKE "+"'%"+searched+"%'";
            ResultSet resultSet=st.executeQuery(query);
            while(resultSet.next()) {
                Book tmp = new Book(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3),resultSet.getDouble(6),resultSet.getString(5),Integer.parseInt(resultSet.getString(4)));

                books.add(tmp);
            }
        } catch (SQLException e){
            System.out.println("Wrong: " + e.getMessage());
        }
    }


    public BookRepo() {
        books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }
}