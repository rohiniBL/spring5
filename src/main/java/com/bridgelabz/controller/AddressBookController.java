package com.bridgelabz.controller;

import com.bridgelabz.model.AddressBook;
import com.bridgelabz.service.IAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AddressBookController {

    @Autowired
    private IAddressBookService addressBookService;

    @PostMapping("addressbook")
    public Mono<AddressBook> saveAddressBook(@RequestBody AddressBook addressBook){
        return addressBookService.saveAddressBook(addressBook);
    }

    @GetMapping("addressbook")
    public Flux<AddressBook> getAddressBook() {
        return addressBookService.getAddressBook();
    }
}
