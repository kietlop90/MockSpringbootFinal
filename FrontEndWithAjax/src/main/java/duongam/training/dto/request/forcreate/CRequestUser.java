package duongam.training.dto.request.forcreate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import duongam.training.dto.customvalid.FieldMatch;
import duongam.training.dto.enums.ERole;
import lombok.*;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class CRequestUser {
    private String username;
    private String password;
}
