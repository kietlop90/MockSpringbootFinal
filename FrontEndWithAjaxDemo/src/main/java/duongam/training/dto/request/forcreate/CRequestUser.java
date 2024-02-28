package duongam.training.dto.request.forcreate;

import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class CRequestUser {
    private String username;
    private String password;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private Long createdBy;
    private Long modifiedBy;
    private String role;
    private String name;
    private String email;
    private String phone;
    private String dob;
    private String gender;
    private Boolean status;
}
