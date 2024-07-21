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
public class BorrowRecordController {
    private final BorrowRecordService borrowRecordService;
    @PostMapping("/borrow")
    public ResponseEntity<GenericResponse<BorrowRecordDto>> borrow(@RequestParam long userId, @RequestParam long bookId){
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("borrow_record","borrow _record _created _header")
                .body(GenericResponse.success(borrowRecordService.borrow(userId,bookId),"Borrow Record Created ",HttpStatus.CREATED,HttpStatus.CREATED.value()));
    }
    @PostMapping("/return")
    public ResponseEntity<GenericResponse<BorrowRecordDto>> returnBook(@RequestParam long userId, @RequestParam long bookId){
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("borrow_record","borrow _record _created _header")
                .body(GenericResponse.success(borrowRecordService.returnBook(userId,bookId),"Book Returned success ",HttpStatus.CREATED,HttpStatus.CREATED.value()));
    }
    @GetMapping("/check_availability")
    public ResponseEntity<GenericResponse<?>> checkAvailable( @RequestParam long bookId){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("borrow_record","borrow _record _created _header")
                .body(GenericResponse.success(borrowRecordService.checkAvailable(bookId),"Available Check Success ",HttpStatus.ACCEPTED,HttpStatus.ACCEPTED.value()));
    }
    @GetMapping("/all_borrow_record")
    public ResponseEntity<GenericResponse<?>> getBorrowRecord(@RequestParam long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).header("borrow_record", "borrow _record _created _header").body(GenericResponse.success(borrowRecordService.viewBorrowRecords(userId), "Get borrow Record By User Id ", HttpStatus.OK, HttpStatus.OK.value()));
    }
    @GetMapping("/all_borrow_records")
    public ResponseEntity<GenericResponse<List<BorrowRecordDto>>> getBorrowRecord() {
        return ResponseEntity.status(HttpStatus.CREATED).header("borrow_record", "borrow _record _created _header").body(GenericResponse.success(borrowRecordService.viewAllBorrowRecords(), "Get All Borrow Record ", HttpStatus.OK, HttpStatus.OK.value()));
    }
}
