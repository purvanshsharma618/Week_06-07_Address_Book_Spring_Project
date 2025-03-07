package com.example.AddressBookSpringProject.controller;

import com.example.AddressBookSpringProject.dto.AddressBookDTO;
import com.example.AddressBookSpringProject.model.AddressBook;
import com.example.AddressBookSpringProject.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    private final AddressBookService addressBookService;

    @Autowired
    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    // GET request to fetch all address books
    @GetMapping
    public ResponseEntity<List<AddressBook>> getAddressBooks() {
        return ResponseEntity.ok(addressBookService.getAllAddressBooks());
    }

    // GET request to fetch an address book by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable Long id) {
        return addressBookService.getAddressBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST request to create a new address book entry
    @PostMapping("/create")
    public ResponseEntity<AddressBook> addAddressBook(@RequestBody AddressBook newBook) {
        return ResponseEntity.ok(addressBookService.addAddressBook(newBook));
    }

    // PUT request to update an existing address book entry
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAddressBook(@PathVariable Long id, @RequestBody AddressBook updatedBook) {
        return addressBookService.updateAddressBook(id, updatedBook)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE request to remove an address book by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> addressBookDeleteById(@PathVariable Long id) {
        boolean isDeleted = addressBookService.deleteAddressBook(id);
        return isDeleted
                ? ResponseEntity.ok("Address book with ID: " + id + " is deleted")
                : ResponseEntity.notFound().build();
    }
}