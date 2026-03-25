package com.example.repaso.repositories;

import com.example.repaso.model.DronePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IDronePositionRepositorie extends JpaRepository<DronePosition, Long> {

    List<DronePosition> findByDroneCodeOrderByRecordedAtAsc(String droneCode);
    //                                              String corregido ↑
}