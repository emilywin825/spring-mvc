package com.spring.mvc.self.repository;

import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepositoryImpl;
import com.spring.mvc.self.entity.Self;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SelfRepositoryImplTest {
    SelfRepositoryImpl repository = new SelfRepositoryImpl();

    @Test
    void findAllTest() {

        // when: 테스트 실제 상황
        List<Self> list = repository.findAll();

        System.out.println(list);

    }
}