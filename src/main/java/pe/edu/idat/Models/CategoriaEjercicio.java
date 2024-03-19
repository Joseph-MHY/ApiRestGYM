package pe.edu.idat.Models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "CategoriasEjercicio")
public class CategoriaEjercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCategoEjercicio")
    private Long idCategoEjercicio;

    @Column(name = "NomCategoEjercicio", nullable = false)
    private String nomCategoEjercicio;

}
