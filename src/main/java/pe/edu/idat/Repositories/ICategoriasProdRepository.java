package pe.edu.idat.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.Models.CategoriasProducto;

@Repository
public interface ICategoriasProdRepository extends CrudRepository<CategoriasProducto, Long> {
}
