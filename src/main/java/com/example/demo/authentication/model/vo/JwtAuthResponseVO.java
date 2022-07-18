package com.example.demo.authentication.model.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthResponseVO {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthResponseVO(String accessToken) {
        this.accessToken = accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
