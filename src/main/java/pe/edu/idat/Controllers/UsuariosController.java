package pe.edu.idat.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.Models.Usuarios;
import pe.edu.idat.Services.UsuarioService;
import pe.edu.idat.Utils.Constantes;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuariosController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public Iterable<Usuarios> getUsers(){
        try {
            return usuarioService.getUsuarios();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{correo}")
    public Optional<Usuarios> getUserById(@PathVariable("correo") String correo){
        try{
            return usuarioService.getUsuarioById(correo);
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @PostMapping
    public ResponseEntity<Object> postUser(@RequestBody Usuarios usuario) {
        try {
            if(usuario == null){
                return ResponseEntity.badRequest().body("El usuario no puede ser nulo");
            }
            Usuarios nuevoUsuario = usuarioService.postUsuario(usuario);
            if(nuevoUsuario != null){
                return ResponseEntity.accepted().body(Constantes.returnMessageAndObject(Constantes.CREATED_USER, nuevoUsuario));
            } else {
                return ResponseEntity.badRequest().body("Error al crear usuario");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Object> putUser(@RequestParam("correo") String correo, @RequestBody Usuarios userUpdate) {
        try {
            if (correo == null || userUpdate == null) {
                return ResponseEntity.badRequest().body("El correo y el cuerpo de la solicitud no pueden ser nulos.");
            }

            Usuarios usuarioActualizado = usuarioService.putUsuario(correo, userUpdate);
            if (usuarioActualizado != null) {
                return ResponseEntity.accepted().body(Constantes.returnMessageAndObject(Constantes.UPDATE_USER, usuarioActualizado));
            } else {
                return ResponseEntity.badRequest().body(Constantes.returnMessage("No se pudo encontrar el usuario para actualizar."));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("Error al actualizar el usuario: " + ex.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUser(@RequestParam("correo") String correo) {
        try {
            Usuarios user = usuarioService.deleteUsuario(correo);
            if (user != null) {
                return ResponseEntity.accepted().body(Constantes.returnMessage(Constantes.DELETE_USER));
            } else {
                return ResponseEntity.badRequest().body(Constantes.returnMessage("Error al Eliminar el Usuario"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("Error al eliminar el usuario: " + ex.getMessage());
        }
    }
}
