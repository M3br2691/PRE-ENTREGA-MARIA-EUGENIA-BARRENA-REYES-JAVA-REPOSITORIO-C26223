package com.techlab.articulo;

import java.util.ArrayList;
import java.util.Scanner;
import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.model.ArticuloElectronico;
import com.techlab.articulo.model.ArticuloPeriferico;
import com.techlab.articulo.model.ArticuloAlimenticio;
import com.techlab.articulo.model.ArticuloLimpieza;

public class App {

    public static void main(String[] args) {
        // 1. Crear el objeto Scanner para leer datos
        Scanner scanner = new Scanner(System.in);

        // 2. Crear listas ArrayList para almacenar artículos y categorías
        ArrayList<Articulo> articulos = new ArrayList<>();
        ArrayList<Categoria> categorias = new ArrayList<>();

        // 3. Invocar el método que precarga las categorías
        precargarCategorias(categorias);

        // 4. Variable para controlar el menú
        int opcion;

        // 5. Ciclo do-while para mantener el programa en ejecución
        do {
            System.out.println("======================================================");
            System.out.println("SISTEMA DE ARTÍCULOS - CLASE 4 (HERENCIA Y TO_STRING)");
            System.out.println("======================================================");
            System.out.println("1 - Ingresar artículo");
            System.out.println("2 - Listar artículos");
            System.out.println("3 - Consultar un artículo");
            System.out.println("4 - Modificar un artículo");
            System.out.println("5 - Eliminar un artículo");
            System.out.println("6 - Listar categorías");
            System.out.println("0 - Salir");
            System.out.println("======================================================");

            // Utilizamos el método auxiliar validado para leer la opción del menú
            opcion = leerEntero(scanner, "Ingrese una opción: ");

            // 6. Utilizar un switch para ejecutar la opción seleccionada
            switch (opcion) {
                case 1:
                    ingresarArticulo(scanner, articulos, categorias);
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
                case 6:
                    listarCategorias(categorias);
                    break;
                case 0:
                    System.out.println("\n¡Gracias por utilizar el sistema! Saliendo del programa...");
                    break;
                default:
                    System.out.println("\n❌ Opción no válida. Por favor, intente nuevamente.\n");
                    break;
            }

        } while (opcion != 0); // Termina si el usuario elige 0

        // 7. Cerrar el Scanner al finalizar
        scanner.close();
    }

    // =========================================================================
    // 4.2. Precarga de categorías
    // =========================================================================
    public static void precargarCategorias(ArrayList<Categoria> categorias) {
        categorias.add(new Categoria(1, "Electrónica", "Productos tecnológicos y electrónicos"));
        categorias.add(new Categoria(2, "Periféricos", "Accesorios para computadora"));
        categorias.add(new Categoria(3, "Alimentos", "Productos alimenticios"));
        categorias.add(new Categoria(4, "Limpieza", "Artículos de limpieza del hogar"));
    }

    // =========================================================================
    // 6.6. Opción 6: Listar categorías
    // =========================================================================
    public static void listarCategorias(ArrayList<Categoria> categorias) {
        System.out.println("\n--- LISTA DE CATEGORÍAS DISPONIBLES ---");
        for (Categoria cat : categorias) {
            System.out.println(cat); // Aprovecha automáticamente el método toString()
        }
        System.out.println();
    }

    // =========================================================================
    // 7. MÉTODOS AUXILIARES OBLIGATORIOS (Lectura y Validación con try-catch)
    // =========================================================================

