package com.example.repaso.controller;

import com.example.repaso.model.Delivery;
import com.example.repaso.repositories.IDeliveryRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    @Autowired
    private IDeliveryRepositorie deliveryRepository;

    /**
     * Endpoint 1: Obtener entregas completadas por código de dron
     * URL: GET http://localhost:8080/api/deliveries/completed?droneCode=DRN-001
     */
    @GetMapping("/completed")
    public List<Delivery> getCompletedDeliveriesByDroneCode(@RequestParam String droneCode) {
        // Llama al query method: findByDroneCodeAndStatus
        return deliveryRepository.findByDroneCodeAndStatus(droneCode, "COMPLETED");
    }
}