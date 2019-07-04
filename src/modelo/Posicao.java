package modelo;

public class Posicao {

	public static final Posicao[] DELTAS = { new Posicao(0, 1),
			new Posicao(-1, 1), new Posicao(1, 1),
				new Posicao(1, 0), new Posicao(-1, 0), new Posicao(0, -1)};

	/**
	 * @uml.property name="x"
	 */
	private int x;
	/**
	 * @uml.property name="y"
	 */
	private int y;

	/**
	 * @param x
	 * @param y
	 */
	public Posicao(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isAdjacente(Posicao posicaoInicial, Posicao posicaoDestino) {
		long deltax = Math.abs(posicaoDestino.getX() - posicaoInicial.getX());
		long deltay = Math.abs(posicaoDestino.getY() - posicaoInicial.getY());

		return deltax == 1 && deltay == 0 || deltay == 1 && deltax == 0;

	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj))
			return true;
		Posicao posicao = (Posicao) obj;
		return x == posicao.x && y == posicao.y;
	}

	public Posicao posicaoDelta(Posicao delta) {
		return new Posicao(x + delta.x, y + delta.y);
	}
}