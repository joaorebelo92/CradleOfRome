package modelo;

import java.util.LinkedList;

import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

public class BonusBomba extends BonusDestruicaoParcial {

	public BonusBomba(PainelBonus painelBonus, int coluna) {
		super("/bonus/bonus_bomba/bonus_bomba_0.png", painelBonus, 6, coluna);
		this.nivel = 5; //Tirar, só está assim para testar
	}
	/**
	 * @return
	 */

	@Override
	public void aplicarPoder(Bloco bloco) {
		
		LinkedList <Posicao> Vizinhos = getPosicoesSobInfluencia(bloco);
		painelBonus.aplicarPoder(Vizinhos);
		super.aplicarPoder(bloco);
	}
	
	public LinkedList<Posicao> getPosicoesSobInfluencia(Bloco bloco){
		LinkedList<Posicao> Vizinhos = new LinkedList<Posicao>();
		
		Posicao pos = bloco.getPosicao();
		
		Vizinhos.add(pos);
		Vizinhos.add(new Posicao(pos.getX()+1,pos.getY()));
		Vizinhos.add(new Posicao(pos.getX()+1,pos.getY()+1));
		Vizinhos.add(new Posicao(pos.getX()+1,pos.getY()-1));
		Vizinhos.add(new Posicao(pos.getX()-1,pos.getY()));
		Vizinhos.add(new Posicao(pos.getX()-1,pos.getY()+1));
		Vizinhos.add(new Posicao(pos.getX()-1,pos.getY()-1));
		Vizinhos.add(new Posicao(pos.getX(),pos.getY()+1));
		Vizinhos.add(new Posicao(pos.getX(),pos.getY()-1));
		
		return Vizinhos;
	}

	@Override
	public CellRepresentation getCellRepresentation() {
		nomeImagem = "/bonus/bonus_bomba/bonus_bomba_" + this.nivel + ".png";
		return new SingleImageCellRepresentation(nomeImagem);
	}
}