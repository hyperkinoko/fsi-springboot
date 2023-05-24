package com.example.milestone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    private Connection connection;

    public BookDao() {
        // データベースの接続設定
        String url = "jdbc:h2:./milestone";
        String username = "sa";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book createBook(Book book) {
        String sql = "INSERT INTO book (id, title, author, numOfPages, classification) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setInt(4, book.getNumOfPages());
            statement.setInt(5, book.getClassification().ordinal());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Book book = createBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public Book findBookById(String id) {
        String sql = "SELECT * FROM book WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return createBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateBook(Book book) {
        String sql = "UPDATE book SET title = ?, author = ?, numOfPages = ?, classification = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getNumOfPages());
            statement.setInt(4, book.getClassification().ordinal());
            statement.setString(5, book.getId());

            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteBookById(String id) {
        String sql = "DELETE FROM book WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);

            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ResultSetからBookオブジェクトを作成する
    private Book createBookFromResultSet(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        int numOfPages = resultSet.getInt("numOfPages");
        int classification = resultSet.getInt("classification");

        Book book = new Book(title, author, numOfPages, classification);
        book.setId(id);
        return book;
    }
}
