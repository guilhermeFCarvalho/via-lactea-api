package rocketshell.vialactea.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class SignIn {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
