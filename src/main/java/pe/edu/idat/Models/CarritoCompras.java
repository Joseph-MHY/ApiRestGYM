package pe.edu.idat.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CarritoCompras")
@Getter
@Setter
@NoArgsConstructor
public class CarritoCompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProdCarrito")
    private Long idProdCarrito;

    @Column(name = "Correo", nullable = false)
    private String correo;

    @Column(name = "FechaCreacion", nullable = false)
    private String fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "Correo", referencedColumnName = "correo", insertable = false, updatable = false)
    private Usuarios usuario;

    public CarritoCompras(String correo, String fechaCreacion) {
        this.correo = correo;
        this.fechaCreacion = fechaCreacion;
    }

    public CarritoCompras(Long idProdCarrito, String correo, String fechaCreacion) {
        this.idProdCarrito = idProdCarrito;
        this.correo = correo;
        this.fechaCreacion = fechaCreacion;
    }

    public CarritoCompras(Long idProdCarrito, String correo, String fechaCreacion, Usuarios usuario) {
        this.idProdCarrito = idProdCarrito;
        this.correo = correo;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
    }
}
