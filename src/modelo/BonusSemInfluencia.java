package modelo;

public abstract class BonusSemInfluencia extends Bonus {

	public BonusSemInfluencia(String nomeImagem, PainelBonus painelBonus,
			int nivelMax, Posicao posicao) {
		super(nomeImagem, painelBonus, nivelMax, posicao);
	}

	@Override
	public void selecionar() {
		aplicarPoder();		
	}

	public abstract void aplicarPoder();
	
	

}