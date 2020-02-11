package com.example.crud_demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Nome é Obrigatório")
    private String name;

    @NotBlank(message = "Email é Obrigatório")
    private String email;

    @NotNull(message = "Idade é Obrigatório")
    private int age;

    public User(){}

    public User(String name, String email, int age){
        this.age = age;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString(){
        return "Nome: " + this.name + "\tIdade: " + this.age;
    }
}
