package modelo;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.LinkedList;

import pt.ipleiria.estg.dei.gridpanel.GridPanel;
import pt.ipleiria.estg.dei.gridpanel.GridPanelEventHandler;

public class PainelBonus extends Painel implements GridPanelEventHandler,
		Aplicavel, PainelAtualizavel, PainelHandler {

	// private Bonus bonus;
	/**
	 * @uml.property name="bonusSeleccionado"
	 */
	private BonusComInfluencia bonusSeleccionado;
	private Bonus[] grelhaBonus; // Array
	private HashMap<String, Bonus> hashBonus;

	public PainelBonus(GridPanel gridPainel, Jogo jogo) {
		super(jogo, gridPainel);
		grelhaBonus = new Bonus[7];
		colocarBonus();
		hashBonus = new HashMap<String, Bonus>();
		hashBonus.put(Bomba.class.getSimpleName(), grelhaBonus[1]);
		hashBonus.put(Martelo.class.getSimpleName(), grelhaBonus[0]);
		hashBonus.put(Granada.class.getSimpleName(), grelhaBonus[5]);
		hashBonus.put(Ampulheta.class.getSimpleName(), grelhaBonus[3]);
		hashBonus.put(Trovao.class.getSimpleName(), grelhaBonus[4]);
		hashBonus.put(Raio.class.getSimpleName(), grelhaBonus[2]);
		hashBonus.put(Estrela.class.getSimpleName(), grelhaBonus[6]);
		gridPainel.setEventHandler(this);
	}

	/**
		 */

	public void colocarBonus() {
		grelhaBonus[1] = new BonusBomba(this, 1);
		grelhaBonus[0] = new BonusMartelo(this, 0);
		grelhaBonus[3] = new BonusAmpulheta(this, 3);
		grelhaBonus[6] = new BonusEstrela(this, 6);
		grelhaBonus[5] = new BonusGranada(this, 5);
		grelhaBonus[2] = new BonusRaio(this, 2);
		grelhaBonus[4] = new BonusTrovao(this, 4);

		actualizar();

	}

	public void incrementarNivel(int i, String nomeElemento) {

		hashBonus.get(nomeElemento).incrementarNivel(i);

		System.out.println(hashBonus.get(nomeElemento).nivel);
		atualizar(hashBonus.get(nomeElemento).getPosicao()); // ACtualizar só
																// um bonus

	}

	public void actualizar() {
		for (int i = 0; i < grelhaBonus.length; i++) {
			this.gridPainel.put(i, 0, grelhaBonus[i].getCellRepresentation());
		}
	}

	public void incrementarNivelVida(int i) {
		jogo.incrementarNivelVida(i);

	}

	public BonusComInfluencia getBonusSelecionado() {
		// TODO Auto-generated method stub
		return bonusSeleccionado;
	}

	public void selecionar(BonusComInfluencia bonusComInfluencia) {
		BonusComInfluencia anterior = bonusSeleccionado;
		bonusSeleccionado = bonusComInfluencia;
		if (anterior != null)
			anterior.desselecionar();
	}
	
	public void aplicarPoder(LinkedList<Posicao> grupo) {
		jogo.aplicarPoder(grupo);
	}

	@Override
	public void atualizar(Posicao posicao) {
		this.gridPainel.put(posicao.getX(), 0,
				grelhaBonus[posicao.getX()].getCellRepresentation());
	}

	@Override
	public void mouseDragged(MouseEvent arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent, int x, int y) {
		grelhaBonus[x].selecionar();
	}

	@Override
	public void mouseReleased(MouseEvent arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	
	/*Estão mal estes metodos*/
	public void usarBonusTrovao(int numeroElementos) {
		jogo.usarBonusTrovao(numeroElementos);
	}

	public void usarBonusEstrela(Bloco bloco) {
		jogo.usarBonusEstrela(bloco);
	}
}
