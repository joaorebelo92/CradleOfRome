package modelo;

public class Bomba extends ElementoBonus {
	public Bomba(Bloco bloco) {
		super("/imagens/elementos/bomba.png", bloco);
	}

	@Override
	public void incrementarNivel(int numeroElementos) {	
		int i = 0;
		if(numeroElementos < 5){
			i = 1;
		}
		else{
			i = 2;
		}
		
		bloco.incrementarNivel(i);
		
	}

}
