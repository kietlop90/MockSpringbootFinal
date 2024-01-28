package duongam.training.dto.request.forcreate;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CRequestCustomer {
    @Size(min = 6, max = 20, message = "Length of firstname should be from 6 to 20")
    private String firstName;

    @Size(min = 6, max = 20, message = "Length of lastname should be from 6 to 20")
    private String lastName;

    @Email
    private String email;

    private String demand;

}
