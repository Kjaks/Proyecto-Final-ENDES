import java.util.List;

/**
 * La clase CancionService proporciona métodos para interactuar con los datos de las canciones
 * y los usuarios, y para realizar operaciones de negocio relacionadas con la compra de canciones.
 */
public class CancionService {
    private CancionDAO cancionDAO;
    private UsuarioDAO usuarioDAO;

    /**
     * Constructor de la clase CancionService.
     * Inicializa las instancias de CancionDAO y UsuarioDAO.
     */
    public CancionService() {
        cancionDAO = new CancionDAO();
        usuarioDAO = new UsuarioDAO();
    }

    /**
     * Busca canciones basadas en un criterio de búsqueda.
     *
     * @param criterio El criterio de búsqueda para el título o el artista de la canción.
     * @return Una lista de canciones que coinciden con el criterio de búsqueda.
     */
    public List<Cancion> buscarCanciones(String criterio) {
        return cancionDAO.buscar(criterio);
    }

    /**
     * Realiza la compra de una canción por parte de un usuario.
     * Verifica si el usuario tiene créditos suficientes antes de realizar la compra.
     *
     * @param usuario El usuario que compra la canción.
     * @param cancion La canción que se desea comprar.
     */
    public void comprarCancion(Usuario usuario, Cancion cancion) {
        int usuarioId = usuario.getId();
        int cancionId = cancion.getId();
    
        if (usuario.getCreditos() >= cancion.getPrecio()) {
            usuario.setCreditos(usuario.getCreditos() - cancion.getPrecio());
            usuarioDAO.actualizar(usuario);
            cancionDAO.registrarCompra(usuarioId, cancionId);
        } else {
            System.out.println("Créditos insuficientes.");
        }
    }

    /**
     * Obtiene todas las canciones almacenadas en la base de datos.
     *
     * @return Una lista de todas las canciones almacenadas en la base de datos.
     */
    public List<Cancion> obtenerTodasCanciones() {
        return cancionDAO.obtenerTodas();
    }
}
