package pe.edu.idat.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.Models.Usuarios;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuarios, String> {
}
