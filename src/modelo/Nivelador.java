package modelo;

public class Nivelador extends Representacao {

	/**
	 * @uml.property name="nivel"
	 */
	protected int nivel;
	protected int nivelMax;

	public Nivelador(String nomeImagem, int nivel, int nivelMax) { // int nivel,
																	// int
																	// nivelMax
		super(nomeImagem);
		this.nivel = nivel;
		this.nivelMax = nivelMax;
		
	}

	public void incrementarNivel(int i) {
		
		if (this.nivel + i <= nivelMax-1) {
			this.nivel += i;
		} else {
			this.nivel = this.nivelMax-1;
		}
		
	}

	
	



//	private void setNivel(int nivel) {
//		this.nivel = nivel;
//	}

}