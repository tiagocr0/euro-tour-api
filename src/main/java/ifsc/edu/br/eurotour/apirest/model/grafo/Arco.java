/*
    Arquivo versão 4.0
    Contempla o uso de buscas, árvores geradoras mínimas, caminho mínimo e fluxo máximo.
*/

package ifsc.edu.br.eurotour.apirest.model.grafo;

/**
 * Classe que representa uma aresta de um {@link Vertice} de origem, e outro de
 * destino, além de conter o peso da distância entre os dois.
 * 
 * @author Osmar
 *
 */
public class Arco implements Comparable<Arco> {

	private Vertice destino;
	private Vertice origem;
	private double peso;

	public Arco(Vertice origem, Vertice destino, double peso) {
		this.origem = origem;
		this.destino = destino;
		this.peso = peso;
	}

	/**
	 * Retorna o valor que representa a origem do Arco
	 * 
	 * @return {@link Vertice} de origem do arco
	 */
	public Vertice getOrigem() {
		return origem;
	}

	/**
	 * Retorna o valor que representa o destino do Arco
	 * 
	 * @return {@link Vertice} de destino do arco
	 */
	public Vertice getDestino() {
		return destino;
	}

	/**
	 * Retorna o valor que representa o peso do Arco
	 * 
	 * @return valor do peso do arco
	 */
	public double getPeso() {
		return peso;
	}

	@Override
	public String toString() {
		return this.destino.toString() + "," + this.peso;
	}

	@Override
	public int compareTo(Arco t) {
		if (this.peso < t.getPeso()) {
			return -1;
		}
		if (this.peso > t.getPeso()) {
			return 1;
		}
		return 0;
	}

}
