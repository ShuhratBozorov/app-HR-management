package uz.pdp.apphrmanagement.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class LoginDto {
    @NotNull
    @Size(min = 5, max = 40)
    private String email;

    @NotNull
    private String password;
}
