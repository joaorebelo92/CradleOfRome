package modelo;

import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.OverlayCellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

public class Bloco extends Celula {

	/**
	 * @uml.property name="nivel"
	 */
	private int nivel;
	private Elemento elemento;
	private boolean sombreado;
	private static final SingleImageCellRepresentation SOMBRA = new SingleImageCellRepresentation(
			"/imagens/blocos/sombra.png");

	public Bloco(int nivel, Posicao posicao, PainelPrincipal painelPrincipal) {
		super(posicao, painelPrincipal, "/imagens/blocos/bloco0.png");
		this.nivel = nivel;
		sombreado = false;
	}

	/**
		 */

	public Elemento getElemento() {
		return elemento;
	}

	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
		if (elemento != null)
			elemento.setBloco(this);
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}

	/**
	 * @uml.property name="elemento"
	 */

	@Override
	public CellRepresentation getCellRepresentation() {
		this.nomeImagem = "/imagens/blocos/bloco" + nivel + ".png";
		CellRepresentation representacao = super.getCellRepresentation();

		if (elemento != null) {
			representacao = new OverlayCellRepresentation(
					new SingleImageCellRepresentation(nomeImagem),
					elemento.getCellRepresentation());
		}
		if (this.sombreado) {
			representacao = new OverlayCellRepresentation(representacao, SOMBRA);
		}
		return representacao;

	}

	public boolean podeCair(Elemento elemento, Posicao delta) {
		return painelPrincipal.podeCair(elemento, delta);
	}

	public void cair(Posicao delta) {
		if (elemento != null) {
			painelPrincipal.cair(elemento, delta);
		}

	}

	public void incrementarNivelVida(int i) {

		painelPrincipal.incrementarNivelVida(i);
	}

	public void removerElemento(Elemento elemento) {
	
		if (painelPrincipal.getBonusSelecionado() instanceof BonusDestruicaoParcial) {
			if (this.elemento.getCorrente() == null) {
				setElemento(null);
				baixarNivel();
			}

			else 
				if (elemento != null) {
					elemento.removerCorrente();
				}
			
		} else if(painelPrincipal.getBonusSelecionado() instanceof BonusDestruicaoTotal) {
			setElemento(null);
			setNivel(0);

		}
		else{
			if (this.elemento.getCorrente() == null) {
				setElemento(null);
				baixarNivel();
			}

			else 
				if (elemento != null) {
					elemento.removerCorrente();
				}
		}

		painelPrincipal.atualizar(this.getPosicao());

	}

	private void baixarNivel() {

		if (this.nivel - 1 >= 0) {
			this.nivel -= 1;
		}
	}

	public void incrementarNivel(int i) {

		painelPrincipal.incrementarNivel(i, this.getElemento().getClass()
				.getSimpleName());
	}

	public void setSombra(boolean sombreado) {
		this.sombreado = sombreado;

	}

}
