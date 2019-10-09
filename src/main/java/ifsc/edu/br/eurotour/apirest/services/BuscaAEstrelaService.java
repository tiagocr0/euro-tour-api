package ifsc.edu.br.eurotour.apirest.services;

import ifsc.edu.br.eurotour.apirest.algorithms.BuscaAEstrela;
import ifsc.edu.br.eurotour.apirest.model.grafo.Arco;
import ifsc.edu.br.eurotour.apirest.model.grafo.Grafo;
import ifsc.edu.br.eurotour.apirest.model.grafo.Vertice;
import ifsc.edu.br.eurotour.apirest.model.mapeamento.Caminho;
import ifsc.edu.br.eurotour.apirest.repository.BuscaAEstrelaRepository;

/**
 * Camada de Serviço para a Busca A*. <br>
 * Delega funções a camada de acesso a dados: {@link BuscaAEstrelaRepository}.
 * 
 * @author Osmar
 *
 */
public class BuscaAEstrelaService {
	BuscaAEstrelaRepository rep;

	public BuscaAEstrelaService() {
		rep = new BuscaAEstrela();
	}

	/**
	 * Realiza a busca A* dado um {@link Grafo}, a partir de um {@link Vertice} de
	 * origem e de destino
	 * 
	 * @param g       {@link Grafo} que contém os {@link Vertice}s e os
	 *                {@link Arco}s
	 * @param inicial {@link Vertice} que representa a origem da busca
	 * @param destino {@link Vertice} que representa o destino da busca
	 * @return {@link Caminho} que deve ser percorrido
	 */
	public Caminho buscaAEstrela(Grafo g, Vertice inicial, Vertice destino) {
		return rep.buscaAEstrela(g, inicial, destino);
	}
}
