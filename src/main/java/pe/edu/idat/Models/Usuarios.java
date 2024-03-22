package pe.edu.idat.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pe.edu.idat.Utils.TipoUsuario;

import java.util.Date;

@Entity
@Table(name = "Usuarios")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Usuarios {

    @Id
    @Column(name = "correo")
    private String correo;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "apellido_usuario")
    private String apellidoUsuario;

    @Column(name = "password")
    private String password;

    @Column(name = "dni")
    private String dni;

    @Column(name = "num_celular")
    private String numCelular;

    @Column(name = "altura")
    private Double altura;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "fecha_registro")
    private String fechaRegistro;

    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario")
    private TipoUsuario tipoUsuario;

    public Usuarios(String correo, String nombreUsuario) {
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
    }

    public Usuarios(String correo,
                    String nombreUsuario,
                    String apellidoUsuario,
                    String password,
                    String dni,
                    String numCelular,
                    String fechaRegistro,
                    String fechaNacimiento,
                    TipoUsuario tipoUsuario
    ) {
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.password = password;
        this.dni = dni;
        this.numCelular = numCelular;
        this.fechaRegistro = fechaRegistro;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuarios(String correo,
                    String nombreUsuario,
                    String apellidoUsuario,
                    String password,
                    String dni,
                    String numCelular,
                    Double altura,
                    Double peso,
                    String fechaRegistro,
                    String fechaNacimiento,
                    TipoUsuario tipoUsuario
    ) {
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.password = password;
        this.dni = dni;
        this.numCelular = numCelular;
        this.altura = altura;
        this.peso = peso;
        this.fechaRegistro = fechaRegistro;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoUsuario = tipoUsuario;
    }
}
