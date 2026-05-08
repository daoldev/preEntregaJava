import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> articulos = new ArrayList<>();
                int opcion;
        do {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║Bienvenido al sistema de gestión - TECHLAB      ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║ 1. Agregar articulo                            ║");
            System.out.println("║ 2. Listar articulos                            ║");
            System.out.println("║ 3. Consultar un articulo                       ║");
            System.out.println("║ 4. Modificar un articulo                       ║");
            System.out.println("║ 5. Eliminar un articulo                        ║");
            System.out.println("║ 0. Salir                                       ║");    
            System.out.println("╚════════════════════════════════════════════════╝");

            opcion = leerEntero(scanner, "Elija una opción:");

            switch (opcion) {
                case 1:
                    ingresarArticulo(scanner, articulos);
                    break;
                case 2:
                    listarArticulos(articulos);
                    break;
                case 3:
                    consultarArticulo(scanner, articulos);
                    break;
                case 4:
                    modificarArticulo(scanner, articulos);
                    break;
                case 5:
                    eliminarArticulo(scanner, articulos);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        } while (opcion != 0);
        scanner.close();

    }
    public static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Valor no válido. Por favor, ingrese un número entero válido.");
            }
        }
    }

    public static void ingresarArticulo(Scanner s, ArrayList<String>listaArticulos) {
        System.out.println("INGRESAR ARTICULO:");
        String descripcion = leerTextoNoVacio(s, "Ingrese la Descripcion del articulo:");
        if (existeArticulo(listaArticulos, descripcion)) {
            System.out.println("El articulo ya existe. No se puede agregar.");
            return;
        }
        listaArticulos.add(descripcion);
        System.out.println("Articulo agregado correctamente.");
    }

    public static void listarArticulos(ArrayList<String> articulos) {
        if (articulos.isEmpty()) {
            System.out.println("No hay articulos registrados.");
        } else {
            System.out.println("Lista de articulos:");
            for (int i = 0; i < articulos.size(); i++) {
                System.out.println((i + 1) + ". " + articulos.get(i));
            }
        }
    }

    public static void consultarArticulo(Scanner scanner, ArrayList<String> articulos) {
        int index = leerEntero(scanner, "Ingrese el número del articulo a consultar: ") - 1;
        if (index >= 0 && index < articulos.size()) {
            System.out.println("Articulo: " + articulos.get(index));
        } else {
            System.out.println("Número de articulo no válido.");
        }
    }

    public static void modificarArticulo(Scanner scanner, ArrayList<String> articulos) {
        int index = leerEntero(scanner, "Ingrese el número del articulo a modificar: ") - 1;
        if (index >= 0 && index < articulos.size()) {
            System.out.print("Ingrese el nuevo nombre del articulo: ");
            String nuevoNombre = scanner.nextLine();
            articulos.set(index, nuevoNombre);
            System.out.println("Articulo modificado exitosamente.");
        } else {
            System.out.println("Número de articulo no válido.");
        }
    }

    public static void eliminarArticulo(Scanner scanner, ArrayList<String> articulos) {
        System.out.println("Eliminar articulo:");
        if(articulos.isEmpty()) {
            System.out.println("No hay articulos registrados.");
            return;
        }
        String descripcionAEliminar = leerTextoNoVacio(scanner, "Ingrese la Descripcion del articulo a eliminar:");
        int posicion = buscarPosicionArticulo(articulos, descripcionAEliminar);
        if (posicion == -1) {
            System.out.println("Articulo no encontrado.");
        }
    }

    public static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        while(true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine();
            if (!texto.trim().isEmpty()) {
                return texto.trim();
            }
            System.out.println("El campo no puede estar vacío.");
        }
    }

    public static boolean existeArticulo(ArrayList<String> articulos, String descripcion) {
        return articulos.contains(descripcion);
    }

    public static int buscarPosicionArticulo(ArrayList<String> articulos, String descripcion) {
        return articulos.indexOf(descripcion);
    }


}
