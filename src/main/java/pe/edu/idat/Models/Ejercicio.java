package pe.edu.idat.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Ejercicio")
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEjercicio")
    private Long idEjercicio;

    @Column(name = "NomEjercicio", nullable = false)
    private String nombreEjercicio;

    @Column(name = "DescEjercicio", nullable = false)
    private String descripcionEjercicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCategoEjercicio", referencedColumnName = "IdCategoEjercicio", nullable = false)
    private CategoriaEjercicio categoriaEjercicio;

    @Column(name = "NomImagen", nullable = false)
    private String nombreImagen;

    public Ejercicio(String nombreEjercicio, String descripcionEjercicio, String nombreImagen) {
        this.nombreEjercicio = nombreEjercicio;
        this.descripcionEjercicio = descripcionEjercicio;
        this.nombreImagen = nombreImagen;
    }

    public Ejercicio(Long idEjercicio, String nombreEjercicio, String descripcionEjercicio, String nombreImagen) {
        this.idEjercicio = idEjercicio;
        this.nombreEjercicio = nombreEjercicio;
        this.descripcionEjercicio = descripcionEjercicio;
        this.nombreImagen = nombreImagen;
    }

    public Ejercicio(Long idEjercicio, String nombreEjercicio, String descripcionEjercicio, CategoriaEjercicio categoriaEjercicio, String nombreImagen) {
        this.idEjercicio = idEjercicio;
        this.nombreEjercicio = nombreEjercicio;
        this.descripcionEjercicio = descripcionEjercicio;
        this.categoriaEjercicio = categoriaEjercicio;
        this.nombreImagen = nombreImagen;
    }
}
