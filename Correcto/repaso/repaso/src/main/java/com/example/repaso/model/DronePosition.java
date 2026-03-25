package com.example.repaso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity(name = "drone_positions")  // Tabla 'drone_positions' en BD
public class DronePosition {

    // ========== CAMPOS ==========

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único de la posición registrada

    /**
     * Latitud de la ubicación del dron
     * Coordenada geográfica (ej: 3.451620)
     */
    private Double latitude;

    /**
     * Longitud de la ubicación del dron
     * Coordenada geográfica (ej: -76.531980)
     */
    private Double longitude;

    /**
     * Fecha y hora en que se registró esta posición
     * Permite trazar la ruta del dron en el tiempo
     */
    private Timestamp recordedAt;

    // ========== RELACIONES ==========

    /**
     * Relación ManyToOne con Drone
     * Muchas posiciones pueden pertenecer a un mismo dron
     *
     * @ManyToOne: Este es el lado dueño (contiene la FK)
     * @JoinColumn(name = "drone_id"): Columna FK en tabla drone_positions
     * nullable = false: Toda posición debe estar asociada a un dron
     */
    @ManyToOne
    @JoinColumn(name = "drone_id", nullable = false)
    @JsonIgnore  // Evita serializar el dron en respuestas JSON (previene bucles)
    private Drone drone;  // Dron al que pertenece esta posición

    /**
     * NOTA: El campo se llama 'drone' pero en la relación OneToMany en Drone
     * se usa 'mappedBy = "subject"'. Esto es inconsistente.
     *
     * Para que coincida, debería ser:
     *   @ManyToOne
     *   @JoinColumn(name = "drone_id")
     *   private Drone subject;
     *
     * O cambiar en Drone.java:
     *   @OneToMany(mappedBy = "drone")
     *   private List<DronePosition> dronePositions;
     *
     * Recomiendo usar 'drone' para consistencia con las demás entidades.
     */
}