package rocketshell.vialactea.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignUp {

    @Email
    private String email;

    @Length(min = 6, max = 20)
    private String password;

}
