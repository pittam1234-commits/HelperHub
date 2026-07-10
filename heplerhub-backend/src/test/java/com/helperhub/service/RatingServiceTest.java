package com.helperhub.service;

import com.helperhub.entity.Rating;
import com.helperhub.repository.RatingRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {

    @Mock
    private RatingRepository repository;

    @InjectMocks
    private RatingService service;

    @Test
    void saveRatingTest(){

        Rating rating = new Rating();

        when(repository.save(rating)).thenReturn(rating);

        service.saveRating(rating);

        verify(repository,times(1)).save(rating);
    }
}