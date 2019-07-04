package modelo;

import java.util.LinkedList;

import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.OverlayCellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

public class Elemento extends Representacao implements Iteravel {

	/**
	 * @uml.property name="corrente"
	 */
	protected Corrente corrente;
	protected Bloco bloco;
	protected Grupo grupo;

	public Elemento(String nomeImagem, Bloco bloco) {
		super(nomeImagem);
		this.bloco = bloco;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelo.Iteravel#iterar()
	 */
	@Override
	public void iterar() {
		
		if (podeCair(this, Posicao.DELTAS[0])) {
			bloco.cair(Posicao.DELTAS[0]);
		} else if (podeCair(this, Posicao.DELTAS[1])) {
			bloco.cair(Posicao.DELTAS[1]);
		} else if (podeCair(this, Posicao.DELTAS[2])) {
			bloco.cair(Posicao.DELTAS[2]);
		}
	}

	public boolean podeCair(Elemento elemento, Posicao delta) {
		return !isAcorrentado() && bloco.podeCair(elemento, delta);
	}

	public void setCorrente(int nivel) {
		this.corrente = new Corrente(nivel);
	}

	@Override
	public CellRepresentation getCellRepresentation() {
		if (corrente != null) {
			return new OverlayCellRepresentation(
					new SingleImageCellRepresentation(nomeImagem),
					corrente.getCellRepresentation());
		}
		return new SingleImageCellRepresentation(nomeImagem);
	}

	public boolean isAcorrentado() {
		return corrente != null;
	}

	public Posicao getPosicao() {
		return bloco.getPosicao();
	}

	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}

	public boolean isSemelhante(Elemento elemento) {
		return (this.getClass().getSimpleName()).equals(elemento.getClass()
				.getSimpleName());
	}

	public boolean isAgrupado() {
		return grupo != null;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;

	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void remover() {
		bloco.removerElemento(this);

	}

	public boolean podeCair() {
		return podeCair(this, Posicao.DELTAS[0])
				&& podeCair(this, Posicao.DELTAS[1])
				&& podeCair(this, Posicao.DELTAS[2]);
	}

	public void removerCorrente() {
		if (this.corrente != null) {
			if (this.corrente.getNivel() == 2) {
				this.corrente.setNivel(1);
				
			} 
			else if (this.corrente.getNivel() == 1)
					this.corrente = null;
			
		}
	}

	public Bloco getBloco() {
		return bloco;
	}

	public Corrente getCorrente() {
		
		return corrente;
	}

}
