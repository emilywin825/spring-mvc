package com.spring.mvc.chap04.dto;

import com.spring.mvc.chap04.controller.ScoreController;
import com.spring.mvc.chap04.repository.ScoreRepositoryImpl;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Setter @Getter @NoArgsConstructor : 필수 -> 스프링이 이 객체 만들어서 넣을 때 Setter, 기본 생성자 사용해서 만들어 넣어줌
public class ScoreRequestDTO { //클라이언트의 요청의 일부 객체를 담는 클래스
    private String name; //학생 이름
    private int kor, eng, math;// 국, 영, 수, 점수
}
