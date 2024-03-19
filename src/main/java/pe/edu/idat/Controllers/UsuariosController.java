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
    public ResponseEntity<Usuarios> postUser(@RequestBody Usuarios usuario) {
        try {
            Usuarios nuevoUsuario = usuarioService.postUsuario(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{correo}")
    public ResponseEntity<Object> putUser(@PathVariable("correo") String correo, @RequestBody Usuarios userUpdate){
        try {
            Usuarios usuarioActualizado = usuarioService.putUsuario(correo, userUpdate);
            return ResponseEntity.accepted().body(Constantes.returnMessageAndObject(Constantes.UPDATE_USER, usuarioActualizado));
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("Error al actualizar el usuario: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{correo}")
    public ResponseEntity<Object> deleteUser(@PathVariable("correo") String correo){
        try{
            Usuarios usuerioEliminado = usuarioService.deleteUsuario(correo);
            return ResponseEntity.accepted().body(Constantes.returnMessage(Constantes.DELETE_USER));
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("Error al eliminar el usuario: " + ex.getMessage());
        }
    }
}
