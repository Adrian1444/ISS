package com.bibllioteca.biblioteca;

import com.bibllioteca.biblioteca.domain.Book;
import com.bibllioteca.biblioteca.domain.Librarian;
import com.bibllioteca.biblioteca.domain.User;
import com.bibllioteca.biblioteca.repository.RepositoryBooks;
import com.bibllioteca.biblioteca.repository.RepositoryLibrarians;
import com.bibllioteca.biblioteca.repository.RepositoryUsers;

import java.util.List;

public class Service {
    RepositoryUsers repositoryUsers;
    RepositoryLibrarians repositoryLibrarians;
    RepositoryBooks repositoryBooks;

    public Service(RepositoryUsers repositoryUsers,RepositoryLibrarians repositoryLibrarians,RepositoryBooks repositoryBooks) {
        this.repositoryUsers = repositoryUsers;
        this.repositoryLibrarians=repositoryLibrarians;
        this.repositoryBooks=repositoryBooks;
    }

    public Librarian checkLibrarianAccount(Integer id, String firstName, String lastName) throws ServiceException {
        Librarian librarian=repositoryLibrarians.findOne(id);
        if(librarian!=null && librarian.getFirstName().equals(firstName) && librarian.getLastName().equals(lastName))
            return librarian;
        else
            throw new ServiceException("Account does not exist");
    }

    public User checkUserAccount(Integer id, String firstName, String lastName) throws ServiceException {
        User user=repositoryUsers.findOne(id);
        if(user!=null && user.getFirstName().equals(firstName) && user.getLastName().equals(lastName))
            return user;
        else
            throw new ServiceException("Account does not exist");
    }

    public List<Book> findAllAvailableBooks(){
        return repositoryBooks.findAllAvailableBooks();
    }
}
