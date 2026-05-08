package com.talento.usuario;

import java.util.Scanner;

public class DatosUsuario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa tu nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Ingresa tu apellido:");
        String apellido = scanner.nextLine();
        System.out.println("Ingresa tu edad:");
        try {
            int edad = Integer.parseInt(scanner.nextLine());
            System.out.println("NOMBRE COMPLETO: " + nombre + " " + apellido);
            System.out.println("EDAD: " + edad);
        } catch (NumberFormatException e) {
            System.out.println("Error: La edad debe ser un número entero.");
        } finally { 
            scanner.close();
        }
    }
}
