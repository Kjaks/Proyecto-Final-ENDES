import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase CancionDAO proporciona métodos para interactuar con la base de datos
 * y realizar operaciones relacionadas con las canciones.
 */
public class CancionDAO {
    private final String url = "jdbc:mysql://localhost:3306/appUsers";
    private final String user = "root";
    private final String password = "root";

    /**
     * Establece una conexión a la base de datos.
     *
     * @return Una conexión a la base de datos.
     * @throws SQLException Si ocurre un error al conectarse a la base de datos.
     */
    private Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Driver not found");
        }
        return con;
    }

    /**
     * Busca canciones en la base de datos basadas en un criterio de búsqueda.
     *
     * @param criterio El criterio de búsqueda para el título o el artista de la canción.
     * @return Una lista de canciones que coinciden con el criterio de búsqueda.
     */
    public List<Cancion> buscar(String criterio) {
        List<Cancion> canciones = new ArrayList<>();
        try {
            String query = "SELECT * FROM canciones WHERE titulo LIKE ? OR artista LIKE ?";
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, "%" + criterio + "%");
                statement.setString(2, "%" + criterio + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        canciones.add(new Cancion(
                                resultSet.getInt("id"),
                                resultSet.getString("titulo"),
                                resultSet.getString("artista"),
                                resultSet.getDouble("precio")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return canciones;
    }

    /**
     * Registra la compra de una canción por parte de un usuario en la base de datos.
     *
     * @param usuarioId El identificador del usuario que compra la canción.
     * @param cancionId El identificador de la canción que se compra.
     */
    public void registrarCompra(int usuarioId, int cancionId) {
        String query = "INSERT INTO compras (usuarioId, cancionId) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, usuarioId);
            statement.setInt(2, cancionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene todas las canciones almacenadas en la base de datos.
     *
     * @return Una lista de todas las canciones almacenadas en la base de datos.
     */
    public List<Cancion> obtenerTodas() {
        List<Cancion> canciones = new ArrayList<>();
        String query = "SELECT * FROM canciones";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                canciones.add(new Cancion(
                        resultSet.getInt("id"),
                        resultSet.getString("titulo"),
                        resultSet.getString("artista"),
                        resultSet.getDouble("precio")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return canciones;
    }

    /**
     * Obtiene una canción de la base de datos según su ID.
     *
     * @param cancionId El ID de la canción que se desea obtener.
     * @return La canción con el ID especificado, o null si no se encuentra.
     */
    public Cancion obtenerPorId(int cancionId) {
        Cancion cancion = null;
        String query = "SELECT * FROM canciones WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cancionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cancion = new Cancion(
                            resultSet.getInt("id"),
                            resultSet.getString("titulo"),
                            resultSet.getString("artista"),
                            resultSet.getDouble("precio")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cancion;
    }

    /**
     * Obtiene todas las canciones compradas por un usuario específico.
     *
     * @param usuarioId El ID del usuario del que se desean obtener las canciones compradas.
     * @return Una lista de canciones compradas por el usuario especificado.
     */
    public List<Cancion> obtenerCancionesCompradasPorUsuario(int usuarioId) {
        List<Cancion> cancionesCompradas = new ArrayList<>();
        String query = "SELECT * FROM compras JOIN canciones ON compras.cancionId = canciones.id WHERE compras.usuarioId = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, usuarioId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Cancion cancion = new Cancion(
                    resultSet.getInt("id"),
                    resultSet.getString("titulo"),
                    resultSet.getString("artista"),
                    resultSet.getDouble("precio")
                );
                cancionesCompradas.add(cancion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cancionesCompradas;
    }
}
