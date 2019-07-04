package modelo;

import java.util.LinkedList;

import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

public class BonusGranada extends BonusDestruicaoTotal {
	public BonusGranada(PainelBonus painelBonus, int coluna) {
		super("/bonus/bonus_granada/bonus_granada_0.png", painelBonus, 10,
				coluna);
		this.nivel = 9; //Tirar, só está assim para testar
	}

	private static int[][] granadaVizinhos = { { 0, 3 }, { -1, 2 }, { 0, 2 },
			{ 1, 2 }, { -2, 1 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 2, 1 },
			{ -3, 0 }, { -2, 0 }, { -1, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 },
			{ -2, -1 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 2, -1 },
			{ -1, -2 }, { 0, -2 }, { 1, -2 }, { 0, -3 } };

	@Override
	public CellRepresentation getCellRepresentation() {

		nomeImagem = "/bonus/bonus_granada/bonus_granada_" + this.nivel
				+ ".png";
		return new SingleImageCellRepresentation(nomeImagem);
	}

	@Override
	public void aplicarPoder(Bloco bloco) {
		// TODO Auto-generated method stub
		LinkedList <Posicao> Vizinhos = getPosicoesSobInfluencia(bloco);
		painelBonus.aplicarPoder(Vizinhos);
		super.aplicarPoder(bloco);
	}

	@Override
	public LinkedList<Posicao> getPosicoesSobInfluencia(Bloco bloco) {
		LinkedList<Posicao> Vizinhos = new LinkedList<Posicao>();
		Posicao pos = bloco.getPosicao();
		Vizinhos.add(pos);
		
		for (int[] i : granadaVizinhos) {
			Vizinhos.add(new Posicao(bloco.getPosicao().getX()
					+ i[0], bloco.getPosicao().getY() + i[1]));
		}

		return Vizinhos;
	}
}
