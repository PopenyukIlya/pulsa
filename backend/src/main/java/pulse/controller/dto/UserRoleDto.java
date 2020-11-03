package pulse.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserRoleDto {
private String newrole;
private List<Long> checked;
}
