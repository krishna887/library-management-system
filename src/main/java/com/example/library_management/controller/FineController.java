package com.example.library_management.controller;

import com.example.library_management.dto.BorrowRecordDto;
import com.example.library_management.util.GenericResponse;
import com.example.library_management.service.BorrowRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FineController {
    private final BorrowRecordService borrowRecordService;

    @GetMapping("/calculate/fine")
    ResponseEntity<GenericResponse<Double>> calculateFine(@RequestParam long borrowId) {
        return ResponseEntity.status(HttpStatus.OK).header("fine_calculation_header", "fine_calculation_header").body(GenericResponse.success(borrowRecordService.calculateFine(borrowId), "Fine Calculation Success", HttpStatus.OK, HttpStatus.OK.value()));
    }
    @PostMapping("/pay/fine")
    ResponseEntity<GenericResponse<BorrowRecordDto>> payFine(@RequestParam long borrowId) {
        return ResponseEntity.status(HttpStatus.CREATED).header("pay_fine_header", "pay-fine_header").body(GenericResponse.success(borrowRecordService.payFine(borrowId), "Fine Paid Success", HttpStatus.CREATED, HttpStatus.CREATED.value()));
    }
    @GetMapping("/all/fines")
    ResponseEntity<GenericResponse<List<BorrowRecordDto>>> getUserFine(@RequestParam long userId) {
        return ResponseEntity.status(HttpStatus.OK).header("user-fine-header", "get-all-user-fine-header").body(GenericResponse.success(borrowRecordService.getUserFines(userId), "User Fine Fetch Success", HttpStatus.OK, HttpStatus.OK.value()));
    }


}
