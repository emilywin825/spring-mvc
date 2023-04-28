package com.spring.mvc.chap04.dto;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor// final 필드 초기화
@Getter @ToString @EqualsAndHashCode
public class ScoreListResponseDTO {
    //final 필드만 있으므로 setter x
    private final int stuNum;
    private final String maskingName; //첫글자 빼고 *처리
    private final double average;
    private final Grade grade;

    public ScoreListResponseDTO(Score s) {
        this.stuNum=s.getStuNum();
        this.maskingName=makeMaskingName(s.getStuName());
        this.average=s.getAverage();
        this.grade=s.getGrade();
    }

    //첫글자 빼고 다 *처리 하기
    private String makeMaskingName(String originalName){
        String maskingName=String.valueOf(originalName.charAt(0));
        for (int i = 0; i < originalName.length(); i++) {
            maskingName+="*";
        }
        return maskingName;
    }
}







