package com.example.repaso.controller;

import com.example.repaso.model.DronePosition;
import com.example.repaso.repositories.IDronePositionRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drone-positions")
public class DronePositionController {

    @Autowired
    private IDronePositionRepositorie dronePositionRepository;

    @GetMapping("/route")
    public List<DronePosition> getDroneRoute(@RequestParam String droneCode) {
        return dronePositionRepository.findByDroneCodeOrderByRecordedAtAsc(droneCode);
    }
}