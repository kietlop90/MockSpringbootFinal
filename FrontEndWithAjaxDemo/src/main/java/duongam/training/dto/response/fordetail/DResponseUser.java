package duongam.training.dto.response.fordetail;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DResponseUser {
    private Long id;

    private String username;

    private List<String> listOfRoles;

    private String token;
}
