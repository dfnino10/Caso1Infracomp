
public class Cliente extends Thread
{
	private Mensaje[] mensajes;
	
	public Cliente(int pNum)
	{
		mensajes = new Mensaje[pNum];	
		for(int i = 0; i < pNum; i++)
		{
			mensajes[i]=new Mensaje(Integer.parseInt(""+(Math.random()*pNum)));
		}
	}
	
	

}


class Mensaje
{
	private int contenido;
	
	public Mensaje(int pContenido)
	{
		contenido = pContenido;
	}
}