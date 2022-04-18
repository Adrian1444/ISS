package com.bibllioteca.biblioteca.repository;

import com.bibllioteca.biblioteca.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class RepositoryUsers {
    private JdbcUtils dbUtils;

    public RepositoryUsers(Properties properties) {
        this.dbUtils = new JdbcUtils(properties);
    }

    public User findOne(Integer id){
        Connection connection= dbUtils.getConnection();
        User user=null;
        try(PreparedStatement preparedStatement= connection.prepareStatement("select * from users where id=?")){
            preparedStatement.setInt(1,id);
            try(ResultSet resultSet= preparedStatement.executeQuery()){
                while(resultSet.next())
                {
                    String firstName=resultSet.getString("first_name");
                    String lastName=resultSet.getString("last_name");
                    String phone=resultSet.getString("phone");
                    String address=resultSet.getString("address");
                    String cnp=resultSet.getString("cnp");
                    user= new User(firstName,lastName,phone,address,cnp);
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
