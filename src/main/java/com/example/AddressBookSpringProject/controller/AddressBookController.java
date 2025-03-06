package com.example.AddressBookSpringProject.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/addressbook") // Base URL
public class AddressBookController {

    private List<String> addressBooks = new ArrayList<>();

    // GET request to fetch all address books
    @GetMapping
    public List<String> getAddressBooks() {
        return addressBooks;
    }

    // Get request to fetch the address book
    public String getAddressById(@RequestParam Long id){
        return "Address Book is found with id "+ id;
    }


    // POST request to create a new address book entry
    @PostMapping("/create")
    public String addAddressBook(@RequestParam String name, @RequestParam String address) {
        addressBooks.add(name + " - " + address);
        return "Address Book name: " + name + " is created with address: " + address;
    }

    // DELETE request to remove an address book by ID
    @DeleteMapping("/delete/{id}")
    public String addressBookDeleteById(@PathVariable int id) {
        if (id >= 0 && id < addressBooks.size()) {
            addressBooks.remove(id);
            return "Address book with ID: " + id + " is deleted";
        } else {
            return "Invalid ID: " + id;
        }
    }

    // PUT request to update an existing address book entry
    @PutMapping("/update/{id}")
    public String updateAddressBook(@PathVariable int id, @RequestParam String name, @RequestParam String address) {
        if (id >= 0 && id < addressBooks.size()) {
            addressBooks.set(id, name + " - " + address);
            return "Address Book with ID: " + id + " is updated.";
        } else {
            return "Invalid ID: " + id;
        }
    }
}
