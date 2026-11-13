package empleado.modelo;

import empleado.exception.LegajoInvalidoException;

import java.time.LocalDate;

public class Empleado {

    private final String legajo;
    private final String nombreCompleto;
    private final String puesto;
    private final Double salario;
    private final LocalDate fechaIngreso;

    public Empleado(String legajo, String nombreCompleto, String puesto, Double salario, LocalDate fechaIngreso) {
        validarLegajo(legajo);

        this.legajo = legajo;
        this.nombreCompleto = nombreCompleto;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
    }

    private void validarLegajo(String legajo) {
        if (legajo == null || !legajo.matches("^[A-Z]{3}\\d{4}$")) {
            throw new LegajoInvalidoException("El legajo debe tener 3 letras mayúsculas seguidas de 4 números (ej: ABC1234).");
        }
    }

    public String getLegajo() {
        return legajo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getPuesto() {
        return puesto;
    }

    public Double getSalario() {
        return salario;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }
}