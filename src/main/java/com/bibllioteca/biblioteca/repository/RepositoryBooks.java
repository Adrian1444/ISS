package com.bibllioteca.biblioteca.repository;

import com.bibllioteca.biblioteca.domain.Book;
import com.bibllioteca.biblioteca.domain.BookStatus;
import com.bibllioteca.biblioteca.domain.BookUser;
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

public class RepositoryBooks {
    private JdbcUtils dbUtils;

    public RepositoryBooks(Properties properties) {
        this.dbUtils=new JdbcUtils(properties);
    }


    public List<Book> findAllAvailableBooks() {
        List<Book> list=new ArrayList<>();
        try (Session session = ORMUtils.getSessionFactory().openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                Query<Book> query = session.createQuery("from Book e where e.status = :status ",Book.class);
                query.setParameter("status","AVAILABLE");
                list = query.list();


                transaction.commit();
            } catch (Exception ex) {
                System.err.println("Eroare la cautare " + ex);

                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }

        return list;
    }

    public List<Book> findAllBooks() {
        List<Book> list=new ArrayList<>();
        try (Session session = ORMUtils.getSessionFactory().openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                Query<Book> query = session.createQuery("from Book",Book.class);
                list = query.list();

                transaction.commit();
            } catch (Exception ex) {
                System.err.println("Eroare la cautare " + ex);

                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }

        return list;
    }

    public void delete(int id){

        try (Session session = ORMUtils.getSessionFactory().openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                session.createQuery("delete Book e where e.idUnique = :id").setParameter("id",id)
                .executeUpdate();
                transaction.commit();
            } catch (Exception ex) {
                System.err.println("Eroare la stergere " + ex);

                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    public void add(Book book) {
        try (Session session = ORMUtils.getSessionFactory().openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                session.save(book);

                transaction.commit();
            } catch (Exception ex) {
                System.err.println("Eroare la inserare " + ex);

                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    public void edit(Integer idUnique, int newIdBook, String newTitle, String newAuthor) {
        try (Session session = ORMUtils.getSessionFactory().openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                Book book=session.load(Book.class,idUnique);
                book.setIdBook(newIdBook);
                book.setAuthor(newAuthor);
                book.setTitle(newTitle);

                session.update(book);

                transaction.commit();
            } catch (Exception ex) {
                System.err.println("Eroare la actualizare " + ex);

                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    public void editStatus(Integer idUnique, String status) {
        try (Session session = ORMUtils.getSessionFactory().openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                Book book=session.load(Book.class,idUnique);
                book.setStatus(status);

                session.update(book);

                transaction.commit();
            } catch (Exception ex) {
                System.err.println("Eroare la actualizare " + ex);

                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    public List<BookUser> findBooksRentedByUser(int id) {
        List<BookUser> list=new ArrayList<>();
        try (Session session = ORMUtils.getSessionFactory().openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                Query<BookUser> query = session.createQuery("from BookUser e where e.idUser = :id ", BookUser.class);
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

        return list;
    }

    public void rentBook(BookUser bookUser) {
        try (Session session = ORMUtils.getSessionFactory().openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                session.save(bookUser);

                transaction.commit();
            } catch (Exception ex) {
                System.err.println("Eroare la inserare " + ex);

                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    public void returnBook(int idBook){

        try (Session session = ORMUtils.getSessionFactory().openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                session.createQuery("delete BookUser e where e.idBook=:idBook").setParameter("idBook",idBook)
                        .executeUpdate();
                transaction.commit();
            } catch (Exception ex) {
                System.err.println("Eroare la stergere " + ex);

                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }
}
