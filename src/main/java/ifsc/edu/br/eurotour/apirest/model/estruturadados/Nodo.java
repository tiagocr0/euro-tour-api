package ifsc.edu.br.eurotour.apirest.model.estruturadados;

import ifsc.edu.br.eurotour.apirest.model.grafo.Vertice;

/**
 * Classe que representa um Nodo, no qual é um {@link Vertice}
 * 
 * @author Osmar
 *
 */
public class Nodo {

	private Vertice info;
	private Nodo proximo;

	public Nodo(Vertice x) {
		this.info = x;
		this.proximo = null;
	}

	/**
	 * Retorna o {@link Vertice} do Nodo
	 * 
	 * @return rótudo do Nodo
	 */
	public Vertice getInfo() {
		return info;
	}

	/**
	 * Adiciona um {@link Vertice} novo ao Nodo
	 * 
	 * @param x novo {@link Vertice} a ser substituído
	 */
	public void setInfo(Vertice x) {
		this.info = x;
	}

	/**
	 * Retorna o valor relativo ao próximo Nodo que este aponta
	 * 
	 * @return {@link Nodo} do próximo
	 */
	public Nodo getProximo() {
		return proximo;
	}

	/**
	 * Adiciona um {@link Nodo} novo ao próximo
	 * 
	 * @param proximo novo {@link Nodo} a ser substituído
	 */
	public void setProximo(Nodo proximo) {
		this.proximo = proximo;
	}

	@Override
	public String toString() {
		return "Nodo [info: " + this.info + "]";
	}

}
