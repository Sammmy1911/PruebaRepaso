package com.example.repaso.repositories;
import com.example.repaso.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
public interface IDeliveryRepositorie extends JpaRepository<Delivery, Long>{
    // Query Method: Entregas completadas por código de dron
    List<Delivery> findByDroneCodeAndStatus(String droneCode, String status);

}