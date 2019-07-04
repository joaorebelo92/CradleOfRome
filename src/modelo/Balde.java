package modelo;

public class Balde extends ElementoComPoder {
	public Balde(Bloco bloco) {
		super("/imagens/elementos/balde.png", bloco);
	}

	@Override
	public void incrementarNivel(int numeroElementos) {
		bloco.incrementarNivelVida(numeroElementos - 2);
		
	}

	/**
		 */

}
