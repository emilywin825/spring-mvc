package com.spring.mvc.self.dto;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormatVisitor;
import com.spring.mvc.self.entity.Self;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Getter
@RequiredArgsConstructor
public class ResponseDTO {
    private final int boardNo;
    private final String shortTitle; // 5자 이상 줄임
    private final String shortContent; // 30자 이상 줄임
    private final String date; // 날짜패턴 yyyy-MM-dd HH:mm
    private final int viewCount;

    public ResponseDTO(Self self){
        this.boardNo= self.getBoardNo();
        this.shortTitle= ShortTitle(self.getTitle());
        this.shortContent= ShortContent(self.getContent());
        this.date=TimeFormat(self.getRegDateTime());
        this.viewCount=self.getViewCount();
    }
    
    //시간 포멧에 맞게 수정
    private String TimeFormat(LocalDateTime regDateTim){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
        String dateString= regDateTim.format(pattern);
        return dateString;
    }

    //Title 5글자로 줄임
    private String ShortTitle(String title){
        int wishLength=5;
        return sliceString(title,wishLength);
    }

    //Content 30글자로 줄임
    private String ShortContent(String title){
        int wishLength=30;
        return sliceString(title,wishLength);
    }
    
    //Title,Content 길이에 맞게 줄임
    private String sliceString(String title,int wishLength){
        return (title.length()>wishLength)?title.substring(0,wishLength)+"..." :title;
    }


}
