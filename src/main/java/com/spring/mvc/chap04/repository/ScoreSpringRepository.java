package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("spring")
public class ScoreSpringRepository implements ScoreRepository{
    @Override
    public List<Score> findAll() {
        return null;
    }

    @Override
    public List<Score> findAll(String sort) { //service가 여기에 연결되어 있음. 여기다 작성
        return ScoreRepository.super.findAll(sort);
    }

    @Override
    public boolean save(Score score) {
        return false;
    }

    @Override
    public boolean deleteByStuNum(int stuNum) {
        return false;
    }

    @Override
    public Score findByStuNum(int stuNum) {
        return null;
    }
}
