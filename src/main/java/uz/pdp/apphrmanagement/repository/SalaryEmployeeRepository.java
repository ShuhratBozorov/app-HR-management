package uz.pdp.apphrmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.apphrmanagement.entity.SalaryEmployee;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SalaryEmployeeRepository extends JpaRepository<SalaryEmployee, UUID> {
    @Query(nativeQuery = true,value = "Select * from employee_salary sal" +
            "join employee emp on sal.employee_id =:id" +
            "where sal.date_time between :start and :ended" +
            "or" +
            "Select * from employee_salary sal" +
            "where sal.date_time  between :start and :ended")
    List<SalaryEmployee> getEmployeeInfo(@Param("start") LocalDateTime start, @Param("end") LocalDateTime ended, @Param("id") UUID id);
}
