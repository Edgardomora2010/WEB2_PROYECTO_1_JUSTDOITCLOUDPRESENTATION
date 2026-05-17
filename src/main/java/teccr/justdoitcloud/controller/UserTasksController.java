package teccr.justdoitcloud.controller;

// Librerías y funcionamiento
import jakarta.validation.Valid; // Activa validaciones para objetos
import lombok.extern.slf4j.Slf4j; // Crea automáticamente un objeto llamado "log" donde se pueda ir creando logs de ejecución
                                 // de la aplicación
import org.springframework.stereotype.Controller; // Marca esta clase como controlador web
import org.springframework.ui.Model; // Permite enviar datos al DOM/HTML
import org.springframework.validation.Errors; // Guarda errores de validación
import org.springframework.web.bind.annotation.*; // GET, POST, RequestMapping, etc
import teccr.justdoitcloud.data.Task; // Clase de lógica de negocios, para diseño de aplicación y modelado de datos
import teccr.justdoitcloud.data.User; // Clase de lógica de negocios, para diseño de aplicación y modelado de datos
import java.time.LocalDateTime;

// GENERALIDADES SOBRE JAVA SPRING BOOT
// Solo son métodos: GET/POST los que tengan anotaciones tipo:
/*
        @GetMapping
        @PostMapping
        @PutMapping
        @DeleteMapping
*/

// Las anotaciones se colocan sobre los métodos, o variables, y aplican o afectan directamente estas. Los métodos o variables
// puede tener varias anotaciones según la necesidad o su función

// Lombok crea automáticamente: private static final Logger log, para manejar log de la aplicación (No incluir información
// sensible bajo ningún motivo, que pueda quedar expuesta vía navegador).
@Slf4j

// Le indica a Spring: "Esta es la clase que maneja rutas web" e interacción con otros componentes.
@Controller

// Ruta base de este controlador
// Aquí : Toda la clase y métodos internos empiezan con ruta: /user/tasks
@RequestMapping("/user/tasks")

// Guarda el objeto "user" en sesión, para que sobreviva entre los requests
@SessionAttributes("user")

/*
 * Controlador MVC encargado de manejar las rutas,
 * interacción y lógica básica relacionadas con
 * tareas del usuario.
 *
 * Gestiona:
 * visualización de tareas
 * creación de nuevas tareas
 * preparación de datos para las vistas HTML
 * comunicación entre modelo User/Task y templates
 */
public class UserTasksController {

    // Este méthod crea automáticamente un objeto "user"
    // disponible para las vistas/controlador
    // es equivalente a: model.addAttribute("user", objeto);
    @ModelAttribute(name = "user")

    // Method público user que devuelve un objeto de clase User, crea un usuario (quemado para ejemplo de la aplicación)
    // válido con datos (usuario, nombre, email,tipo) + datos de tareas(tarea,fecha de creacion,
    // fecha límite, estado)
    public User user() {

        // Clase de negocio, que implementa atribudos del usuario + tareas
        User usr =  new User("christine", "Christine McVie", "christine@fm.com", User.Type.REGULAR);

        // Agregar una cuanta tarea, se invoca clase de negocio, para manejo de tareas
        Task task = new Task("Comprar Leche", LocalDateTime.now(), null, Task.Status.DONE);
        usr.addTask(task);

        task = new Task("Reparacion de sistema de frenos del carro", LocalDateTime.now(),
                LocalDateTime.now().plusDays(3).toLocalDate(), Task.Status.INPROGRESS);
        usr.addTask(task);

        // Devuelve objeto de clase User (usuario + tareas)
        return usr;
    }

    // Maneja GET:
    // /user/tasks
    @GetMapping
    public String showUserTasks(Model model) {

        // Envía un objeto vacío al formulario HTML para crear una nueva tarea
        model.addAttribute("newTask", new Task("", LocalDateTime.now(), null, Task.Status.INPROGRESS));
        // Devuelve plantilla usertasks
        return "usertasks";

    }

    @PostMapping // Este method responde a POST /user/tasks
    public String addTask(

            // Spring toma automáticamente los datos enviados
            // desde el formulario HTML y construye un objeto Task
            // @Valid: valida reglas de la clase Task
            // ejemplo: campos obligatorios, tamaños, etc
            // "newTask" es el nombre con el que el objeto existe
            // dentro del Model/vista
            @Valid
            @ModelAttribute(name = "newTask")
            Task newTask,

            // Aquí Spring coloca errores de validación
            // si algún campo vino incorrecto
            Errors errors,

            // Recupera el objeto "user"
            // guardado previamente en el Model/session
            @ModelAttribute("user")
            User user
    ) {

        // Logger de consola
        log.info("Adding task: " + newTask);

        // Si hubo errores en validación:
        // vuelve a cargar la plantilla usertasks.html
        if (errors.hasErrors()) {
            return "usertasks";
        }

        // Agrega la nueva tarea al usuario
        user.addTask(newTask);

        // Redirecciona al GET /user/tasks
        // para volver a cargar la página actualizada
        /* provoca que el navegador vuelva a entrar al controller.
        Y entonces Spring hace otra vez el flujo normal:

        GET /user/tasks
               ↓
        entra al controller
               ↓
        ejecuta métodos necesarios (@ModelAttribute)
               ↓
        ejecuta el méthod GET correspondiente
               ↓
        carga/prepara Model
               ↓
        renderiza plantilla HTML */

        return "redirect:/user/tasks";
    }

    // Laboratorio 3:
    // Se cre method para para devolver reporte de tareas, solicitado
    // en enunciado de Laboratorio 3. Se realiza desde controller existente
    // ya que el enunciado no plantea más cosas, como para realizar un
    // controlador nuevo, si no que sigue trabajando sobre datos de usuario y
    // tareas.

    // Methodo tipo: GET:
    // /user/tasks/report
    @GetMapping("/report")
    public String showUserTasksReport(Model userTaskModel){
        // Devuelve plantilla de reportr tareas
        return "report";
    }



}
