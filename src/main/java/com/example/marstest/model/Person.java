package com.example.marstest.model;


import javax.persistence.*;
import java.util.List;

@Entity
//@Table(name="person")
//if we want different table name insted of entity name
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    int id;

    String firstName;

    String lastName;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id",referencedColumnName = "id")
    List<Address> address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
}
