package com.techlab.articulo.repository;

import java.util.ArrayList;
import java.util.List;
import com.techlab.articulo.interfaces.Identificable;

public class Repositorio<T extends Identificable> {
    private ArrayList<T> lista = new ArrayList<>();
    // Agrega un objeto a la lista interna (evitando agregar objetos nulos)
    public void agregar(T objeto){
        if (objeto != null) {
            lista.add(objeto);
        }
    }

    // Devuelve una copia de la lista para proteger el encapsulamiento interno
    public List<T> listar(){
        return new ArrayList<>(lista);
    }
    
    // Busca un objeto por su código aprovechando la interfaz Identificable
    public T buscarPorCodigo(int codigo) {
        for (T objeto : lista){
            if (objeto.getCodigo()== codigo) {
                return objeto;
            }
        }
        return null;
    }
    // Elimina un objeto del repositorio
    public void eliminar(T objeto){
        lista.remove(objeto);
    }

    // Verifica si el repositorio no contiene elementos
    public boolean estaVacio(){
        return lista.isEmpty();
    }

    // Devuelve la cantidad de elementos almacenados (Útil para la preentrega)
    public int cantidad() {
        return lista.size();
    }
} 

