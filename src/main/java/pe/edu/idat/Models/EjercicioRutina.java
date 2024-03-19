package pe.edu.idat.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "EjerciciosRutinas")
public class EjercicioRutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEjerRutina")
    private Long idEjerRutina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdRutina", referencedColumnName = "IdRutina", nullable = false)
    private Rutina rutina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdEjercicio", referencedColumnName = "IdEjercicio", nullable = false)
    private Ejercicio ejercicio;

    @Column(name = "Repeticiones", nullable = false)
    private Integer repeticiones;

    @Column(name = "Series", nullable = false)
    private Integer series;

    @Column(name = "TempoDescansoXSeries", nullable = false)
    private Integer tempoDescansoXSeries;

    @Column(name = "DuracionEjercicio", nullable = false)
    private Double duracionEjercicio;

    public EjercicioRutina(Integer repeticiones, Integer series, Integer tempoDescansoXSeries, Double duracionEjercicio) {
        this.repeticiones = repeticiones;
        this.series = series;
        this.tempoDescansoXSeries = tempoDescansoXSeries;
        this.duracionEjercicio = duracionEjercicio;
    }

    public EjercicioRutina(Long idEjerRutina, Integer repeticiones, Integer series, Integer tempoDescansoXSeries, Double duracionEjercicio) {
        this.idEjerRutina = idEjerRutina;
        this.repeticiones = repeticiones;
        this.series = series;
        this.tempoDescansoXSeries = tempoDescansoXSeries;
        this.duracionEjercicio = duracionEjercicio;
    }

    public EjercicioRutina(Long idEjerRutina, Rutina rutina, Ejercicio ejercicio, Integer repeticiones, Integer series, Integer tempoDescansoXSeries, Double duracionEjercicio) {
        this.idEjerRutina = idEjerRutina;
        this.rutina = rutina;
        this.ejercicio = ejercicio;
        this.repeticiones = repeticiones;
        this.series = series;
        this.tempoDescansoXSeries = tempoDescansoXSeries;
        this.duracionEjercicio = duracionEjercicio;
    }
}
