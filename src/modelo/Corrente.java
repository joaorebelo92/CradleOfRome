package modelo;

import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.OverlayCellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

public class Corrente extends Representacao {
	private int nivel;

	/**
	 * @uml.property name="nomeImagem"
	 */
	public Corrente(int nivel) {
		super("/imagens/correntes/corrente_" + nivel + ".png");
		this.nivel = nivel;
	}

	public int getNivel() {
		return nivel;
	}

	
	@Override
	public CellRepresentation getCellRepresentation() {
		return new SingleImageCellRepresentation("/imagens/correntes/corrente_" + nivel + ".png");
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
		
	}

}
