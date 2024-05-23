import java.sql.*;

/**
 * The UsuarioDAO class provides CRUD operations for the Usuario objects in a MySQL database.
 */
public class UsuarioDAO {
    private final String url = "jdbc:mysql://localhost:3306/appUsers";
    private final String user = "root";
    private final String password = "root";

    /**
     * Establishes a connection to the MySQL database.
     *
     * @return A Connection object to the database.
     * @throws SQLException If a database access error occurs or the driver is not found.
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
     * Registers a new user in the database.
     *
     * @param usuario The Usuario object containing the user's information.
     */
    public void registrar(Usuario usuario) {
        String query = "INSERT INTO usuarios (nombreUsuario, contrasena, creditos) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usuario.getNombreUsuario());
            statement.setString(2, usuario.getContrasena());
            statement.setDouble(3, usuario.getCreditos());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Authenticates a user based on the provided username and password.
     *
     * @param nombreUsuario The username of the user.
     * @param contrasena The password of the user.
     * @return A Usuario object if the credentials are correct, null otherwise.
     */
    public Usuario autenticar(String nombreUsuario, String contrasena) {
        String query = "SELECT * FROM usuarios WHERE nombreUsuario = ? AND contrasena = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombreUsuario);
            statement.setString(2, contrasena);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Usuario(
                        resultSet.getInt("id"),
                        resultSet.getString("nombreUsuario"),
                        resultSet.getString("contrasena"),
                        resultSet.getDouble("creditos")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates the user's credits in the database.
     *
     * @param usuario The Usuario object containing the updated user's information.
     */
    public void actualizar(Usuario usuario) {
        String query = "UPDATE usuarios SET creditos = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, usuario.getCreditos());
            statement.setInt(2, usuario.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param usuarioId The ID of the user to retrieve.
     * @return A Usuario object if found, null otherwise.
     */
    public Usuario obtenerPorId(int usuarioId) {
        Usuario usuario = null;
        String query = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, usuarioId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = new Usuario(
                        resultSet.getInt("id"),
                        resultSet.getString("nombreUsuario"),
                        resultSet.getString("contrasena"),
                        resultSet.getDouble("creditos")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
