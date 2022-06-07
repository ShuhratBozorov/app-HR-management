package uz.pdp.apphrmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apphrmanagement.payload.ApiResponse;
import uz.pdp.apphrmanagement.payload.CardDto;
import uz.pdp.apphrmanagement.service.TourniquetCardService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/card")
public class TourniquetCardController {
    @Autowired
    TourniquetCardService tourniquetCardService;

    @PostMapping("/create")
    public HttpEntity<?> createCard(@RequestBody CardDto dto){
        ApiResponse apiResponse = tourniquetCardService.addCard(dto);
        return ResponseEntity.status(apiResponse.getSuccess()?201:409).body(apiResponse);
    }
    @PutMapping("/edet")
    public HttpEntity<?> edetCard(@RequestBody CardDto dto, @PathVariable UUID id){
        ApiResponse edet = tourniquetCardService.edet(dto, id);
        return ResponseEntity.status(edet.getSuccess()?202:409).body(edet);
    }

    @GetMapping("/checked")
    public HttpEntity<?> checkedCard(@PathVariable UUID id){
        ApiResponse apiResponse = tourniquetCardService.checkedCard(id);
        return ResponseEntity.status(apiResponse.getSuccess()?201:409).body(apiResponse);
    }

}
