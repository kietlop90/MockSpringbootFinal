package com.mock.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class SearchListUserResponse {

    private Long total;
    private List<SearchUsersResponse> users;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchUsersResponse {
        private Long id;
        private String username;
        private String name;
    }
}
