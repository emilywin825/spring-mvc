package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import static com.spring.mvc.chap04.entity.Grade.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

@Repository //스프링 빈 등록 : 객체 생성의 제어권을 스프링에게 위임 = 제어의 역전
//@Component보다 조금 더 명시적으로 저장소임을 알려줌
public class ScoreRepositoryImpl implements ScoreRepository {

    // key: 학번, value: 성적정보
    private static final Map<Integer, Score> scoreMap;

    // 학번에 사용할 일련번호
    private static int sequence;

    static {
        scoreMap = new HashMap<>();
        Score stu1 = new Score(new ScoreRequestDTO("뽀로로", 100, 34, 91));
        Score stu2 = new Score(new ScoreRequestDTO("춘식이", 77, 99, 87));
        Score stu3 = new Score(new ScoreRequestDTO("대길이", 98, 66, 85));

        stu1.setStuNum(++sequence);
        stu2.setStuNum(++sequence);
        stu3.setStuNum(++sequence);

        scoreMap.put(stu1.getStuNum(), stu1);
        scoreMap.put(stu2.getStuNum(), stu2);
        scoreMap.put(stu3.getStuNum(), stu3);
    }

    @Override
    public List<Score> findAll() {
        return scoreMap.values()
                .stream()
                .sorted(comparing(Score::getStuNum))
                .collect(toList())
                ;
    }

    @Override
    public List<Score> findAll(String sort) {
        Comparator<Score> comparator = comparing(Score::getStuNum);
        switch (sort) {
            case "num":
                comparator = comparing(Score::getStuNum);
                break;
            case "name":
                comparator = comparing(Score::getName);
                break;
            case "avg":
                comparator = comparing(Score::getAverage).reversed();
                break;
        }
        return scoreMap.values()
                .stream()
                .sorted(comparator)
                .collect(toList())
                ;
    }

    @Override
    public boolean save(Score score) {
        if (scoreMap.containsKey(score.getStuNum())) {
            return false;
        }
        score.setStuNum(++sequence);
        scoreMap.put(score.getStuNum(), score);
//        System.out.println(findAll());
        return true;
    }

    @Override
    public boolean deleteByStuNum(int stuNum) {
        if (!scoreMap.containsKey(stuNum)) return false;
        scoreMap.remove(stuNum);
        return true;
    }

    @Override
    public Score findByStuNum(int stuNum) {
        return scoreMap.get(stuNum);
    }
}