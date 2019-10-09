/*
    Arquivo versão 4.0
    Contempla o uso de buscas, árvores geradoras mínimas, caminho mínimo e fluxo máximo.
*/

package ifsc.edu.br.eurotour.apirest.model.grafo;

import java.util.ArrayList;

/**
 * Classe para abstrair grafos direcionados. A classe utiliza representação por
 * lista de adjacência. IFSC - Lages Prof. Vilson Heck Junior. <br>
 * Modificado por Osmar para simplificar a classe e seu uso.
 */
public class Grafo {

	private final ArrayList<Vertice> vertices = new ArrayList<>();

	public Grafo() {

	}

	/**
	 * Adiciona um {@link Vertice} ao grafo com o rótulo desejado
	 * 
	 * @param rotulo Rótulo do {@link Vertice} a ser adicionado ao grafo
	 */
	public void adicionarVertice(String rotulo) {
		Vertice novo = new Vertice(rotulo);
		vertices.add(novo);
	}

	/**
	 * Retorna todos os {@link Vertice}s presentes no Grafo
	 * 
	 * @return {@link ArrayList} de todos os {@link Vertice}s do Grafo
	 */
	public ArrayList<Vertice> obterVertices() {
		return this.vertices;
	}

	/**
	 * Obtém dado {@link Vertice} a partir de seu rótulo, caso houver dentro do
	 * Grafo
	 * 
	 * @param rotulo Rótulo do {@link Vertice} a ser buscado
	 * @return {@link Vertice} caso encontrar este no Grafo, ou null caso contrário
	 */
	public Vertice pesquisaVertice(String rotulo) {
		int indice = vertices.indexOf(new Vertice(rotulo));
		return (indice >= 0) ? vertices.get(indice) : null;
	}

	/**
	 * Retorna todos os {@link Arco} presentes no Grafo
	 * 
	 * @return {@link ArrayList} contendo todos os {@link Arco}s do Grafo
	 */
	public ArrayList<Arco> obterTodosOsArcos() {
		ArrayList<Arco> resultado = new ArrayList<>();
		for (Vertice vertice : vertices) {
			resultado.addAll(vertice.obterArcos());
		}
		return resultado;
	}

	/**
	 * Para cada um dos {@link Vertice}s presentes no Grafo, zera sua distância, se
	 * já foi visitado e seus pais, avôs, etc.
	 * 
	 * @param aGrafo
	 */
	public static void reiniciarGrafo(Grafo aGrafo) {
		for (Vertice lVertice : aGrafo.obterVertices()) {
			lVertice.zerarVisitas();
			lVertice.zerarDistancia();
			lVertice.setCaminho("");
			lVertice.zerarDistanciaHeuristica();
			lVertice.setCaminhoInverso("");
		}
	}
}