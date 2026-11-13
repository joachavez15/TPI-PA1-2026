package ar.edu.undec.adapter.service.empleado.controller;

import ar.edu.undec.adapter.service.empleado.model.EmpleadoRequestDTO;
import ar.edu.undec.adapter.service.empleado.model.EmpleadoResponseDTO;
import ar.edu.undec.adapter.service.empleado.mapper.EmpleadoWebMapper;
import empleado.input.CrearEmpleadoInput;
import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final CrearEmpleadoInput crearEmpleadoInput;

    public EmpleadoController(CrearEmpleadoInput crearEmpleadoInput) {
        this.crearEmpleadoInput = crearEmpleadoInput;
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponseDTO> crearEmpleado(@RequestBody EmpleadoRequestDTO requestDTO) {
        Empleado empleadoDominio = new Empleado(
                null,
                requestDTO.getLegajo(),
                requestDTO.getNombreCompleto(),
                Puesto.valueOf(requestDTO.getPuesto()),
                requestDTO.getSalario(),
                requestDTO.getFechaIngreso()
        );

        Empleado empleadoCreado = crearEmpleadoInput.crear(empleadoDominio);
        EmpleadoResponseDTO responseDTO = EmpleadoWebMapper.aResponseDTO(empleadoCreado);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}