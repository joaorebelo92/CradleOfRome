package modelo;

import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

public class BonusAmpulheta extends BonusSemInfluencia {

	public BonusAmpulheta(PainelBonus painelBonus, int coluna) {
		super("/bonus/bonus_ampulheta/bonus_ampulheta_0.png", painelBonus, 8, new Posicao(coluna, 0));
		this.nivel = 7; //Tirar, só está assim para testar
		
	}

	@Override
	public void aplicarPoder() {
		resetNivel();
		painelBonus.incrementarNivelVida(10);
		painelBonus.atualizar(getPosicao());
		
	}
	@Override
	public CellRepresentation getCellRepresentation() {

		nomeImagem = "/bonus/bonus_ampulheta/bonus_ampulheta_" + this.nivel + ".png";
		return new SingleImageCellRepresentation(nomeImagem);
	}
	
}
