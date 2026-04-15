package com.project.airBnbApp.repository;

import com.project.airBnbApp.dto.BookingDto;
import com.project.airBnbApp.entity.Booking;
import com.project.airBnbApp.entity.Hotel;
import com.project.airBnbApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking , Long > {
    Optional<Booking> findByPaymentSessionId(String sessionId);

    List<Booking> findByHotel(Hotel hotel);

    List<Booking> findByHotelAndCreatedAtBetween(Hotel hotel, LocalDateTime startDate, LocalDateTime endTime);

    List<BookingDto> findByUser(User user);
}
