package com.example.repaso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity(name = "drone_events")  // Tabla 'drone_events' en BD
public class DroneEvent {

    // ========== CAMPOS ==========

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único del evento

    /**
     * Tipo de evento
     * Posibles valores: MISSION_STARTED, PACKAGE_PICKED, ARRIVED_DESTINATION,
     *                  DELIVERY_COMPLETED, MISSION_PLANNED, etc.
     */
    private String eventType;

    /**
     * Fecha y hora exacta en que ocurrió el evento
     */
    private Timestamp eventTime;

    /**
     * Descripción detallada del evento
     * Ejemplo: 'Package picked at warehouse'
     */
    private String description;

    // ========== RELACIONES ==========

    /**
     * Relación ManyToOne con Delivery
     * Muchos eventos pueden pertenecer a una misma entrega
     *
     * @JoinColumn(name = "delivery_id"): Columna FK en tabla drone_events
     * nullable = false: Todo evento debe estar asociado a una entrega
     */
    @ManyToOne
    @JoinColumn(name = "delivery_id", nullable = false)
    @JsonIgnore  // Evita serializar la entrega en respuestas JSON (previene bucles)
    private Delivery delivery;  // Entrega a la que pertenece este evento

    /**
     * Relación ManyToOne con Drone
     * Muchos eventos pueden pertenecer a un mismo dron
     *
     * @JoinColumn(name = "drone_id"): Columna FK en tabla drone_events
     * nullable = false: Todo evento debe estar asociado a un dron
     *
     * NOTA: Esta relación permite consultar todos los eventos de un dron
     * independientemente de la entrega
     */
    @ManyToOne
    @JoinColumn(name = "drone_id", nullable = false)
    @JsonIgnore
    private Drone drone;  // Dron que generó este evento
}