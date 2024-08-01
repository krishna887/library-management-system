package com.example.library_management.controller;

import com.example.library_management.dto.ReservationRecordDto;
import com.example.library_management.util.GenericResponse;
import com.example.library_management.service.ReservationRecordService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReservationController {
    private final ReservationRecordService reservationRecordService;
    @PostMapping("/reserve")
    public ResponseEntity<GenericResponse<ReservationRecordDto>> borrow(@RequestParam long userId, @RequestParam long bookId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GenericResponse.success(reservationRecordService.reserveBook(userId, bookId), "Reservation Record Created ", HttpStatus.CREATED, HttpStatus.CREATED.value()));
    }
    @PostMapping("/reserve/cancel/{id}")
    public ResponseEntity<GenericResponse<String>> cancelReservation(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GenericResponse.success(reservationRecordService.cancelReservation(id), "Reservation Record Cancel Success ", HttpStatus.CREATED, HttpStatus.CREATED.value()));
    }
    @GetMapping("/all_reservation_record")
    public ResponseEntity<GenericResponse<?>> getReservationRecords(@RequestParam long userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GenericResponse.success(reservationRecordService.viewReservationsByUser(userId), "Get Reserve Record By User Id ", HttpStatus.OK, HttpStatus.OK.value()));
    }
    @GetMapping("/all_reservations")
    public ResponseEntity<GenericResponse<?>> getAllReservationRecords() {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GenericResponse.success(reservationRecordService.getAllReservations(), "Get All Reservation Records ", HttpStatus.OK, HttpStatus.OK.value()));
    }


}
