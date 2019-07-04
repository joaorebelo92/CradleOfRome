package modelo;

import java.util.LinkedList;
import java.util.Random;

import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

public class BonusTrovao extends BonusSemInfluencia {

	private Random random;
	

	public BonusTrovao(PainelBonus painelBonus, int coluna) {
		super("/bonus/bonus_trovao/bonus_trovao_0.png", painelBonus, 9,
				new Posicao(coluna, 0));
		this.nivel = 8;
		random = new Random();
	}

	@Override
	public CellRepresentation getCellRepresentation() {

		nomeImagem = "/bonus/bonus_trovao/bonus_trovao_" + this.nivel + ".png";
		return new SingleImageCellRepresentation(nomeImagem);
	}

	@Override
	public void aplicarPoder() {
		int numeroElementos = random.nextInt(15 - 5 + 1) + 5;
		resetNivel();
		painelBonus.usarBonusTrovao(numeroElementos);
		painelBonus.atualizar(getPosicao());
		
	}

}
