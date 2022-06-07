package uz.pdp.apphrmanagement.payload;

import lombok.Data;
import uz.pdp.apphrmanagement.entity.Task;
import uz.pdp.apphrmanagement.entity.TourniquetHistory;

import java.util.List;

@Data
public class DashboardDto {
    private List<Task> taskList;

    private List<TourniquetHistory> histories;
}
