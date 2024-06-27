package med.voll.api.domain.consulta;

import med.voll.api.domain.medicos.Medico;
import med.voll.api.domain.medicos.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private ConsultaRepository consultaRepository;

    public void agendar(DatosAgendarConsulta datos) {

        if(pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("Este ID para el paciente no fue encontrado");
        }
        if(datos.idMedico()==null && medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridad("Este ID para el paciente no fue encontrado");
        }

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var medico = seleccionarMedicos(datos);
        var consulta = new Consulta(null, medico, paciente, datos.fecha());
        consultaRepository.save(consulta);
    }

    private Medico seleccionarMedicos(DatosAgendarConsulta datos) {
        if (datos.idMedico()!=null) {
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad()==null) {
            throw new ValidacionDeIntegridad("Debe seleccionar una especialidad para el médico");
        }
        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(),datos.fecha());
    }
}
