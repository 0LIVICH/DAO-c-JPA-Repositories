package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    
    private final PersonRepository personRepository;
    
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    
    // CRUD операции
    public List<Person> findAll() {
        return personRepository.findAll();
    }
    
    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }
    
    public Person save(Person person) {
        return personRepository.save(person);
    }
    
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
    
    // Специальные методы
    public List<Person> findByCity(String city) {
        return personRepository.findByCity(city);
    }
    
    public List<Person> findByAgeLessThanOrderByAgeAsc(Integer age) {
        return personRepository.findByAgeLessThanOrderByAgeAsc(age);
    }
    
    public Optional<Person> findByNameAndSurname(String name, String surname) {
        return personRepository.findByNameAndSurname(name, surname);
    }
} 