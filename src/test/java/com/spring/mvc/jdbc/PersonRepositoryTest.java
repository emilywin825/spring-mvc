package com.spring.mvc.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    PersonRepository repository;

    @Test
    @DisplayName("사람의 이름과 나이 정보를 DB person table에 잘 삽입해야 한다.")
    void saveTest(){
        //given
        Person p = new Person();
        p.setPersonName("맹구");
        p.setPersonAge(11);
        //when
        repository.save(p);
        //then
    }
    @Test
    void UpdateTest(){
        //given
        Person p = new Person();
        p.setPersonName("BOBBY");
        p.setPersonAge(20);
        //when
        repository.update(p);
        //then
    }

    @Test
    void DelteTest(){
        //given
        Person p = new Person();
        //when
        repository.delete(3);
        //then
    }

    @Test
    void findAllTest(){
        //when
        List<Person> people = repository.findAll();
        for (Person p : people) {
            System.out.println(p);
        }
    }

}