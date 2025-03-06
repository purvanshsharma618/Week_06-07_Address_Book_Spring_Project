package com.example.AddressBookSpringProject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/addressbook") // Base URL
public class AddressBookController {

    private List<String> addressBooks = new ArrayList<>();

    // GET request to fetch all address books
    @GetMapping
    public ResponseEntity<List<String>> getAddressBooks() {
        return ResponseEntity.ok(addressBooks);
    }

    // GET request to fetch an address book by ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getAddressById(@PathVariable int id) {
        if (id >= 0 && id < addressBooks.size()) {
            return ResponseEntity.ok("Address Book found: " + addressBooks.get(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST request to create a new address book entry
    @PostMapping("/create")
    public ResponseEntity<String> addAddressBook(@RequestParam String name, @RequestParam String address) {
        addressBooks.add(name + " - " + address);
        return ResponseEntity.ok("Address Book name: " + name + " is created with address: " + address);
    }

    // DELETE request to remove an address book by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> addressBookDeleteById(@PathVariable int id) {
        if (id >= 0 && id < addressBooks.size()) {
            addressBooks.remove(id);
            return ResponseEntity.ok("Address book with ID: " + id + " is deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT request to update an existing address book entry
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAddressBook(@PathVariable int id, @RequestParam String name, @RequestParam String address) {
        if (id >= 0 && id < addressBooks.size()) {
            addressBooks.set(id, name + " - " + address);
            return ResponseEntity.ok("Address Book with ID: " + id + " is updated.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

