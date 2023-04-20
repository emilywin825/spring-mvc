package com.spring.mvc.chap04.dto;

import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
//@Setter @Getter @NoArgsConstructor : 필수 -> 스프링이 이 객체 만들어서 넣을 때 Setter, 기본 생성자 사용해서 만들어 넣어줌
public class ScoreRequestDTO {
    private String name; //학생 이름
    private int kor, eng, math;// 국, 영, 수, 점수
}
