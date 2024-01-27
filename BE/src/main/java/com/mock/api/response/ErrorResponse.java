package com.mock.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(description = "ErrorResponse")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    @ApiModelProperty(value = "status", example = "false")
    @Getter
    private final boolean status = false;

    @ApiModelProperty(value = "errorCode", example = "error code")
    @Getter
    @JsonInclude(Include.NON_EMPTY)
    private String errorCode;

    @ApiModelProperty(value = "message", example = "error message")
    @Getter
    private String message;

    @ApiModelProperty(value = "errors", example = "error details")
    @Getter
    @JsonInclude(Include.NON_EMPTY)
    private List<String> errors;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
