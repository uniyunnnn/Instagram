package com.cos.photogramstart.web.dto.subscribe;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {
    
    private Integer id;
    
    private String username;

    private String profileImageUrl;

    private Integer subscribeState;

    private Integer equalUserState;
    
    public SubscribeDto(BigDecimal id, String username, String profileImageUrl, BigDecimal subscribeState, BigDecimal equalUserState) {
        this.id = id != null ? id.intValue() : null;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.subscribeState = subscribeState != null ? subscribeState.intValue() : null;
        this.equalUserState = equalUserState != null ? equalUserState.intValue() : null;
    }
}