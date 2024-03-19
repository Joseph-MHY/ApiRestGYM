package pe.edu.idat.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ConfiguracionEntrenamiento")
public class ConfiguracionEntrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdConfig")
    private Long idConfig;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Correo", referencedColumnName = "correo")
    private Usuarios usuario;

    @Column(name = "DiasEntrenamiento")
    private Integer diasEntrenamiento;

    @Column(name = "DiaInicioEntrenamiento")
    private String diaInicioEntrenamiento;

    public ConfiguracionEntrenamiento(Integer diasEntrenamiento, String diaInicioEntrenamiento) {
        this.diasEntrenamiento = diasEntrenamiento;
        this.diaInicioEntrenamiento = diaInicioEntrenamiento;
    }

    public ConfiguracionEntrenamiento(Long idConfig, Integer diasEntrenamiento, String diaInicioEntrenamiento) {
        this.idConfig = idConfig;
        this.diasEntrenamiento = diasEntrenamiento;
        this.diaInicioEntrenamiento = diaInicioEntrenamiento;
    }

    public ConfiguracionEntrenamiento(Long idConfig, Usuarios usuario, Integer diasEntrenamiento, String diaInicioEntrenamiento) {
        this.idConfig = idConfig;
        this.usuario = usuario;
        this.diasEntrenamiento = diasEntrenamiento;
        this.diaInicioEntrenamiento = diaInicioEntrenamiento;
    }
}
