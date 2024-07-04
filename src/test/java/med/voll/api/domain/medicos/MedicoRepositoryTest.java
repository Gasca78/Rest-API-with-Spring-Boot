package med.voll.api.domain.medicos;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.paciente.DatosRegistroPaciente;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static med.voll.api.domain.medicos.Especialidad.CARDIOLOGIA;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // Indicamos que vamos a trabajar con persistencia de datos
// Indica que no debe de usar una base de datos externa, sino algúna ya configurada
// en este caso, la de MySQL
// Ya que si se necesitan usar base de datos de prueba, se debe separar la de producción
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Realizar operaciones con base de datos externa e indicar que no vamos a reemplazar la base de datos que estamos usando
@ActiveProfiles("test") // Perfil que se está utilizando
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Debería retornar null cuando el médico se encuentre en consulta con otro paciente en ese horario")
    void seleccionarMedicoConEspecialidadEnFechaEscenario1() {

        // Given
        var proximoLunes10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medico =registrarMedico("Jose","jose@mail.com","123456",CARDIOLOGIA);
        var paciente = registrarPaciente("Antonio", "antonio@mail.com","6543211");
        registrarConsulta(medico,paciente,proximoLunes10H);

        // When
        var medicoLibre = medicoRepository.seleccionarMedicoConEspecialidadEnFecha(CARDIOLOGIA, proximoLunes10H);

        //Then
        assertThat(medicoLibre).isNull();
    }

    @Test
    @DisplayName("Debería retornar un medico cuando realice la consulta en la base de datos para ese horario")
    void seleccionarMedicoConEspecialidadEnFechaEscenario2() {

        // Given
        var proximoLunes10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        // When
        var medico =registrarMedico("Jose","jose@mail.com","123456",CARDIOLOGIA);

        // Then
        var medicoLibre = medicoRepository.seleccionarMedicoConEspecialidadEnFecha(CARDIOLOGIA, proximoLunes10H);

        assertThat(medicoLibre).isEqualTo(medico);
    }

    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha) {
        em.persist(new Consulta(null, medico, paciente, fecha));
    }

    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad) {
        var medico = new Medico(datosMedico(nombre, email, documento, especialidad));
        em.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente(String nombre, String email, String documento) {
        var paciente = new Paciente(datosPaciente(nombre, email, documento));
        em.persist(paciente);
        return paciente;
    }


    private DatosRegistroMedico datosMedico(String nombre, String email, String documento, Especialidad especialidad) {
        return new DatosRegistroMedico(
                nombre,
                email,
                "3321568523",
                documento,
                especialidad,
                datosDireccion()
        );
    }

    private DatosRegistroPaciente datosPaciente(String nombre, String email, String documento) {
        return new DatosRegistroPaciente(
                nombre,
                email,
                "3321568523",
                documento,
                datosDireccion()
        );
    }

    private DatosDireccion datosDireccion() {
        return new DatosDireccion(
                "loca",
                "azul",
                "acapulco",
                "321",
                "12"
        );
    }
}