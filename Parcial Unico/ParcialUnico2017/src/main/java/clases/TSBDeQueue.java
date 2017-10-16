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
 *
 * @author juani
 * @param <E>
 */
public class TSBDeQueue<E> extends AbstractCollection<E> implements Deque<E>, Serializable, Cloneable {

    private static final int DEFAULT_ARRAY_LENGHT = 16;

    protected Object[] items;
    protected int size;

    public TSBDeQueue() {
        this(DEFAULT_ARRAY_LENGHT);
    }

    public TSBDeQueue(int initialCapacity) {
        if (initialCapacity <= 0) {
            initialCapacity = DEFAULT_ARRAY_LENGHT;
        }
        items = new Object[initialCapacity];
        size = 0;
    }

    public TSBDeQueue(Collection<? extends E> c) {
        this.items = c.toArray();
        size = c.size();
    }

    @Override
    public int size() {
        return size;
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
        addLast(e);
        return true;
    }

    @Override
    public void addFirst(E e) {
        exceptOnNull(e);
        // TODO: Para optimizar: Si hay que agrandar el arreglo se hacen 2
        //       llamadas a System.arraycopy seguidas.
        ensureAddCapacity();
        System.arraycopy(items, 0, items, 1, size++);
        items[0] = e;
    }

    @Override
    public void addLast(E e) {
        exceptOnNull(e);
        ensureAddCapacity();
        items[size++] = e;
    }
    
    private void exceptOnNull(Object o){
        if (o == null){
            throw new NullPointerException();
        }
    }
    
    public void ensureCapacity(int minCapacity) {
        if (items.length >= minCapacity) {
            return;
        }
        setCapacity(minCapacity);
    }

    /**
     * Asegura que haya espacio para agregar un elemento. Si no lo hay agranda
     * el array de items.
     */
    private void ensureAddCapacity() {
        if (size == items.length) {
            setCapacity(items.length * 2);
        }
    }

    /**
     * Aumenta o disminuye el tamaño del array de items. Si la capacidad
     * solicitada es menor que la cantidad de elementos actual no hace nada.
     *
     * @param newCapacity - la capacidad solicitada
     */
    private void setCapacity(int newCapacity) {
        if (newCapacity < size) {
            return;
        }
        Object[] replacement = new Object[newCapacity];
        System.arraycopy(items, 0, replacement, 0, size);
        items = replacement;
    }

    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E old = (E) items[0];
        System.arraycopy(items, 1, items, 0, --size);
        trimCapacity();
        return old;
    }

    @Override
    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E old = (E) items[size - 1];
        size--;
        trimCapacity();
        return old;
    }

    /**
     * Si hay mucho espacio vacio reduce el array. Cuando el array items es muy
     * grande, comparado con la cantidad de elementos que contiene, se lo
     * reduce.
     */
    private void trimCapacity() {
        if (items.length >= size * 2) {
            if (size > DEFAULT_ARRAY_LENGHT) {
                setCapacity(size + (int) (DEFAULT_ARRAY_LENGHT / 2));
            }
        }
    }

    @Override
    public E pollFirst() {
        if (size == 0) {
            return null;
        }
        return removeFirst();
    }

    @Override
    public E pollLast() {
        if (size == 0) {
            return null;
        }
        return removeLast();
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
        if (size == 0) {
            return null;
        }
        return (E) items[0];
    }

    @Override
    public E peekLast() {
        if (size == 0) {
            return null;
        }
        return (E) items[size - 1];
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        if (o == null) {
            return false;
        }
        try {
            return removeOccurrence((E) o, iterator());
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        if (o == null) {
            return false;
        }
        try {
            return removeOccurrence((E) o, descendingIterator());
        } catch (ClassCastException e) {
            return false;
        }
    }

    private boolean removeOccurrence(E e, Iterator it) {
        while (it.hasNext()) {
            if (e.equals(it.next())) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    private void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(items, index + 1, items, index, size - 1 - index);
        size--;
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
        if (obj.getClass() == this.getClass()) {
            TSBDeQueue<?> other = (TSBDeQueue<?>) obj;
            if (size != other.size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (size != other.size) {
                    throw new ConcurrentModificationException();
                }
                if (!items[i].equals(other.items[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
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
        return new AscendingIterator();
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new DescendingIterator();
    }

    private class AscendingIterator<E> implements Iterator<E> {

        int nextItemIndex;
        int currentItemIndex;
        int expectedSize;

        public AscendingIterator() {
            nextItemIndex = 0;
            currentItemIndex = -1;
            expectedSize = size;
        }

        @Override
        public boolean hasNext() {
            // Si llega a size ya se pasó y no incrementará más.
            return nextItemIndex != size;
        }

        @Override
        public E next() {
            if (nextItemIndex == size) {
                throw new NoSuchElementException();
            }
            // Detecta inserciones y eliminaciones concurrentes,
            // pero no modificaciones.
            if (items[nextItemIndex] == null || size != expectedSize) {
                throw new ConcurrentModificationException();
            }
            currentItemIndex = nextItemIndex++;
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
            expectedSize--;
        }

    }

    private class DescendingIterator<E> implements Iterator<E> {

        int currentItemIndex;
        int nextItemIndex;
        int expectedSize;

        public DescendingIterator() {
            currentItemIndex = -1;
            nextItemIndex = size - 1;
            expectedSize = size;
        }

        @Override
        public boolean hasNext() {
            return nextItemIndex >= 0;
        }

        @Override
        public E next() {
            if (currentItemIndex == 0) {
                throw new NoSuchElementException();
            }
            // Detecta inserciones y eliminaciones concurrentes,
            // pero no modificaciones.
            if (size != expectedSize || items[nextItemIndex] == null) {
                throw new ConcurrentModificationException();
            }
            currentItemIndex = nextItemIndex--;
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
            expectedSize--;
        }

    }    
}
