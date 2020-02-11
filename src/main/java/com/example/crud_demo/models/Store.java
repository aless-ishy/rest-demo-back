package com.example.crud_demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Store {
    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    private String name;


    @NotBlank
    private String address;

    @NotNull
    private long number;

    @NotBlank
    private String phone;


    @NotNull
    private long rating;

    public Store(){}

    public Store(String name, String address, long number, String phone, long rating) {
        this.name = name;
        this.address = address;
        this.number = number;
        this.phone = phone;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", number=" + number +
                ", phone='" + phone + '\'' +
                ", rating=" + rating +
                '}';
    }
}
