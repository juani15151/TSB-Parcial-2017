package tsb.ejemplos.serializacion;
import java.io.Serializable;

public class TSBNode <E extends Comparable> implements Serializable
{
   private E info;
   private TSBNode<E> next;
   
   public TSBNode()
   {
   }
   
   public TSBNode(E x, TSBNode <E> p)
   {
     info = x;
     next = p;
   }
   
   public TSBNode<E> getNext()
   {
     return next;
   }
   
   public void setNext(TSBNode<E> p)
   {
     next = p;
   }
   
   public E getInfo()
   {
     return info;
   }
   
   public void setInfo(E p)
   {
     info = p;
   }

   @Override
   public String toString()
   {
     return info.toString();   
   }
}

