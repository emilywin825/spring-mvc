package com.spring.mvc.spring_jdbc;

import com.spring.mvc.jdbc.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonSpringRepository {
    //우리가 application.properties 파일에 작성해 둔 거 얘가 찾아서 알아서 함
    // 스프링 JDBC 활용 - 데이터베이스 접속 설정 정보를 설정파일을 통해 불러와서 사요합니다.
    private final JdbcTemplate jdbcTemplate;

    //저장 기능
    public void savePerson(Person p){
        String sql="INSERT INTO person(person_name, person_age) VALUES(?,?)";
        jdbcTemplate.update(sql,p.getPersonName(),p.getPersonAge());
    }

    //삭제 기능
    public void removePerson(long id){
        String sql="DELETE FROM person WHERE id=?";
        jdbcTemplate.update(sql,id);
    }

    public boolean modify(Person p){
        String sql="UPDATE person SET person_name=? person_age=? WHERE id=?";
        int result = jdbcTemplate.update(sql, p.getPersonName(), p.getPersonAge(), p.getId());
        return result==1;
    }

    //전체 조회 기능
    public List<Person> findAll(){
        String sql="SELECT * FROM person";
        //RowMapper : 인터페이스 -> 추상클래스 사용해 구현
        List<Person> personList=jdbcTemplate.query(sql, (RowMapper) (rs, rowNum) -> {
            Person p=new Person(rs);
/*                p.setId(rs.getLong("id"));
            p.setPersonName(rs.getString("person_name"));
            p.setPersonAge(rs.getInt("person_age"));*/
            return p;
        });
        return personList;
    }

    //개별 조회
    public Person findOne(long id) {
        String sql="SELECT * FROM person WHERE id=?";
        return jdbcTemplate.queryForObject(sql,(rs, n) -> new Person(rs), id);
    }

}
