package pulse.payload.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.DateType;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
}
