package modelo;

abstract class ElementoComPoder extends Elemento {

	public ElementoComPoder(String nomeImagem, Bloco bloco) {
		super(nomeImagem, bloco);
	}

	/**
	 * @param elemento 
		 */
	public abstract void incrementarNivel(int numeroElementos);

	
}