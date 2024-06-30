package com.example.library_management.controller;

import com.example.library_management.dto.ReservationRecordDto;
import com.example.library_management.response.GenericResponse;
import com.example.library_management.service.BookService;
import com.example.library_management.service.ReservationRecordService;
import com.example.library_management.service.UserService;
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
        return ResponseEntity.status(HttpStatus.CREATED).header("borrow_record", "borrow _record _created _header").body(GenericResponse.success(reservationRecordService.reserveBook(userId, bookId), "Reservation Record Created ", HttpStatus.CREATED, HttpStatus.CREATED.value()));
    }

    @PostMapping("/reserve/cancel")
    public ResponseEntity<GenericResponse<String>> cancilReservation(@RequestParam long id) {
        return ResponseEntity.status(HttpStatus.CREATED).header("reservation_record", "cancel reservation record").body(GenericResponse.success(reservationRecordService.cancelReservation(id), "Reservation Record Created ", HttpStatus.CREATED, HttpStatus.CREATED.value()));
    }

    @GetMapping("/all_reservation_record")
    public ResponseEntity<GenericResponse<?>> getReservationRecords(@RequestParam long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).header("reserved_record", "reserved record _header").body(GenericResponse.success(reservationRecordService.viewReservationsByUser(userId), "Get Reserve Record By User Id ", HttpStatus.OK, HttpStatus.OK.value()));
    }


}
