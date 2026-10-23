package com.techlab.articulo.model;

// 1- Importar la interfaz identificable

import com.techlab.articulo.interfaces.Identificable;

// 2 - Declarar que la clase implementa la interfaz



public class Categoria implements Identificable {
    private int codigo;
    private String nombre;
    private String descripcion;

    // 1. Constructor completo
    public Categoria(int codigo, String nombre, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // 3 - Cumplimiento del contrato de Identificable

    @Override 
    
    // 2. Métodos Getters y Setters
    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    // 3. Método toString() sobrescrito para mostrar los datos prolijos
    @Override
    public String toString() {
        return "Categoria {\n" +
               "codigo=" + codigo + ",\n" +
               "nombre='" + nombre + "',\n" +
               "descripcion='" + descripcion + "'\n" +
               "}";
    }
}
