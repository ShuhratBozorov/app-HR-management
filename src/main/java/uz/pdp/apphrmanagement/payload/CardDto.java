package uz.pdp.apphrmanagement.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CardDto {
    @NotNull
    private UUID compId;

    @NotNull
    private UUID employeeId;
}
