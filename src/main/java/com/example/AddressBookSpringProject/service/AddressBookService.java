package com.example.AddressBookSpringProject.service;

import com.example.AddressBookSpringProject.model.AddressBook;

import com.example.AddressBookSpringProject.model.AddressBook;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {

    private final List<AddressBook> addressBooks = new ArrayList<>();
    // To generate unique IDs
    private Long idCounter = 1L;

    // Fetch all address books
    public List<AddressBook> getAllAddressBooks() {
        return addressBooks;
    }

    // Fetch address book by ID
    public Optional<AddressBook> getAddressBookById(Long id) {
        return addressBooks.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    // Add new address book
    public AddressBook addAddressBook(AddressBook newBook) {
        newBook.setId(idCounter++);
        addressBooks.add(newBook);
        return newBook;
    }

    // Update existing address book
    public Optional<AddressBook> updateAddressBook(Long id, AddressBook updatedBook) {
        Optional<AddressBook> existingBook = getAddressBookById(id);
        existingBook.ifPresent(book -> {
            book.setName(updatedBook.getName());
            book.setAddress(updatedBook.getAddress());
        });
        return existingBook;
    }

    // Delete address book by ID
    public boolean deleteAddressBook(Long id) {
        return addressBooks.removeIf(book -> book.getId().equals(id));
    }
}

