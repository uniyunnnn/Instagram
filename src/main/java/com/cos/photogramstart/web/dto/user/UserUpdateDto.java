package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.Users;

import lombok.Data;

@Data
public class UserUpdateDto {
    // 필수로 받아야 하는 데이터
    private String name;
    private String password;

    // 받지 않아도 되는 데이터
    private String website;
    private String bio;
    private String phone;
    private String gender;

    // 받지않아도되는 데이터를 엔티티로 만들면 조금 위험함 코드수정이필요한 부분
    public Users toEntity() {
        return Users.builder()
                .name(name)
                .password(password)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}