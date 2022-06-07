package uz.pdp.apphrmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apphrmanagement.entity.TourniquetCard;
import uz.pdp.apphrmanagement.entity.TourniquetHistory;
import uz.pdp.apphrmanagement.payload.ApiResponse;
import uz.pdp.apphrmanagement.payload.TourHistoryDto;
import uz.pdp.apphrmanagement.repository.EmployeeRepository;
import uz.pdp.apphrmanagement.repository.TourniquetCardHistoryRepository;
import uz.pdp.apphrmanagement.repository.TourniquetCardRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TourniquetHistoryService {
    @Autowired
    TourniquetCardHistoryRepository tourniquetCardHistoryRepository;
    @Autowired
    TourniquetCardRepository tourniquetCardRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public ApiResponse enter(TourHistoryDto dto){
        Optional<TourniquetCard> cardOptional = tourniquetCardRepository.findByIdAndStatusTrue(dto.getCardId());
        if (!cardOptional.isPresent())
            return new ApiResponse("Card not found", false);
        TourniquetCard tourniquetCard = cardOptional.get();
        TourniquetHistory history = new TourniquetHistory();
        history.setTourniquetCard(tourniquetCard);
        history.setEnteredAt(new Timestamp(System.currentTimeMillis()));
        tourniquetCardHistoryRepository.save(history);
        return new ApiResponse("Employee entered", true);
    }

    public ApiResponse exit(TourHistoryDto dto){
        Optional<TourniquetCard> cardOptional = tourniquetCardRepository.findByIdAndStatusTrue(dto.getCardId());
        if (!cardOptional.isPresent())
            return new ApiResponse("Card not found", false);
        TourniquetCard tourniquetCard = cardOptional.get();
        TourniquetHistory history = new TourniquetHistory();
        history.setTourniquetCard(tourniquetCard);
        history.setExitedAt(new Timestamp(System.currentTimeMillis()));
        tourniquetCardHistoryRepository.save(history);
        return new ApiResponse("Employee entered", true);
    }
    public List<TourniquetHistory> info(LocalDate start, LocalDate end, UUID employeeId){
        LocalDateTime dateTimeStart = start.atTime(00,00,00);
        LocalDateTime dateTimeEnd = end.atTime(23,59,59);
        Timestamp startTime = Timestamp.valueOf(dateTimeStart);
        Timestamp endTime = Timestamp.valueOf(dateTimeEnd);

        List<TourniquetHistory> tourniquetHistoryByEmployee =
                tourniquetCardHistoryRepository.getTourniquetHistoryByEmployee(startTime, endTime, employeeId);
        return tourniquetHistoryByEmployee;

    }
}
