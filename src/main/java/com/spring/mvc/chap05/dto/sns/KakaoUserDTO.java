package com.spring.mvc.chap05.dto.sns;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mariadb.jdbc.export.Prepare;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Setter @Getter
@ToString
public class KakaoUserDTO {
    private long id;
    @JsonProperty("connected_at")
//    private LocalDateTime connected_at 이렇게 쓰면 자바에서  @JsonProperty("connected_at") 사용해서 구분
    private LocalDateTime connectedAt;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Setter @Getter @ToString
    public static class KakaoAccount {
        private String email;
        private Profile profile;

//        profile이 한번 더 객체이니까 또 만들어줌
        @Setter @Getter @ToString
        public static class Profile{
            private String nickname;
            @JsonProperty("profile_image_url")
            private String profileImageUrl;
        }
    }
}
