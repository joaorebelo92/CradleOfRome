package modelo;

import java.util.LinkedList;

public class Grupo {
	private LinkedList<Elemento> elementos;

	public Grupo() {
		// TODO Auto-generated constructor stub
		this.elementos = new LinkedList<Elemento>();
		
	}

//	private void adicionar(Grupo grupo) {
//		elementos.addAll(grupo.elementos);
//		for (Elemento elemento : grupo.elementos) {
//			elemento.setGrupo(this);
//		}
//	}

	public void adicionar(LinkedList<Elemento> elementosAdicionar) {
		// TODO Auto-generated method stub
		elementos.addAll(elementosAdicionar);
		for (Elemento elemento : elementosAdicionar) {
			elemento.setGrupo(this);
		}
	}



	public void explodir() {
		System.out.println(elementos.size());
		if (elementos.getFirst() instanceof ElementoComPoder) {
			((ElementoComPoder) elementos.getFirst())
					.incrementarNivel(elementos.size());
		}

		for (Elemento elemento : elementos) {
			elemento.remover();
		}
		elementos.clear();

	}

	

	public void adicionarElemento(Elemento elemento) {
		elementos.add(elemento);
	}

	public int getNumElementos() {
		return elementos.size();
	}
}