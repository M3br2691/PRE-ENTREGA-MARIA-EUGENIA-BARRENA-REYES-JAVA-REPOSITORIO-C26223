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

    // Cumplimiento del contrato de Calculable (a través de la clase base Articulo)
    
    @Override 
    public double calcularPrecioFinal(){
        if (diasParaVencimiento<=7) {
            return getPrecio() * 0.80; // 20% de descuento por vencimiento próximo     
        }
        if (diasParaVencimiento<=15) {
            return getPrecio() * 0.90; // 10% de descuento
        }
        return getPrecio(); // precio normal
    }



    @Override
    public String toString() {
        return super.toString() + " [subtipo limpieza]";
    }
}
