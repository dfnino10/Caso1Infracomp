//David Felipe Niño		201412734
//Nicolás Mateo Hernández Rojas		201412420

import java.util.ArrayList;

public class Cliente extends Thread
{
	private ArrayList<Mensaje> mensajes;
	private boolean termino;
	private Buffer buf;
	private int idCliente;
	
	public Cliente(int pId, int pNum, Buffer pBuf)
	{
		mensajes = new ArrayList<Mensaje>();		
		idCliente = pId+1;
		buf = pBuf;
		if(pNum==0)
		{
			termino = true;
		}
		else
		{
			termino = false;
		}
		
		for(int i = 0; i < pNum; i++)
		{
			mensajes.add(new Mensaje((int)(Math.random()*99),this));
		}
	}
	
	public void run()
	{
		while(!termino)
		{	
			while(buf.getClientesActuales() == buf.getCapacidad())
			{			
				yield();			
			}	
			
			buf.entrarCliente();
			
			if(mensajes.size() > 0)
			{
				enviarMensaje();	
			}
			else
			{
				termino = true;
			}
			
			buf.salirCliente();
		}
		System.out.println("Yo, C" + getIdCliente() + " termino");
	}
	
	public void enviarMensaje()
	{		
		Mensaje aEnviar = mensajes.remove(mensajes.size()-1);
		buf.recibirMensaje(aEnviar);
		synchronized(buf)
		{
			buf.notify();
		}
		synchronized(aEnviar)
		{		
			
			try
			{
				System.out.println("Yo, C" + getIdCliente() + " envío " + aEnviar.getContenido());
				aEnviar.wait();
				System.out.println("Yo, C" + getIdCliente() + " recibo " + aEnviar.getContenido());
			}
			catch(InterruptedException e)
			{
				System.out.println(e.getMessage());
			}
		}		
	}
	
	public ArrayList<Mensaje> getMensajes()
	{
		return mensajes;
	}

	public void setMensajes(ArrayList<Mensaje> mensajes) 
	{
		this.mensajes = mensajes;
	}

	public boolean isTermino() 
	{
		return termino;
	}

	public void setTermino(boolean termino) 
	{
		this.termino = termino;
	}

	public Buffer getBuf() 
	{
		return buf;
	}

	public void setBuf(Buffer buf) 
	{
		this.buf = buf;
	}

	public int getIdCliente() 
	{
		return idCliente;
	}

	public void setIdCliente(int id) 
	{
		this.idCliente = id;
	}	
}

