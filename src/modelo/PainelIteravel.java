package modelo;

import pt.ipleiria.estg.dei.gridpanel.GridPanel;

public abstract class PainelIteravel extends Painel implements Iteravel {

	public PainelIteravel(GridPanel gridPainel, Jogo jogo) {
		super(jogo, gridPainel);
	}

	
	//vida.baixarNivel(tempo);
	//gridPainelVida.put(0, 1, vida.getCellRepresentation(tempo));//fazer aparecer a imagem da vida consoante o tempo
	 

}