package modelo;

public abstract class Bonus extends Nivelador {

	protected PainelBonus painelBonus;
	private Posicao posicao;
	protected boolean bonusSelecionado;

	// Array de bonus

	/**
	 * @uml.property name="nivelMax"
	 */

	public Bonus(String nomeImagem, PainelBonus painelBonus, int nivelMax, Posicao posicao) {
		super(nomeImagem,0,nivelMax);
		this.painelBonus = painelBonus;
		this.posicao = posicao;
		bonusSelecionado = false;
	}

	/**
	 */

	public Posicao getPosicao() {
		return posicao;
	}

	/**
			 */
	public abstract void selecionar();

	public boolean isActivo() {
		// TODO Auto-generated method stub
		return bonusSelecionado;
	}

	public void resetNivel() {
		this.nivel = 0;
		bonusSelecionado=false;
	}

	

}