package ifsc.edu.br.eurotour.apirest.model.estruturadados;

import ifsc.edu.br.eurotour.apirest.model.grafo.Vertice;

/**
 * Classe que representa uma Lista Disciplinada - Pilha
 * 
 * @author Osmar
 *
 */
public class Pilha {

	private Nodo topo;

	public Pilha() {
		this.topo = null;
	}

	/**
	 * Retorna o valor do topo da pilha
	 * 
	 * @return {@link Nodo} presente no topo da pilha
	 */
	public Nodo getTopo() {
		return topo;
	}

	/**
	 * Adiciona num novo {@link Nodo} ao topo da pilha
	 * 
	 * @param topo {@link Nodo} a ser adicionado a pilha
	 */
	public void setTopo(Nodo topo) {
		this.topo = topo;
	}

	/**
	 * Verifica se a Pilha está vazia
	 * 
	 * @return true se a pilha estiver vazia, e false caso contrário
	 */
	public boolean estaVazia() {
		return this.topo == null;
	}

	/**
	 * Insere um vértice ao topo da pilha
	 * 
	 * @param texto {@link Vertice} a ser inserido a pilha
	 */
	public void push(Vertice texto) {
		Nodo novoNodo = new Nodo(texto);
		novoNodo.setProximo(this.topo);
		this.topo = novoNodo;
	}

	/**
	 * Retira o {@link Vertice} do topo da Pilha e o retorna
	 * 
	 * @return {@link Vertice} que foi retirado
	 */
	public Vertice pop() {
		if (!this.estaVazia()) {
			Vertice aux = this.topo.getInfo();
			this.topo = this.topo.getProximo();
			return aux;
		} else {
			return null;
		}
	}

}
