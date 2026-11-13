package ar.edu.undec.adapter.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Importá las excepciones de tu módulo core según corresponda:
// import empleado.excepcion.EmpleadoYaExisteException;
// import empleado.excepcion.EmpleadoNoEncontradoException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(empleado.exception.EmpleadoYaExisteException.class)
    public ResponseEntity<String> handleEmpleadoYaExiste(empleado.exception.EmpleadoYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(empleado.exception.EmpleadoNoEncontradoException.class)
    public ResponseEntity<String> handleEmpleadoNoEncontrado(empleado.exception.EmpleadoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}