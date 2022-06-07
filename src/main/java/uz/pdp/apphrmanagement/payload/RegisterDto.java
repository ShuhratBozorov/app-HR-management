package uz.pdp.apphrmanagement.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import java.util.UUID;

@Data
public class RegisterDto {
    @Email
    private String email;

    private String password;

    private UUID EmployeeId;
}
