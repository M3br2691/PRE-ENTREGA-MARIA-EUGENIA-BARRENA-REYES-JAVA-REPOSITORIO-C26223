package com.techlab.articulo;

import java.util.Scanner;
import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.repository.Repositorio;
import com.techlab.articulo.menu.MenuArticulos;
import com.techlab.articulo.menu.MenuCategorias;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Instanciación de los repositorios genéricos
        Repositorio<Articulo> repoArticulos = new Repositorio<>();
        Repositorio<Categoria> repoCategorias = new Repositorio<>();
        // Precarga de categorías iniciales para no tener que cargarlas a mano
repoCategorias.agregar(new Categoria(1, "Electrónicos", "Artículos electrónicos generales"));
repoCategorias.agregar(new Categoria(2, "Periféricos", "Accesorios y componentes de computación"));
repoCategorias.agregar(new Categoria(3, "Alimentos", "Productos alimenticios y perecederos"));
repoCategorias.agregar(new Categoria(4, "Limpieza", "Productos de higiene y limpieza"));
        // 2. Instanciación de los menús (inyectando sus dependencias)
        MenuCategorias menuCategorias = new MenuCategorias(scanner, repoCategorias);
        MenuArticulos menuArticulos = new MenuArticulos(scanner, repoArticulos, repoCategorias);

        int opcion;
        do {
            System.out.println("\n=================================");
            System.out.println("   SISTEMA DE GESTIÓN TECHLAB   ");
            System.out.println("=================================");
            System.out.println("1 - Gestión de Categorías");
            System.out.println("2 - Gestión de Artículos");
            System.out.println("0 - Salir");
            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.print("❌ Entrada inválida. Ingrese un número: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> menuCategorias.ejecutar();
                case 2 -> menuArticulos.ejecutar();
                case 0 -> System.out.println("👋 ¡Gracias por usar el sistema!");
                default -> System.out.println("❌ Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}