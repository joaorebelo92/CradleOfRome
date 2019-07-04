package modelo;

public class Celula extends Representacao {

	protected PainelPrincipal painelPrincipal;
	/**
	 * @uml.property  name="posicao"
	 */
	protected Posicao posicao;

	public Celula(Posicao posicao, PainelPrincipal painelPrincipal, String nomeImagem) {
		super(nomeImagem);
		this.posicao=posicao;
		this.painelPrincipal=painelPrincipal;
	}

	public Posicao getPosicao() {
		return this.posicao;
	}

}