package teccr.justdoitcloud.data;

// Lombok:
// genera automáticamente getters, setters,
// toString(), equals(), hashCode(), etc
import lombok.Data;

// Lombok:
// genera constructor automáticamente
// SOLO con atributos final o @NonNull
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// @Data equivale más o menos a incluir:
// getters
// setters
// toString
// equals
// hashCode
@Data

// Constructor de clase
// Genera automáticamente constructor con atributos final/@NonNull
@RequiredArgsConstructor
public class User {

    // Datos de usuario

    // Nombre de usuario
    private final String userName;
    // Nombre real
    private final String name;
    // Correo
    private final String email;
    // Tipo de usuario (ADMIN o REGULAR)
    private final Type type;

    // Lista de tareas del usuario, inicia vacía por defecto
    private List<Task> tasks = new ArrayList<>();

    // Enum interno: User.Type.ADMIN / User.Type.REGULAR
    public enum Type {
        ADMIN,
        REGULAR
    }

    // agrega una tarea a la lista
    public void addTask(Task task) {
        this.tasks.add(task);
    }
}