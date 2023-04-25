package com.spring.mvc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    // Datasource 설정 : 데이터베이스 연결 정보 (밑에 4개)
    // URL : dbms가 설치된 경로
    // USERNAME : DB 계정명
    // PASSWORD : DB password
    // DRIVER CLASS : DBMS마다 설치한 커넥터 드라이버
    
    //커넥션 풀 설정
    // : DB 접속시 사용하는 리소스를 관리하는 프로그램

    //히카리 cp 설정하면 Datasource 설정 + 커넥션 풀 설정을 합칠 수 있음
//    @Bean
//    public DataSource dataSource(){
//        //히카리 설정
//        HikariConfig config = new HikariConfig();
//        config.setUsername("root"); //유저 이름
//        config.setPassword("1234");//비번
//        config.setJdbcUrl("jdbc:mariadb://localhost:3307/spring"); //우리는 지금 mariadb 쓰니까. 다른거 쓰면 gpt한테 물어봐!!
//        config.setDriverClassName("org.mariadb.jdbc.Driver");
//
//        return new HikariDataSource(config);


    }










