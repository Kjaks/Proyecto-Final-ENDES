import java.util.List;

/**
 * The UsuarioService class provides various services for managing users and their interactions
 * with songs, including user registration, authentication, credit management, and song purchases.
 */
public class UsuarioService {
    private UsuarioDAO usuarioDAO;
    private CancionDAO cancionDAO;

    /**
     * Constructs a new UsuarioService and initializes the UsuarioDAO and CancionDAO.
     */
    public UsuarioService() {
        usuarioDAO = new UsuarioDAO();
        cancionDAO = new CancionDAO();
    }

    /**
     * Registers a new user with the provided username, password, and credits.
     *
     * @param nombreUsuario the username of the new user
     * @param contrasena the password of the new user
     * @param creditos the initial credits of the new user
     */
    public void registrarUsuario(String nombreUsuario, String contrasena, double creditos) {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContrasena(contrasena);
        usuario.setCreditos(creditos);
        usuarioDAO.registrar(usuario);
    }

    /**
     * Authenticates a user with the given username and password.
     *
     * @param nombreUsuario the username of the user
     * @param contrasena the password of the user
     * @return the authenticated user, or null if authentication fails
     */
    public Usuario autenticarUsuario(String nombreUsuario, String contrasena) {
        return usuarioDAO.autenticar(nombreUsuario, contrasena);
    }

    /**
     * Adds credits to a user's account.
     *
     * @param usuarioId the ID of the user
     * @param cantidad the amount of credits to add
     */
    public void agregarCreditos(int usuarioId, double cantidad) {
        Usuario usuario = usuarioDAO.obtenerPorId(usuarioId);
        if (usuario != null) {
            usuario.setCreditos(usuario.getCreditos() + cantidad);
            usuarioDAO.actualizar(usuario);
        } else {
            System.out.println("Usuario no encontrado");
        }
    }

    /**
     * Retrieves a list of songs purchased by a user.
     *
     * @param usuarioId the ID of the user
     * @return a list of songs purchased by the user
     */
    public List<Cancion> obtenerCancionesCompradas(int usuarioId) {
        return cancionDAO.obtenerCancionesCompradasPorUsuario(usuarioId);
    }

    /**
     * Allows a user to purchase a song if they have sufficient credits.
     *
     * @param usuarioId the ID of the user
     * @param cancionId the ID of the song
     */
    public void comprarCancion(int usuarioId, int cancionId) {
        Usuario usuario = usuarioDAO.obtenerPorId(usuarioId);
        Cancion cancion = cancionDAO.obtenerPorId(cancionId);

        if (usuario != null && cancion != null) {
            if (usuario.getCreditos() >= cancion.getPrecio()) {
                usuario.setCreditos(usuario.getCreditos() - cancion.getPrecio());
                usuarioDAO.actualizar(usuario);
                cancionDAO.registrarCompra(usuarioId, cancionId);
                System.out.println("Canción comprada exitosamente.");
            } else {
                System.out.println("Créditos insuficientes.");
            }
        } else {
            System.out.println("Usuario o canción no encontrados.");
        }
    }
}
