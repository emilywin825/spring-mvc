package com.spring.mvc.chap05.entity;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

/* <db구조-maria db>
create table tbl_reply (
   reply_no INT(10) auto_increment,
   reply_text VARCHAR(1000) not null,
   reply_writer VARCHAR(100) not null,
   reply_date DATETIME default current_timestamp,
   board_no INT(10),
   constraint pk_reply primary key (reply_no),
   constraint fk_reply
   foreign key (board_no)
   references tbl_board (board_no)
   on delete cascade
);
* */

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
//mybatis-config.xml에 자동 카멜케이스 to 스네이크 케이스 변환 설정해놨으므로 테이블 칼럼명이랑 똑같이 카멜케이스로 만들어야 함
//아니면 따로 xml 파일에서 <resultMap> 만드어서 이름 지정해줘야 하니까
    private long replyNo;
    private String replyText;
    private String replyWriter;
    private LocalDateTime replyDate;
    private long boardNo;

}
