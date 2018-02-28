import java.util.ArrayList;

public class Cliente extends Thread
{
	private ArrayList<Mensaje> mensajes;
	private boolean termino;
	private Buffer buf;
	
	public Cliente(int pNum, Buffer pBuf)
	{
		mensajes = new ArrayList<Mensaje>();	
		for(int i = 0; i < pNum; i++)
		{
			mensajes.add(new Mensaje(Integer.parseInt(""+(Math.random()*pNum))));
		}
		
		buf = pBuf;
		if(mensajes.size()==0)
		{
			termino = true;
		}
		else
		{
			termino = false;
		}

	}
	
	public void run()
	{
		while(!termino)
		{			
			enviarMensaje();
		}
	}
	
	public synchronized void enviarMensaje()
	{
		buf.recibirMensaje(mensajes.remove(mensajes.size()-1));
		if(mensajes.size() <=0)
		{
			termino = true;
		}
	}
}

