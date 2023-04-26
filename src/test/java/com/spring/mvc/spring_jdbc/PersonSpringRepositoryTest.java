package com.spring.mvc.spring_jdbc;

import com.spring.mvc.jdbc.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonSpringRepositoryTest {
    
    @Autowired
    PersonSpringRepository repository;

    @Test
    void savePersonTest(){
        //given
        Person p = new Person();
        p.setPersonName("김스프링");
        p.setPersonAge(2);

        repository.savePerson(p);
    }

    @Test
    void removePersonTest(){
        //given
        long id=9L;
        repository.removePerson(id);
    }

    @Test
    void modifyPersonTest() {
        // given
        Person p = new Person();
        p.setId(6L);
        p.setPersonName("만지호");
        p.setPersonAge(10000);
        // when
        boolean flag = repository.modify(p);
        // then
        assertTrue(flag);
    }

    @Test
    void findAllTest() {
        List<Person> people = repository.findAll();
        for (Person p : people) {
            System.out.println("p = " + p);
        }
    }

    @Test
    void findOneTest() {
        Person p = repository.findOne(5L);
        System.out.println("p = " + p);

        assertEquals("춘식이", p.getPersonName());
    }

}