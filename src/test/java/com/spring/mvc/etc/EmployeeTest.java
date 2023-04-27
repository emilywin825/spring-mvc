package com.spring.mvc.etc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class EmployeeTest {
    @Test
    public void Employee(){
        Employee e=Employee.builder()
                .position("대리")
                .empName("홍길동")
                .empId(999)
                .hireDate(LocalDate.of(2019,3,15))
                .build();

        System.out.println("e = "+e);

        Actor.builder()
                .actorName("장돈건")
                .hasPhone(false)
                .actorAge(40)
                .build();
    }
}
