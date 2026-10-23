package com.techlab.articulo.model;

// 1. Importar las interfaces desde el paquete 'interfaces'

import com.techlab.articulo.interfaces.Calculable;
import com.techlab.articulo.interfaces.Identificable;

// 2. Indicar que la clase abstracta implementa ambas interfaces
public abstract class Articulo implements Calculable, Identificable {
    private int codigo;
    private String nombre;
    private double precio;
    private Categoria categoria;

    public Articulo(int codigo, String nombre, double precio, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    // 3. Cumplimiento de Identificable (ya tenías el método, agregamos @Override)
    @Override

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public abstract String getTipoArticulo();
    public abstract String getDetalleEspecifico();

    // 4. Cumplimiento de Calculable: se declara abstracto para que cada subtipo lo resuelva
    @Override
    public abstract double calcularPrecioFinal();

    @Override 
    public String toString() {
        return "Articulo {\n" +
               "codigo=" + codigo + ",\n" +
               "nombre='" + nombre + "',\n" +
               "precio=" + precio + ",\n" +
               "categoria='" + (categoria != null ? categoria.getNombre() : "Sin Categoría") + "',\n" +
               "tipo='" + getTipoArticulo() + "',\n" +
               "detalle='" + getDetalleEspecifico() + "'\n" +
               "}";
    }
}
