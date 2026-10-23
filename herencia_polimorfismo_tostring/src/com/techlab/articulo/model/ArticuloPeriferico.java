package com.techlab.articulo.model;

public class ArticuloPeriferico extends Articulo {
     private int garantiaMeses;

    public ArticuloPeriferico(int codigo, String nombre, double precio, Categoria categoria, int garantiaMeses) {
        super(codigo, nombre, precio, categoria);
        this.garantiaMeses = garantiaMeses;
    }

    public int getGarantiaMeses() { return garantiaMeses; }
    public void setGarantiaMeses(int garantiaMeses) { this.garantiaMeses = garantiaMeses; }

    @Override
    public String getTipoArticulo() {
        return "Periférico";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Garantía: " + garantiaMeses + " meses";
    // Cumplimiento del contrato de Calculable (a través de Articulo)
    }@Override 
    public double calcularPrecioFinal(){
        if (garantiaMeses>12) {
            return getPrecio()*1.10; // 10% de recargo si supera el año de garantía
        }
        return getPrecio();
    }

    @Override
    public String toString() {
        return super.toString() + " [subtipo periférico]";
    }
    
   
    }

