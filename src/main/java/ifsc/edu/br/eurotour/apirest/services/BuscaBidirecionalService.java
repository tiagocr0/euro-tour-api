package ifsc.edu.br.eurotour.apirest.services;

import ifsc.edu.br.eurotour.apirest.algorithms.BuscaBidirecional;
import ifsc.edu.br.eurotour.apirest.model.grafo.Arco;
import ifsc.edu.br.eurotour.apirest.model.grafo.Grafo;
import ifsc.edu.br.eurotour.apirest.model.grafo.Vertice;
import ifsc.edu.br.eurotour.apirest.model.mapeamento.Caminho;
import ifsc.edu.br.eurotour.apirest.repository.BuscaBidirecionalRepository;

/**
 * Camada de Serviço para a Busca Bidirecional. <br>
 * Delega funções a camada de acesso a
 * dados:{@link BuscaBidirecionalRepository}.
 * 
 * @author Osmar
 *
 */
public class BuscaBidirecionalService {

	public BuscaBidirecionalRepository rep;

	public BuscaBidirecionalService() {
		rep = new BuscaBidirecional();
	}

	/**
	 * Realiza a busca de aprofundamento iterativo dado um {@link Grafo}, a partir
	 * de um {@link Vertice} de origem e de destino
	 * 
	 * @param g       {@link Grafo} que contém os {@link Vertice}s e os
	 *                {@link Arco}s
	 * @param inicial {@link Vertice} que representa a origem da busca
	 * @param destino {@link Vertice} que representa o destino da busca
	 * @return {@link Caminho} que deve ser percorrido
	 */
	public Caminho buscaBidirecional(Grafo g, Vertice inicial, Vertice destino) {
		return rep.buscaBidirecional(g, inicial, destino);
	}
}
