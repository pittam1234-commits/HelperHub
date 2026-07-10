package com.helperhub.service;

import com.helperhub.entity.Payment;
import com.helperhub.repository.PaymentRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private PaymentRepository repository;

    @InjectMocks
    private PaymentService service;

    @Test
    void savePaymentTest(){

        Payment payment = new Payment();

        when(repository.save(payment)).thenReturn(payment);

        service.savePayment(payment);

        verify(repository,times(1)).save(payment);
    }
}