    /**
     * 7.4. Leer un número entero con control de errores try-catch
     */
    public static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String entrada = scanner.nextLine().trim();
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Debe ingresar un número entero válido.");
            }
        }
    }

    /**
     * 7.5. Leer un entero no negativo (mayor o igual a cero)
     */
    public static int leerEnteroNoNegativo(Scanner scanner, String mensaje) {
        while (true) {
            int valor = leerEntero(scanner, mensaje);
            if (valor >= 0) {
                return valor;
            }
            System.out.println("❌ Error: El número no puede ser negativo.");
        }
    }

    /**
     * 7.6. Leer un número decimal no negativo (double) con control try-catch
     */
    public static double leerDoubleNoNegativo(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String entrada = scanner.nextLine().trim();
                double valor = Double.parseDouble(entrada);

                // Rechazar valores negativos, NaN o Infinity
                if (valor < 0 || Double.isNaN(valor) || Double.isInfinite(valor)) {
                    System.out.println("❌ Error: El valor debe ser un número decimal positivo y válido.");
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Debe ingresar un número decimal válido (ejemplo: 1500.50).");
            }
        }
    }

    // =========================================================================
    // 5. MÉTODOS DE BÚSQUEDA OBLIGATORIOS
    // =========================================================================

    /**
     * Busca una categoría por su código numérico dentro de la lista
     */
    public static Categoria buscarCategoriaPorCodigo(ArrayList<Categoria> categorias, int codigo) {
        for (Categoria cat : categorias) {
            if (cat.getCodigo() == codigo) {
                return cat; // Si la encuentra, devuelve la categoría entera
            }
        }
        return null; // Si no la encuentra, devuelve null
    }

    /**
     * Busca un artículo por su código único en la lista general (Polimorfismo)
     */
    public static Articulo buscarArticuloPorCodigo(ArrayList<Articulo> articulos, int codigo) {
        for (Articulo art : articulos) {
            if (art.getCodigo() == codigo) {
                return art;
            }
        }
        return null;
    }

    // =========================================================================
    // 6.1. Opción 1: Ingresar Artículo (Uso de Polimorfismo)
    // =========================================================================
    public static void ingresarArticulo(Scanner scanner, ArrayList<Articulo> articulos,
            ArrayList<Categoria> categorias) {
        System.out.println("\n--- NUEVO ARTÍCULO ---");

        int codigo;
        while (true) {
            codigo = leerEnteroNoNegativo(scanner, "Ingrese el código del artículo: ");
            if (buscarArticuloPorCodigo(articulos, codigo) == null) {
                break;
            }
            System.out.println("❌ Error: Ya existe un artículo registrado con ese código.");
        }

        String nombre = leerTextoNoVacio(scanner, "Ingrese el nombre del artículo: ");
        double precio = leerDoubleNoNegativo(scanner, "Ingrese el precio: ");

        System.out.println("\nCategorías disponibles:");
        for (Categoria cat : categorias) {
            System.out.println("  " + cat.getCodigo() + " - " + cat.getNombre());
        }

        Categoria categoriaSeleccionada = null;
        while (categoriaSeleccionada == null) {
            int codCat = leerEntero(scanner, "Seleccione el código de la categoría: ");
            categoriaSeleccionada = buscarCategoriaPorCodigo(categorias, codCat);
            if (categoriaSeleccionada == null) {
                System.out.println("❌ Error: El código de categoría ingresado no existe.");
            }
        }

        Articulo articulo;

        if (categoriaSeleccionada.getCodigo() == 1) {
            int garantia = leerEnteroNoNegativo(
                    scanner,
                    "Ingrese los meses de garantía: ");

            articulo = new ArticuloElectronico(
                    codigo,
                    nombre,
                    precio,
                    categoriaSeleccionada,
                    garantia);

        } else if (categoriaSeleccionada.getCodigo() == 2) {
            int garantia = leerEnteroNoNegativo(
                    scanner,
                    "Ingrese los meses de garantía: ");

            articulo = new ArticuloPeriferico(
                    codigo,
                    nombre,
                    precio,
                    categoriaSeleccionada,
                    garantia);

        } else if (categoriaSeleccionada.getCodigo() == 3) {
            int diasVencimiento = leerEnteroNoNegativo(
                    scanner,
                    "Ingrese los días restantes para el vencimiento: ");

            articulo = new ArticuloAlimenticio(
                    codigo,
                    nombre,
                    precio,
                    categoriaSeleccionada,
                    diasVencimiento);

        } else {
            int diasVencimiento = leerEnteroNoNegativo(
                    scanner,
                    "Ingrese los días restantes para el vencimiento: ");

            articulo = new ArticuloLimpieza(
                    codigo,
                    nombre,
                    precio,
                    categoriaSeleccionada,
                    diasVencimiento);
        }

        articulos.add(articulo);

        System.out.println("\n✅ Artículo registrado con éxito.\n");
    }

    // =========================================================================
    // 6.2. Opción 2: Listar Artículos (Uso de Polimorfismo)
    // =========================================================================
    public static void listarArticulos(ArrayList<Articulo> articulos) {
        System.out.println("\n--- LISTA DE ARTÍCULOS REGISTRADOS ---");

        if (articulos.isEmpty()) {
            System.out.println("⚠️ No hay artículos registrados en el sistema.");
        } else {
            for (Articulo art : articulos) {
                System.out.println(art);
                System.out.println("------------------------------------------------------");
            }
        }
        System.out.println();
    }

  
    public static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (!entrada.isEmpty()) {
                return entrada;
            }
            System.out.println("❌ Error: Este campo no puede quedar vacío.");
        }
    } // <- Esta llave cierra el método leerTextoNoVacio

    // =========================================================================
    // 6.3. Opción 3: Consultar un Artículo
    // =========================================================================
    public static void consultarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {
        System.out.println("\n--- CONSULTAR ARTÍCULO ---");
        if (articulos.isEmpty()) {
            System.out.println("⚠️ No hay artículos registrados en el sistema.");
            return;
        }
        int codigo = leerEnteroNoNegativo(scanner, "Ingrese el código del artículo a consultar: ");
        Articulo art = buscarArticuloPorCodigo(articulos, codigo);

        if (art != null) {
            System.out.println("\n✅ Artículo encontrado:");
            System.out.println(art);
            if (art instanceof com.techlab.articulo.model.ArticuloElectronico) {
                com.techlab.articulo.model.ArticuloElectronico elec = (com.techlab.articulo.model.ArticuloElectronico) art;
                System.out.println("📞 Teléfono Mesa de Ayuda: " + elec.nroTelMesaDeAyudaParaReclamos());
            }
        } else {
            System.out.println("❌ Error: No se encontró ningún artículo con el código " + codigo);
        }
        System.out.println();
    }

    // =========================================================================
    // 6.4. Opción 4: Modificar un Artículo
    // =========================================================================
    public static void modificarArticulo(Scanner scanner, ArrayList<Articulo> articulos)  {
        System.out.println("\n--- MODIFICAR ARTÍCULO ---");
        if (articulos.isEmpty()) {
            System.out.println("⚠️ No hay artículos registrados en el sistema.");
            return;
        }
        int codigo = leerEnteroNoNegativo(scanner, "Ingrese el código del artículo a modificar: ");
        Articulo art = buscarArticuloPorCodigo(articulos, codigo);

        if (art != null) {
            System.out.println("\nDatos actuales del artículo:");
            System.out.println(art);

            System.out.println("\n--- Ingrese los nuevos datos ---");
            String nuevoNombre = leerTextoNoVacio(scanner, "Nuevo nombre: ");
            double nuevoPrecio = leerDoubleNoNegativo(scanner, "Nuevo precio: ");

            
            art.setNombre(nuevoNombre);
            art.setPrecio(nuevoPrecio);
            
            if (art instanceof ArticuloElectronico) {
                int nuevaGarantia = leerEnteroNoNegativo(
                        scanner,
                        "Nuevos meses de garantía: ");

                ((ArticuloElectronico) art).setGarantiaMeses(nuevaGarantia);

            } else if (art instanceof ArticuloPeriferico) {
                int nuevaGarantia = leerEnteroNoNegativo(
                        scanner,
                        "Nuevos meses de garantía: ");

                ((ArticuloPeriferico) art).setGarantiaMeses(nuevaGarantia);

            } else if (art instanceof ArticuloAlimenticio) {
                int nuevosDias = leerEnteroNoNegativo(
                        scanner,
                        "Nuevos días para el vencimiento: ");

                ((ArticuloAlimenticio) art).setDiasParaVencimiento(nuevosDias);

            } else if (art instanceof ArticuloLimpieza) {
                int nuevosDias = leerEnteroNoNegativo(
                        scanner,
                        "Nuevos días para el vencimiento: ");

                ((ArticuloLimpieza) art).setDiasParaVencimiento(nuevosDias);
            }
            System.out.println("\n✅ Artículo modificado con éxito.\n");
        } else {
            System.out.println("❌ Error: No se encontró ningún artículo con el código " + codigo);
        }
    }

    // =========================================================================
    // 6.5. Opción 5: Eliminar un Artículo
    // =========================================================================
    public static void eliminarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {
        System.out.println("\n--- ELIMINAR ARTÍCULO ---");
        if (articulos.isEmpty()) {
            System.out.println("⚠️ No hay artículos registrados en el sistema.");
            return;
        }
        int codigo = leerEnteroNoNegativo(scanner, "Ingrese el código del artículo a eliminar: ");
        Articulo art = buscarArticuloPorCodigo(articulos, codigo);

        if (art != null) {
            articulos.remove(art);
            System.out.println("\n✅ Artículo eliminado correctamente de los registros.\n");
        } else {
            System.out.println("❌ Error: No se encontró ningún artículo con el código " + codigo);
        }
    }
} // <- Esta ÚLTIMA llave cierra la clase App por completo
