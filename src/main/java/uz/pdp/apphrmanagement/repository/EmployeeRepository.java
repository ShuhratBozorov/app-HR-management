package uz.pdp.apphrmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apphrmanagement.entity.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Boolean existsByEmailAndEnabledTrue(String email);

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByIdAndEnabledTrue(UUID id);

    Optional<Employee> findByEmailAndEmailCode(String email, Integer emailCode);

    List<Employee> findAllByCompanyIdAndEnabledTrue(UUID company_id);
}
