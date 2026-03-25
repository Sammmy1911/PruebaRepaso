package com.example.repaso.controller;

import com.example.repaso.model.DroneEvent;
import com.example.repaso.repositories.IDroneEventRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/drone-events")
public class DroneEventController {

    @Autowired
    private IDroneEventRepositorie droneEventRepository;

    @GetMapping("/last")
    public ResponseEntity<DroneEvent> getLastEventByDeliveryId(@RequestParam Long deliveryId) {
        Optional<DroneEvent> event = droneEventRepository.findFirstByDeliveryIdOrderByEventTimeDesc(deliveryId);

        if (event.isPresent()) {
            return ResponseEntity.ok(event.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}