package pe.edu.idat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pe.edu.idat.Models.Usuarios;
import pe.edu.idat.Utils.TipoUsuario;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApigymApplicationTests {

	@Test
	public void testTipoUsuario() {
		Usuarios user = new Usuarios();
		user.setTipoUsuario(TipoUsuario.Usuario);
		assertEquals("Usuario", user.getTipoUsuario().toString());
	}

}
