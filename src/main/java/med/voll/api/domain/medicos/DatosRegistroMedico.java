package med.voll.api.domain.medicos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRegistroMedico(
        @NotBlank
        String nombre,
        @NotBlank
        String telefono,
        @NotBlank
        @Email
        String email,
        @NotBlank
        /* Si hubiera dejado el documento o número como string
           podría haber usado el @Pattern(regexp = "\\d{4,6}")
           para delimitar que sólo ingresen un string de 4 a 6
           dígitos.*/
        String documento,
        @NotNull
        Especialidad especialidad,
        @NotNull
        @Valid
        DatosDireccion direccion
) {
}
