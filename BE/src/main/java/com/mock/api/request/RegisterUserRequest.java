package com.mock.api.request;

import com.mock.api.constant.AppConstants;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserRequest {

    @ApiModelProperty(value = "username", allowableValues = "6 - 200 characters", required = true)
    // this for comment swagger
    @NotBlank
    @Size(min = 6, max = 200)
    private String username;

    @NotBlank
    @Size(min = 8, max = 200)
    @Pattern(regexp = AppConstants.REGEX_PASSWORD)
    private String password;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "dob", dataType = "java.util.LocalDate", example = "2021-12-31")
    private LocalDate dob;
}
