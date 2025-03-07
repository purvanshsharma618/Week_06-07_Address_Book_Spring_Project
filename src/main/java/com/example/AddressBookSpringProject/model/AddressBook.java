package com.example.AddressBookSpringProject.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class AddressBook {
    private Long id;
    @NotEmpty(message="name can not be empty")
    @Pattern(regexp = "^[A-Z][a-z]{2,}" ,  message = "Name must start with a capital letter and have at least 3 characters")
    private String name;
    private String address;

    public AddressBook() {}

    public AddressBook(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
