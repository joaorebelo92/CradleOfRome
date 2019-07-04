package modelo;



public class Parede extends Celula {
	public Parede(Posicao posicao, PainelPrincipal painelPrincipal) {
		super(posicao,painelPrincipal, "/imagens/paredes/parede.png");
		this.painelPrincipal = painelPrincipal;
	}
	
}
