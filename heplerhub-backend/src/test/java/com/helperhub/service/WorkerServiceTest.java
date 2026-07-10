package com.helperhub.service;

import com.helperhub.entity.Worker;
import com.helperhub.repository.WorkerRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WorkerServiceTest {

    @Mock
    private WorkerRepository repository;

    @InjectMocks
    private WorkerService service;

    @Test
    void saveWorkerTest(){

        Worker worker = new Worker();
        worker.setName("Ramesh");

        when(repository.save(worker)).thenReturn(worker);

        Worker saved = service.saveWorker(worker);

        assertEquals("Ramesh", saved.getName());
    }

    @Test
    void getAllWorkersTest(){

        when(repository.findAll())
                .thenReturn(Arrays.asList(new Worker(),new Worker()));

        assertEquals(2,service.getAllWorkers().size());
    }
}