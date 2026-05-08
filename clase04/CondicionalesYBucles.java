import java.util.Scanner;

public class CondicionalesYBucles {
    public static void main(String[] args) {
        int opcion;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Ingresa una opción (1-3) o 0 para salir:");
            
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Opción 1 seleccionada.");
                    break;
                case 2:
                    System.out.println("Opción 2 seleccionada.");
                    break;
                case 3:
                    System.out.println("Opción 3 seleccionada.");
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 0);
        scanner.close();

    }
}