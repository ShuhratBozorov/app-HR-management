package uz.pdp.apphrmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apphrmanagement.entity.Company;
import uz.pdp.apphrmanagement.entity.Employee;
import uz.pdp.apphrmanagement.entity.TourniquetCard;
import uz.pdp.apphrmanagement.payload.ApiResponse;
import uz.pdp.apphrmanagement.payload.CardDto;
import uz.pdp.apphrmanagement.repository.CompanyRepository;
import uz.pdp.apphrmanagement.repository.EmployeeRepository;
import uz.pdp.apphrmanagement.repository.TourniquetCardRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class TourniquetCardService {
    @Autowired
    TourniquetCardRepository tourniquetCardRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;

    public ApiResponse addCard(CardDto cardDto){
        Optional<Employee> optionalEmployee = employeeRepository.findById(cardDto.getEmployeeId());
        if (!optionalEmployee.isPresent())
            return new ApiResponse("Employee not found", false);

        Optional<Company> optionalCompany = companyRepository.findById(cardDto.getCompId());
        if (!optionalCompany.isPresent())
            return new ApiResponse("Company not found", false);
        Company company = optionalCompany.get();
        Employee employee = optionalEmployee.get();

        TourniquetCard tourniquetCard = new TourniquetCard();
        tourniquetCard.setEmployee(employee);
        tourniquetCard.setCompany(company);
        tourniquetCardRepository.save(tourniquetCard);
        return new ApiResponse("Save tournoquet card success", true);
    }

    public ApiResponse edet(CardDto cardDto, UUID id){

        Optional<TourniquetCard> cardOptional = tourniquetCardRepository.findById(id);
        if (!cardOptional.isPresent())
            return new ApiResponse("Card not found", false);
        TourniquetCard tourniquetCard = cardOptional.get();

        Optional<Employee> optionalEmployee = employeeRepository.findById(cardDto.getEmployeeId());
        if (!optionalEmployee.isPresent())
            return new ApiResponse("Employee not found", false);
        Employee employee = optionalEmployee.get();
        Optional<Company> optionalCompany = companyRepository.findById(cardDto.getCompId());
        if (!optionalCompany.isPresent())
            return new ApiResponse("Company not found", false);
        Company company = optionalCompany.get();

        tourniquetCard.setCompany(company);
        tourniquetCard.setEmployee(employee);
        tourniquetCardRepository.save(tourniquetCard);
        return new ApiResponse("Edded card", true);
    }

    public ApiResponse checkedCard(UUID id){
        Optional<TourniquetCard> cardOptional = tourniquetCardRepository.findById(id);
        if (!cardOptional.isPresent())
            return new ApiResponse("Card not found", false);
        TourniquetCard tourniquetCard = cardOptional.get();
        tourniquetCard.setStatus(false);
        tourniquetCardRepository.save(tourniquetCard);
        return new ApiResponse("Checked card success", true);
    }
}
