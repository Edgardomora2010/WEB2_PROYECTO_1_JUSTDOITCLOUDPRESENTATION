package teccr.justdoitcloud.data;

// Validación:
// el campo debe existir/no ser null
import jakarta.validation.constraints.NotNull;

// Validación:
// tamaño mínimo/máximo de texto
import jakarta.validation.constraints.Size;


// Lombok:
// genera getters, setters, toString, etc
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalDate;

// Genera por defecto:
// getters
// setters
// toString
// equals
// hashCode
@Data
public class Task {

    // Validación:
    // mínimo 3 caracteres
    //
    // si falla:
    // "Descripcion debe tener al menos 3 caracteres"
    @Size(min=3, message = "Descripcion debe tener al menos 3 caracteres")

    // Descripción de la tarea
    private final String description;

    // Fecha/hora de creación
    private final LocalDateTime created;

    // Fecha límite
    private final LocalDate deadline;

    // Validación:
    // no puede venir null
    @NotNull
    // Estado de la tarea
    private final Status status;

    // Enum:
    // Task.Status.PENDING
    // Task.Status.INPROGRESS
    // Task.Status.DONE
    public enum Status {

        // Pendiente
        PENDING,

        // En progreso
        INPROGRESS,

        // Finalizada
        DONE
    }
}