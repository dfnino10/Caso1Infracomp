public class Servidor extends Thread
{
	private Buffer buf;
	
	public Servidor (Buffer pbuf)
	{
		buf = pbuf;
	}
	
	public void run()
	{
		while(buf.getNumMensajes() > 0)
		{
			responderMensaje();
		}
	}
	
	public synchronized void responderMensaje()
	{
		Mensaje recibido = buf.delegarMensaje();
		recibido.setContenido(recibido.getContenido()+1);
		buf.recibirRespuesta(recibido);
		System.out.println(recibido.getContenido());
		synchronized(recibido)
		{
			recibido.notify();
		}	
	}	

	
}
