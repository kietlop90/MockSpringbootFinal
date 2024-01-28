package duongam.training.dto.response.forlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LResponseUser {
    private Long id;
    private String name;
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
