package com.spring.mvc.chap05.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter @Getter
@NoArgsConstructor
@ToString @EqualsAndHashCode
@AllArgsConstructor
@Builder
public class SignUpRequestDTO {
    @NotBlank
    private String account;
    @NotBlank
    private String password;
    @NotBlank @Size(min=2,max=6)
    private String name;
    @NotBlank @Email
    private String email;

//    이거 이름 sign-up.jsp의
//    <input type="file" id="profile-img"
//           accept="image/*" style="display: none;"
//           name="profileImage" >의 name 속성과 같게 설정
    private MultipartFile profileImage;
}
