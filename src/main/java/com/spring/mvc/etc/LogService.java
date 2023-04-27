package com.spring.mvc.etc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j // 로그 라이브러리
public class LogService {
    
    /*
    * - 로그 : 발생 시간, 로그 레벨, 파일 저장등이 필요
    * - 로그 라이브러리 : logback, log4j, slf4j -> 이거 사용하면 위에 작성한 로그를 다 해줌
    *
    * - 로그 레벨(번호 커질수록 심각)
    * =========== 개발 서버는 보통 1~3번 사용 =========================
    * =========== 운영 서버는 보통 3~6번 사용 =========================
    * 1. trace : 애플리케이션의 실행흐름 세부정보,(무슨 함수 콜됐다 이런거) 디버깅 목적
    * 2. debug : 변수값, 파라미터값 같은 내부 정보 출력, 디버깅 목적
    * 3. info : 운영환경에서 일반적인 작동 정보들, 시스템 상태, 진행중인 작업 정보 (url 요청이 발생했따 이런거)
    * 4. warn : 잠재적인 문제상황을 경고. 구성값이 예상 범위를 벗어났거나 시스템 리소스 부족
    * 5. error : 예외 발생, 기능이 실패등의 심각한 문제상황
    * 6. fatal : 치명적인 오류, 시스템이 지속될 수 없는 상황, 즉각 조치가 필요한 경우
    * */
    public void showLog(){
        System.out.println("hello log!!");
        log.trace("hello trace!"); //이건 trace레벨인것 같아 하면 이렇게 작성
        log.debug("hello debug!");
        log.info("hello info");
        log.warn("hello warn");
        log.error("hello error");
    }
}
