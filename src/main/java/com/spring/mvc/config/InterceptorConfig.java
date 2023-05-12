package com.spring.mvc.config;

//다양한 인터셉터에 관련 설정을 등록하는 클래스

import com.spring.mvc.interceptor.BoardInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor // BoardInterceptor 주입받기 위해서
public class InterceptorConfig
        implements WebMvcConfigurer {

        private final BoardInterceptor boardInterceptor;

        //인터셉터 설정 등록
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //게시판 인터셉터 설정
        registry.addInterceptor(boardInterceptor)
        .addPathPatterns("/board/*") //어떤 경로에서 인터셉터를 실행할 것인가
        .excludePathPatterns("/board/list","/board/detail"); //인터셉트를 실행하지 않을 경로
//     -> /board로 시작하는 모든 경로에 인터셉트가 걸림
     }
}
