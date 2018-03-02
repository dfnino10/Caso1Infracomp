//David Felipe Ni�o		201412734
//Nicol�s Mateo Hern�ndez Rojas		201412420

//N�tese que se debe leer el documento adjunto a la soluci�n del caso (explicaci�n de su
//funcionamiento) para comprender las impresiones y la ejecuci�n del programa.

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main 
{
	private static int numClientes;
	private static int numServidores;
	private static int numMensajesPorCliente;
	
	public Main()
	{
		
	}
	
	public static void main(String[] args) 
	{
		System.out.println("Empieza jornada");		
		
		try
		{
			inicializar();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
		
		Buffer buf = new Buffer(1);
		
		for(int i = 0; i < numClientes; i++)
		{
			Cliente c = new Cliente(i, numMensajesPorCliente, buf);
			c.start();
		}		
		
		for(int i = 0; i < numServidores; i++)
		{
			Servidor s = new Servidor(i, buf);
			s.start();
		}
	}
	
	public static void inicializar() throws IOException
	{
		File archivo = new File("./data/datos.txt");	
		FileReader reader = new FileReader( archivo );		
		BufferedReader lector = new BufferedReader( reader );
		numClientes = Integer.valueOf(lector.readLine());
		numServidores = Integer.valueOf(lector.readLine());
		numMensajesPorCliente = Integer.valueOf(lector.readLine());
		lector.close( );
		reader.close( );		
	}
}
