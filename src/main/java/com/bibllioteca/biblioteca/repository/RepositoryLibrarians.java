package com.bibllioteca.biblioteca.repository;

import com.bibllioteca.biblioteca.domain.Librarian;
import com.bibllioteca.biblioteca.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class RepositoryLibrarians {
    private JdbcUtils dbUtils;

    public RepositoryLibrarians(Properties properties) {
        this.dbUtils = new JdbcUtils(properties);
    }

    public Librarian findOne(Integer id){
        Connection connection= dbUtils.getConnection();
        Librarian user=null;
        try(PreparedStatement preparedStatement= connection.prepareStatement("select * from librarians where id=?")){
            preparedStatement.setInt(1,id);
            try(ResultSet resultSet= preparedStatement.executeQuery()){
                while(resultSet.next())
                {
                    String firstName=resultSet.getString("first_name");
                    String lastName=resultSet.getString("last_name");
                    user= new Librarian(firstName,lastName);
                    user.setId(id);
                    connection.close();
                    return user;
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
        return user;
    }
}
