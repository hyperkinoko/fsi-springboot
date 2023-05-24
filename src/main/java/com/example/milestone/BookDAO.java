package com.example.milestone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private Connection connection;

    public BookDAO() {
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

    public Book createBook(PostBookRequest input) {
        Book book = new Book(input.getTitle(), input.getAuthor(), input.getNumOfPages(), input.getClassification());

        String sql = "INSERT INTO books (id, title, author, numOfPages, classification) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setInt(4, book.getNumOfPages());
            statement.setString(5, book.getClassification().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

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
        String sql = "SELECT * FROM books WHERE id = ?";

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

    public Book updateBook(String id, PostBookRequest input) {
        String sql = "UPDATE books SET title = ?, author = ?, numOfPages = ?, classification = ? WHERE id = ?";

        Book book = findBookById(id);

        if(book == null) {
            return null;
        }

        book.setTitle(input.getTitle());
        book.setAuthor(input.getAuthor());
        book.setNumOfPages(input.getNumOfPages());
        book.setClassification(BookClassification.fromNumber(input.getClassification()));

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getNumOfPages());
            statement.setString(4, book.getClassification().toString());
            statement.setString(5, book.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public void deleteBookById(String id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
