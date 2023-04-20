package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;

import java.util.List;

// 역할 명세(추상화): 성적 정보를 잘 저장하고, 조회하고, 삭제하고, 수정해야 한다.
//그래서 어디에서 저장,조회,삭제하니? -> 인터페이스는 몰라 추상적이니까
//오라클,세이브파일,메모리등 어디에 저장하던 그냥 save라고 해 -> 인터페이스 추상화
public interface ScoreRepository {
    //성적 정보 전체 목록 조회
    List<Score> findAll(); //일반 목록 조회
    default List<Score> findAll(String sort){ 
        //실무에서 처음 설계와 다르게 이렇게 메소드가 추가되는 경우 만약 이 인터페이스를 구현하는 메소드가 300개라면 300개의 클래스가 모두 오류남
        //그럴때 이렇게 메소드 추가하는 경우 default 붙이고 정의해두면 구현이 강제되지 않음 -> 시간날 때 하면 되니까 이렇게 해둠
        return null;
    }; //정렬조회

    //성적 정보 등록
    boolean save(Score score);

    //성적 정보 삭제
    boolean deleteByStuNum(int stuNum);

    //성적 정보 개별 조회
    Score findByStuNum(int stuNum);
}
