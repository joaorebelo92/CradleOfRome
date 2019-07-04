package modelo;

import java.util.LinkedList;

import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.OverlayCellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

public class BonusMartelo extends BonusDestruicaoParcial {

	public BonusMartelo(PainelBonus painelBonus, int coluna) {
		super("/bonus/bonus_martelo/bonus_martelo_0.png", painelBonus, 5,coluna);
		this.nivel = 4; //Tirar, só está assim para testar
	}

	/**
	 */
	@Override
	public void aplicarPoder(Bloco b)
	{
		b.getElemento().remover();
		super.aplicarPoder(b);
	}

	@Override
	public CellRepresentation getCellRepresentation() {
		nomeImagem = "/bonus/bonus_martelo/bonus_martelo_" + this.nivel
				+ ".png";
		return new SingleImageCellRepresentation(nomeImagem);
	}

	@Override
	public LinkedList<Posicao> getPosicoesSobInfluencia(Bloco bloco) {
		LinkedList<Posicao> Vizinhos = new LinkedList<Posicao>();
		Posicao pos = bloco.getPosicao();
		Vizinhos.add(pos);
		return Vizinhos;
	}
}