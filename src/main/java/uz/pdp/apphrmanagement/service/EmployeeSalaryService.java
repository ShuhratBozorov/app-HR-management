package uz.pdp.apphrmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.apphrmanagement.entity.Employee;
import uz.pdp.apphrmanagement.entity.SalaryEmployee;
import uz.pdp.apphrmanagement.payload.ApiResponse;
import uz.pdp.apphrmanagement.repository.EmployeeRepository;
import uz.pdp.apphrmanagement.repository.SalaryEmployeeRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Service
public class EmployeeSalaryService {
    @Autowired
    SalaryEmployeeRepository salaryEmployeeRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public ApiResponse payment(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null
                &&authentication.isAuthenticated()
                &&authentication.getPrincipal().equals("anonymousUser"));
        Employee employee = (Employee) authentication.getPrincipal();

        List<Employee> employeeList = employeeRepository.findAllByCompanyIdAndEnabledTrue(employee.getCompany().getId());
        for (Employee employees : employeeList) {
            SalaryEmployee salary = new SalaryEmployee();
            salary.setSalary(employees.getSalary());
            salary.setEmployee(employees);
            salary.setDateTime(LocalDateTime.now());
            salaryEmployeeRepository.save(salary);
        }
        return new ApiResponse("Payment success", true);
    }

    public List<SalaryEmployee> infoSalary(UUID id, LocalDate start, LocalDate end){
        LocalDateTime startTime = start.atTime(00,00,00);
        LocalDateTime endTime = end.atTime(23,59,59);
        List<SalaryEmployee> employeeInfo = salaryEmployeeRepository.getEmployeeInfo(startTime, endTime, id);
        return employeeInfo;
    }
}
