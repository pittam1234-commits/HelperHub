package com.helperhub.service;

import com.helperhub.entity.Booking;
import com.helperhub.entity.User;
import com.helperhub.entity.Worker;
import com.helperhub.repository.BookingRepository;
import com.helperhub.repository.UserRepository;
import com.helperhub.repository.WorkerRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository repository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private WorkerRepository workerRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private BookingService service;

    @Test
    void saveBookingTest() {

        User user = new User();
        user.setId(1L);
        user.setName("Ajay");
        user.setEmail("ajay@gmail.com");

        Worker worker = new Worker();
        worker.setId(1L);
        worker.setName("Ramesh");
        worker.setCategory("Electrician");
        worker.setCity("Hyderabad");

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setWorker(worker);
        booking.setBookingDate(LocalDate.now());
        booking.setBookingTime(LocalTime.now());

        when(repository.save(any(Booking.class))).thenReturn(booking);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(workerRepository.findById(1L)).thenReturn(Optional.of(worker));

        service.saveBooking(booking);

        verify(repository, times(1)).save(any(Booking.class));
        verify(emailService, times(1))
                .sendEmail(anyString(), anyString(), anyString());
    }
}