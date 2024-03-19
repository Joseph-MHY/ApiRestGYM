package pe.edu.idat.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CategoriasProd")
@Data
public class CategoriasProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCategoria")
    private Long idCategoria;

    @Column(name = "NomCategoria", nullable = false)
    private String nomCategoria;
}
