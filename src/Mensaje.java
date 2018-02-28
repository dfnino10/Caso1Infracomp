
public class Mensaje 
{

	private int contenido;	

	private Cliente emisor;
	
	public Mensaje(int pContenido, Cliente pEmisor)
	{
		contenido = pContenido;
		emisor = pEmisor;
	}
	
	public int getContenido() {
		return contenido;
	}

	public void setContenido(int contenido) {
		this.contenido = contenido;
	}

	public Cliente getEmisor() {
		return emisor;
	}

	public void setEmisor(Cliente emisor) {
		this.emisor = emisor;
	}
	
	
}
