/**
 * La clase Cancion representa una canción con su respectivo identificador, título, artista y precio.
 */
public class Cancion {
    private int id;         // El identificador único de la canción
    private String titulo;  // El título de la canción
    private String artista; // El nombre del artista de la canción
    private double precio;  // El precio de la canción en la moneda especificada

    /**
     * Constructor de la clase Cancion.
     * 
     * @param id      El identificador único de la canción.
     * @param titulo  El título de la canción.
     * @param artista El nombre del artista de la canción.
     * @param precio  El precio de la canción.
     */
    public Cancion(int id, String titulo, String artista, double precio) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.precio = precio;
    }

    /**
     * Obtiene el identificador de la canción.
     * 
     * @return El identificador único de la canción.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador de la canción.
     * 
     * @param id El identificador único de la canción.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el título de la canción.
     * 
     * @return El título de la canción.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título de la canción.
     * 
     * @param titulo El título de la canción.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el nombre del artista de la canción.
     * 
     * @return El nombre del artista de la canción.
     */
    public String getArtista() {
        return artista;
    }

    /**
     * Establece el nombre del artista de la canción.
     * 
     * @param artista El nombre del artista de la canción.
     */
    public void setArtista(String artista) {
        this.artista = artista;
    }

    /**
     * Obtiene el precio de la canción.
     * 
     * @return El precio de la canción.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de la canción.
     * 
     * @param precio El precio de la canción.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}