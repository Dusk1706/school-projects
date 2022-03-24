package PooSeis;
import java.io.*;
public class Leer
{
	public static String dato()
	{
		String sdato = "";
		try
		{
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader nose = new BufferedReader(isr);
			sdato =nose.readLine() ;
		}
		catch(IOException e)
		{
			System.err.println("Error:"+ e.getMessage());
		}
		return sdato;
	}
	public static short datoShort()
	{
	
		try
		{
			String sd=dato();
			if (sd==null)
			{
				System.out.println();
   			    return Short.MIN_VALUE;
			}
			else
			   return Short.parseShort(sd);  // antes return Short.parseShort(dato());
		}
		catch(NumberFormatException e)
		{
			// return Short.MIN_VALUE;
			System.out.print("Dato invalido, volver a teclear el dato: ");
			return datoShort();
		}
	}
	public static int datoInt()
	{
		try
		{
			return Integer.parseInt(dato());
		}
		catch(NumberFormatException e)
		{
            // return Integer.MIN_VALUE;
			System.out.print("No seas bruto, te pedi un dato entero: ");
			return datoInt();
		}
	}
	public static long datoLong()
	{
		try
		{
			return Long.parseLong(dato());
		}
		catch(NumberFormatException e)
		{
			//return Long.MIN_VALUE;
			System.out.print("Dato invalido, volver a teclear el dato: ");
			return datoLong();
		}
	}
	public static float datoFloat()
	{
		try
		{
			Float f = new Float(dato());
			return f.floatValue();
		}
		catch(NumberFormatException e)
		{
			if(dato()==null)
			return Float.NaN;
			else
			{
			System.out.print("Dato invalido, volver a teclear el dato: ");
			return datoFloat();
			}
		}
	}
	public static double datoDouble()
	{
		String cadena=dato();
		try
		{
			return Double.parseDouble(cadena);
			// Double d = new Double(dato());
			// return d.doubleValue();
		}
		catch(NumberFormatException e)
		{
			
			if(cadena.length()==0)
			   return Double.NaN;
			else
			{
				System.out.print("Dato invalido, volver a teclear el dato: ");
				return datoDouble();
			}
		}
	}
	public static char datocar()
	{
		char sdato=' ';
		try
		{
			sdato=(char)System.in.read();
			System.in.skip(System.in.available());
		}
		catch(IOException e)
		{
			System.err.println("Error:"+ e.getMessage());
		}
		return sdato;
	}

}
