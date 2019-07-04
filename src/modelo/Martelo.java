package modelo;

public class Martelo extends ElementoBonus {
	public Martelo(Bloco bloco) {
		super("/imagens/elementos/martelo.png",bloco);
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
