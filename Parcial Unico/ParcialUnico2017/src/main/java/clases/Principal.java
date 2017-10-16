/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juani
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    /**
     * Serializa una cola.
     *
     * @param d
     * @param path
     */
    public static void grabar(ExtraDeque d, String path) {
        throw new UnsupportedOperationException();
    }

    /**
     * Lee una cola previamente serializada.
     *
     * @param path
     * @return
     */
    public static ExtraDeque leer(String path) {
        throw new UnsupportedOperationException();
    }

    /**
     * Lee un archivo de numeros (1 por linea) y lo guarda en la cola.
     *
     * @param path
     * @return
     */
    public static TSBDeQueue<Integer> cargar(String path) {
        File f = new File(path);
        TSBDeQueue<Integer> list = new TSBDeQueue<>();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo: " + f.getAbsolutePath());
            System.exit(-1);
        }
        return list;
    }

    public static int primerPrimo(TSBDeQueue d) {
        throw new UnsupportedOperationException();
    }

    private static boolean esPrimo(int num) {
        throw new UnsupportedOperationException();
    }

    public static int promedio(TSBDeQueue d) {
        throw new UnsupportedOperationException();
    }

    public static int mediana(TSBDeQueue d) {
        throw new UnsupportedOperationException();
    }

    public static int maximo(TSBDeQueue d) {
        throw new UnsupportedOperationException();
    }

    public static int minimo(TSBDeQueue d) {
        throw new UnsupportedOperationException();
    }

    public static long inversiones(TSBDeQueue d) {
        throw new UnsupportedOperationException();
    }

    public static long sumaMaxima(TSBDeQueue d) {
        throw new UnsupportedOperationException();
    }

}
