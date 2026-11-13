package empleado.modelo;

import empleado.exception.LegajoInvalidoException;
import empleado.exception.AtributoObligatorioException;
import empleado.exception.SalarioInsuficienteException;

import java.time.LocalDate;

public class Empleado {

    private final String legajo;
    private final String nombreCompleto;
    private final String puesto;
    private final Double salario;
    private final LocalDate fechaIngreso;

    public Empleado(String legajo, String nombreCompleto, String puesto, Double salario, LocalDate fechaIngreso) {
        validarLegajo(legajo);
        validarAtributoObligatorio(nombreCompleto, "El nombre completo es obligatorio");
        validarAtributoObligatorio(puesto, "El puesto es obligatorio");

        if (salario == null) {
            throw new AtributoObligatorioException("El salario es obligatorio");
        }
        if (salario <= 0.0) {
            throw new SalarioInsuficienteException("El salario debe ser mayor a cero.");
        }
        if (fechaIngreso == null) {
            throw new AtributoObligatorioException("La fecha de ingreso es obligatoria");
        }

        validarPisoSalarial(puesto, salario);


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

    private void validarAtributoObligatorio(String valor, String mensaje) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new AtributoObligatorioException(mensaje);
        }
    }

    private void validarPisoSalarial(String puesto, double salario){

        double pisoMinimo = switch (puesto.toUpperCase()){
            case "ANALISTA" -> 500000.0;
            case "SUPERVISOR" -> 800000.0;
            case "GERENTE" -> 1200000.0;
            default -> 0;
        };

        if (salario < pisoMinimo){
            throw new SalarioInsuficienteException("El salario no alcanza el piso mínimo para el puesto de " + puesto + " ($" + pisoMinimo + ").");
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