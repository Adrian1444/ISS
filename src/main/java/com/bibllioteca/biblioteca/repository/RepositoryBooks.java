package com.bibllioteca.biblioteca.repository;

import com.bibllioteca.biblioteca.domain.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepositoryBooks {
    private JdbcUtils dbUtils;

    public RepositoryBooks(Properties properties) {
        this.dbUtils=new JdbcUtils(properties);
    }


    public List<Book> findAllAvailableBooks() {
        Connection connection= dbUtils.getConnection();
        List<Book> availableBooks=new ArrayList<>();
        try(PreparedStatement preparedStatement= connection.prepareStatement("select * from books where status='AVAILABLE'")){
            try(ResultSet resultSet= preparedStatement.executeQuery()){
                while(resultSet.next())
                {
                    int idUnique=resultSet.getInt("idUnique");
                    int idBook=resultSet.getInt("idBook");
                    String title=resultSet.getString("title");
                    String author=resultSet.getString("author");
                    Book book=new Book(idBook,title,author);
                    availableBooks.add(book);

                }
            }
        }catch (SQLException e)
        {
            System.err.println("Error DB "+e);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableBooks;
    }
}
