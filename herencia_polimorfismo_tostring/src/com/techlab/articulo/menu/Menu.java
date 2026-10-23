package com.techlab.articulo.menu;

import java.util.Scanner;

public abstract class Menu {
    protected Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    // Métodos obligatorios que deberá implementar cada menú específico
    public abstract void mostrarMenu();
    public abstract void ejecutar();

    // --- Métodos utilitarios de lectura por consola ---

    protected int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("❌ Entrada inválida. Ingrese un número entero: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        return valor;
    }

    protected double leerDouble(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextDouble()) {
            System.out.print("❌ Entrada inválida. Ingrese un número decimal (ej. 1500.50): ");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer
        return valor;
    }

    protected String leerTextoNoVacio(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("❌ El texto no puede estar vacío. Reintente:");
            }
        } while (texto.isEmpty());
        return texto;
    }
}