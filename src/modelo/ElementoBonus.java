package modelo;

abstract class ElementoBonus extends ElementoComPoder {

	public ElementoBonus(String nomeImagem, Bloco bloco) {
		super(nomeImagem, bloco);

	}

	public void incrementarNivel(int numeroElementos) {
		int i=0;
		if(numeroElementos <5)
			i=1;
		if(numeroElementos >= 5)
			i=2;
		
		bloco.incrementarNivel(i);
	}

}