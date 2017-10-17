package clases;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementacion propia de java.util.ArrayDeque.
 *
 * No admite null.
 * TODO: No detecta overflows cuando el tamaño de la cola crece demasiado.
 * 
 * @author juani
 * @param <E>
 */
public class TSBDeQueue<E> extends AbstractCollection<E> implements Deque<E>, Serializable, Cloneable {

    /**
     * La capacidad por defecto del arreglo.
     */
    private static final int DEFAULT_CAPACITY = 16;
    
    /**
     * El espacio que se dejará libre al reducir el tamaño del arreglo.
     */
    private static final int REMOVE_MARGIN = 8;

    protected Object[] items;
    protected int size;
    protected int modCount;

    public TSBDeQueue() {
        this(DEFAULT_CAPACITY);
    }

    public TSBDeQueue(int capacity) {
        if (capacity <= 0) {
            capacity = DEFAULT_CAPACITY;
        }
        items = new Object[capacity];
        size = 0;
        modCount = 0;
    }

    public TSBDeQueue(Collection<? extends E> c) {
        // TODO: Habria que verificar que c no contenga nulls.
        this.items = c.toArray();
        size = c.size();
        modCount = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void set(int index, E element) {
        modCount++;
        items[index] = element;
    }
    
    // METODOS DEL PARCIAL - TURNO TARDE
    public int checkDuplicates(E e){
        requireNotNull(e);
        int count = 0;
        Iterator i = iterator();
        while(i.hasNext()){
            if(e.equals(i.next())) count++;
        }
        return count;
    }
    
    
    /**
     * Agrega el elemento especificado al final de la cola.
     *
     * Es equivalente a addLast(E).
     *
     * @param e - el elemento a agregar
     * @return true (como especifica Collection.add(E))
     */
    @Override
    public boolean add(E e) {
        return offerLast(e);
    }

    @Override
    public void addFirst(E e) {
        offerFirst(e);
    }

    @Override
    public void addLast(E e) {
        offerLast(e);
    }

    private void requireNotNull(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (items.length >= minCapacity) {
            return;
        }
        Object[] replacement = new Object[minCapacity];
        System.arraycopy(items, 0, replacement, 0, size);
        items = replacement;
    }

    @Override
    public boolean offerFirst(E e) {
        requireNotNull(e);

        if (size == items.length) {
            ensureCapacity(items.length * 2);
        }
        
        System.arraycopy(items, 0, items, 1, size++);
        set(0, e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        requireNotNull(e);
        
        if (size == items.length) {
            ensureCapacity(items.length * 2);
        }
        set(size++, e);
        return true;
    }

    @Override
    public E removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E old = (E) items[0];
        System.arraycopy(items, 1, items, 0, --size);
        modCount++;
        trimCapacity();
        return old;
    }

    @Override
    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E old = (E) items[--size];
        modCount++;
        trimCapacity();
        return old;
    }

    /**
     * Elimina el elemento en la posicion indicada y reacomoda el arreglo.
     * @param index - el indice del elemento a eliminar.
     */
    protected void removeAt(int index) {
        requireValidIndex(index);
        if(index == size - 1){
            items[index] = null;
        } else {
            System.arraycopy(items, index + 1, items, index, size - 1 - index);
        }
        size--;
        modCount++;
        trimCapacity();
    }

    /**
     * Si hay mucho espacio vacio reduce el array. Cuando el array items es muy
     * grande, comparado con la cantidad de elementos que contiene, se lo
     * reduce.
     */
    private void trimCapacity() {
        if (items.length >= size * 2) {
            if (size > DEFAULT_CAPACITY) {
                Object[] replacement = new Object[size + REMOVE_MARGIN];
                System.arraycopy(items, 0, replacement, 0, size);
                items = replacement;
            }
        }
    }

    @Override
    public E pollFirst() {
        return size == 0 ? null : removeFirst();
    }

    @Override
    public E pollLast() {
        return size == 0 ? null : removeLast();
    }

