package com.helperhub.controller;

import com.helperhub.entity.Worker;
import com.helperhub.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workers")
@CrossOrigin("*")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    // Add Worker
    @PostMapping
    public Worker addWorker(@RequestBody Worker worker) {
        return workerService.saveWorker(worker);
    }

    // Get All Workers
    @GetMapping
    public List<Worker> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    // Get Worker By ID
    @GetMapping("/{id}")
    public Optional<Worker> getWorkerById(@PathVariable Long id) {
        return workerService.getWorkerById(id);
    }

    // Update Worker
    @PutMapping("/{id}")
    public Worker updateWorker(@PathVariable Long id,
                               @RequestBody Worker worker) {
        return workerService.updateWorker(id, worker);
    }

    // Delete Worker
    @DeleteMapping("/{id}")
    public String deleteWorker(@PathVariable Long id) {
        workerService.deleteWorker(id);
        return "Worker Deleted Successfully";
    }

    // Search By Name
    @GetMapping("/search")
    public List<Worker> searchByName(@RequestParam String name) {
        return workerService.searchByName(name);
    }

    // Search By Category
    @GetMapping("/category/{category}")
    public List<Worker> searchByCategory(@PathVariable String category) {
        return workerService.searchByCategory(category);
    }

    // Search By City
    @GetMapping("/city/{city}")
    public List<Worker> searchByCity(@PathVariable String city) {
        return workerService.searchByCity(city);
    }

    // Search By Category And City
    @GetMapping("/filter")
    public List<Worker> searchByCategoryAndCity(
            @RequestParam String category,
            @RequestParam String city) {

        return workerService.searchByCategoryAndCity(category, city);
    }

    // Search By Experience
    @GetMapping("/experience/{experience}")
    public List<Worker> searchByExperience(@PathVariable int experience) {
        return workerService.searchByExperience(experience);
    }

    // Search By Price
    @GetMapping("/price")
    public List<Worker> searchByPrice(
            @RequestParam double min,
            @RequestParam double max) {

        return workerService.searchByPrice(min, max);
    }

    // Available Workers
    @GetMapping("/available")
    public List<Worker> getAvailableWorkers() {
        return workerService.getAvailableWorkers();
    }

    // Update Availability
    @PutMapping("/{id}/availability")
    public Worker updateAvailability(
            @PathVariable Long id,
            @RequestParam boolean available) {

        return workerService.updateAvailability(id, available);
    }

    // Pagination
    @GetMapping("/page")
    public Page<Worker> getWorkers(
            @RequestParam int page,
            @RequestParam int size) {

        return workerService.getWorkers(page, size);
    }

    // Sorting
    @GetMapping("/sort")
    public List<Worker> sortWorkers(
            @RequestParam String field) {

        return workerService.getWorkersSorted(field);
    }

    // Pagination + Sorting
    @GetMapping("/page-sort")
    public Page<Worker> pageSort(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String field) {

        return workerService.getWorkers(page, size, field);
    }

    // Upload Worker Image
    @PostMapping("/{id}/upload")
    public ResponseEntity<String> uploadWorkerImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        try {

            String fileName = workerService.uploadImage(id, file);

            return ResponseEntity.ok(
                    "Image Uploaded Successfully : " + fileName);

        } catch (IOException e) {

            return ResponseEntity.badRequest()
                    .body("Image Upload Failed");
        }
    }
}