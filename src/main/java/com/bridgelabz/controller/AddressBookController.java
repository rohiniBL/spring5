package com.bridgelabz.controller;

import com.bridgelabz.model.AddressBook;
import com.bridgelabz.service.IAddressBookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AddressBookController {

    @Autowired
    private IAddressBookService addressBookService;

    @PostMapping("addressbook")
    @Operation(description = "This API is used to create Operation",summary = "Create Book")
    public Mono<AddressBook> saveAddressBook(@RequestBody AddressBook addressBook){
        return addressBookService.saveAddressBook(addressBook);
    }

    @GetMapping("addressbook")
    public Flux<AddressBook> getAddressBook() {
        return addressBookService.getAddressBook();
    }

    @DeleteMapping("addressbook/{id}")
    public Mono<Void> deleteBook(@PathVariable(name = "id") String id){
    return addressBookService.deleteBook(id);
    }
}