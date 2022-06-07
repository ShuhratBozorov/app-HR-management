package uz.pdp.apphrmanagement.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskDto {
    private String name;

    private String description;

    private Integer deadline;

    private UUID employeeId;
}
