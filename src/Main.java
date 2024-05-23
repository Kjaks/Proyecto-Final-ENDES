import java.util.List;
import java.util.Scanner;

/**
 * Clase principal para la aplicación de gestión de usuarios y canciones.
 * Permite registrar, autenticar, agregar créditos, ver información de usuario,
 * ver canciones disponibles y comprar canciones.
 */
public class Main {
    private static UsuarioService usuarioService = new UsuarioService();
    private static CancionService cancionService = new CancionService();
    private static Usuario usuarioActual;
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Método principal que ejecuta el menú principal y maneja las opciones del usuario.
     * 
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    registrar();
                    break;
                case 2:
                    autenticar();
                    break;
                case 3:
                    agregarCreditos();
                    break;
                case 4:
                    verInformacionUsuario();
                    break;
                case 5:
                    verCancionesDisponibles();
                    break;
                case 6:
                    comprarCancion();
                    break;
                case 7:
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente de nuevo.");
            }
        }
    }

    /**
     * Muestra el menú principal de opciones al usuario.
     */
    private static void mostrarMenu() {
        System.out.println("\n=== Menú Principal ===");
        if (usuarioActual != null) {
            System.out.println("Usuario: " + usuarioActual.getNombreUsuario() + " - Créditos: " + usuarioActual.getCreditos());
        }
        System.out.println("1. Registrarse");
        System.out.println("2. Autenticar");
        System.out.println("3. Agregar Créditos");
        System.out.println("4. Ver Información de Usuario");
        System.out.println("5. Ver Canciones Disponibles");
        System.out.println("6. Comprar Canción");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Registra un nuevo usuario solicitando el nombre de usuario y la contraseña.
     */
    private static void registrar() {
        System.out.print("Ingrese nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();
        double creditosIniciales = 0.0; // Créditos iniciales para un nuevo usuario

        usuarioService.registrarUsuario(nombreUsuario, contrasena, creditosIniciales);
        System.out.println("Usuario registrado exitosamente.");
    }

    /**
     * Autentica a un usuario solicitando el nombre de usuario y la contraseña.
     * Actualiza la variable usuarioActual si la autenticación es exitosa.
     */
    public static void autenticar() {
        System.out.print("Ingrese nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();

        usuarioActual = usuarioService.autenticarUsuario(nombreUsuario, contrasena);
        if (usuarioActual != null) {
            System.out.println("Autenticación exitosa.");
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    /**
     * Agrega créditos a la cuenta del usuario autenticado.
     */
    private static void agregarCreditos() {
        if (usuarioActual == null) {
            System.out.println("Debe autenticar primero.");
            return;
        }

        System.out.print("Ingrese la cantidad de dinero a agregar: ");
        double cantidad = Double.parseDouble(scanner.nextLine());

        usuarioService.agregarCreditos(usuarioActual.getId(), cantidad);
        usuarioActual.setCreditos(usuarioActual.getCreditos() + cantidad); // Actualizar los créditos del usuario actual en memoria
        System.out.println("Créditos agregados exitosamente.");
    }

    /**
     * Muestra la información del usuario autenticado, incluyendo los créditos disponibles
     * y las canciones compradas.
     */
    private static void verInformacionUsuario() {
        if (usuarioActual == null) {
            System.out.println("Debe autenticar primero.");
            return;
        }

        System.out.println("\n=== Información del Usuario ===");
        System.out.println("Nombre de Usuario: " + usuarioActual.getNombreUsuario());
        System.out.println("Créditos Disponibles: " + usuarioActual.getCreditos());

        List<Cancion> cancionesCompradas = usuarioService.obtenerCancionesCompradas(usuarioActual.getId());
        if (cancionesCompradas.isEmpty()) {
            System.out.println("No has comprado ninguna canción.");
        } else {
            System.out.println("Canciones Compradas:");
            for (Cancion cancion : cancionesCompradas) {
                System.out.println("- " + cancion.getTitulo() + " - " + cancion.getArtista());
            }
        }
    }

    /**
     * Muestra una lista de todas las canciones disponibles para comprar.
     */
    private static void verCancionesDisponibles() {
        System.out.println("\n=== Canciones Disponibles ===");
        List<Cancion> canciones = cancionService.obtenerTodasCanciones();
        if (canciones.isEmpty()) {
            System.out.println("No hay canciones disponibles en este momento.");
        } else {
            for (int i = 0; i < canciones.size(); i++) {
                Cancion cancion = canciones.get(i);
                System.out.printf("%d. %s - %s ($%.2f)\n", i + 1, cancion.getTitulo(), cancion.getArtista(), cancion.getPrecio());
            }
        }
    }

    /**
     * Permite al usuario autenticado comprar una canción seleccionada de la lista de canciones disponibles.
     */
    private static void comprarCancion() {
        if (usuarioActual == null) {
            System.out.println("Debe autenticar primero.");
            return;
        }

        System.out.println("\n=== Comprar Canción ===");
        System.out.print("Ingrese el número de la canción que desea comprar (0 para cancelar): ");
        int seleccion = Integer.parseInt(scanner.nextLine());

        if (seleccion == 0) {
            System.out.println("Compra cancelada.");
            return;
        }

        List<Cancion> canciones = cancionService.obtenerTodasCanciones();
        if (seleccion > 0 && seleccion <= canciones.size()) {
            Cancion cancionSeleccionada = canciones.get(seleccion - 1);
            if (usuarioActual.getCreditos() >= cancionSeleccionada.getPrecio()) {
                cancionService.comprarCancion(usuarioActual, cancionSeleccionada);
                System.out.println("Canción comprada exitosamente.");
            } else {
                System.out.println("Créditos insuficientes.");
            }
        } else {
            System.out.println("Número de canción inválido.");
        }
    }
}
