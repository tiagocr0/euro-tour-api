package ifsc.edu.br.eurotour.apirest.services;

import ifsc.edu.br.eurotour.apirest.algorithms.BuscaAprofundamentoIterativo;
import ifsc.edu.br.eurotour.apirest.model.grafo.Arco;
import ifsc.edu.br.eurotour.apirest.model.grafo.Grafo;
import ifsc.edu.br.eurotour.apirest.model.grafo.Vertice;
import ifsc.edu.br.eurotour.apirest.model.mapeamento.Caminho;
import ifsc.edu.br.eurotour.apirest.repository.BuscaAprofundamentoIterativoRepository;

/**
 * Camada de Serviço para a Busca de Aprofundamento Iterativo. <br>
 * Delega funções a camada de acesso a dados:
 * {@link BuscaAprofundamentoIterativoRepository}.
 * 
 * @author Osmar
 *
 */
public class BuscaAprofundamentoIterativoService {

	private BuscaAprofundamentoIterativoRepository rep;

	public BuscaAprofundamentoIterativoService() {
		rep = new BuscaAprofundamentoIterativo();
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
	public Caminho buscaAprofundamentoIterativo(Grafo g, Vertice inicial, Vertice destino) {
		return rep.buscaAprofundamentoIterativo(g, inicial, destino);
	}

}
