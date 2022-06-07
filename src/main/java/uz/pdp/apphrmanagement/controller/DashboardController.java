package uz.pdp.apphrmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apphrmanagement.entity.Employee;
import uz.pdp.apphrmanagement.entity.SalaryEmployee;
import uz.pdp.apphrmanagement.entity.Task;
import uz.pdp.apphrmanagement.entity.TourniquetHistory;
import uz.pdp.apphrmanagement.payload.DashboardDto;
import uz.pdp.apphrmanagement.service.EmployeeSalaryService;
import uz.pdp.apphrmanagement.service.EmployeeService;
import uz.pdp.apphrmanagement.service.TaskService;
import uz.pdp.apphrmanagement.service.TourniquetHistoryService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/dashboard")
public class DashboardController {
    @Autowired
    EmployeeSalaryService employeeSalaryService;

    @Autowired
    TaskService taskService;

    @Autowired
    TourniquetHistoryService tourniquetHistoryService;

    @Autowired
    EmployeeService employeeService;

    @PreAuthorize("hasAnyRole('DIRECTOR', 'MANAGER')")
    @GetMapping("/listEmployee")
    public HttpEntity<?> getListEmployee(){
        List<Employee> allEmployee = employeeService.getAllEmployee();
        return ResponseEntity.ok(allEmployee);
    }
    @PreAuthorize("hasAnyRole('DIRECTOR')")
    @GetMapping(value = "/infoTaskTourniquet/{id}")
    public HttpEntity<?> getInfoTaskTouniquet(@PathVariable UUID id, @RequestParam LocalDate start, @RequestParam LocalDate end){
        DashboardDto dto = new DashboardDto();
        List<Task> taskInfo = taskService.info(start, end, id);
        List<TourniquetHistory> historiesInfo = tourniquetHistoryService.info(start, end, id);
        dto.setHistories(historiesInfo);
        dto.setTaskList(taskInfo);
        return ResponseEntity.ok(dto);
    }
    @PreAuthorize("hasAnyRole('DIRECTOR')")
    @GetMapping("/salaryInfo")
    public HttpEntity<?> getInfoSalary(@PathVariable UUID id, @RequestParam LocalDate start, @RequestParam LocalDate end){
        List<SalaryEmployee> employeeSalaries = employeeSalaryService.infoSalary(id, start, end);
        return ResponseEntity.ok(employeeSalaries);
    }
    @PreAuthorize("hasAnyRole('DIRECTOR', 'MANAGER')")
    @GetMapping("/taskInfo")
    public HttpEntity<?> getListTask(){
        List<Task> allTask = taskService.getAllTask();
        return ResponseEntity.ok(allTask);
    }
}
