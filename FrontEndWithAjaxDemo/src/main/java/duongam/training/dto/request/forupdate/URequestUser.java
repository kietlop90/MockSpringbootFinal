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
    private String password;
    private String roleId;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String dob;
    private String gender;
    private Boolean status;
    private String createdBy;
    private LocalDate createdDate;
    private String modifiedBy;
    private LocalDate modifiedDate;
}
