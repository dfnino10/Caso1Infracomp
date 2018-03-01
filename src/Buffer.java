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
	
	public Mensaje delegarMensaje()
	{
		return mensajes.remove(mensajes.size()-1);
	}
	
	public static void main(String[] args) 
	{
		System.out.println("Empieza jornada");
		
		Buffer buf = new Buffer(5,10,3);
		
		for(int i = 0; i < clientes; i++)
		{
			Cliente c = new Cliente(3, buf);
			c.start();
		}
		
		clientes = 0;
		
		for(int i = 0; i < servidores; i++)
		{
			Servidor s = new Servidor(buf);
			s.start();
		}
		
		servidores = 0;
	}

	public void recibirMensaje(Mensaje msg) 
	{
		mensajes.add(msg);
	}	
	
	public void recibirRespuesta(Mensaje pMensaje)
	{
		pMensaje.getEmisor().recibirRespuesta(pMensaje);
	}


	public int getCapacidad() {
		return capacidad;
	}


	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	public void entrarCliente()
	{
		clientes ++;
	}
	
	public void salirCliente()
	{
		clientes --;
	}
}
