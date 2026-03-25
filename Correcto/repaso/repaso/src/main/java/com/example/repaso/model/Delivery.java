

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;  // Para manejar fechas y horas
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "deliveries")  // Tabla 'deliveries' en BD
public class Delivery {

    // ========== CAMPOS ==========

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único de la entrega

    /**
     * Código de seguimiento de la entrega
     * Ejemplo: 'DEL-2026-0001'
     */
    private String trackingCode;

    /**
     * Estado de la entrega
     * Posibles valores: PENDING, IN_PROGRESS, COMPLETED, CANCELLED
     */
    private String status;

    /**
     * Dirección de destino de la entrega
     * Ejemplo: 'Calle 10 # 20-30'
     */
    private String destination;

    /**
     * Fecha y hora en que se asignó la entrega al dron
     * @Column(updatable = false): No se puede modificar después de crear
     */
    @Column(updatable = false)
    private Timestamp assignedAt;

    // ========== RELACIONES ==========

    /**
     * Relación ManyToOne con Drone
     * Muchas entregas pueden pertenecer a un mismo dron
     *
     * @ManyToOne: Este es el lado dueño de la relación (contiene la FK)
     * @JoinColumn: Define la columna de la llave foránea
     *   name = "drone_id": Nombre de la columna en la tabla deliveries
     *   nullable = false: La entrega siempre debe tener un dron asignado
     */
    @ManyToOne
    @JoinColumn(name = "drone_id", nullable = false)
    private Drone drone;  // Dron asignado a esta entrega

    /**
     * Relación OneToMany con DroneEvent
     * Una entrega puede tener muchos eventos asociados
     *
     * mappedBy = "delivery": Apunta al campo 'delivery' en DroneEvent
     * cascade = CascadeType.ALL: Si se elimina una entrega, se eliminan sus eventos
     */
    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<DroneEvent> droneEvents;  // Eventos ocurridos durante esta entrega
}