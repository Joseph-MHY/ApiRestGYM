package pe.edu.idat.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.idat.Models.Usuarios;
import pe.edu.idat.Repositories.IUsuarioRepository;
import pe.edu.idat.Utils.TipoUsuario;

import java.util.Optional;

@Slf4j
@Service
public class UsuarioService {

    public UsuarioService() {
    }

    private IUsuarioRepository IUsuarioRepository;

    @Autowired
    public UsuarioService(IUsuarioRepository IUsuarioRepository) {
        this.IUsuarioRepository = IUsuarioRepository;
    }

    public Iterable<Usuarios> getUsuarios(){
        return IUsuarioRepository.findAll();
    }

    public Optional<Usuarios> getUsuarioById(String correo){
        return IUsuarioRepository.findById(correo);
    }

    public Usuarios postUsuario(Usuarios usuario){
        if (usuario != null ) {
            usuario.setTipoUsuario(TipoUsuario.Usuario);
            return IUsuarioRepository.save(usuario);
        } else {
            throw new IllegalArgumentException("Tipo de usuario no válido: " + usuario.getTipoUsuario());
        }
    }

    public Usuarios postAdmin(Usuarios usuario){
        if (usuario != null ) {
            return IUsuarioRepository.save(usuario);
        } else {
            throw new IllegalArgumentException("Tipo de usuario no válido: " + usuario.getTipoUsuario());
        }
    }

    public Usuarios putUsuario(String correo, Usuarios usuarioActualizado) {
        Optional<Usuarios> usuarioOptional = IUsuarioRepository.findById(correo);
        return usuarioOptional.map(usuario -> {
            usuario.setNombreUsuario(usuarioActualizado.getNombreUsuario());
            usuario.setApellidoUsuario(usuarioActualizado.getApellidoUsuario());
            usuario.setPassword(usuarioActualizado.getPassword());
            usuario.setDni(usuarioActualizado.getDni());
            usuario.setNumCelular(usuarioActualizado.getNumCelular());
            usuario.setAltura(usuarioActualizado.getAltura());
            usuario.setPeso(usuarioActualizado.getPeso());
            usuario.setFechaRegistro(usuarioActualizado.getFechaRegistro());
            usuario.setFechaNacimiento(usuarioActualizado.getFechaNacimiento());
            usuario.setTipoUsuario(TipoUsuario.Usuario);
            log.info("Datos de Usuario actualizado: {}", usuario);
            return IUsuarioRepository.save(usuario);
        }).orElse(null);
    }


    public Usuarios deleteUsuario(String correo) {
        Optional<Usuarios> usuariosOptional = IUsuarioRepository.findById(correo);
        if (usuariosOptional.isPresent()) {
            Usuarios usuario = usuariosOptional.get();
            IUsuarioRepository.delete(usuario);
            return usuario;
        } else {
            log.info("El usuario no se puede eliminar porque no existe.");
            return null;
        }
    }
}
