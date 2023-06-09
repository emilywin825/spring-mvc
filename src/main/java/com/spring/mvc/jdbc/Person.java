package com.spring.mvc.jdbc;

//  엔터티 : 이 클래스는 db 테이블과의 연동을 위한 객체
//  db테이블의 컬럼과 1:1로 매칭되는 필드를 적어주세요

import lombok.*;
import java.sql.ResultSet;
import java.sql.SQLException;

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
@Builder // : 파라미터를 활용하여 빌더 패턴을 자동으로 생성
public class Person { //여기에 담아서 insert/ db가 select한거 여기 담아서 전달.
    private long id;
    private String personName;
    private int personAge;

    public Person(ResultSet rs) throws SQLException { //MyBatis쓰면 이거 필요 없음
        this.id=rs.getLong("id");
        this.personName=rs.getString("person_name");
        this.personAge= Integer.parseInt("person_age");
    }
}
