package com.helperhub.service;

import com.helperhub.entity.Worker;
import com.helperhub.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository repository;

    private static final String UPLOAD_DIR =
        System.getProperty("user.dir") + File.separator + "uploads";

    // Add Worker
    public Worker saveWorker(Worker worker) {
        return repository.save(worker);
    }

    // Get All Workers
    public List<Worker> getAllWorkers() {
        return repository.findAll();
    }

    // Get Worker By Id
    public Optional<Worker> getWorkerById(Long id) {
        return repository.findById(id);
    }

    // Update Worker
    public Worker updateWorker(Long id, Worker worker) {

        Worker existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Worker Not Found"));

        existing.setName(worker.getName());
        existing.setCategory(worker.getCategory());
        existing.setPhone(worker.getPhone());
        existing.setEmail(worker.getEmail());
        existing.setCity(worker.getCity());
        existing.setExperience(worker.getExperience());
        existing.setPricePerHour(worker.getPricePerHour());
        existing.setAvailable(worker.isAvailable());
        existing.setImageUrl(worker.getImageUrl());

        return repository.save(existing);
    }

    // Delete Worker
    public void deleteWorker(Long id) {
        repository.deleteById(id);
    }

    // Search By Name
    public List<Worker> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    // Search By Category
    public List<Worker> searchByCategory(String category) {
        return repository.findByCategoryIgnoreCase(category);
    }

    // Search By City
    public List<Worker> searchByCity(String city) {
        return repository.findByCityIgnoreCase(city);
    }

    // Search By Category And City
    public List<Worker> searchByCategoryAndCity(String category, String city) {
        return repository.findByCategoryIgnoreCaseAndCityIgnoreCase(category, city);
    }

    // Search By Experience
    public List<Worker> searchByExperience(int experience) {
        return repository.findByExperienceGreaterThanEqual(experience);
    }

    // Search By Price
    public List<Worker> searchByPrice(double min, double max) {
        return repository.findByPricePerHourBetween(min, max);
    }

    // Available Workers
    public List<Worker> getAvailableWorkers() {
        return repository.findByAvailableTrue();
    }

    // Update Availability
    public Worker updateAvailability(Long id, boolean available) {

        Worker worker = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Worker Not Found"));

        worker.setAvailable(available);

        return repository.save(worker);
    }

    // Pagination
    public Page<Worker> getWorkers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return repository.findAll(pageable);
    }

    // Sorting
    public List<Worker> getWorkersSorted(String field) {

        return repository.findAll(Sort.by(field));
    }

    // Pagination + Sorting
    public Page<Worker> getWorkers(int page, int size, String field) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(field));

        return repository.findAll(pageable);
    }

    // Upload Worker Image
    public String uploadImage(Long workerId,
                              MultipartFile file) throws IOException {

        Worker worker = repository.findById(workerId)
                .orElseThrow(() -> new RuntimeException("Worker Not Found"));

        File directory = new File(UPLOAD_DIR);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = System.currentTimeMillis()
                + "_"
                + file.getOriginalFilename();

        File destination = new File(directory, fileName);

        file.transferTo(destination);

        worker.setImageUrl(fileName);

        repository.save(worker);

        return fileName;
    }
}
