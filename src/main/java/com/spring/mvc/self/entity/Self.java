package com.spring.mvc.self.entity;

import com.spring.mvc.self.dto.RequestDTO;
import com.spring.mvc.self.dto.ResponseDTO;
import com.spring.mvc.self.dto.SelfDetailDTO;
import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter
@EqualsAndHashCode @ToString
@AllArgsConstructor
@NoArgsConstructor
public class Self {
    private int boardNo; // 게시글 번호
    private String title; // 제목
    private String content; // 내용
    private int viewCount; // 조회수
    private LocalDateTime regDateTime; // 작성일자시간

    public Self(int boardNo,String title,String content){
        this.boardNo=boardNo;
        this.title=title;
        this.content=content;
        this.regDateTime=LocalDateTime.now();
    }

    public Self(RequestDTO dto){
        this.title=dto.getTitle();
        this.content=dto.getContent();
        this.regDateTime=LocalDateTime.now();
    }
}
