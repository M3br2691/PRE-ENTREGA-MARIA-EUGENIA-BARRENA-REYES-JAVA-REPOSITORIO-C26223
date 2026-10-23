package com.techlab.articulo.model;

public class ArticuloGeneral extends Articulo {

    public ArticuloGeneral(int codigo, String nombre, double precio, Categoria categoria) {
        super(codigo, nombre, precio, categoria);
    }

    @Override
    public String getTipoArticulo() {
        return "General";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Sin especificaciones especiales";
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecio(); // El precio final es directamente el precio base
    }

    @Override
    public String toString() {
        return super.toString() + " [subtipo general]";
    }
}