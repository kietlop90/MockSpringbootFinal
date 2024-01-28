package com.mock.api.request;

import com.mock.api.constant.AppConstants;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class UpdateUserRequest {

    @NotNull
    private Long id;

    @NotBlank
    @Size(min = 6, max = 200)
    private String username;

    @NotBlank
    @Size(min = 8, max = 200)
    @Pattern(regexp = AppConstants.REGEX_PASSWORD)
    private String password;

    private String name;
    private String email;

    @Parameter(name = "date of birthday", required = false, example = "2021-12-31")
    private LocalDate dob;
}
