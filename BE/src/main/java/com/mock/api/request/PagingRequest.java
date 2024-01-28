package com.mock.api.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * This class common setup common all paging of project
 */
public class PagingRequest {

    @NotNull
    @Min(1)
    private Integer page;

    @Getter
    @NotNull
    @Min(5)
    @Max(1000)
    private Integer limit;

    public final Integer getPage() {
        return page - 1;
    }

    public final Integer getLimit() {
       return limit;
    }
}
