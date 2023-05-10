package com.spring.mvc.chap05.dto;

import lombok.*;
import org.springframework.stereotype.Service;

//sign-up.jsp의 name 속성으로
@Setter @Getter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class LoginRequestDTO {
    private String account;
    private String password;
    private String autoLogin;
}
