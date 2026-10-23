package com.techlab.articulo.menu;

import java.util.Scanner;
import com.techlab.articulo.model.*;
import com.techlab.articulo.repository.Repositorio;

public class MenuArticulos extends Menu {
    private Repositorio<Articulo> repositorioArticulos;
    private Repositorio<Categoria> repositorioCategorias;

    public MenuArticulos(Scanner scanner, Repositorio<Articulo> repositorioArticulos, Repositorio<Categoria> repositorioCategorias) {
        super(scanner);
        this.repositorioArticulos = repositorioArticulos;
        this.repositorioCategorias = repositorioCategorias;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n--- GESTIÓN DE ARTÍCULOS ---");
        System.out.println("1 - Ingresar artículo");
        System.out.println("2 - Listar artículos");
        System.out.println("3 - Consultar un artículo");
        System.out.println("4 - Eliminar un artículo");
        System.out.println("0 - Volver al menú principal");
    }

    @Override
    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> ingresarArticulo();
                case 2 -> listarArticulos();
                case 3 -> consultarArticulo();
                case 4 -> eliminarArticulo();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void ingresarArticulo() {
        int codigo = leerEntero("Ingrese el código del artículo: ");
        if (repositorioArticulos.buscarPorCodigo(codigo) != null) {
            System.out.println("❌ Ya existe un artículo con el código " + codigo);
            return;
        }

        String nombre = leerTextoNoVacio("Ingrese nombre del artículo: ");
        double precio = leerDouble("Ingrese precio base: ");

        // Muestra categorías si existen
        if (!repositorioCategorias.estaVacio()) {
            System.out.println("\nCategorías disponibles:");
            for (Categoria c : repositorioCategorias.listar()) {
                System.out.println(" - Código " + c.getCodigo() + ": " + c.getNombre());
            }
        } else {
            System.out.println("\n⚠️ No hay categorías registradas aún.");
        }

        int codCat = leerEntero("Seleccione el código de categoría (o ingrese un código nuevo para crearla): ");
        Categoria catSeleccionada = repositorioCategorias.buscarPorCodigo(codCat);

        // Si la categoría no existe, se crea sobre la marcha
        if (catSeleccionada == null) {
            System.out.println("\n⚠️ La categoría " + codCat + " no existe.");
            System.out.println("--- CREANDO NUEVA CATEGORÍA ---");
            String nombreCat = leerTextoNoVacio("Nombre de la nueva categoría: ");
            String descCat = leerTextoNoVacio("Descripción: ");
            
            catSeleccionada = new Categoria(codCat, nombreCat, descCat);
            repositorioCategorias.agregar(catSeleccionada);
            System.out.println("✅ Categoría '" + nombreCat + "' creada y vinculada con éxito.");
        }

        System.out.println("\nSeleccione el tipo de artículo:");
        System.out.println("1 - Periférico");
        System.out.println("2 - Limpieza");
        System.out.println("3 - Alimenticio");
        System.out.println("4 - Electrónico");
        System.out.println("5 - General / Librería");
        int tipo = leerEntero("Opción: ");

        Articulo nuevoArticulo = null;

        switch (tipo) {
            case 1 -> {
                int garantia = leerEntero("Garantía en meses: ");
                nuevoArticulo = new ArticuloPeriferico(codigo, nombre, precio, catSeleccionada, garantia);
            }
            case 2 -> {
                int dias = leerEntero("Días para el vencimiento: ");
                nuevoArticulo = new ArticuloLimpieza(codigo, nombre, precio, catSeleccionada, dias);
            }
            case 3 -> {
                int dias = leerEntero("Días para el vencimiento: ");
                nuevoArticulo = new ArticuloAlimenticio(codigo, nombre, precio, catSeleccionada, dias);
            }
            case 4 -> {
                int garantia = leerEntero("Garantía en meses: ");
                nuevoArticulo = new ArticuloElectronico(codigo, nombre, precio, catSeleccionada, garantia);
            }
            case 5 -> {
                nuevoArticulo = new ArticuloGeneral(codigo, nombre, precio, catSeleccionada);
            }
            default -> System.out.println("❌ Tipo no válido.");
        }

        if (nuevoArticulo != null) {
            repositorioArticulos.agregar(nuevoArticulo);
            System.out.println("✅ Artículo registrado exitosamente.");
        }
    }

    private void listarArticulos() {
        if (repositorioArticulos.estaVacio()) {
            System.out.println("⚠️ No hay artículos registrados.");
            return;
        }
        System.out.println("\n--- LISTA DE ARTÍCULOS ---");
        for (Articulo art : repositorioArticulos.listar()) {
            System.out.println(art);
        }
    }

    private void consultarArticulo() {
        int codigo = leerEntero("Ingrese el código del artículo a buscar: ");
        Articulo art = repositorioArticulos.buscarPorCodigo(codigo);
        if (art != null) {
            System.out.println(art);
        } else {
            System.out.println("❌ Artículo no encontrado.");
        }
    }

    private void eliminarArticulo() {
        int codigo = leerEntero("Ingrese el código del artículo a eliminar: ");
        Articulo art = repositorioArticulos.buscarPorCodigo(codigo);
        if (art != null) {
            repositorioArticulos.eliminar(art);
            System.out.println("✅ Artículo eliminado correctamente.");
        } else {
            System.out.println("❌ Artículo no encontrado.");
        }
    }
}