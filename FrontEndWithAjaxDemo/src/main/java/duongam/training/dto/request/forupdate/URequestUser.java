package duongam.training.dto.request.forupdate;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class URequestUser {
    private Long id;
    private String username;
    private String password;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private String role;
    private String name;
    private String email;
    private String phone;
    private String dob;
    private String gender;
    private Boolean status;
}
