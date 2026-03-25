package com.example.repaso.repositories;
import com.example.repaso.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
public interface IDroneRepositorie extends JpaRepository<Drone, Long>{

}