package com.bridgelabz.service;

import com.bridgelabz.model.AddressBook;
import com.bridgelabz.model.Person;
import com.bridgelabz.repository.AddressBookRepository;
import com.bridgelabz.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PersonService implements IPersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressBookRepository addressBookRepository;

    @Override
    public Mono<Person> savePerson(Person person, String addressBookId) {
        return addressBookRepository.findById(addressBookId)
                .flatMap(savedAddressBook -> {
                    person.setAddressBook(savedAddressBook);
                    return personRepository.save(person);
                }).switchIfEmpty(Mono.defer(() -> {
                    return Mono.empty();
                }));
    }
}
