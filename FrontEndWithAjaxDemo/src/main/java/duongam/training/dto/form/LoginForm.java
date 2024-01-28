package duongam.training.dto.form;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginForm {
	private String username;
	private String password;
}
