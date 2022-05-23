package com.bibllioteca.biblioteca.domain;

import java.util.Objects;

public class Book {
    Integer idUnique;
    Integer idBook;
    String title;
    String author;
    String status;

    public Book() {
    }

    public Book(Integer idBook, String title, String author) {
        this.idBook = idBook;
        this.title = title;
        this.author = author;
        this.status="AVAILABLE";
    }

    public Integer getIdUnique() {
        return idUnique;
    }

    public void setIdUnique(Integer idUnique) {
        this.idUnique = idUnique;
    }

    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(idUnique, book.idUnique) && Objects.equals(idBook, book.idBook) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && status == book.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUnique, idBook, title, author, status);
    }

    @Override
    public String toString() {
        return title + " by " + author + " " + status;
    }
}
