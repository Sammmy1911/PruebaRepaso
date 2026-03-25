package com.example.repaso.repositories;
import com.example.repaso.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

public interface IDroneEventRepositorie extends JpaRepository<DroneEvent, Long>{
    // Query Method: Último evento de una entrega
    Optional<DroneEvent> findFirstByDeliveryIdOrderByEventTimeDesc(Long deliveryId);
}