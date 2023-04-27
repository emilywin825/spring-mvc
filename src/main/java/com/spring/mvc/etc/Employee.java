package com.spring.mvc.etc;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.annotation.HandlesTypes;
import java.time.LocalDate;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
public class Employee {
    private int empId; // 사번
    private String empName; //사원명
    private String position; //직급
    private LocalDate hireDate; //입사일자

    // 빌터 패턴 구현

    //1. 내부 클래스를 외부와 같은 스펙으로 만듦
    public static class EmployeeBuilder{
        private int empId; // 사번
        private String empName; //사원명
        private String position; //직급
        private LocalDate hireDate; //입사일자

    //2. 내부 클래스의 생성자를 private으로 제한해서 외부에서 생성 불가능하게 함..
    //   Employee 클래스 밖에서 생성 불가능하게 함
    private EmployeeBuilder() {}

        //3. 각 필드별로 빌더 메서드들을 구현
        public EmployeeBuilder empId(int EmpId){
            this.empId=empId;
            return this;
        }
        public EmployeeBuilder empName(String empName){
            this.empName=empName;
            return this;
        }
        public EmployeeBuilder position(String position){
            this.position=position;
            return this;
        }
        public EmployeeBuilder hireDate(LocalDate hireDate){
            this.hireDate=hireDate;
            return this;
        }

        //4. 빌더 완료 메서드 선언
        public Employee build(){
            return new Employee(empId, empName, position, hireDate);
        }
    }

    //5. 빌더 호출 메서드
    public static EmployeeBuilder builder(){
        return new EmployeeBuilder();
    }

}
