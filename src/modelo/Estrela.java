package modelo;

public class Estrela extends ElementoBonus {
	public Estrela(Bloco bloco) {
		super("/imagens/elementos/estrela.png",bloco);
	}
//	@Override
//	public void incrementarNivel(int numeroElementos) {	
//		int i = 0;
//		if(numeroElementos < 5){
//			i = 1;
//		}
//		else{
//			i = 2;
//		}
//		
//		bloco.incrementarNivel(i);
//		
//	}
}
