package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.jdbc.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScoreMapperTest {
    int stuNum;
    @Autowired
    ScoreMapper mapper;


    @Test
    void saveTest(){
        //given
        ScoreRequestDTO dto = ScoreRequestDTO.builder()
                .name("김동혁")
                .kor(50)
                .eng(70)
                .math(99)
                .build();
        Score s = new Score(dto);

        //when
        boolean flag=mapper.save(s);

        //then
        assertTrue(flag);
    }

    @Test
    void deleteTest(){
        //given
        int stuNum=6;
        //when
        boolean flag=mapper.deleteByStuNum(stuNum);

        //then
        assertTrue(flag);
    }

    @Test
    void findByStuNum(){
        //given
        int stuNum=4;
        //when
//        Score sc = mapper.findByStuNum(stuNum);
        System.out.println("sc = "+ mapper.findByStuNum(stuNum));
        //then
//        assertTrue();
    }


}
