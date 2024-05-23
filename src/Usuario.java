/**
 * La clase Usuario representa a un usuario con un identificador único, nombre de usuario, contraseña y créditos.
 */
public class Usuario {
    private int id;
    private String nombreUsuario;
    private String contrasena;
    private double creditos;

    /**
     * Crea una nueva instancia de la clase Usuario con los parámetros especificados.
     *
     * @param id el identificador único del usuario
     * @param nombreUsuario el nombre de usuario
     * @param contrasena la contraseña del usuario
     * @param creditos la cantidad de créditos del usuario
     */
    public Usuario(int id, String nombreUsuario, String contrasena, double creditos) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.creditos = creditos;
    }

    public Usuario() {
        //TODO Auto-generated constructor stub
    }

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return el identificador del usuario
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * @param id el nuevo identificador del usuario
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return el nombre de usuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param nombreUsuario el nuevo nombre de usuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasena la nueva contraseña del usuario
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtiene la cantidad de créditos del usuario.
     *
     * @return los créditos del usuario
     */
    public double getCreditos() {
        return creditos;
    }

    /**
     * Establece la cantidad de créditos del usuario.
     *
     * @param creditos los nuevos créditos del usuario
     */
    public void setCreditos(double creditos) {
        this.creditos = creditos;
    }
}
