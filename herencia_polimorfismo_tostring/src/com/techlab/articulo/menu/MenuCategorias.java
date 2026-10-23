package com.techlab.articulo.menu;

import java.util.Scanner;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.repository.Repositorio;

public class MenuCategorias extends Menu {
    private Repositorio<Categoria> repositorioCategorias;

    // Se cambió el ';' por una ',' entre los parámetros
    public MenuCategorias(Scanner scanner, Repositorio<Categoria> repositorioCategorias) {
        super(scanner);
        this.repositorioCategorias = repositorioCategorias;
    }

    @Override 
    public void mostrarMenu() {
        System.out.println("\n---- GESTIÓN DE CATEGORÍAS ----");
        System.out.println("1 - Ingresar Categoría");
        System.out.println("2 - Listar Categorías");
        System.out.println("3 - Consultar una Categoría");
        System.out.println("4 - Modificar una Categoría");
        System.out.println("5 - Eliminar una Categoría");
        System.out.println("0 - Volver al Menú Principal");
    }

    @Override 
    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            // Usamos la sintaxis -> para evitar ejecuciones en cadena
            switch (opcion) {
                case 1 -> ingresarCategoria();
                case 2 -> listarCategorias();
                case 3 -> consultarCategoria();
                case 4 -> modificarCategoria();
                case 5 -> eliminarCategoria();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void ingresarCategoria() {
        int codigo = leerEntero("Ingrese el código de la categoría: ");
        if (repositorioCategorias.buscarPorCodigo(codigo) != null) {
            System.out.println("❌ Ya existe una categoría con ese código.");
            return;
        }
        String nombre = leerTextoNoVacio("Ingrese el nombre: ");
        String descripcion = leerTextoNoVacio("Ingrese la descripción: ");
        Categoria categoria = new Categoria(codigo, nombre, descripcion);
        repositorioCategorias.agregar(categoria);
        System.out.println("✅ ¡Categoría agregada con éxito!");
    }

    private void listarCategorias() {
        if (repositorioCategorias.estaVacio()) {
            System.out.println("⚠️ No hay categorías registradas.");
            return;
        }
        System.out.println("\n--- LISTA DE CATEGORÍAS ---");
        for (Categoria cat : repositorioCategorias.listar()) {
            System.out.println(cat);
        }
    }

    private void consultarCategoria() {
        int codigo = leerEntero("Ingrese el código a buscar: ");
        Categoria cat = repositorioCategorias.buscarPorCodigo(codigo);
        if (cat != null) {
            System.out.println(cat);
        } else {
            System.out.println("❌ Categoría no encontrada.");
        }
    }

    private void modificarCategoria() {
        int codigo = leerEntero("Ingrese el código de la categoría a modificar: ");
        Categoria cat = repositorioCategorias.buscarPorCodigo(codigo);
        if (cat == null) {
            System.out.println("❌ Categoría no encontrada.");
            return;
        }
        cat.setNombre(leerTextoNoVacio("Nuevo nombre (" + cat.getNombre() + "): "));
        cat.setDescripcion(leerTextoNoVacio("Nueva descripción (" + cat.getDescripcion() + "): "));
        System.out.println("✅ Categoría modificada.");
    }

    private void eliminarCategoria() {
        int codigo = leerEntero("Ingrese el código de la categoría a eliminar: ");
        Categoria cat = repositorioCategorias.buscarPorCodigo(codigo);
        if (cat != null) {
            repositorioCategorias.eliminar(cat);
            System.out.println("✅ Categoría eliminada.");
        } else {
            System.out.println("❌ Categoría no encontrada.");
        }
    }
}