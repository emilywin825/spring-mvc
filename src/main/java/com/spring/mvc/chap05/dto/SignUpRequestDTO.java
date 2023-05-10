package com.spring.mvc.chap05.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter @Getter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class SignUpRequestDTO {
    @NotBlank
    private String account;
    @NotBlank
    private String password;
    @NotBlank @Size(min=2,max=6)
    private String name;
    @NotBlank @Email
    private String email;
}
