package pe.edu.idat.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.idat.Models.Usuarios;
import pe.edu.idat.Repositories.UsuarioRepository;
import pe.edu.idat.Utils.TipoUsuario;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Iterable<Usuarios> getUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuarios> getUsuarioById(String correo){
        return usuarioRepository.findById(correo);
    }

    public Usuarios postUsuario(Usuarios usuario){
        if (usuario != null ) {
            usuario.setTipoUsuario(TipoUsuario.Usuario);
            return usuarioRepository.save(usuario);
        } else {
            throw new IllegalArgumentException("Tipo de usuario no válido: " + usuario.getTipoUsuario());
        }
    }

    public Usuarios postAdmin(Usuarios usuario){
        if (usuario != null ) {
            return usuarioRepository.save(usuario);
        } else {
            throw new IllegalArgumentException("Tipo de usuario no válido: " + usuario.getTipoUsuario());
        }
    }

    public Usuarios putUsuario(String correo, Usuarios usuarioActualizado) {
        Optional<Usuarios> usuarioOptional = usuarioRepository.findById(correo);
        if (usuarioOptional.isPresent()) {
            Usuarios usuario = usuarioOptional.get();
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
            log.info("Datos de Usuario actualizado: ", usuario.toString());
            return usuarioRepository.save(usuario);
        } else {
            log.info("El usuario no se actualizo correctamente");
            return null;
        }
    }

    public Usuarios deleteUsuario(String correo) {
        Optional<Usuarios> usuariosOptional = usuarioRepository.findById(correo);
        if (usuariosOptional.isPresent()) {
            Usuarios usuario = usuariosOptional.get();
            usuarioRepository.delete(usuario);
            return usuario;
        } else {
            log.info("El usuario no se puede eliminar porque no existe.");
            return null;
        }
    }
}
