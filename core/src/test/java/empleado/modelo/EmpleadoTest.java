package empleado.modelo;

import empleado.exception.LegajoInvalidoException;
import empleado.exception.AtributoObligatorioException;
import empleado.exception.SalarioInsuficienteException;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmpleadoTest {

    @Test
    void deberiaLanzarLegajoInvalidoException() {
        // Arrange & Act & Assert
        assertThrows(LegajoInvalidoException.class, () -> {
            new Empleado(
                    "123ABCD", // Legajo inválido (debe ser 3 letras y 4 números: ABC1234)
                    "Juan Pérez",
                    "ANALISTA",
                    600000.0,
                    LocalDate.of(2023, 5, 10)
            );
        });
    }

    @Test
    void deberiaLanzarAtributoObligatorioException() {
        // Arrange & Act & Assert
        assertThrows(AtributoObligatorioException.class, () -> {
            new Empleado(
                    "ABC1234", // Legajo válido
                    null,       // Nombre nulo (inválido)
                    "ANALISTA",
                    600000.0,
                    LocalDate.of(2023, 5, 10)
            );
        });
    }

    @Test
    void deberiaLanzarSalarioInsuficienteException() {
        // Arrange & Act & Assert
        assertThrows(SalarioInsuficienteException.class, () -> {
            new Empleado(
                    "ABC1234",
                    "Juan Pérez",
                    "ANALISTA",
                    0.0, // Salario inválido (cero o negativo)
                    LocalDate.of(2023, 5, 10)
            );
        });
    }











}