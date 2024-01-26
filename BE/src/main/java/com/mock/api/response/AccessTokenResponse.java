package com.mock.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccessTokenResponse {
    private final String type = "Bearer";
    private String accessToken;
    private boolean isPassword;
}
