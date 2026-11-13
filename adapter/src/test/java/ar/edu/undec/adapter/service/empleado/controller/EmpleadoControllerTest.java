package ar.edu.undec.adapter.service.empleado.controller;

import ar.edu.undec.adapter.service.empleado.model.EmpleadoRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import empleado.input.CrearEmpleadoInput;
import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmpleadoController.class)
class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CrearEmpleadoInput crearEmpleadoInput; // Puerto de entrada del core

    @Test
    void crearEmpleado_conDatosValidos_deberiaRetornarCreatedYEmpleadoDTO() throws Exception {
        UUID id = UUID.randomUUID();
        Empleado empleadoCreado = new Empleado(id, "ABC1234", "Juan Perez", Puesto.ANALISTA, 600000.0, LocalDate.now());

        when(crearEmpleadoInput.crear(any())).thenReturn(empleadoCreado);

        // Simulamos el DTO de request web
        // ... (definiremos el request DTO luego)
    }

    @Test
    void crearEmpleado_cuandoEmpleadoYaExiste_deberiaRetornarConflict() throws Exception {
        when(crearEmpleadoInput.crear(any())).thenThrow(new empleado.exception.EmpleadoYaExisteException("El empleado ya existe"));
        EmpleadoRequestDTO requestDTO = new EmpleadoRequestDTO();
        requestDTO.setLegajo("ABC1234");
        requestDTO.setNombreCompleto("Juan Perez");
        requestDTO.setPuesto("ANALISTA");
        requestDTO.setSalario(600000.0);
        requestDTO.setFechaIngreso(LocalDate.now());

        mockMvc.perform(post("/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isConflict()); // Esperamos un 409
    }





}