package modelo;

import pt.ipleiria.estg.dei.gridpanel.GridPanel;

public class Painel {

	/**
	 * @uml.property  name="gridPainelBonus"
	 */
	protected  Jogo jogo;
	protected  GridPanel gridPainel;
	
	public Painel(Jogo jogo, GridPanel gridPainel) {
		super();
		this.jogo = jogo;
		this.gridPainel = gridPainel;
	}





}