package modelo;

import java.util.LinkedList;

import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

public class BonusRaio extends BonusDestruicaoTotal {

	public BonusRaio(PainelBonus painelBonus, int coluna) {
		super("/bonus/bonus_raio/bonus_raio_0.png", painelBonus, 7, coluna);
		this.nivel = 6; //Tirar, só está assim para testar
	}

	@Override
	public CellRepresentation getCellRepresentation() {

		nomeImagem = "/bonus/bonus_raio/bonus_raio_" + this.nivel + ".png";
		return new SingleImageCellRepresentation(nomeImagem);
	}

	@Override
	public void aplicarPoder(Bloco bloco) {
		LinkedList <Posicao> Vizinhos = getPosicoesSobInfluencia(bloco);
		painelBonus.aplicarPoder(Vizinhos);
		super.aplicarPoder(bloco);
		
	}

	@Override
	public LinkedList<Posicao> getPosicoesSobInfluencia(Bloco bloco) {

		LinkedList<Posicao> Vizinhos = new LinkedList<Posicao>();
		Posicao pos = bloco.getPosicao();

		for (int x = 0; x < 20; x++) {
			Vizinhos.add(new Posicao(x, pos.getY()));
		}
		for (int y = 0; y < 10; y++) {
			Vizinhos.add(new Posicao(pos.getX(), y));
		}
		
		return Vizinhos;
	}
}
