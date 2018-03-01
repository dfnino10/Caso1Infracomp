public class Servidor extends Thread
{
	private Buffer buf;
	
	public Servidor (Buffer pbuf)
	{
		
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
	
	public synchronized void responderMensaje()
	{
		Mensaje recibido = buf.delegarMensaje();
//		System.out.println("Yo, " + this + " recojo " + recibido.getContenido() + " de " + recibido.getEmisor());
		System.out.println("Yo, " + this + " recojo " + recibido.getContenido());
		recibido.setContenido(recibido.getContenido()+1);
		
		synchronized(recibido)
		{
//			System.out.println("Yo, " + this + " respondo " + recibido.getContenido() + " a " + recibido.getEmisor());
			System.out.println("Yo, " + this + " respondo " + recibido.getContenido());
			recibido.notify();
		}	
	}	

	
}
