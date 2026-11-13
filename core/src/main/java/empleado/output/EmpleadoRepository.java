package empleado.output;

import empleado.modelo.Empleado;

public interface EmpleadoRepository {
    boolean existePorLegajo(String legajo);
    Empleado guardar(Empleado empleado);
}