package com.example.demo.repository;

import com.example.demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
    // Метод для поиска по городу
    @Query("SELECT p FROM Person p WHERE p.city = :city")
    List<Person> findByCity(@Param("city") String city);
    
    // Метод для поиска по возрасту (меньше переданного и отсортировано по возрастанию)
    @Query("SELECT p FROM Person p WHERE p.age < :age ORDER BY p.age ASC")
    List<Person> findByAgeLessThanOrderByAgeAsc(@Param("age") Integer age);
    
    // Метод для поиска по имени и фамилии (возвращает Optional)
    @Query("SELECT p FROM Person p WHERE p.name = :name AND p.surname = :surname")
    Optional<Person> findByNameAndSurname(@Param("name") String name, @Param("surname") String surname);
} 