package com.example.repaso.model;

// Importaciones necesarias
import com.fasterxml.jackson.annotation.JsonIgnore;  // Evita bucles infinitos en JSON
import jakarta.persistence.*;  // Anotaciones JPA
import lombok.Data;  // Genera getters, setters, toString, equals, hashCode
import lombok.NoArgsConstructor;  // Genera constructor sin argumentos
import lombok.ToString;  // Para personalizar toString

import java.util.List;  // Para las colecciones

@Data  // Lombok: genera todos los métodos boilerplate
@NoArgsConstructor  // Lombok: constructor sin parámetros
@Entity(name = "drone")  // JPA: esta clase es una entidad, tabla 'drone' en BD
public class Drone {

    // ========== CAMPOS ==========

    @Id  // JPA: este campo es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-incremental en BD
    private Long id;  // Identificador único del dron

    @Column(unique = true, nullable = false)  // Restricciones: valor único y no nulo
    private String code;  // Código único del dron (ej: 'DRN-001')

    // @Column implícito: nombre de columna = nombre del campo
    private String name;  // Nombre del dron (ej: 'Falcon One')

    private String status;  // Estado: AVAILABLE, BUSY, MAINTENANCE

    // ========== RELACIONES ==========

    /**
     * Relación OneToMany con DronePosition
     * Un dron puede tener muchas posiciones registradas
     *
     * mappedBy = "subject": Indica que el lado dueño es DronePosition.subject
     * cascade = CascadeType.ALL: Todas las operaciones (persist, merge, remove) se propagan
     * fetch = FetchType.LAZY (por defecto): Las posiciones se cargan bajo demanda
     */
    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL)
    @JsonIgnore  // Evita serializar esta lista en respuestas JSON (previene bucles)
    @ToString.Exclude  // Lombok: excluye esta lista del método toString()
    private List<DronePosition> dronePositions;  // Lista de posiciones del dron

    /**
     * Relación OneToMany con DroneEvent
     * Un dron puede tener muchos eventos asociados
     *
     * mappedBy = "drone": Apunta al campo 'drone' en la clase DroneEvent
     * NOTA: No se especifica cascade para evitar eliminaciones en cascada no deseadas
     */
    @OneToMany(mappedBy = "drone")
    @JsonIgnore
    @ToString.Exclude
    private List<DroneEvent> droneEvents;  // Lista de eventos del dron

    /**
     * Relación OneToMany con Delivery
     * Un dron puede realizar muchas entregas
     *
     * mappedBy = "drone": Apunta al campo 'drone' en la clase Delivery
     */
    @OneToMany(mappedBy = "drone")
    @JsonIgnore
    @ToString.Exclude
    private List<Delivery> deliveries;  // Lista de entregas asignadas al dron
}