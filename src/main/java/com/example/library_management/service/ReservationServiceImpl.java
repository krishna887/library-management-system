package com.example.library_management.service;

import com.example.library_management.dto.ReservationRecordDto;
import com.example.library_management.entity.Book;
import com.example.library_management.entity.ReservationRecord;
import com.example.library_management.entity.User;
import com.example.library_management.exception.CustomIllegalStateException;
import com.example.library_management.exception.NotLoggedInUserException;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.repository.BookRepository;
import com.example.library_management.repository.ReservationRecordRepository;
import com.example.library_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationRecordService {
    private final UserRepository userRepository;
    private final ReservationRecordRepository reservationRepository;
    private final BookRepository bookRepository;
    private final BorrowRecordService borrowRecordService;
    private final ModelMapper mapper;
    private  final CheckLoginService checkLoginService;

    @Override
    public ReservationRecordDto reserveBook(Long userId, Long bookId) {
       User authenticatedUser= userRepository.findByUsername( checkLoginService.getCurrentAuthenticatedUsername()).orElseThrow(()->new ResourceNotFoundException("User is Not found of this name "));
       if(!Objects.equals(authenticatedUser.getId(), userId)){
           throw  new NotLoggedInUserException("User is not Logged In");
       }
        if (borrowRecordService.getTotalFines(userId) >= 500) {
            throw new CustomIllegalStateException("Cannot reserve books with fines >= 500");

        }
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        if (book.getCopiesAvailable() > 0) {
            throw new CustomIllegalStateException("Book is available. No need to reserve.");
        }

        Optional<ReservationRecord> existingReservation = reservationRepository.findByBookIdAndActiveTrue(bookId).stream().filter(reservation -> reservation.getUser().getId().equals(userId)).findFirst();

        if (existingReservation.isPresent()) {
            throw new CustomIllegalStateException("You have already reserved this book.");
        }

        ReservationRecord reservation = new ReservationRecord();
        reservation.setUser(user);
        reservation.setBook(book);
        reservation.setReservationDate(LocalDate.now());
        reservation.setActive(true);

        reservationRepository.save(reservation);
        return mapper.map(reservation, ReservationRecordDto.class);
    }

    @Override
    public String cancelReservation(Long reservationId) {
        ReservationRecord reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));
        reservation.setActive(false);
        reservation.setCancelled(true);
        reservationRepository.save(reservation);
        return "Reservation Cancel success ";


    }

    @Override
    public List<ReservationRecordDto> viewReservationsByUser(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        List<ReservationRecord> reservationRecords = reservationRepository.findByUserId(userId);
        return reservationRecords.stream().map(reservationRecord -> mapper.map(reservationRecord, ReservationRecordDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ReservationRecordDto> getAllReservations() {
       List<ReservationRecord> reservationRecordList= reservationRepository.findAll();
      return reservationRecordList.stream().map(reservationRecord -> mapper.map(reservationRecord, ReservationRecordDto.class)).collect(Collectors.toList());
    }
}
