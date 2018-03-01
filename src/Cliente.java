import java.util.ArrayList;

public class Cliente extends Thread
{
	private ArrayList<Mensaje> mensajes;
	private boolean termino;
	private Buffer buf;
	
	public Cliente(int pNum, Buffer pBuf)
	{
		mensajes = new ArrayList<Mensaje>();	
		
		
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
			while(buf.getClientes() == buf.getCapacidad())
			{			
				yield();			
			}	
			if(mensajes.size() > 0)
			{
				enviarMensaje();	
			}
			else
			{
				termino = true;
			}
		}
		System.out.println("Yo, " + this + " termino");
	}
	
	public void enviarMensaje()
	{
		buf.entrarCliente();
		Mensaje aEnviar = mensajes.remove(mensajes.size()-1);
		buf.recibirMensaje(aEnviar);
		synchronized(buf)
		{
			buf.notifyAll();
		}
		synchronized(aEnviar)
		{		
			
			try
			{
				System.out.println("Yo, " + this + " envío " + aEnviar.getContenido());
				aEnviar.wait();
				System.out.println("Yo, " + this + " recibo " + aEnviar.getContenido());
			}
			catch(InterruptedException e)
			{
				System.out.println(e.getMessage());
			}
		}
		
		
//		buf.darRespuesta();
		
//		if(mensajes.size() <=0)
//		{
//			termino = true;
//		}
		buf.salirCliente();
	}
	
	public void recibirRespuesta(Mensaje pMensaje)
	{
		mensajes.add(pMensaje);
	}
}

