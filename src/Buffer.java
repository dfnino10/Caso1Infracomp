//David Felipe Niño		201412734
//Nicolás Mateo Hernández Rojas		201412420

import java.util.ArrayList;

public class Buffer 
{
	private int capacidad;
	private int clientesActuales;
	private ArrayList<Mensaje> mensajes;	
	
	public Buffer(int pCapacidad)
	{
		capacidad = pCapacidad;
		clientesActuales = 0;
		mensajes = new ArrayList<Mensaje>();
	}
	
	
	public int getClientesActuales() 
	{
		return clientesActuales;
	}

	public void setClientesActuales(int clientesActuales) 
	{
		this.clientesActuales = clientesActuales;
	}
	
	public int getNumMensajes()
	{
		return mensajes.size();
	}
	
	public synchronized Mensaje delegarMensaje()
	{
		return mensajes.remove(mensajes.size()-1);
	}

	public synchronized void recibirMensaje(Mensaje msg) 
	{
		mensajes.add(msg);
	}	
	
	public int getCapacidad()
	{
		return capacidad;
	}

	public void setCapacidad(int capacidad) 
	{
		this.capacidad = capacidad;
	}
	
	public synchronized void entrarCliente()
	{
		clientesActuales ++;
	}
	
	public synchronized void salirCliente()
	{
		clientesActuales --;
	}

}
