package pe.edu.idat.Models;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Rutina")
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdRutina")
    private Long idRutina;

    @Column(name = "Correo", nullable = false)
    private String correo;

    @Column(name = "NombreRutina", nullable = false)
    private String nombreRutina;

    @Column(name = "DescRutina", nullable = false)
    private String descRutina;

    @Column(name = "NomImagen", nullable = false)
    private String nomImagen;

    @ManyToOne
    @JoinColumn(name = "Correo", referencedColumnName = "correo", insertable = false, updatable = false)
    private Usuarios usuario;

    public Rutina(String correo, String nombreRutina, String descRutina, String nomImagen) {
        this.correo = correo;
        this.nombreRutina = nombreRutina;
        this.descRutina = descRutina;
        this.nomImagen = nomImagen;
    }

    public Rutina(Long idRutina, String correo, String nombreRutina, String descRutina, String nomImagen) {
        this.idRutina = idRutina;
        this.correo = correo;
        this.nombreRutina = nombreRutina;
        this.descRutina = descRutina;
        this.nomImagen = nomImagen;
    }

    public Rutina(Long idRutina, String correo, String nombreRutina, String descRutina, String nomImagen, Usuarios usuario) {
        this.idRutina = idRutina;
        this.correo = correo;
        this.nombreRutina = nombreRutina;
        this.descRutina = descRutina;
        this.nomImagen = nomImagen;
        this.usuario = usuario;
    }
}
