package com.mock.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SearchListUserRequest extends PagingRequest{
    private String search;
}
