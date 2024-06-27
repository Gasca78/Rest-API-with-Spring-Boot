package med.voll.api.domain.medicos;

import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRespuestaMedico(
        Long id,
        String nombre,
        String telefono,
        String email,
        String documento,
        String especialidad,
        DatosDireccion datosDireccion) {
}
