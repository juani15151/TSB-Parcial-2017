/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;

/**
 *
 * @author juani
 */
public class ExtraDeque<E> extends TSBDeQueue<E> implements Serializable {

    private boolean ordenado;
    
    @Override
    public void clear() {
        items = new Object[items.length];
        size = 0;
        modCount++;
    }

    /**
     * Ordenamiento por QuickSort.
     *
     * Requiere que el arreglo contenga objetos que implementen Comparable
     * ORDENAMIENTO SIN PROBAR.
     *
     * @throws ClassCastException - si el arreglo no contiene objetos
     * Comparable.
     */
    public void ordenar() {
        ordenar(0, size - 1);
    }

    private void ordenar(int izq, int der) {
        // Nota: Puede que falle siempre, en ese caso hay que dejarlo como
        //      object[] y castear el x e y a Comparable.
        Comparable[] v = (Comparable[]) items;
        int i = izq, j = der;
        Comparable x = v[(izq + der) / 2];
        Comparable y;
        do {
            while (v[i].compareTo(x) < 0 && i < der) {
                i++;
            }
            while (v[j].compareTo(x) > 0 && j > izq) {
                j--;
            }
            if (i <= j) {
                y = v[i];
                v[i] = v[j];
                v[j] = y;
                i++;
                j--;
                modCount++;
            }
        } while (i <= j);
        if (izq < j) {
            ordenar(izq, j);
        }
        if (i < der) {
            ordenar(i, der);
        }
    }

    public E buscar(E objeto) {
        // TODO: Busqueda binaria.
        throw new UnsupportedOperationException();
    }

}
