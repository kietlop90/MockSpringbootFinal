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
    @Size(min = 6, max = 50, message = "Length of firstname should be from 6 to 20")
    private String name;
    @Email
    private String email;
    private String phone;
    private List<String> roles;
    private LocalDate dob;
    private String gender;
    private boolean status;
    private String createdBy;
    private LocalDate createdDate;
    private String modifiedBy;
    private LocalDate modifiedDate;
    private String username;
}
