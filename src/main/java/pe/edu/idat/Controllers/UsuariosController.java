package pe.edu.idat.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.Models.Usuarios;
import pe.edu.idat.Services.UsuarioService;
import pe.edu.idat.Utils.Constantes;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/api/usuarios")
public class UsuariosController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuariosController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public UsuariosController() {
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

    @PostMapping("/signup")
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

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody Map<String, String> requestBody) {
        try {
            String correo = requestBody.get("correo");
            String password = requestBody.get("password");

            if (correo == null || password == null) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "El correo y la contraseña no pueden ser nulos."));
            }

            boolean loginExitoso = usuarioService.loginUsuario(correo, password);
            if (loginExitoso) {
                return ResponseEntity.ok().body(Collections.singletonMap("mensaje", "Credenciales correctas"));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("mensaje", "Credenciales inválidas"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "Error al procesar la solicitud de inicio de sesión: " + ex.getMessage()));
        }
    }

    @GetMapping("/email/{correo}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable("correo") String correo) {
        try {
            Optional<Usuarios> usuarioOptional = usuarioService.getUsuarioByEmail(correo);
            if (usuarioOptional.isPresent()) {
                Usuarios usuario = usuarioOptional.get();
                return ResponseEntity.ok().body(usuario);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("Error al obtener el usuario por correo: " + ex.getMessage());
        }
    }

    @PutMapping("/{correo}")
    public ResponseEntity<Object> actualizarPerfil(@PathVariable String correo, @RequestBody Usuarios usuarioModificado) {
        Usuarios usuarioActualizado = usuarioService.actualizarPerfil(correo, usuarioModificado);
        if (usuarioActualizado != null) {
            // Construye el mensaje JSON de éxito
            Map<String, String> response = new HashMap<>();
            response.put("mensajePerfil", "Perfil actualizado correctamente para el correo: " + correo);
            // Devuelve el mensaje JSON con el estado HTTP aceptado (202)
            return ResponseEntity.accepted().body(response);
        } else {
            // Construye el mensaje JSON de error
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensajePerfil", "No se pudo actualizar el perfil para el correo: " + correo);
            // Devuelve el mensaje de error en formato JSON con el estado HTTP no encontrado (404)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PutMapping("/actualizarpassword")
    public ResponseEntity<Object> actualizarContra(@RequestBody Map<String, String> requestBody) {
        String correo = requestBody.get("correo");
        String password = requestBody.get("password");
        String palabraClave = requestBody.get("palabraClave");

        if (correo == null || password == null || palabraClave == null) {
            return ResponseEntity.badRequest().body("El correo, la contraseña y la palabra clave no pueden ser nulos.");
        }

        // Llamar al servicio para obtener el usuario por correo
        Optional<Usuarios> usuarioOptional = usuarioService.getUsuarioByEmail(correo);
        if (usuarioOptional.isPresent()) {
            Usuarios usuario = usuarioOptional.get();

            // Verificar si la palabra clave coincide con la registrada para el usuario
            if (palabraClave.equals(usuario.getPalabraClave())) {
                // Actualizar la contraseña
                usuario.setPassword(password);

                // Guardar los cambios en la base de datos
                Usuarios usuarioActualizado = usuarioService.actualizarpass(correo, password);

                if (usuarioActualizado != null) {
                    // Construir el mensaje JSON de éxito
                    Map<String, String> response = new HashMap<>();
                    response.put("mensaje", "Contraseña actualizada correctamente");
                    // Devolver el mensaje JSON con el estado HTTP aceptado (202)
                    return ResponseEntity.accepted().body(response);
                } else {
                    // Construir el mensaje JSON de error
                    Map<String, String> errorResponse = Collections.singletonMap("mensaje", "No se pudo actualizar la contraseña");
                    // Devolver el mensaje de error en formato JSON con el estado HTTP interno del servidor (500)
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
                }
            } else {
                // La palabra clave no coincide con la registrada para el usuario
                return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "La palabra clave no coincide con la registrada para el usuario."));
            }
        } else {
            // No se encontró ningún usuario con el correo dado
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "Correo no registrado"));
        }
    }

}
