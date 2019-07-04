package modelo;

import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.SwingUtilities;

import org.omg.DynamicAny.DynValueOperations;

import pt.ipleiria.estg.dei.gridpanel.GridPanel;
import pt.ipleiria.estg.dei.gridpanel.GridPanelEventHandler;
import pt.ipleiria.estg.dei.utils.FileHandler;

public class PainelPrincipal extends PainelIteravel implements Iteravel,
		GridPanelEventHandler, Aplicavel, PainelAtualizavel, PainelHandler {

	private Celula[][] grelhaCelulas; // Criar gralha de blocos
	private LinkedList<Elemento> elementos;
	private LinkedList<Grupo> grupos;
	private Grupo grupoAux;

	private boolean aArrastar = false;
	private Elemento elementoATrocar;
	private Elemento elementoTrocado;
	private Posicao verificaPosicao = new Posicao(0, 0);
	private Random random;
	/********** DECLARAR ELEMENTOS FICHEIRO *********/

	private static final String VAZIO = "X";
	private static final String PAREDE = "P"; // Alterar para P quando houver
												// corrente
	private static final String ANEL = "a";
	private static final String BALDE = "b";
	private static final String BOMBA = "c";
	private static final String CAMARAO = "d";
	private static final String BIFE = "e";
	private static final String ELMO = "f";
	private static final String FOLHA = "g";
	private static final String MADEIRA = "h";
	private static final String MARTELO = "i";

	private static final String AMPULHETA = "m";
	private static final String CAVEIRA = "v";
	private static final String ESTRELA = "s";
	private static final String GRANADA = "r";
	private static final String RAIO = "o";
	private static final String TROVAO = "q";

	public PainelPrincipal(GridPanel gridPainel, Jogo jogo) {
		super(gridPainel, jogo);
		this.gridPainel = gridPainel;
		this.grelhaCelulas = new Celula[20][10];
		this.jogo = jogo;
		this.elementos = new LinkedList<>();
		this.grupos = new LinkedList<>();
		grupoAux = new Grupo();
		criaTabuleiro();
		iniciarTabuleiro();

		gridPainel.setEventHandler(this);
	}

	private void iniciarTabuleiro() {
		for (int i = 0; i < grelhaCelulas.length; i++) {
			for (int j = 0; j < grelhaCelulas[i].length; j++) {
				atualizar(grelhaCelulas[i][j].getPosicao());
			}
		}

	}

	private void criaTabuleiro() {
		// TODO Auto-generated method stub
		FileHandler handler = new FileHandler("/nivel/nivel2.txt");
		String conteudo = handler.readFile();
		String[] colunas = null;
		int y = 0;

		for (String linha : conteudo.split("\n")) {
			colunas = linha.split(" ");

			for (int x = 0; x < colunas.length; x++) {

				String nivelBloco = colunas[x].substring(0, 1);
				String tipo = colunas[x].substring(1, 2);
				String nivelCorrente = colunas[x].substring(2, 3);

				if (tipo.equals(PAREDE)) {
					Parede p = new Parede(new Posicao(x, y), this);
					adicionar(p);
				} else {
					Bloco bloco = new Bloco(Integer.parseInt(nivelBloco),
							new Posicao(x, y), this);
					switch (tipo.toLowerCase()) {
					case ANEL:
						bloco.setElemento(new Anel(bloco));
						break;
					case CAMARAO:
						bloco.setElemento(new Camarao(bloco));
						break;
					case BOMBA:
						bloco.setElemento(new Bomba(bloco));
						break;
					case BALDE:
						bloco.setElemento(new Balde(bloco));
						break;
					case BIFE:
						bloco.setElemento(new Bife(bloco));
						break;
					case ELMO:
						bloco.setElemento(new Elmo(bloco));
						break;
					case FOLHA:
						bloco.setElemento(new Folha(bloco));
						break;
					case MADEIRA:
						bloco.setElemento(new Madeira(bloco));
						break;
					case MARTELO:
						bloco.setElemento(new Martelo(bloco));
						break;

					case AMPULHETA:
						bloco.setElemento(new Ampulheta(bloco));
						break;
					case CAVEIRA:
						bloco.setElemento(new Caveira(bloco));
						break;
					case ESTRELA:
						bloco.setElemento(new Estrela(bloco));
						break;
					case GRANADA:
						bloco.setElemento(new Granada(bloco));
						break;
					case RAIO:
						bloco.setElemento(new Raio(bloco));
						break;
					case TROVAO:
						bloco.setElemento(new Trovao(bloco));
						break;
					default:
						break;
					}

					if (bloco.getElemento() != null) {
						int aux = 0;
						try {
							aux = Integer.parseInt(nivelCorrente);
						} catch (Exception e) {
							e.printStackTrace();
						}
						switch (aux) {
						case 1:
							bloco.getElemento().setCorrente(aux);
							break;
						case 2:
							bloco.getElemento().setCorrente(aux);
							break;

						default:
							break;
						}
					}

					adicionar(bloco);
				}

			}
			y++;
		}

	}

	private void adicionar(Parede parede) {
		grelhaCelulas[parede.getPosicao().getX()][parede.getPosicao().getY()] = parede;
	}

	private void adicionar(Bloco bloco) {
		grelhaCelulas[bloco.getPosicao().getX()][bloco.getPosicao().getY()] = bloco;
		if (bloco.getElemento() != null) {
			elementos.add(bloco.getElemento());
		}
	}

	private void agrupar() {
		agruparHorizontal();
		agruparVertical();
	}

	private void explodir() {
		for (Grupo grupo : grupos) {

			grupo.explodir();

		}
		grupos.clear();
	}

	private void agruparHorizontal() {
		for (Elemento elemento : elementos) {
			if (!elemento.podeCair(elemento, Posicao.DELTAS[0])
					&& !elemento.podeCair(elemento, Posicao.DELTAS[1])
					&& !elemento.podeCair(elemento, Posicao.DELTAS[2])) {
				grupoAux.adicionarElemento(elemento);
				adicionarProximo(elemento, Posicao.DELTAS[3]);

			}

		}

	}

	private void agruparVertical() {
		for (Elemento elemento : elementos) {
			if (!elemento.podeCair(elemento, Posicao.DELTAS[0])
					&& !elemento.podeCair(elemento, Posicao.DELTAS[1])
					&& !elemento.podeCair(elemento, Posicao.DELTAS[2])) {
				grupoAux.adicionarElemento(elemento);
				adicionarProximo(elemento, Posicao.DELTAS[0]);

			}

		}

	}

	private void adicionarProximo(Elemento elemento, Posicao delta) {
		Posicao posicao = elemento.getPosicao().posicaoDelta(delta);
		if (isPosicaoValida(new Posicao(posicao.getX(), posicao.getY()))
				&& grelhaCelulas[posicao.getX()][posicao.getY()] instanceof Bloco
				&& ((Bloco) grelhaCelulas[posicao.getX()][posicao.getY()])
						.getElemento() != null
				&& !(((Bloco) grelhaCelulas[posicao.getX()][posicao.getY()])
						.getElemento()).podeCair(elemento, Posicao.DELTAS[0])
				&& !(((Bloco) grelhaCelulas[posicao.getX()][posicao.getY()])
						.getElemento()).podeCair(elemento, Posicao.DELTAS[1])
				&& !(((Bloco) grelhaCelulas[posicao.getX()][posicao.getY()])
						.getElemento()).podeCair(elemento, Posicao.DELTAS[2])
				&& elemento.getNomeImagem() == ((Bloco) grelhaCelulas[posicao
						.getX()][posicao.getY()]).getElemento().getNomeImagem()) {

			grupoAux.adicionarElemento(((Bloco) grelhaCelulas[posicao.getX()][posicao
					.getY()]).getElemento());

			// Chamada recursiva
			adicionarProximo(
					((Bloco) grelhaCelulas[posicao.getX()][posicao.getY()])
							.getElemento(),
					delta);
		} else {
			if (!grupos.contains(grupoAux) && grupoAux.getNumElementos() > 2) {
				grupos.add(grupoAux);
			}
			grupoAux = new Grupo();

		}

	}

	private void aplicarSombra(LinkedList<Posicao> posicoes, boolean sombra) {
		for (Posicao posicao : posicoes) {
			if (grelhaCelulas[posicao.getX()][posicao.getY()] instanceof Bloco
					&& getBloco(posicao).getElemento() != null) {
				getBloco(posicao).setSombra(sombra);
				atualizar(posicao);
			}
		}
	}

	private void tirarSombra() {
		Posicao posicao;
		for (int i = 0; i < grelhaCelulas.length; i++) {
			for (int j = 0; j < grelhaCelulas[i].length; j++) {
				if (grelhaCelulas[i][j] instanceof Bloco) {
					posicao = new Posicao(i, j);
					getBloco(posicao).setSombra(false);
					atualizar(grelhaCelulas[i][j].getPosicao());
				}

			}
		}
	}

	private boolean isPosicaoValida(Posicao posicao) {
		int x = posicao.getX();
		int y = posicao.getY();
		return x < grelhaCelulas.length && x >= 0
				&& y < grelhaCelulas[0].length && y >= 0;
	}

	@Override
	public void iterar() {
		gerarAleatorio();
		for (Elemento elemento : elementos) {
			if (!elemento.isAcorrentado()) {
				elemento.iterar();
			}
		}
		agrupar();
		explodir();

	}

	// Mostrar na grelha
	/*
	 * (non-Javadoc)
	 * 
	 * @see modelo.PainelEventHandler#atualizar(modelo.Posicao)
	 */
	public void atualizar(Posicao p) {
		gridPainel.put(p.getX(), p.getY(),
				grelhaCelulas[p.getX()][p.getY()].getCellRepresentation());
		gridPainel.repaint();
	}

	public void aplicarPoder(LinkedList<Posicao> grupoARemover) {
		for (Posicao posicao : grupoARemover) {
			Bloco bloco = getBloco(posicao);
			if (bloco != null) {
				Elemento elemento = bloco.getElemento();
				if (elemento != null)
					elemento.remover();
			}
		}
	}

	//Está mal efectuado, mas não conseguimos de outra maneira e também não tivemos tempo :S
	public void usarBonusTrovao(int numeroElementos) {
		System.out.println(numeroElementos);
		while (numeroElementos != 0) {
			int x = random.nextInt(20);
			int y = random.nextInt(10);
			if (grelhaCelulas[x][y] instanceof Bloco
					&& ((Bloco) grelhaCelulas[x][y]).getElemento() != null) {

				((Bloco) grelhaCelulas[x][y]).getElemento().remover();
				numeroElementos--;
				atualizar(grelhaCelulas[x][y].getPosicao());
			}

		}

	}

	public void usarBonusEstrela(Bloco bloco) {
		LinkedList<Posicao> Vizinhos = new LinkedList<Posicao>();
		Posicao pos = bloco.getPosicao();
		Vizinhos.add(pos);
		for (int x = 0; x < grelhaCelulas.length; x++) {
			for (int y = 0; y < grelhaCelulas[y].length; y++) {
				if (grelhaCelulas[x][y] instanceof Bloco
						&& ((Bloco) grelhaCelulas[x][y]).getElemento() != null
						&& ((Bloco) grelhaCelulas[x][y]).getElemento().getClass().equals(bloco.getElemento().getClass())){
				//	Vizinhos.add(grelhaCelulas[x][y].getPosicao());
					Vizinhos.add(new Posicao(x,y));
				}
			}
		}
		//aplicarSombra(Vizinhos, true);s
		aplicarPoder(Vizinhos);
		
	}

	public void gerarAleatorio() {
		for (int i = 0; i < grelhaCelulas.length; i++) {
			Posicao posicaoSeleccionada = new Posicao(i, 0);
			Bloco blocoSeleccionado = getBloco(posicaoSeleccionada);
			if (blocoSeleccionado instanceof Bloco
					&& blocoSeleccionado.getElemento() == null) {

				random = new Random();
				int posicaoRandom = random.nextInt(15 - 1 + 1) + 1;
				switch (posicaoRandom) {
				case 1:
					blocoSeleccionado.setElemento(new Anel(blocoSeleccionado));
					break;
				case 2:
					blocoSeleccionado
							.setElemento(new Camarao(blocoSeleccionado));
					break;
				case 3:
					blocoSeleccionado.setElemento(new Bomba(blocoSeleccionado));
					break;
				case 4:
					blocoSeleccionado.setElemento(new Balde(blocoSeleccionado));
					break;
				case 5:
					blocoSeleccionado.setElemento(new Bife(blocoSeleccionado));
					break;
				case 6:
					blocoSeleccionado.setElemento(new Elmo(blocoSeleccionado));
					break;
				case 7:
					blocoSeleccionado.setElemento(new Folha(blocoSeleccionado));
					break;
				case 8:
					blocoSeleccionado
							.setElemento(new Madeira(blocoSeleccionado));
					break;
				case 9:
					blocoSeleccionado
							.setElemento(new Martelo(blocoSeleccionado));
					break;
				case 10:
					blocoSeleccionado.setElemento(new Ampulheta(
							blocoSeleccionado));
					break;
				case 11:
					blocoSeleccionado
							.setElemento(new Caveira(blocoSeleccionado));
					break;
				case 12:
					blocoSeleccionado
							.setElemento(new Estrela(blocoSeleccionado));
					break;
				case 13:
					blocoSeleccionado
							.setElemento(new Granada(blocoSeleccionado));
					break;
				case 14:
					blocoSeleccionado.setElemento(new Raio(blocoSeleccionado));
					break;
				case 15:
					blocoSeleccionado
							.setElemento(new Trovao(blocoSeleccionado));
					break;
				default:
					break;
				}

				atualizar(posicaoSeleccionada);
				adicionar(blocoSeleccionado);

			}
		}
	}

	/* FAzer */
	public boolean podeCair(Elemento elemento, Posicao delta) {

		Posicao destino = elemento.getBloco().getPosicao().posicaoDelta(delta);
		Celula celula = grelhaCelulas[destino.getX()][destino.getY()];
		if (delta.getX() == 0) {
			return isPosicaoValida(destino) && celula instanceof Bloco
					&& getBloco(destino).getElemento() == null;
		}

		return isPosicaoValida(destino)
				&& celula instanceof Bloco
				&& getBloco(destino).getElemento() == null
				&& grelhaCelulas[destino.getX()][destino.getY() - 1] instanceof Parede
				|| isPosicaoValida(destino)
				&& celula instanceof Bloco
				&& getBloco(destino).getElemento() == null
				&& isPosicaoValida(new Posicao(destino.getX(),
						destino.getY() - 1))
				&& grelhaCelulas[destino.getX()][destino.getY() - 1] instanceof Bloco
				&& getBloco(new Posicao(destino.getX(), destino.getY() - 1))
						.getElemento() != null
				&& getBloco(new Posicao(destino.getX(), destino.getY() - 1))
						.getElemento().isAcorrentado()

				|| isPosicaoValida(destino)
				&& celula instanceof Bloco
				&& getBloco(destino).getElemento() == null
				&& isPosicaoValida(new Posicao(destino.getX(),
						destino.getY() - 1))
				&& grelhaCelulas[destino.getX()][destino.getY() - 1] instanceof Bloco
				&& getBloco(new Posicao(destino.getX(), destino.getY() - 1))
						.getElemento() == null;

	}

	public void cair(Elemento elemento, Posicao delta) {
		// System.out.println(elemento);
		Posicao origem = elemento.getPosicao();
		Posicao destino = elemento.getPosicao().posicaoDelta(delta);
		Bloco blocoOrigem = (Bloco) grelhaCelulas[origem.getX()][origem.getY()];
		Bloco blocoDestino = (Bloco) grelhaCelulas[destino.getX()][destino
				.getY()];
		blocoDestino.setElemento(blocoOrigem.getElemento());
		blocoOrigem.setElemento(null);
		atualizar(blocoDestino.getPosicao());
		atualizar(blocoOrigem.getPosicao());
	}

	public Bloco getBloco(Posicao posicao) {
		int x = posicao.getX();
		int y = posicao.getY();

		return isPosicaoValida(posicao) && grelhaCelulas[x][y] instanceof Bloco ? (Bloco) grelhaCelulas[x][y]
				: null;

	}

	public void incrementarNivelVida(int i) {

		jogo.incrementarNivelVida(i);
	}

	public void incrementarNivel(int i, String nomeElemento) {

		jogo.incrementarNivel(i, nomeElemento);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelo.PainelEventHandler#mouseDragged(java.awt.event.MouseEvent,
	 * int, int)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see modelo.PainelHandler#mouseDragged(java.awt.event.MouseEvent, int,
	 * int)
	 */
	@Override
	public void mouseDragged(MouseEvent mouseEvent, int x, int y) {
		// Verificar Grupo

		if (aArrastar) {

			Posicao origem = elementoATrocar.getPosicao();
			Posicao destino = new Posicao(x, y);

			if (!destino.equals(origem)) {

				Celula celulaDestino = getBloco(destino);
				Celula celulaOrigem = getBloco(origem);

				if (verificaPosicao.isAdjacente(origem, destino)
						&& celulaDestino instanceof Bloco
						&& ((Bloco) celulaDestino) != null
						&& ((Bloco) celulaDestino).getElemento() != null
						&& !((Bloco) celulaDestino).getElemento()
								.isAcorrentado()) {

					elementoTrocado = ((Bloco) celulaDestino).getElemento();
					((Bloco) celulaDestino).setElemento(elementoATrocar);
					((Bloco) celulaOrigem).setElemento(elementoTrocado);
					aArrastar = false;
				}
			}
			atualizar(origem);
			atualizar(destino);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelo.PainelEventHandler#mousePressed(java.awt.event.MouseEvent,
	 * int, int)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see modelo.PainelHandler#mousePressed(java.awt.event.MouseEvent, int,
	 * int)
	 */
	@Override
	public void mousePressed(MouseEvent mouseEvent, int x, int y) {

		if (jogo.getBonusSelecionado() != null) {
			Bloco bloco = getBloco(new Posicao(x, y));

			if (bloco != null && bloco.getElemento() != null) {
				jogo.getBonusSelecionado().aplicarPoder(bloco);
				tirarSombra();
			}
		} else {
			Bloco bloco = getBloco(new Posicao(x, y));

			if (bloco != null && bloco.getElemento() != null
					&& !bloco.getElemento().isAcorrentado()) {
				elementoATrocar = bloco.getElemento();
				aArrastar = true;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelo.PainelEventHandler#mouseMoved(java.awt.event.MouseEvent, int,
	 * int)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see modelo.PainelHandler#mouseMoved(java.awt.event.MouseEvent, int, int)
	 */
	@Override
	public void mouseMoved(MouseEvent mouseEvent, int x, int y) {
		if (jogo.getBonusSelecionado() != null
				&& grelhaCelulas[x][y] instanceof Bloco) {
			Bloco bloco = getBloco(new Posicao(x, y));
			LinkedList<Posicao> posicoesSombreadas;
			tirarSombra();
			posicoesSombreadas = jogo.getBonusSelecionado()
					.getPosicoesSobInfluencia(bloco);
			if (bloco != null && bloco.getElemento() != null) {
				aplicarSombra(posicoesSombreadas, true);

			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelo.PainelEventHandler#mouseReleased(java.awt.event.MouseEvent,
	 * int, int)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see modelo.PainelHandler#mouseReleased(java.awt.event.MouseEvent, int,
	 * int)
	 */
	@Override
	public void mouseReleased(MouseEvent mouseEvent, int x, int y) {
		aArrastar = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelo.PainelEventHandler#mouseExited(java.awt.event.MouseEvent)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see modelo.PainelHandler#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	public BonusComInfluencia getBonusSelecionado() {
		return jogo.getBonusSelecionado();
	}
}