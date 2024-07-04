package med.voll.api.controller;

import med.voll.api.domain.consulta.AgendaDeConsultaService;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.DatosDetallesConsulta;
import med.voll.api.domain.medicos.Especialidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest // Trabajar con todos los componentes dentro de Spring (usar en controllers para verbos HTTP)
@AutoConfigureMockMvc // Se encarga de configurar todos los componentes para simular una petición para el controlador
@AutoConfigureJsonTesters // Se encarga de hacer las configuraciones para esta clase de prueba, relacionada al parámetro JacksonTester
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    // Toma un objeto que es del tipo Java y lo hace un Json
    @Autowired
    private JacksonTester<DatosAgendarConsulta> agendarConsultaJacksonTester;

    @Autowired
    private JacksonTester<DatosDetallesConsulta> detallesConsultaJacksonTester;

    @MockBean // Referencia a simular esa clase de serivicios
    private AgendaDeConsultaService agendaDeConsultaService;

    @Test
    @DisplayName("Deberia retornar estado HTTP 400 cuando los datos ingresados sean inválidos")
    @WithMockUser // Para simular el envío del token
    void agendarEscenario1() throws Exception {
        // Given                                                 // When
        var response = mvc.perform(post("/consultas")).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deberia retornar estado HTTP 200 cuando los datos ingresados son válidos")
    @WithMockUser // Para simular el envío del token
    void agendarEscenario2() throws Exception {
        // Given
        var fecha = LocalDateTime.now().plusHours(1);
        var especialidad = Especialidad.CARDIOLOGIA;
        var datos = new DatosDetallesConsulta(null, 2l, 5l, fecha);

        when(agendaDeConsultaService.agendar(any())).thenReturn(datos); // any "cualquier decisión
        // When
        var response = mvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON) //JSON, parámetros del detallamiento de consulta, el encabezado, la fecha en la que se está realizando y el tipo de archivo que se está enviando
                        .content(agendarConsultaJacksonTester.write(new DatosAgendarConsulta(null, 2l, 5l, fecha, especialidad)).getJson()))
                .andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
                                                                                                        // Se pone la "l" para especificar que es long
        var jsonEsperado = detallesConsultaJacksonTester.write(new DatosDetallesConsulta(null, 2l, 5l, fecha)).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}