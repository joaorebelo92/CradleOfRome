package modelo;

public class Ampulheta extends ElementoBonus{
	
		public Ampulheta(Bloco bloco) {
			super("/imagens/elementos/ampulheta.png",bloco);
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

