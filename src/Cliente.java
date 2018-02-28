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
			mensajes.add(new Mensaje(i,this));
		}

	}
	
	public void run()
	{
		System.out.println("hola");
		while(!termino)
		{	
			System.out.println(mensajes.get(mensajes.size()-1).getContenido());
			enviarMensaje();			
		}
	}
	
	public void enviarMensaje()
	{
		Mensaje aEnviar = mensajes.remove(mensajes.size()-1);
		buf.recibirMensaje(aEnviar);
		synchronized(aEnviar)
		{
			try
			{
				aEnviar.wait();
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
	}
	
	public void recibirRespuesta(Mensaje pMensaje)
	{
		mensajes.add(pMensaje);
	}
}

