package com.bridgelabz.service;

import com.bridgelabz.model.AddressBook;
import com.bridgelabz.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AddressBookService implements IAddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;


    @Override
    public Mono<AddressBook> saveAddressBook(AddressBook addressBook) {
        System.out.println("REcord saved");
       Mono<AddressBook> book =addressBookRepository.save(addressBook);
        System.out.println(book);
        return book;
    }

    @Override
    @Cacheable("addressbook")
    public Flux<AddressBook> getAddressBook() {
        return addressBookRepository.findAll();
    }

    @Override
    public Mono<Void> deleteBook(String id) {
       return addressBookRepository.findById(id).flatMap(addressBook ->
               addressBookRepository.delete(addressBook)).switchIfEmpty(Mono.defer(()-> Mono.empty()));
    }
}
