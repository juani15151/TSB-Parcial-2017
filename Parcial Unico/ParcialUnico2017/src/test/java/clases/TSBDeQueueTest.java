package clases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author Ing. Valerio Frittelli.
 */
public class TSBDeQueueTest {

    private TSBDeQueue<Integer> instance;
    private TSBDeQueue<Integer> instance2;
    private TSBDeQueue<Integer> empty;

    public TSBDeQueueTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println("* UtilsJUnit4Test: @Before method");
        int data[] = {1, 5, 7, 6, 10, 7, 8, 11};

        empty = new TSBDeQueue<>();

        instance = new TSBDeQueue<>();
        instance2 = new TSBDeQueue<>();
        for (int value : data) {
            instance.add(value);
            instance2.add(value);
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test de los constructores de la clase TSBDeQueue.
     */
    @org.junit.Test
    public void testConstructors() {
        // No debe lanzar excepciones.
        TSBDeQueue<Integer> cola;
        cola = new TSBDeQueue();
        cola = new TSBDeQueue(-1);
        ArrayList<Integer> data = new ArrayList<>();
        data.add(10);
        data.add(20);
        cola = new TSBDeQueue(data);
        assertEquals(2, cola.size());
    }

    /**
     * Test of add method, of class TSBDeQueue.
     */
    @org.junit.Test(expected = NullPointerException.class)
    public void testAdd() {
        System.out.println("add");
        int size = instance.size();
        instance.add(101);
        assertEquals((Integer) 101, instance.getLast());
        assertEquals(size + 1, instance.size());
        instance.add(null);
    }

    /**
     * Prueba que el arreglo crece cuando es necesario.
     */
    @org.junit.Test
    public void testAdaptative() {
        // No debe lanzar excepciones.
        TSBDeQueue<Integer> cola = new TSBDeQueue(1);
        for (int i = 0; i < 100; i++) {
            cola.add(1);
        }
    }

    /**
     * Test of iterator method, of class TSBDeQueue.
     */
    @org.junit.Test (expected = NoSuchElementException.class)
    public void testIterator() {
        System.out.println("iterator");
        int ta = instance.size();
        Iterator r1 = instance.iterator();
        assertNotEquals(null, r1);
        assertTrue(r1.hasNext());
        assertNotEquals(null, r1.next());
        r1.remove();
        assertEquals(ta - 1, instance.size());
        // Next luego de un remove no se debe saltear elementos.
        assertEquals((Integer) 5, r1.next());
        // Opcional. 2 invocaciones seguida de remove deben fallar.
        try {
            r1.remove();
            r1.remove(); // Debe lanzar la excepcion aqui.
            fail(); 
        } catch (IllegalStateException e) {
            // Ok
        }
        // Si se invoca a next cuando hasNext da falso debe fallar.
        Iterator i2 = empty.iterator();
        i2.next();
    }

    /**
     * Test of size method, of class TSBDeQueue.
     */
    @org.junit.Test
    public void testSize() {
        System.out.println("size");
        assertEquals(8, instance.size());
        assertEquals(0, empty.size());
    }

    /**
     * Test of addFirst method, of class TSBDeQueue.
     */
    @org.junit.Test(expected = NullPointerException.class)
    public void testAddFirst() {
        System.out.println("addFirst");
        instance.addFirst(99);
        assertEquals((Integer) 99, instance.getFirst());
        instance.addFirst(null);
    }

    /**
     * Test of addLast method, of class TSBDeQueue.
     */
    @org.junit.Test(expected = NullPointerException.class)
    public void testAddLast() {
        System.out.println("addLast");
        instance.addLast(101);
        assertEquals((Integer) 101, instance.getLast());
        instance.addLast(null);
    }

    /**
     * Test of offerFirst method, of class TSBDeQueue.
     */
    @org.junit.Test(expected = NullPointerException.class)
    public void testOfferFirst() {
        System.out.println("offerFirst");
        assertTrue(instance.offerFirst(99));
        assertEquals((Integer) 99, instance.getFirst());
        instance.offerFirst(null);
    }

    /**
     * Test of offerLast method, of class TSBDeQueue.
     */
    @org.junit.Test(expected = NullPointerException.class)
    public void testOfferLast() {
        System.out.println("offerLast");
        assertTrue(instance.offerLast(101));
        assertEquals((Integer) 101, instance.getLast());
        instance.offerLast(null);
    }

    /**
     * Test of removeFirst method, of class TSBDeQueue.
     */
    @org.junit.Test(expected = NoSuchElementException.class)
    public void testRemoveFirst() {
        System.out.println("removeFirst");
        int ta = instance.size();
        assertEquals((Integer) 1, instance.removeFirst());
        assertEquals(ta - 1, instance.size());
        int r1 = empty.removeFirst();
    }

    /**
     * Test of removeLast method, of class TSBDeQueue.
     */
    @org.junit.Test(expected = NoSuchElementException.class)
    public void testRemoveLast() {
        System.out.println("removeLast");
        int ta = instance.size();
        assertEquals((Integer) 11, instance.removeLast());
        assertEquals(ta - 1, instance.size());
        int r1 = empty.removeLast();
    }

    /**
     * Test of pollFirst method, of class TSBDeQueue.
     */
    @org.junit.Test
    public void testPollFirst() {
        System.out.println("pollFirst");
        int ta = instance.size();
        assertEquals((Integer) 1, instance.pollFirst());
        assertEquals(ta - 1, instance.size());
        assertNull(empty.pollFirst());
    }

    /**
     * Test of pollLast method, of class TSBDeQueue.
     */
    @org.junit.Test
    public void testPollLast() {
        System.out.println("pollLast");
        int ta = instance.size();
        assertEquals((Integer) 11, instance.pollLast());
        assertEquals(ta - 1, instance.size());
        assertNull(empty.pollLast());
    }

    /**
     * Test of getFirst method, of class TSBDeQueue.
     */
    @org.junit.Test(expected = NoSuchElementException.class)
    public void testGetFirst() {
        System.out.println("getFirst");
        assertEquals((Integer) 1, instance.getFirst());
        int r1 = empty.getFirst();
    }

    /**
     * Test of getLast method, of class TSBDeQueue.
     */
    @org.junit.Test(expected = NoSuchElementException.class)
    public void testGetLast() {
        System.out.println("getLast");
        assertEquals((Integer) 11, instance.getLast());
        int r1 = empty.getLast();
    }

    /**
     * Test of peekFirst method, of class TSBDeQueue.
     */
    @org.junit.Test
    public void testPeekFirst() {
        System.out.println("peekFirst");
        assertEquals((Integer) 1, instance.peekFirst());
        assertNull(empty.peekFirst());
    }

    /**
     * Test of peekLast method, of class TSBDeQueue.
     */
    @org.junit.Test
    public void testPeekLast() {
        System.out.println("peekLast");
        assertEquals((Integer) 11, instance.peekLast());
        assertNull(empty.peekLast());
    }

    /**
     * Test of removeFirstOccurrence method, of class TSBDeQueue.
     */
    @org.junit.Test
    public void testRemoveFirstOccurrence() {
        System.out.println("removeFirstOccurrence");
        assertTrue(instance.removeFirstOccurrence(7));
        assertFalse(instance.removeFirstOccurrence(99));
        assertFalse(empty.removeFirstOccurrence(10));
        // Controla que se haya eliminado el primer 7 y no otro.
        Iterator it = instance.iterator();
        it.next();
        it.next();
        assertEquals((Integer) 6, it.next());

    }

    /**
     * Test of removeLastOccurrence method, of class TSBDeQueue.
     */
    @org.junit.Test
    public void testRemoveLastOccurrence() {
        System.out.println("removeLastOccurrence");
        assertTrue(instance.removeLastOccurrence(7));
        assertFalse(instance.removeLastOccurrence(99));
        assertFalse(empty.removeLastOccurrence(10));
        // Controla que no se haya eliminado el primer 7
        Iterator it = instance.iterator();
        it.next();
        it.next();
        assertEquals((Integer) 7, it.next());
    }

    /**
     * Test of offer method, of class TSBDeQueue.
     */
    @org.junit.Test(expected = NullPointerException.class)
    public void testOffer() {
        System.out.println("offer");
        assertTrue(instance.offer(101));
        assertEquals((Integer) 101, instance.getLast());
        instance.offer(null);
    }

    /**
     * Test of remove method, of class TSBDeQueue.
     */
    @org.junit.Test(expected = NoSuchElementException.class)
    public void testRemove() {
        System.out.println("remove");
        int ta = instance.size();
        assertEquals((Integer) 1, instance.remove());
        assertEquals(ta - 1, instance.size());
        int r1 = empty.remove();
    }

    /**
     * Test of poll method, of class TSBDeQueue.
     */
    @org.junit.Test
    public void testPoll() {
        System.out.println("pollFirst");
        int ta = instance.size();
        assertEquals((Integer) 1, instance.poll());
        assertEquals(ta - 1, instance.size());
        assertNull(empty.poll());
    }

    /**
     * Test of element method, of class TSBDeQueue.
     */
    @org.junit.Test(expected = NoSuchElementException.class)
    public void testElement() {
        System.out.println("element");
        assertEquals((Integer) 1, instance.element());
        int r1 = empty.element();
    }

    /**
     * Test of peek method, of class TSBDeQueue.
     */
    @org.junit.Test
    public void testPeek() {
        System.out.println("peek");
        assertEquals((Integer) 1, instance.peek());
        assertNull(empty.peek());
    }

    /**
     * Test of push method, of class TSBDeQueue.
     */
    @org.junit.Test(expected = NullPointerException.class)
    public void testPush() {
        System.out.println("push");
        instance.push(99);
        assertEquals((Integer) 99, instance.getFirst());
        instance.push(null);
    }

    /**
     * Test of pop method, of class TSBDeQueue.
     */
    @org.junit.Test(expected = NoSuchElementException.class)
    public void testPop() {
        System.out.println("pop");
        int ta = instance.size();
        assertEquals((Integer) 1, instance.pop());
        assertEquals(ta - 1, instance.size());
        int r1 = empty.pop();
    }

    /**
     * Test of descendingIterator method, of class TSBDeQueue.
     */
    @org.junit.Test (expected = NoSuchElementException.class)
    public void testDescendingIterator() {
        System.out.println("descendingIterator");
        int as = instance.size();
        Iterator r1 = instance.descendingIterator();
        assertNotEquals(null, r1);
        assertTrue(r1.hasNext());
        assertNotEquals(null, r1.next());
        r1.remove();
        assertEquals(as - 1, instance.size());
        // Next luego de un remove no se debe saltear elementos.
        assertEquals((Integer) 8, r1.next());
        // Opcional. 2 invocaciones seguida de remove deben fallar.
        try {
            r1.remove();
            r1.remove(); // Debe lanzar la excepcion aqui.
            fail(); 
        } catch (IllegalStateException e) {
            // Ok
        }
        // Si se invoca a next cuando hasNext da falso debe fallar.
        Iterator i2 = empty.iterator();
        i2.next();
    }

    /**
     * Test of clone method, of class TSBDeQueue.
     */
    @org.junit.Test
    public void testClone() {
        System.out.println("clone");
        try {
            TSBDeQueue<Integer> copy = (TSBDeQueue<Integer>) instance.clone();
            assertTrue(instance.equals(copy));
            // Test de clonado profundo
            instance.addFirst(50);
            assertEquals((Integer) 1, copy.getFirst());
        } catch (CloneNotSupportedException e) {
            // No deberia ocurrir.
            fail();
        }
    }

    /**
     * Test of equals method, of class TSBDeQueue.
     */
    @org.junit.Test
    public void testEquals() {
        System.out.println("equals");
        assertTrue(instance.equals(instance));
        assertFalse(instance.equals(empty));
        // Prueba que si tienen igual contenido de equals = true;
        assertTrue(instance.equals(instance2));
        // sin importar la cantidad de modificaciones.        
        for (int i = 0; i < 100; i++) {
            instance.addLast(1);
        }
        for (int i = 0; i < 100; i++) {
            instance.removeLast();
        }

        assertTrue(instance.equals(instance2));
    }

    /**
     * Test of hashCode method, of class TSBDeQueue.
     */
    @org.junit.Test
    public void testhashCode() {
        System.out.println("hashCode");
        assertNotEquals(empty.hashCode(), instance.hashCode());
        // Un hashcode siempre debe ser mayor a 0.
        assertNotEquals(0, empty.hashCode());
    }
}
