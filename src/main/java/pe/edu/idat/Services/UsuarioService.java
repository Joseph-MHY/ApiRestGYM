package pe.edu.idat.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.idat.Models.Usuarios;
import pe.edu.idat.Repositories.IUsuarioRepository;
import pe.edu.idat.Utils.TipoUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



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

    public Usuarios putUsuario(String correo, Usuarios usuarioModificado) {
        Optional<Usuarios> usuarioOptional = IUsuarioRepository.findById(correo);
        return usuarioOptional.map(usuario -> {
            Usuarios usuarioActualizado = new Usuarios(
                    usuarioModificado.getCorreo(),
                    usuarioModificado.getNombreUsuario(),
                    usuarioModificado.getApellidoUsuario(),
                    usuarioModificado.getPassword(),
                    usuarioModificado.getPalabraClave(),
                    usuarioModificado.getDni(),
                    usuarioModificado.getNumCelular(),
                    usuarioModificado.getAltura(),
                    usuarioModificado.getPeso(),
                    usuarioModificado.getFechaRegistro(),
                    usuarioModificado.getFechaNacimiento(),
                    TipoUsuario.Usuario
            );
            return IUsuarioRepository.save(usuarioActualizado);
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

    public boolean loginUsuario(String correo, String password) {
        Optional<Usuarios> usuarioOptional = IUsuarioRepository.findById(correo);
        if (usuarioOptional.isPresent()) {
            Usuarios usuario = usuarioOptional.get();
            // Verificar si la contraseña coincide
            return usuario.getPassword().equals(password);
        }
        return false; // Si el usuario no existe
    }

    public Optional<Usuarios> getUsuarioByEmail(String correo) {
        return IUsuarioRepository.findByCorreo(correo);
    }
    public Usuarios actualizarPerfil(String correo, Usuarios usuarioModificado) {
        Optional<Usuarios> usuarioOptional = IUsuarioRepository.findById(correo);
        return usuarioOptional.map(usuario -> {
            // Actualizar los campos del usuario
            usuario.setNombreUsuario(usuarioModificado.getNombreUsuario());
            usuario.setApellidoUsuario(usuarioModificado.getApellidoUsuario());
            usuario.setDni(usuarioModificado.getDni());
            usuario.setNumCelular(usuarioModificado.getNumCelular());
            usuario.setAltura(usuarioModificado.getAltura());
            usuario.setPeso(usuarioModificado.getPeso());
            usuario.setFechaNacimiento(usuarioModificado.getFechaNacimiento());
            // Guardar los cambios en la base de datos
            return IUsuarioRepository.save(usuario);
        }).orElse(null);
    }

    public ResponseEntity<Object> actualizarPassword(String correo, String nuevaPassword, String palabraClave) {
        // Obtener el usuario por su correo electrónico
        Optional<Usuarios> usuarioOptional = IUsuarioRepository.findById(correo);
        if (usuarioOptional.isPresent()) {
            Usuarios usuario = usuarioOptional.get();

            // Validar la palabra clave antes de actualizar la contraseña
            if (!usuario.getPalabraClave().equals(palabraClave)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"mensaje\": \"La palabra clave proporcionada no coincide con la del usuario.\"}");
            }

            // Actualizar la contraseña
            usuario.setPassword(nuevaPassword);
            IUsuarioRepository.save(usuario);
            return ResponseEntity.accepted().body("{\"mensaje\": \"Contraseña actualizada correctamente.\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mensaje\": \"No se encontró ningún usuario con el correo electrónico proporcionado.\"}");
        }
    }




}
