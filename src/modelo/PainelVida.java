package modelo;


import javax.swing.JOptionPane;

import pt.ipleiria.estg.dei.gridpanel.GridPanel;

public class PainelVida extends PainelIteravel {

	/**
	 * @uml.property  name="vida"
	 */
	private Vida vida;
	public PainelVida(GridPanel gridPainel, Jogo jogo) {
		super(gridPainel, jogo);
		this.gridPainel = gridPainel;
		this.vida = new Vida(); //Criar uma vida
		
	}
	@Override
	public void iterar() {
		System.out.println(this.vida.nivel);
		gridPainel.put(0, 1, vida.getCellRepresentation());
		//long tempoactual = System.currentTimeMillis();
		vida.baixarNivel();
	}
	public void incrementarNivelVida(int i) {
		
		vida.incrementarNivel(i);
	}

//	public void setBonusSeleccionado(int i) {
//		incrementarNivelVida(i);
//		
//	}
	
}
