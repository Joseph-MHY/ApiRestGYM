package pe.edu.idat.TestUsuarios;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pe.edu.idat.Controllers.UsuariosController;
import pe.edu.idat.Models.Usuarios;
import pe.edu.idat.Services.UsuarioService;
import pe.edu.idat.Utils.Constantes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuariosControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuariosController usuariosController;

    @Test
    void getUsers() {
        // Arrange
        List<Usuarios> usuariosList = Arrays.asList(new Usuarios("correo1", "usuario1"), new Usuarios("correo2", "usuario2"));
        when(usuarioService.getUsuarios()).thenReturn(usuariosList);

        // Act
        Iterable<Usuarios> result = usuariosController.getUsers();

        // Assert
        assertEquals(usuariosList, result);
        verify(usuarioService, times(1)).getUsuarios();
    }

    @Test
    void getUserById() {
        // Arrange
        Usuarios usuario = new Usuarios("correo1", "usuario1");
        when(usuarioService.getUsuarioById("correo1")).thenReturn(Optional.of(usuario));

        // Act
        Optional<Usuarios> result = usuariosController.getUserById("correo1");

        // Assert
        assertEquals(Optional.of(usuario), result);
        verify(usuarioService, times(1)).getUsuarioById("correo1");
    }

    @Test
    void postUser() {
        // Arrange
        Usuarios usuario = new Usuarios("correo1", "usuario1");
        when(usuarioService.postUsuario(any())).thenReturn(usuario);

        // Act
        ResponseEntity<Object> response = usuariosController.postUser(usuario);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(Constantes.returnMessageAndObject(Constantes.CREATED_USER, usuario), response.getBody());
        verify(usuarioService, times(1)).postUsuario(any());
    }

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    void putUser() {
        // Arrange
        Usuarios usuario = new Usuarios("correo1", "usuario1");
        when(usuarioService.putUsuario("correo1", usuario)).thenReturn(usuario);

        // Act
        ResponseEntity<Object> response = usuariosController.putUser("correo1", usuario);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(Constantes.returnMessageAndObject(Constantes.UPDATE_USER, usuario), response.getBody());
        verify(usuarioService, times(1)).putUsuario("correo1", usuario);
    }


    @Test
    void deleteUser() {
        // Arrange
        when(usuarioService.deleteUsuario("correo1")).thenReturn(new Usuarios("correo1", "usuario1"));

        // Act
        ResponseEntity<Object> response = usuariosController.deleteUser("correo1");

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(usuarioService, times(1)).deleteUsuario("correo1");
    }
}
