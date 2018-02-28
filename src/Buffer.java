
public class Buffer 
{
	private int capacidad;
	private static int clientes;
	private static int servidores;
	
	public Buffer(int pCapacidad, int pClientes, int pServidores)
	{
		capacidad = pCapacidad;
		clientes = pClientes;
		servidores = pServidores;
	}
	
	public static void main(String[] args) 
	{
		Buffer buf = new Buffer(1,1,1);
		
		for(int i = 0; i < clientes; i++)
		{
			Cliente c = new Cliente(2);
			c.start();
		}
		
		for(int i = 0; i < servidores; i++)
		{
			Servidor s = new Servidor();
			s.start();
		}
	}
	
}
