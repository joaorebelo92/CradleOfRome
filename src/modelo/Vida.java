package modelo;

import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

public class Vida extends Nivelador {
	private long tempo;
	public Vida() {
		super("/imagens/vida/vida_20.png",20,21);
		this.tempo = System.currentTimeMillis();
	}

	//
	public void baixarNivel() {

		// System.out.println(System.currentTimeMillis()-tempo);
		if (System.currentTimeMillis() - tempo >= 10000) {
			this.nivel = this.nivel - 1;
			tempo = System.currentTimeMillis();
		}
	}

@Override
	public CellRepresentation getCellRepresentation() {
		nomeImagem = "/imagens/vida/vida_" + this.nivel + ".png";
		return new SingleImageCellRepresentation(nomeImagem);
	}
	/**
				 */

}
