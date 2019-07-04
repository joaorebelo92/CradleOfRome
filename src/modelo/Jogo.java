package modelo;

import java.util.LinkedList;

import pt.ipleiria.estg.dei.gridpanel.GridPanel;

public class Jogo implements Iteravel, Aplicavel, PainelAtualizavel {

	public Jogo(GridPanel painelPrincipal, GridPanel painelBonus,
			GridPanel painelVida) {
		super();
		this.painelPrincipal = new PainelPrincipal(painelPrincipal, this);
		this.painelBonus = new PainelBonus(painelBonus, this);
		this.painelVida = new PainelVida(painelVida, this);
		this.pontuacao = 0; // Começa a 0
	}

	/**
	 * @uml.property name="painelPrincipal"
	 */
	private PainelPrincipal painelPrincipal;
	/**
	 * @uml.property name="painelBonus"
	 */
	private PainelBonus painelBonus;
	/**
	 * @uml.property name="painelVida"
	 */
	private PainelVida painelVida;

	public void iterar() {

		this.painelVida.iterar();
		this.painelPrincipal.iterar();

	}

	/**
	 * @uml.property name="pontuacao"
	 */
	private int pontuacao;

	public void incrementarNivelVida(int i) {
		painelVida.incrementarNivelVida(i);
	}

	public void incrementarNivel(int i, String nomeElemento) {
		
		painelBonus.incrementarNivel(i, nomeElemento);
		
	}

	public BonusComInfluencia getBonusSelecionado() {
		return painelBonus.getBonusSelecionado();
	}

	/* (non-Javadoc)
	 * @see modelo.Aplicavel#aplicarPoder(java.util.LinkedList)
	 */
	public void aplicarPoder(LinkedList<Posicao> grupo) {
		painelPrincipal.aplicarPoder(grupo);
	}

	@Override
	public void atualizar(Posicao posicao) {
		painelBonus.atualizar(posicao);
	}

	public void usarBonusTrovao(int numeroElementos) {
		painelPrincipal.usarBonusTrovao(numeroElementos);
	}

	public void usarBonusEstrela(Bloco bloco) {
		painelPrincipal.usarBonusEstrela(bloco);
	}


}
