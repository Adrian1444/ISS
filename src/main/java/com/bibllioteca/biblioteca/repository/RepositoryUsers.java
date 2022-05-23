package com.bibllioteca.biblioteca.repository;

import com.bibllioteca.biblioteca.domain.Librarian;
import com.bibllioteca.biblioteca.domain.User;
import com.bibllioteca.biblioteca.utils.ORMUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepositoryUsers {
    private JdbcUtils dbUtils;

    public RepositoryUsers(Properties properties) {
        this.dbUtils = new JdbcUtils(properties);
    }

    public User findOne(Integer id){
        List<User> list=new ArrayList<>();
        try (Session session = ORMUtils.getSessionFactory().openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                Query<User> query = session.createQuery("from User e where e.id = :id ",User.class);
                query.setParameter("id",id);
                list = query.list();


                transaction.commit();
            } catch (Exception ex) {
                System.err.println("Eroare la cautare " + ex);

                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }

        return list.get(0);
    }

}
