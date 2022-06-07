package uz.pdp.apphrmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apphrmanagement.entity.Employee;
import uz.pdp.apphrmanagement.payload.ApiResponse;
import uz.pdp.apphrmanagement.payload.EmployeeDto;
import uz.pdp.apphrmanagement.payload.LoginDto;
import uz.pdp.apphrmanagement.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/auth")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addDirector")
    public HttpEntity<?> addDirector(@Valid @RequestBody EmployeeDto employeeDto){
        ApiResponse apiResponse = employeeService.addDirector(employeeDto);
        return ResponseEntity.status(apiResponse.getSuccess()?201:409).body(apiResponse);
    }

    @PreAuthorize("hasRole('DIRECTOR')")
    @PostMapping("/addManager")
    public HttpEntity<?> addManager(@Valid @RequestBody EmployeeDto employeeDto){
        ApiResponse apiResponse = employeeService.addManager(employeeDto);
        return ResponseEntity.status(apiResponse.getSuccess()?201:409).body(apiResponse);
    }

    @PreAuthorize("hasAnyRole('DIRECTOR', 'MANAGER')")
    @PostMapping("/addWorker")
    public HttpEntity<?> addWorker(@Valid @RequestBody EmployeeDto employeeDto){
        ApiResponse apiResponse = employeeService.addWorker(employeeDto);
        return ResponseEntity.status(apiResponse.getSuccess()?201:409).body(apiResponse);
    }

    @GetMapping("/inActive{id}")
    public HttpEntity<?> inActive(@PathVariable UUID id){
        ApiResponse apiResponse = employeeService.inActiveEmployee(id);
        return ResponseEntity.status(apiResponse.getSuccess()?203:409).body(apiResponse);
    }

    @PreAuthorize("hasAnyRole('DIRECTOR', 'MANAGER')")
    @GetMapping("/list")
    public HttpEntity<List<Employee>> getEmployee(){
        List<Employee> allEmployee = employeeService.getAllEmployee();

        return ResponseEntity.ok(allEmployee);
    }

    @PostMapping("/verify")
    public HttpEntity<?> verifyEmail(@RequestParam Integer emailCode, @RequestParam String sendingEmail, HttpServletRequest request){
        ApiResponse apiResponse = employeeService.verifyEmail(sendingEmail, emailCode, request);
        return ResponseEntity.status(apiResponse.getSuccess()?201:409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto){
        ApiResponse login = employeeService.login(loginDto);
        return ResponseEntity.status(login.getSuccess()?202:409).body(login);
    }
}
