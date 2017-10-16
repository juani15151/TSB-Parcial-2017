package tsb.ejemplos.serializacion;
import java.io.*;

public class  Persona implements Comparable<Persona>, Serializable
{
   private String nombre;
   private int edad;
   
   public Persona(String nom, int ed)
   {
     nombre = nom;
     edad   = ed;
   }

   public String getNombre()
   {
     return nombre;
   }

   public int getEdad()
   {
     return edad;
   }

   public void setNombre(String nom)
   {
     nombre = nom;
   }

   public void setEdad(int ed)
   {
     edad = ed;
   }

   @Override
   public String toString()
   {
     return "\nNombre: " + nombre + "\tEdad: " + edad;
   }

   @Override
   public int compareTo(Persona x)
   {
       return nombre.compareTo(x.getNombre());
   }
}
