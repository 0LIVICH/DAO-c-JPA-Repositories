package com.example.demo;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private final PersonRepository personRepository;
    
    @Autowired
    public DataInitializer(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Очищаем таблицу
        personRepository.deleteAll();
        
        // Добавляем тестовые данные
        personRepository.save(new Person("Иван", "Иванов", 25, "Москва"));
        personRepository.save(new Person("Петр", "Петров", 30, "Санкт-Петербург"));
        personRepository.save(new Person("Анна", "Сидорова", 22, "Москва"));
        personRepository.save(new Person("Мария", "Козлова", 28, "Казань"));
        personRepository.save(new Person("Сергей", "Смирнов", 35, "Москва"));
        personRepository.save(new Person("Елена", "Волкова", 19, "Санкт-Петербург"));
        personRepository.save(new Person("Алексей", "Лебедев", 27, "Новосибирск"));
        personRepository.save(new Person("Ольга", "Соколова", 31, "Москва"));
        
        System.out.println("Тестовые данные загружены в базу данных!");
    }
} 