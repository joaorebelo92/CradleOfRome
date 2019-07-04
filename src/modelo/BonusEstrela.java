package modelo;

import java.util.LinkedList;

import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

public class BonusEstrela extends BonusDestruicaoTotal {
	public BonusEstrela(PainelBonus painelBonus, int coluna) {
		super("/bonus/bonus_estrela/bonus_estrela_0.png", painelBonus, 12,
				coluna);
		this.nivel = 11; //Tirar, só está assim para testar
	}

	@Override
	public CellRepresentation getCellRepresentation() {

		nomeImagem = "/bonus/bonus_estrela/bonus_estrela_" + this.nivel
				+ ".png";
		return new SingleImageCellRepresentation(nomeImagem);
	}

	@Override
	public void aplicarPoder(Bloco bloco) {
		// TODO Auto-generated method stub
		

		painelBonus.usarBonusEstrela(bloco);
		super.aplicarPoder(bloco);
		
		
		resetNivel();
//		painelBonus.usarBonusTrovao();
//		painelBonus.atualizar(getPosicao());
	}

	@Override
	public LinkedList<Posicao> getPosicoesSobInfluencia(Bloco bloco) {
		
		LinkedList<Posicao> Vizinhos = new LinkedList<Posicao>();
		
		Posicao posBloco = bloco.getPosicao();
		Vizinhos.add(posBloco);
		
		
		return Vizinhos;
	}
}
