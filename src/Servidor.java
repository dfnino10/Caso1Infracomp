//David Felipe Niño		201412734
//Nicolás Mateo Hernández Rojas		201412420

public class Servidor extends Thread
{
	private Buffer buf;
	private int idServidor;
	
	public Servidor (int pId, Buffer pbuf)
	{
		idServidor = pId+1;
		buf = pbuf;
	}
	
	public void run()
	{
		while(true)
		{
			while(buf.getNumMensajes() == 0)
			{	
				synchronized(buf)
				{
					try
					{
						buf.wait();
					}
					catch(InterruptedException e)
					{
						System.out.println("El cliente no pudo dormir");
					}
				}												
			}	
			
			responderMensaje();									
		}		
	}
	
	public void responderMensaje()
	{
		
		Mensaje recibido = buf.delegarMensaje();
		
		System.out.println("Yo, S" + getIdServidor() + " recojo " + recibido.getContenido() + " de C" + recibido.getEmisor().getIdCliente());

		recibido.setContenido(recibido.getContenido()+1);
		
		synchronized(recibido)
		{
			System.out.println("Yo, S" + getIdServidor() + " respondo " + recibido.getContenido() + " a C" + recibido.getEmisor().getIdCliente());
			recibido.notify();
		}	
	}

	public Buffer getBuf() 
	{
		return buf;
	}

	public void setBuf(Buffer buf) 
	{
		this.buf = buf;
	}

	public int getIdServidor() 
	{
		return idServidor;
	}

	public void setIdServidor(int id) 
	{
		this.idServidor = id;
	}		
}
