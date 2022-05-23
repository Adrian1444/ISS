package com.bibllioteca.biblioteca.domain;

import java.util.Objects;

public class BookUser {
    int idBook;
    int idUser;
    int idRent;

    public BookUser() {
    }

    public BookUser(int idBook, int idUser) {
        this.idBook = idBook;
        this.idUser = idUser;
    }

    public BookUser(int idBook, int idUser, int idRent) {
        this.idBook = idBook;
        this.idUser = idUser;
        this.idRent = idRent;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdRent() {
        return idRent;
    }

    public void setIdRent(int idRent) {
        this.idRent = idRent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookUser bookUser = (BookUser) o;
        return idBook == bookUser.idBook && idUser == bookUser.idUser && idRent == bookUser.idRent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBook, idUser, idRent);
    }
}
