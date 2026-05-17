package teccr.justdoitcloud;

// Clase principal para arrancar Spring Boot
import org.springframework.boot.SpringApplication;

// Activa configuración automática de Spring Boot
//
// Escanea componentes:
// @Controller
// @Service
// @Repository
// etc
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Permite configurar rutas MVC simples
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

// Interfaz de configuración MVC
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Marca esta clase como aplicación principal Spring Boot
@SpringBootApplication

// Implementa configuración MVC personalizada
public class JustDoItCloudApplication implements WebMvcConfigurer {

    // Punto de entrada principal Java
    public static void main(String[] args) {

        // Arranca servidor Spring Boot
        // Inicializa:
        // controllers
        // templates
        // rutas
        // servidor embebido Tomcat
        // contexto Spring
        SpringApplication.run(JustDoItCloudApplication.class, args);
    }

    // Sobrescribe configuración MVC
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        // Ruta:
        // http://localhost:8080/
        // renderiza automáticamente: home.html
        // sin necesidad de crear un controller
        registry.addViewController("/").setViewName("home");

    }

}
