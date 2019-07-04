package modelo;

import java.util.LinkedList;

public abstract class BonusComInfluencia extends Bonus{

	public BonusComInfluencia(String nomeImagem, PainelBonus painelBonus,
			int nivelMax, int coluna) {
		super(nomeImagem, painelBonus, nivelMax, new Posicao(coluna, 0));

	}

	public void aplicarPoder(Bloco bloco) {
		resetNivel();
		bonusSelecionado = false;
		painelBonus.selecionar(null);
		painelBonus.atualizar(getPosicao());
	}

	@Override
	public void selecionar() {
		bonusSelecionado = (nivel == nivelMax - 1);

		if (bonusSelecionado) {
			nivel = nivelMax;
			painelBonus.selecionar(this);
		}
		painelBonus.atualizar(getPosicao());
	}

	public void desselecionar() {
		painelBonus.selecionar(null);
		nivel = Math.min(nivel, nivelMax - 1);
		painelBonus.atualizar(getPosicao());
	}

	public abstract LinkedList<Posicao> getPosicoesSobInfluencia(Bloco bloco);
}
