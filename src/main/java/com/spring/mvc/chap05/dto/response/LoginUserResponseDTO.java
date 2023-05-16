package com.spring.mvc.chap05.dto.response;

import lombok.*;

@Setter @Getter
@ToString @EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
//로그인이 성공하면 세션에 로그인한 회원들에 대한 dto
public class LoginUserResponseDTO {
    private String account;
    private String nickName;
    private String email;
    private String auth;

}
