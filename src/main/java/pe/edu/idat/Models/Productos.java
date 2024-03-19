package pe.edu.idat.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Productos")
@Getter
@Setter
@NoArgsConstructor
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProducto")
    private Long idProducto;

    @Column(name = "NomProducto", nullable = false)
    private String nomProducto;

    @Column(name = "DescProducto", nullable = false)
    private String descProducto;

    @Column(name = "PrecCosto", nullable = false)
    private double precCosto;

    @Column(name = "PrecVenta", nullable = false)
    private double precVenta;

    @Column(name = "Stock", nullable = false)
    private int stock;

    @ManyToOne
    @JoinColumn(name = "IdCategoria", nullable = false)
    private CategoriasProducto categoriaProducto;

    public Productos(String nomProducto, String descProducto, double precCosto, double precVenta, int stock) {
        this.nomProducto = nomProducto;
        this.descProducto = descProducto;
        this.precCosto = precCosto;
        this.precVenta = precVenta;
        this.stock = stock;
    }

    public Productos(Long idProducto, String nomProducto, String descProducto, double precCosto, double precVenta, int stock) {
        this.idProducto = idProducto;
        this.nomProducto = nomProducto;
        this.descProducto = descProducto;
        this.precCosto = precCosto;
        this.precVenta = precVenta;
        this.stock = stock;
    }

    public Productos(Long idProducto, String nomProducto, String descProducto, double precCosto, double precVenta, int stock, CategoriasProducto categoriaProducto) {
        this.idProducto = idProducto;
        this.nomProducto = nomProducto;
        this.descProducto = descProducto;
        this.precCosto = precCosto;
        this.precVenta = precVenta;
        this.stock = stock;
        this.categoriaProducto = categoriaProducto;
    }
}
