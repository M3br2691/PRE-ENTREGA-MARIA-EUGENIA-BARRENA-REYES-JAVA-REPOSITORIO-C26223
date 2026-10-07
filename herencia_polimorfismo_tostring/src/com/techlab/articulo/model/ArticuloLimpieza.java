package com.techlab.articulo.model;

public class ArticuloLimpieza extends Articulo {
    private int diasParaVencimiento;

    public ArticuloLimpieza(int codigo, String nombre, double precio, Categoria categoria, int diasParaVencimiento) {
        super(codigo, nombre, precio, categoria);
        this.diasParaVencimiento = diasParaVencimiento;
    }

    public int getDiasParaVencimiento() { return diasParaVencimiento; }
    public void setDiasParaVencimiento(int diasParaVencimiento) { this.diasParaVencimiento = diasParaVencimiento; }

    @Override
    public String getTipoArticulo() {
        return "Limpieza";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Días restantes para el vencimiento: " + diasParaVencimiento;
    }

    @Override
    public String toString() {
        return super.toString() + " [subtipo limpieza]";
    }
}
