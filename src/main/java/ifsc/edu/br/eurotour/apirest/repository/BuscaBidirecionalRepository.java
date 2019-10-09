package ifsc.edu.br.eurotour.apirest.repository;

import ifsc.edu.br.eurotour.apirest.model.grafo.Grafo;
import ifsc.edu.br.eurotour.apirest.model.grafo.Vertice;
import ifsc.edu.br.eurotour.apirest.model.mapeamento.Caminho;
/**
 * Camada de acesso a dados da {@link BuscaBidirecional}.<br>
 * Provê funções a camada de serviço: {@link BuscaBidirecionalService}
 * 
 * @author Osmar
 *
 */
public interface BuscaBidirecionalRepository {

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
	public Caminho buscaBidirecional(Grafo g, Vertice inicial, Vertice destino);
}
