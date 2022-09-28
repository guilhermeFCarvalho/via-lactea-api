package rocketshell.vialactea.dto.sign;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class SignIn {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
