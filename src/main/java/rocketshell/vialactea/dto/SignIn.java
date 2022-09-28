package rocketshell.vialactea.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class SignIn {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

}
