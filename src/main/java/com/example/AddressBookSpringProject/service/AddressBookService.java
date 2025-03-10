package com.example.AddressBookSpringProject.service;

import com.example.AddressBookSpringProject.exception.AddressBookException;
import com.example.AddressBookSpringProject.model.AddressBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AddressBookService {

    // List used to store data in Memory
    private final List<AddressBook> addressBooks = new ArrayList<>();
    // To generate unique IDs
    private Long idCounter = 1L;

    // Fetch all address books
    public List<AddressBook> getAllAddressBooks() {
        log.info("Fetching all address books. Total count: {}", addressBooks.size());
        return addressBooks;
    }

    // Fetch address book by ID
    public Optional<AddressBook> getAddressBookById(Long id) {
        log.info("Fetching address book with ID: {}", id);
        Optional<AddressBook> addressBook = addressBooks.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();

        if(addressBook.isEmpty()){
            throw new AddressBookException("Id not Exists In Database");
        }
        return  addressBook;
    }

    // Add new address book
    public AddressBook addAddressBook(AddressBook newBook) {
        newBook.setId(idCounter++);
        addressBooks.add(newBook);
        return newBook;
    }

    // Update existing address book
    public Optional<AddressBook> updateAddressBook(Long id, AddressBook updatedBook) {
        log.debug("Updating an Address Book with ID :{}", id);
        Optional<AddressBook> existingBook = getAddressBookById(id);
        existingBook.ifPresent(book -> {
            book.setName(updatedBook.getName());
            book.setAddress(updatedBook.getAddress());
        });

        if(existingBook.isEmpty()){
            throw  new AddressBookException("Id Dose Not Exists in Database");
        }
        log.info("{} id Details Successfully Updated" , id);
        return existingBook;
    }

    // Delete address book by ID
    public boolean deleteAddressBook(Long id) {
        log.debug("Deleting address book with ID: {}", id);
        return addressBooks.removeIf(book -> book.getId().equals(id));
    }
}
