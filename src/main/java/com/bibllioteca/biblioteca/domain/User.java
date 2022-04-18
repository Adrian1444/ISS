package com.bibllioteca.biblioteca.domain;

import java.util.Objects;

public class User {
    Integer id;
    String firstName;
    String lastName;
    String phone;
    String address;
    String cnp;

    public User(String firstName, String lastName, String phone, String address, String cnp) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.cnp = cnp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && phone.equals(user.phone) && address.equals(user.address) && cnp.equals(user.cnp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phone, address, cnp);
    }
}
