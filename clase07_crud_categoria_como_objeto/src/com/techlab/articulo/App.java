package com.techlab.articulo;
import java.util.ArrayList;
import java.util.Scanner;

import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;

public class App {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Articulo> articulos = new ArrayList<>();
        ArrayList<Categoria> categorias = new ArrayList<>();
        precargarCategorias(categorias);
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
            System.out.println("║ 6. Listar categorias precargadas               ║");
            System.out.println("║ 0. Salir                                       ║");    
            System.out.println("╚════════════════════════════════════════════════╝");

            opcion = leerEntero(scanner, "Elija una opción:");

            switch (opcion) {
                case 1:
                    ingresarArticulo(scanner, articulos, categorias);
                    break;
                case 2:
                    listarArticulos(articulos, categorias);
                    break;
                case 3:
                    consultarArticulo(scanner, articulos, categorias);
                    break;
                case 4:
                    //modificarArticulo(scanner, articulos);
                    break;
                case 5:
                    eliminarArticulo(scanner, articulos);
                    break;
                case 6:
                    listarCategorias(categorias);
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
    private static void listarCategorias(ArrayList<Categoria> categorias) {
        System.out.println("Categorias precargadas:");
        for (Categoria categoria : categorias) {
            System.out.println(categoria.getCodigo() + " - " + categoria.getNombre() + ": " + categoria.getDescripcion());
        }
    }
    private static void precargarCategorias(ArrayList<Categoria> categorias) {
        categorias.add(new Categoria(1, "Electrónica", "Artículos electrónicos"));
        categorias.add(new Categoria(2, "Ropa", "Ropa para hombres y mujeres"));
        categorias.add(new Categoria(3, "Libros", "Libros de various temas"));
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

    public static void ingresarArticulo(Scanner s, ArrayList<Articulo>articulos, ArrayList<Categoria> categorias) {
        System.out.println("INGRESAR ARTICULO:");

        int codigo = leerEntero(s, "Ingrese el código del articulo:");
        
        
        if (buscarArticuloPorCodigo(articulos, codigo) != null) {
            System.out.println("El código del articulo ya existe..");
            return;
        }

        String nombre = leerTextoNoVacio(s, "Ingrese el nombre del articulo:");
        double precio = leerDoubleNoNegativo(s, "Ingrese el precio del articulo:");
        listarCategorias(categorias);
        Categoria categoriaElegida = pedirCategoriaExistente(s, categorias);
        Articulo articulo = new Articulo(codigo, nombre, precio, categoriaElegida); // Asignamos la primera categoría por defecto

        articulos.add(articulo);
        System.out.println("Articulo agregado correctamente.");
    }


    private static Categoria pedirCategoriaExistente(Scanner s, ArrayList<Categoria> categorias) {
        int codigoCategoria = leerEntero(s, "Ingrese el código de la categoría:");
        Categoria categoria = buscarCategoriaPorCodigo(categorias, codigoCategoria);
        if (categoria !=null) {
            return categoria;
        } else {
            System.out.println("Categoría no encontrada. Por favor, intente nuevamente.");
            return pedirCategoriaExistente(s, categorias);
        }
    }
    private static Categoria buscarCategoriaPorCodigo(ArrayList<Categoria> categorias, int codigo) {
        for (Categoria categoria : categorias) {
            if (categoria.getCodigo() == codigo) {
                return categoria;
            }
        }
        return null;
    }
    public static void listarArticulos(ArrayList<Articulo> articulos, ArrayList<Categoria> categorias) {
        if (articulos.isEmpty()) {
            System.out.println("No hay articulos cargados.");
        } 
        System.out.println("Lista de articulos");
        for (Articulo articulo : articulos) {
            System.out.println(articulo.getCodigo() + " - " + articulo.getNombre() + " - $" + articulo.getPrecio() + " - Categoría: " + articulo.getCategoria().getNombre());

        }
    }

    public static void consultarArticulo(Scanner scanner, ArrayList<Articulo> articulos, ArrayList<Categoria> categorias) {
        int index = leerEntero(scanner, "Ingrese el número del articulo a consultar: ") - 1;
        if (index >= 0 && index < articulos.size()) {
            Articulo articulo = articulos.get(index);
            System.out.println("Articulo: " + articulo.getNombre() + " Categoría: " + articulo.getCategoria().getNombre());
        } else {
            System.out.println("Número de articulo no válido.");
        }
    }

    public static void modificarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {
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

    public static void eliminarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {
        System.out.println("Eliminar articulo:");
        if(articulos.isEmpty()) {
            System.out.println("No hay articulos registrados.");
            return;
        }
        String descripcionAEliminar = leerTextoNoVacio(scanner, "Ingrese el nombre del articulo a eliminar:");
        int posicion = buscarPosicionArticulo(articulos, descripcionAEliminar);
        if (posicion == -1) {
            System.out.println("Articulo no encontrado.");
        }
        else { 
            articulos.remove(posicion);
            System.out.println("Articulo eliminado exitosamente.");
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
    private static double leerDoubleNoNegativo(Scanner s, String string) {
        while (true) {
            System.out.print(string);
            try {
                double valor = Double.parseDouble(s.nextLine());
                if (valor >= 0) {
                    return valor;
                } else {
                    System.out.println("El valor no puede ser negativo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
            }
        }
    }
    public static boolean existeArticulo(ArrayList<Articulo> articulos, String descripcion) {
        return articulos.contains(descripcion);
    }

    public static int buscarPosicionArticulo(ArrayList<Articulo> articulos, String descripcion) {
        for (int i = 0; i < articulos.size(); i++) {
            if (articulos.get(i).getNombre().equalsIgnoreCase(descripcion)) {
                return i;
            }
        }
        return -1;

    }

    public static Articulo buscarArticuloPorCodigo(ArrayList<Articulo> articulos, int codigo) {
        for (Articulo articulo : articulos) {
            if (articulo.getCodigo() == codigo) {
                return articulo;
            }
        }
        return null;
    }


}
