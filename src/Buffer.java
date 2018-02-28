import java.util.ArrayList;

public class Buffer 
{
	private int capacidad;
	private static int clientes;
	private static int servidores;	
	private ArrayList<Mensaje> mensajes;
	
	public Buffer(int pCapacidad, int pClientes, int pServidores)
	{
		capacidad = pCapacidad;
		clientes = pClientes;
		servidores = pServidores;
		mensajes = new ArrayList<Mensaje>();
	}
	
	
	public static int getClientes() {
		return clientes;
	}

	public static void setClientes(int clientes) 
	{
		Buffer.clientes = clientes;
	}
	
	public int getNumMensajes()
	{
		return mensajes.size();
	}
	
	public static void main(String[] args) 
	{
		Buffer buf = new Buffer(1,1,1);
		
		for(int i = 0; i < clientes; i++)
		{
			Cliente c = new Cliente(2, buf);
			c.start();
		}
		
		for(int i = 0; i < servidores; i++)
		{
			Servidor s = new Servidor();
			s.start();
		}
	}


	public void recibirMensaje(Mensaje msg) 
	{
		mensajes.add(msg);
	}
	
}
