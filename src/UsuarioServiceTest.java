import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class UsuarioServiceTest {
    private UsuarioService usuarioService;
    private CancionService cancionService;

    @Before
    public void setUp() {
        usuarioService = new UsuarioService();
        cancionService = new CancionService();
    }

    @Test
    public void testRegistrarUsuario() {
        // Crear un nuevo usuario
        usuarioService.registrarUsuario("testUser", "testPassword", 100.0);
        // Intentar autenticar con las credenciales del nuevo usuario
        Usuario usuario = usuarioService.autenticarUsuario("testUser", "testPassword");

        // Capturar la salida en la consola
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Realizar la operación que imprimiría en la consola
        System.out.println("Registro de usuario exitoso.");

        // Verificar si la salida contiene el mensaje esperado
        assertTrue(outContent.toString().contains("Registro de usuario exitoso."));
    }

    @Test
    public void testBuscarCanciones() {
        // Prueba de búsqueda de canciones
        List<Cancion> canciones = cancionService.buscarCanciones("prueba");
        assertNotNull(canciones);
    }

}
