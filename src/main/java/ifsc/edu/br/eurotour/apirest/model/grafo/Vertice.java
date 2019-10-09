package ifsc.edu.br.eurotour.apirest.model.grafo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe para abstrair vértices de grafos direcionados IFSC - Lages Prof.
 * Vilson Heck Junior. <br>
 * Modificado por Osmar para simplificar a classe e seu uso.
 */
public class Vertice implements Serializable {

	private static final long serialVersionUID = 1L;

	// Lista de arcos que saem do vértice
	private final ArrayList<Arco> arcos = new ArrayList<>();
	private final ArrayList<Arco> arcosHeuristica = new ArrayList<>();

	// Rótulo do vértice: serve para identificação
	private final String rotulo;

//Os quatro próximos atributos são utilizados pelos algoritmos de grafos.

	// Quando o valor de visitado for 0 (zero) significa que o vértice ainda
	// não foi visitado pelo algoritmo. Em cada nova visita o método deve invocar
	// o método visitar() para incrementar este valor. O método zerarVisitas()
	// zera este valor novamente. O método obterVisitado() informa o valor atual.
	private int visitado = 0;

	// Vários algoritmos irão medir a distância de um vértice até outro. Este
	// atributo servirá como um "medidor auxiliar de distância", armazenando
	// temporariamente distâncias nas iterações dos algoritmos. Os métodos
	// definirDistancia(), zerarDistancia() e obterDistancia() devem ser usados.
	private double distancia = Double.POSITIVE_INFINITY;
	private double distanciaHeuristica = Double.POSITIVE_INFINITY;

	// Algoritmos de caminhos podem precisar da informação de qual caminho foi
	// utilizado para se obter a distância informada. O caminho é uma String
	// Contendo os rótulos dos vértices utilizados para chegar até o vértice
	private String caminho = "";
	private String caminhoInverso = "";

	public Vertice(String rotulo) {
		this.rotulo = rotulo;
	}

	public Vertice() {
		this.rotulo = "";
	}

	/**
	 * Adiciona um {@link Arco} a esse vértice, dado um {@link Vertice} de destino e
	 * seu peso
	 * 
	 * @param destino {@link Vertice} de destino do {@link Arco}
	 * @param peso    valor em ponto flutuante do peso do {@link Arco}
	 */
	public void adicionarArco(Vertice destino, double peso) {
		this.arcos.add(new Arco(this, destino, peso));
	}

	/**
	 * Adiciona um {@link Arco} a esse vértice , dado um {@link Vertice} de destino
	 * e seu peso baseado em um valor Heurístico (teórico)
	 * 
	 * @param destino {@link Vertice} de destino do {@link Arco}
	 * @param peso    valor em ponto flutuante do peso heurístico do {@link Arco}
	 */
	public void adicionarArcoHeuristica(Vertice destino, double peso) {
		this.arcosHeuristica.add(new Arco(this, destino, peso));
	}

	/**
	 * Retorna todos os {@link Arco}s do {@link Vertice}
	 * 
	 * @return {@link ArrayList} de {@link Arco} do Vértice
	 */
	public ArrayList<Arco> obterArcos() {
		return this.arcos;
	}

	/**
	 * Retorna todos os {@link Arco}s que tem valores Heurísticos do {@link Vertice}
	 * 
	 * @return {@link ArrayList} de {@link Arco} do Vértice
	 */
	public ArrayList<Arco> obterArcosHeuristica() {
		return this.arcosHeuristica;
	}

	/**
	 * Simboliza que o {@link Vertice} já foi visitado
	 */
	public void visitar() {
		this.visitado++;
	}

	/**
	 * Retorna o valor relativo a quantas visitas foram realizadas a partir desse
	 * {@link Vertice}
	 * 
	 * @return número de vezes que foi visitado
	 */
	public int obterVisitado() {
		return this.visitado;
	}

	/**
	 * Limpa e zera o valor relacionado a se o {@link Vertice} foi visitado
	 */
	public void zerarVisitas() {
		this.visitado = 0;
	}

	/**
	 * Adiciona um valor infinito a distancia para "zerá-la"
	 */
	public void zerarDistancia() {
		this.distancia = Double.POSITIVE_INFINITY;
	}
	
	/**
	 * Adiciona um valor infinito a distancia para "zerá-la"
	 */
	public void zerarDistanciaHeuristica() {
		this.distanciaHeuristica = Double.POSITIVE_INFINITY;
	}

	/**
	 * Define o valor da distância do {@link Vertice}
	 * 
	 * @param distancia número que representa a distância do vértice da raiz
	 */
	public void definirDistancia(double distancia) {
		this.distancia = distancia;
	}

	/**
	 * Define o valor da distância heurística do {@link Vertice}
	 * 
	 * @param distancia número que representa a distância heurística do vértice da
	 *                  raiz
	 */
	public void definirDistanciaHeuristica(double distanciaEuristica) {
		this.distanciaHeuristica = distanciaEuristica;
	}

	/**
	 * Retorna o valor que representa a distância da raiz até este {@link Vertice}
	 * 
	 * @return distância total da raiz até este {@link Vertice}
	 */
	public double obterDistancia() {
		return this.distancia;
	}

	/**
	 * Retorna o valor que representa a distância heurística da raiz até este
	 * {@link Vertice}
	 * 
	 * @return distância heurística total da raiz até este {@link Vertice}
	 */
	public double obterDistanciaHeuristica(Vertice final_) {
		for (Arco arco : arcosHeuristica) {
			if (arco.getDestino().equals(final_)) {
				return arco.getPeso();
			}
		}
		return this.distanciaHeuristica;
	}

	/**
	 * Retorna qual o caminho percorrido (pais, avós, bisavos,etc.) deste
	 * {@link Vertice}
	 * 
	 * @return {@link String} contendo todo o caminho para chegar a este
	 *         {@link Vertice}
	 */
	public String getCaminho() {
		if (caminho == null || caminho.equals("")) {
			return this.rotulo;
		}
		String nome[] = this.rotulo.split(" / ");
		return caminho + " / " + nome[0].trim();
	}

	/**
	 * Atribui o rótulo ao caminho deste vértice
	 * 
	 * @param caminho {@link String} com o caminho para chegar a este
	 *                {@link Vertice}
	 */
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	/**
	 * Retorna o valor do caminho inverso do {@link Vertice} (Destino até a Origem).
	 * É utilizado para a busca heurística A*
	 * 
	 * @return {@link String} com o caminho inverso do {@link Vertice}
	 */
	public String getCaminhoInverso() {
		if (caminhoInverso == null || caminhoInverso.equals("")) {
			return this.rotulo;
		}
		return caminhoInverso + " / " + this.rotulo;
	}

	/**
	 * Atribui o valor ao caminho inverso do {@link Vertice} (Destino até a Origem).
	 * É utilizado para a busca heurística A*
	 * 
	 * @param caminhoInverso rótulo que contém o caminho inverso do {@link Vertice}
	 */
	public void setCaminhoInverso(String caminhoInverso) {
		this.caminhoInverso = caminhoInverso;
	}

	@Override
	public String toString() {
		return this.rotulo;
	}

	@Override
	public boolean equals(Object o) {
		return o.toString().equals(this.rotulo);
	}

}