package com.bridgelabz.service;

import com.bridgelabz.model.AddressBook;
import com.bridgelabz.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AddressBookService implements IAddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Override
    public Mono<AddressBook> saveAddressBook(AddressBook addressBook) {
        return addressBookRepository.save(addressBook);
    }

    @Override
    public Flux<AddressBook> getAddressBook() {
        return addressBookRepository.findAll();
    }
}