    @Override
    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return (E) items[0];
    }

    @Override
    public E getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return (E) items[size - 1];
    }

    @Override
    public E peekFirst() {
        return size == 0 ? null : (E) items[0];
    }

    @Override
    public E peekLast() {
        return size == 0 ? null : (E) items[size - 1];
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        Iterator it = iterator();
        return o == null ? false : removeOccurrence(o, it);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        Iterator it = descendingIterator();
        return o == null ? false : removeOccurrence(o, it);
    }

    /**
     * Recorre la cola con un iterador dado y borra la primer ocurrencia de un
     * objeto.
     * @param o - el objeto que se desea eliminar.
     * @param it - el iterador a utilizar.
     * @return true si el objeto fue encontrado y eliminado.
     */
    private boolean removeOccurrence(Object o, Iterator it) {
        E element;
        try {
            element = (E) o;
        } catch (ClassCastException err) {
            return false; // El objeto es de otro tipo, no esta en la lista.
        }
        while (it.hasNext()) {
            if (element.equals(it.next())) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    private void requireValidIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean offer(E e) {
        return offerLast(e);
    }

    @Override
    public E remove() {
        return removeFirst();
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        TSBDeQueue<E> copy = (TSBDeQueue<E>) super.clone();
        copy.items = new Object[items.length];
        System.arraycopy(items, 0, copy.items, 0, size);
        return copy;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TSBDeQueue<?> other = (TSBDeQueue<?>) obj;
        if (this.size != other.size) {
            return false;
        }
        // Usar el iterador provee deteccion de modificaciones concurrentes
        // durante el recorrido.
        Iterator it = iterator();
        Iterator otherIt = other.iterator();
        while(it.hasNext()){
            if(!it.next().equals(otherIt.next())){
                return false;
            }
        }        
        return true;
    }     

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Arrays.deepHashCode(this.items);
        hash = 37 * hash + this.size;
        return hash;
    }

    @Override
    public Iterator<E> iterator() {
        return new StepIterator(1);
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new StepIterator(-1);
    }
   
    private class StepIterator<E> implements Iterator<E> {

        int nextItemIndex;
        int currentItemIndex;
        int expectedModCount;
        final int step;

        public StepIterator(int step) {            
            if(step == 0){
                throw new IllegalArgumentException("Step no puede ser cero.");
            }
            nextItemIndex = step > 0 ? 0 : size - 1;
            currentItemIndex = -1;
            expectedModCount = modCount;            
            this.step = step;
        }

        @Override
        public boolean hasNext() {
            // Si llega a size ya se pasó y no incrementará más.
            return step > 0 ? nextItemIndex < size : nextItemIndex >= 0;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }            
            currentItemIndex = nextItemIndex;
            nextItemIndex += step;
            return (E) items[currentItemIndex];
        }

        @Override
        public void remove() {
            if (currentItemIndex == -1) {
                // Ocurre cuando se invoca a remove() antes que next()
                // o cuando se invoca remove() 2 veces seguidas.
                throw new IllegalStateException();
            }
            removeAt(currentItemIndex);
            currentItemIndex = -1;
            if(step > 0) nextItemIndex--;
            expectedModCount++;
        }
    }
    
    public Iterator oddIndexIterator(){
        return new OddIndexIterator();
    }
    
    private class OddIndexIterator<E> implements Iterator<E>{
       int nextItemIndex;
        int currentItemIndex;
        int expectedModCount;

        public OddIndexIterator() {            
            nextItemIndex = 1;
            currentItemIndex = -1;
            expectedModCount = modCount;            
        }

        @Override
        public boolean hasNext() {
            // Si llega a size ya se pasó y no incrementará más.
            return nextItemIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }            
            currentItemIndex = nextItemIndex;
            nextItemIndex += 2;
            return (E) items[currentItemIndex];
        }

        @Override
        public void remove() {
            if (currentItemIndex == -1) {
                // Ocurre cuando se invoca a remove() antes que next()
                // o cuando se invoca remove() 2 veces seguidas.
                throw new IllegalStateException();
            }
            removeAt(currentItemIndex);
            currentItemIndex = -1;
            // Al borrar un elemento se corren todos los indices 1 lugar a la izquierda,
            // entonces los siguientes impares pasan a ser pares, actualemente se
            // retornan los nuevos impares.
            // Para evitar ese comportamiento descomente la siguiente linea.
             nextItemIndex--;
            expectedModCount++;
        }
        
    }
}
