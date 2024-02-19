package duongam.training.dto.request.forcreate;

import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class CRequestUser {
    private String password;
    private String roleId;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String dob;
    private String gender;
    private Boolean status;
}
