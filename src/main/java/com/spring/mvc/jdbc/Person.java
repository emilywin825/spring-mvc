package com.spring.mvc.jdbc;

//  엔터티 : 이 클래스는 db 테이블과의 연동을 위한 객체
//  db테이블의 컬럼과 1:1로 매칭되는 필드를 적어주세요

import lombok.*;

/*<person 테이블>
create table person (
    id int(10) auto_increment,
    person_name VARCHAR(50) not null,
    person_age int(3),
    constraint pk_person_id
    primary key(id)
    );*/
@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Person { //여기에 담아서 insert/ db가 select한거 여기 담아서 전달.
    private long id;
    private String personName;
    private int personAge;
}
