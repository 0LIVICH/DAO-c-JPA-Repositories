package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    
    private final PersonService personService;
    
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    
    // CRUD операции
    
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.findAll();
        return ResponseEntity.ok(persons);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personService.findById(id);
        return person.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person savedPerson = personService.save(person);
        return ResponseEntity.ok(savedPerson);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        Optional<Person> existingPerson = personService.findById(id);
        if (existingPerson.isPresent()) {
            person.setId(id);
            Person updatedPerson = personService.save(person);
            return ResponseEntity.ok(updatedPerson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        Optional<Person> person = personService.findById(id);
        if (person.isPresent()) {
            personService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Специальные методы
    
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Person>> getPersonsByCity(@PathVariable String city) {
        List<Person> persons = personService.findByCity(city);
        return ResponseEntity.ok(persons);
    }
    
    @GetMapping("/age/{age}")
    public ResponseEntity<List<Person>> getPersonsByAgeLessThan(@PathVariable Integer age) {
        List<Person> persons = personService.findByAgeLessThanOrderByAgeAsc(age);
        return ResponseEntity.ok(persons);
    }
    
    @GetMapping("/search")
    public ResponseEntity<Person> getPersonByNameAndSurname(
            @RequestParam String name,
            @RequestParam String surname) {
        Optional<Person> person = personService.findByNameAndSurname(name, surname);
        return person.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
} 