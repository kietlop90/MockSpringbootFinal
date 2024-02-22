package duongam.training.dto.response.fordetail;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DResponseUser {
    private Long id;

    private String username;

    private String role;

    private String token;

    private String email;

    private String dob;

    private String gender;

    private String phone;

    private String name;

    private Boolean status;

}
