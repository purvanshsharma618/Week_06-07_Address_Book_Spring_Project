package com.example.AddressBookSpringProject.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AddressBookDTO {

    private String name;
    private String address;

    public AddressBookDTO() {}

    public AddressBookDTO(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
