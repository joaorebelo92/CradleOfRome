package modelo;

import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

public class Representacao {

	protected String nomeImagem;

	public Representacao(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public CellRepresentation getCellRepresentation() {
		return new SingleImageCellRepresentation(nomeImagem);
	}

}