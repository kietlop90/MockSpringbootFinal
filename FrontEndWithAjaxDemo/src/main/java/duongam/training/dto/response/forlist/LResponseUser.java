package duongam.training.dto.response.forlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LResponseUser {
    private Long id;
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
