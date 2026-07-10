package com.helperhub.service;

import com.helperhub.entity.User;
import com.helperhub.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserService service;

    @Test
    void saveUserTest() {

        User user = new User();
        user.setName("Ajay");
        user.setEmail("ajay@gmail.com");

        when(repository.save(any(User.class))).thenReturn(user);

        User saved = service.saveUser(user);

        assertEquals("Ajay", saved.getName());

        verify(repository, times(1)).save(any(User.class));

        verify(emailService, times(1))
                .sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void getAllUsersTest() {

        when(repository.findAll())
                .thenReturn(Arrays.asList(new User(), new User()));

        assertEquals(2, service.getAllUsers().size());

        verify(repository, times(1)).findAll();
    }
}