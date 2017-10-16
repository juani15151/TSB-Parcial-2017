package tsb.ejemplos.serializacion;

import java.util.Scanner;
public class Principal
{
   private static TSBSimpleList<Persona> sl;

   static public void grabar() 
   {
       sl = new TSBSimpleList<>();
       Persona a,b,c;
       a = new Persona("Juan", 20);
       b = new Persona("Luis", 30);
       c = new Persona("Ana", 40);
       sl.addFirst(a);
       sl.addFirst(b);
       sl.addFirst(c);
       System.out.println(sl);
       
       try
       {
           TSBSimpleListWriter slw = new TSBSimpleListWriter();
           slw.write( sl );
       }
       catch(TSBSimpleListIOException e)
       {
            System.out.println("Error: " + e.getMessage());    
       }
   }

   static public void leer()
   {
       try
       {
           TSBSimpleListReader slr = new TSBSimpleListReader();
           sl = (TSBSimpleList<Persona>) slr.read();
           
           System.out.println(sl);
        }
        catch( TSBSimpleListIOException e)
        {
            System.out.println("Error: " + e.getMessage());    
        }
   }
  
   public static void main(String [] args) 
   {
       Scanner sc = new Scanner(System.in);
       int op;
       do
       {
          System.out.println("1. Grabar");
          System.out.println("2. Recuperar");
          System.out.println("3. Probar");
          System.out.println("4. Salir");
          System.out.print("Ingrese opcion: ");
          op = sc.nextInt();
          switch(op)
          {
             case 1:   grabar();  
                       break;

             case 2:   leer();
                       break;

             case 3:   System.out.println(sl);
          }
       }
       while(op != 4);
   }
}

