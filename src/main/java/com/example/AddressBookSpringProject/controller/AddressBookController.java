package com.example.AddressBookSpringProject.controller;

import com.example.AddressBookSpringProject.dto.AddressBookDTO;
import com.example.AddressBookSpringProject.model.AddressBook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/addressbook") // Base URL
public class AddressBookController {

    private List<AddressBook> addressBooks = new ArrayList<>();
    private Long idCounter = 1L;

    // GET request to fetch all address books
    @GetMapping
    public ResponseEntity<List<AddressBook>> getAddressBooks() {
        return ResponseEntity.ok(addressBooks);
    }

    // GET request to fetch an address book by ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressBook> getAddressById(@PathVariable Long id) {
        return addressBooks.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST request to create a new address book entry
    @PostMapping("/create")
    public ResponseEntity<AddressBook> addAddressBook(@RequestBody AddressBookDTO addressBookDTO) {
        AddressBook newAddressBook = new AddressBook(idCounter++, addressBookDTO.getName(), addressBookDTO.getAddress());
        addressBooks.add(newAddressBook);
        return ResponseEntity.ok(newAddressBook);
    }

    // DELETE request to remove an address book by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> addressBookDeleteById(@PathVariable Long id) {
        boolean removed = addressBooks.removeIf(book -> book.getId().equals(id));
        if (removed) {
            return ResponseEntity.ok("Address book with ID: " + id + " is deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT request to update an existing address book entry
    @PutMapping("/update/{id}")
    public ResponseEntity<AddressBook> updateAddressBook(@PathVariable Long id, @RequestBody AddressBookDTO addressBookDTO) {
        for (AddressBook book : addressBooks) {
            if (book.getId().equals(id)) {
                book.setName(addressBookDTO.getName());
                book.setAddress(addressBookDTO.getAddress());
                return ResponseEntity.ok(book);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
