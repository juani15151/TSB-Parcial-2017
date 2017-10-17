/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.Iterator;

/**
 *
 * @author juani
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // n - valores a insertar en la cola
        int n = 10;
        TSBDeQueue<Integer> cola = new TSBDeQueue<>(100);
        for(int i = 0; i < n; i++){
            int rand = (int) (Math.random() * 100);
            if(Math.random() > 0.5){
                cola.addFirst(rand);
            } else {
                cola.addLast(rand);
            }
        }
        System.out.println(cola);
        Iterator it = cola.oddIndexIterator();
        for(int i = 0; i < 3; i++){
            it.next();
        }
        it.remove();
        System.out.println(cola);
    }    
}
