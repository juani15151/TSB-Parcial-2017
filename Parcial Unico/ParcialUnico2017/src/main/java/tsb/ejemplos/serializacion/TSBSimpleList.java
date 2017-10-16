package tsb.ejemplos.serializacion;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TSBSimpleList <E extends Comparable> implements Iterable<E>, Serializable
{
      private TSBNode<E> frente; 
      private int cantidad;
      
      public TSBSimpleList ()
      {
          frente = null;
          cantidad = 0;
      }
      
      public void add(int index, E x)
      {
          if( index < 0 || index > size() ) throw new IndexOutOfBoundsException( "Indice fuera del rango" );

          TSBNode <E> nuevo = new TSBNode<>( x, frente );
          if( index == 0 ) frente = nuevo;
          else
          {
            TSBNode <E> p = frente;
            for( int i = 0; i < index - 1; i++ ) { p = p.getNext(); }
            nuevo.setNext( p.getNext() );
            p.setNext( nuevo );
          }         
          
          cantidad++;
      } 
      
      public void addFirst(E x)
      {
            if ( x == null ) return;
            
            TSBNode < E > p = new TSBNode <> (x, frente);
            frente = p;
            
            cantidad++;
      }  

      public void addInOrder(E x)
      {
            TSBNode <E> nuevo = new TSBNode <>( x, null );
            TSBNode <E> p = frente, q = null;
            while ( p != null && x.compareTo( p.getInfo() ) >= 0 )
            {
                q = p;
                p = p.getNext();
            }
            nuevo.setNext( p );
            if( q != null ) q.setNext( nuevo );
            else frente = nuevo;
            
            cantidad++;
      }  
      
      public void addLast(E x)
      {
            if(x  == null) return;
            
            TSBNode <E> nuevo = new TSBNode <>( x, null );
            TSBNode <E> p = frente, q = null;
            while ( p != null )
            {
                q = p;
                p = p.getNext();
            }
            if( q != null ) q.setNext( nuevo );
            else frente = nuevo;
            
            cantidad++;
      } 

      public void clear()
      {
         frente = null;
         cantidad = 0;
      }
      
      public boolean contains(E x)
      {
          TSBNode <E> p = frente;
          while ( p != null && x.compareTo( p.getInfo() ) != 0 ) { p = p.getNext(); }    
          return ( p != null );
      }
      
      public E get( int index )
      {
          if( index < 0 || index >= size() ) throw new IndexOutOfBoundsException( "Indice fuera del rango" );
         
          TSBNode <E> p = frente;
          for( int i = 0; i < index; i++ ){  p = p.getNext(); }
          return p.getInfo();
      }
      
      public E getFirst()
      {
         if (frente == null) throw new NoSuchElementException("Error: la lista esta vacia...");
         return frente.getInfo();
      }
      
      public E getLast()
      {
         if (frente == null) throw new NoSuchElementException("Error: la lista esta vacia...");
         TSBNode <E> p = frente, q = null;
         while( p != null )
         {
            q = p;
            p = p.getNext();
         }
         return (E) (( q != null )? q.getInfo() : frente.getInfo());
      }
      
      public int indexOf( E x )
      {          
            int c = 0;
            for ( TSBNode <E> p = frente; p != null; p = p.getNext() )
            {
                if( x.compareTo( p.getInfo() ) == 0 ) return c;
                c++;
            }
            return -1;
      }
            
      public boolean isEmpty()
      {
         return (frente == null);    
      }
      
      @Override
      public Iterator<E> iterator()
      {
         return new SimpleListIterator();
      }
      
      public boolean remove( E x )
      {
          TSBNode <E> p = frente, q = null;
          while( p != null && x.compareTo( p.getInfo() ) != 0  ) 
          {
              q = p;
              p = p.getNext();
          }
          
          if( p == null ) return false; 
          if( q == null ) frente = p.getNext();
          else q.setNext( p.getNext() ); 
          
          cantidad--;
          return true;
      }
      
      public E remove( int index )
      {
          if( index < 0 || index >= size() ) throw new IndexOutOfBoundsException( "Indice fuera del rango" );

          TSBNode <E> p = frente, q = null;
          for( int i = 0; i < index; i++ ) 
          {
              q = p;
              p = p.getNext();
          }
          
          E x = p.getInfo();
          if( q == null ) frente = p.getNext();
          else q.setNext( p.getNext() );
          
          cantidad--;
          return x; 
      }
      
      public E removeLast()
      {
         if (frente == null) throw new NoSuchElementException("Error: la lista esta vacia...");
         
         TSBNode <E> p = frente, q = null;
         while( p.getNext() != null )
         {
            q = p;
            p = p.getNext();
         }
         E x = p.getInfo();
         if( q != null ) q.setNext( p.getNext() );
         else frente = p.getNext();
         
         cantidad--;
         return x;
      }
      
      public E removeFirst()
      {
         if (frente == null) throw new NoSuchElementException("Error: la lista esta vacia...");
         
         E x = frente.getInfo();
         frente = frente.getNext();
         
         cantidad--;
         return x;
      }
     
      public boolean removeFirstOccurrence( E x )
      {
          return remove(x);
      }
      
      public E search (E x)
      {
            for ( TSBNode <E> p = frente; p != null; p = p.getNext() )
            {
                if( x.compareTo( p.getInfo() ) == 0 ) return p.getInfo();
            }
            return null;
      }
      
      public E set( int index, E x )
      {
          if( index < 0 || index >= size() ) throw new NoSuchElementException( "Indice fuera del rango" );
         
          TSBNode <E> p = frente;
          for( int i = 0; i < index; i++ ) { p = p.getNext(); }
          
          E ant = p.getInfo();
          p.setInfo( x );
          return ant;
      }
      
      public int size()
      {
          return cantidad;
      }
      
      @Override
      public String toString()
      {
          TSBNode <E> p = frente;
          String res = "[ ";
          while( p != null )
          {
             res = res + p.toString();
             if ( p.getNext() != null ) res = res + " - ";
             p = p.getNext();
          }
          res = res + " ]";
          return res;
      }
    
      /**
       * Clase interna para implementar el iterador.
       */
      private class SimpleListIterator implements Iterator <E>
      {

          private TSBNode <E> actual; // patron Iterator: direccion del nodo que toca procesar.
          private TSBNode <E> previo; // direccion del nodo anterior al actual.
        
          public SimpleListIterator()
          {
             actual = null;
             previo = null;
          }
        
         @Override
         public boolean hasNext()
         {
              if (frente == null)
              {
                  return false;
              }
              if (actual != null && actual.getNext() == null)
              {
                  return false;
              }
              return true;
         }

         @Override
         public E next()
         {
            if (!hasNext())
            {
                throw new NoSuchElementException("No quedan elementos por recorrer");
            }

            if (actual == null)
            {
                actual = frente;
            } 
            else
            {
                previo = actual;
                actual = actual.getNext();
            }
            return actual.getInfo();
         }

         @Override
         public void remove()
         {
            TSBNode <E> aux = actual;
            if (actual != null)
            {
                if (previo == null)
                {
                    frente = actual.getNext();
                }
                else
                {
                    previo.setNext(actual.getNext());
                }
                actual = actual.getNext();
                aux.setNext(null);
                
                cantidad--;
            }
            else
                throw new NoSuchElementException();
         }
      }
}
