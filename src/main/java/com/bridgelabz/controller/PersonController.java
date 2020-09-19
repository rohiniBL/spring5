package com.bridgelabz.controller;

import com.bridgelabz.dto.Response;
import com.bridgelabz.model.Person;
import com.bridgelabz.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Observable;

@RestController
public class PersonController {

    @Autowired
    private IPersonService personService;

    @PostMapping(value = "person", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Response>> createPerson(@RequestBody Person person,
                                                     @RequestParam("addressBookId") String addressBookId) {
        return personService.savePerson(person, addressBookId)
                .map(savedPerson -> {
                    Response response = new Response("Person added successfully", savedPerson);
                    return ResponseEntity.ok(response);
                }).switchIfEmpty(Mono.defer(() -> {
                    Response response = new Response("AddressBook does not exist with this id", null);
                    return Mono.just(new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND));
                }));
    }
}
