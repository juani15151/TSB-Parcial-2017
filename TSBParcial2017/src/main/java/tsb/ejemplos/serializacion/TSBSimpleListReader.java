package tsb.ejemplos.serializacion;

/**
 *  Clase que permite recuperar desde un archivo externo un objeto de
 *  la clase TSBSimpleList que haya sido grabado por Serializacion.
 *  @author Ing. Valerio Frittelli.
 *  @version Septiembre de 2017.
 */
import java.io.*;
public class TSBSimpleListReader 
{
      private String arch = "lista.dat";
    
      /**
       * Crea un objeto SimpleListReader. Asume que el nombre del archivo desde el 
       * cual se recupera es "lista.dat".
       */
      public TSBSimpleListReader()
      {
      }
      
      /**
       * Crea un objeto SimpleListReader. Fija el nombre del archivo desde el cual 
       * se recupera con el nombre tomado como parametro.
       * @param nom el nombre del archivo a abrir para iniciar la recuperacion.
       */
      public TSBSimpleListReader(String nom)
      {
          arch = nom;
      }
      
      
      /**
       * Recupera una SimpleList desde un archivo serializado.
       * @throws TSBSimpleListIOException si se encuentra un error de IO.
       * @return una referencia al objeto recuperado.
       */
      public TSBSimpleList read() throws TSBSimpleListIOException
      {
           TSBSimpleList sl = null;
           
           try
           {
                 FileInputStream istream = new FileInputStream(arch);
                 ObjectInputStream p = new ObjectInputStream(istream);
          
                 sl = ( TSBSimpleList ) p.readObject();
                 
                 p.close();
                 istream.close();
           }
           catch (Exception e)
           {
             throw new TSBSimpleListIOException("No se pudo recuperar la lista");
           }
           
           return sl;
       }
}