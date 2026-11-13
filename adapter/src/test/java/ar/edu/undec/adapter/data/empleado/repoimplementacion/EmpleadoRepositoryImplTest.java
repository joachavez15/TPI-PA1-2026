package ar.edu.undec.adapter.data.empleado.repoimplementacion;

import ar.edu.undec.adapter.data.empleado.crud.SpringDataEmpleadoRepository;
import ar.edu.undec.adapter.data.empleado.model.EmpleadoEntity;
import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(EmpleadoRepositoryImpl.class)
class EmpleadoRepositoryImplTest {

    @Autowired
    private EmpleadoRepositoryImpl empleadoRepository;

    @Autowired
    private SpringDataEmpleadoRepository springDataEmpleadoRepository;

    @Test
    void guardar_empleadoNuevo_deberiaPersistirYRetornarEmpleado() {
        // Arrange
        UUID id = UUID.randomUUID();
        Empleado empleadoDominio = new Empleado(
                id,
                "L1234",
                "Juan Perez",
                Puesto.DESARROLLADOR,
                1500.0,
                LocalDate.now()
        );

        // Act
        Empleado resultado = empleadoRepository.guardar(empleadoDominio);

        // Assert
        assertNotNull(resultado);
        assertEquals(empleadoDominio.getLegajo(), resultado.getLegajo());
        assertTrue(springDataEmpleadoRepository.existsByLegajo("L1234"));
    }
}