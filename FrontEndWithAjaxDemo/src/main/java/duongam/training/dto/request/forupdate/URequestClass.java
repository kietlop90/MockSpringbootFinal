package duongam.training.dto.request.forupdate;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class URequestClass {
    private Long id;
    @Size(min = 6, max = 50, message = "Length of firstname should be from 6 to 20")
    private String name;
    private String email;
    private String phone;
    private String role;
    private LocalDate dob;
    private String gender;
    private Boolean status;
    private String createdBy;
    private Timestamp createdDate;
    private String modifiedBy;
    private Timestamp modifiedDate;
    private String username;
}
