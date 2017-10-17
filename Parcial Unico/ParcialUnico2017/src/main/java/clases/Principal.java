/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

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

    /**
     * Genera un arreglo de numeros aleatorios
     * @param min - valor minimo de los numeros generados, incluyente.
     * @param max - valor maximo de los numeros generados, excluyente.
     * @param amount - la cantidad de numeros aleatorios a generar
     * @return 
     */
    public static ArrayList<Integer> generateRandom(int min, int max, int amount){
        ArrayList<Integer> numeros = new ArrayList<>(amount);
        // Java 8
        new Random().ints(amount, min, max).forEach(value -> numeros.add(value));
        return numeros;
    }

}
