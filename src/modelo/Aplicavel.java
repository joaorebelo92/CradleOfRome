package modelo;

import java.util.LinkedList;

public interface Aplicavel {

	public void aplicarPoder(LinkedList<Posicao> grupo);
	public BonusComInfluencia getBonusSelecionado();

}