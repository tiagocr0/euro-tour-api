package ifsc.edu.br.eurotour.apirest.algorithms;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ifsc.edu.br.eurotour.apirest.model.grafo.Arco;
import ifsc.edu.br.eurotour.apirest.model.grafo.Grafo;
import ifsc.edu.br.eurotour.apirest.model.grafo.Vertice;
import ifsc.edu.br.eurotour.apirest.model.mapeamento.Caminho;
import ifsc.edu.br.eurotour.apirest.repository.BuscaAEstrelaRepository;

/**
 * Realiza a busca A* ao chamar o método buscaAEstrela
 * 
 * 
 * @author equipe.mapa
 *
 */
public class BuscaAEstrela implements BuscaAEstrelaRepository, Comparator<Vertice> {

	public static final int MENOR_DISTANCIA = 0;

	private static Vertice final_;

	/**
	 * 
	 * Cria o caminho com o menor custo partindo do vertice inicial baseado em uma heuristica.
	 * 
	 * @param aGrafo - Parametro utilizado para reiniciar as informações do grafo.
	 * @param aInicial - Ponto de partida para a busca de custo uniforme 
	 * @param aFinal - Objetivo, utilizado para verificar se já chegamos ao destino.
	 * 
	 * @param Caminho para ser exibido na parte gráfica.
	 * 
	 * */
	public Caminho buscaAEstrela(Grafo aGrafo, Vertice aInicial, Vertice aFinal) {
		long lTempoInicio = System.nanoTime();
		int lGerados = 1;
		int lExplorados = 0;
		final_ = aFinal;
		List<Vertice> lVerticesAbertos = new ArrayList<>();
		List<Vertice> lVerticesExpandidos = new ArrayList<>();
		Grafo.reiniciarGrafo(aGrafo);
		aInicial.definirDistancia(0);
		aInicial.visitar();
		lVerticesAbertos.add(aInicial);
		
		while (lVerticesAbertos.size() > 0) {
			Vertice lVerticeOrigem = lVerticesAbertos.get(MENOR_DISTANCIA);
			for (Arco lArco : lVerticeOrigem.obterArcos()) {
				Vertice lVerticeDestino = lArco.getDestino();
				Double lDistanciaPorPeso = lArco.getPeso();
				if (lVerticeDestino.obterVisitado() == 0) {
					lVerticeDestino.visitar();
					lVerticeDestino.setCaminho(lVerticeOrigem.getCaminho());
					lVerticeDestino.definirDistancia(lVerticeOrigem.obterDistancia() + lDistanciaPorPeso);
					lVerticesAbertos.add(lArco.getDestino());
				} else if (lVerticeDestino.obterDistancia() > (lVerticeDestino.obterDistanciaHeuristica(lVerticeOrigem)
						+ lVerticeOrigem.obterDistancia())) {
					lVerticeDestino.setCaminho(lVerticeOrigem.getCaminho());
					lVerticeDestino.definirDistancia(lVerticeOrigem.obterDistancia() + lDistanciaPorPeso);
					lVerticesAbertos.add(lArco.getDestino());
				}
				lGerados++;
			}
			lVerticesAbertos.remove(lVerticeOrigem);
			lVerticesExpandidos.add(lVerticeOrigem);
			ordernar(lVerticesAbertos);
			if (lVerticeOrigem.equals(aFinal)) {
				lExplorados = lVerticesExpandidos.size();
				return Caminho.converter(aGrafo, aFinal, lVerticeOrigem.obterDistancia(), lGerados, lExplorados, Caminho.gerarTempoProcessamento(lTempoInicio));
			}
		}
		return Caminho.converter(aGrafo, aFinal, aFinal.obterDistancia(), lGerados, lExplorados, Caminho.gerarTempoProcessamento(lTempoInicio));
	}

	/** 
	 * Chama a collections e o método sort para realizar a ordenação conforme nossa implementação do compare.
	 * 
	 * @param aArcos - Lista que deseja ordenar.
	 * 
	 * */
	
	private static void ordernar(List<Vertice> aArcos) {
		// Ordena a lista de arcos em ordem crescente
		Collections.sort(aArcos, new BuscaAEstrela());
	}

	/** 
	 * Faz comparações e realiza as ordenações dos arcos por distancia + distancia heuristica.
	 * 
	 * @param aVerticeI - vertice que será comparado com o VerticeII.
	 * @param aVerticeII - vertice que será comparado com o VerticeI
	 * 
	 * */
	// Ordenação dos arcos por distancia + distancia heuristica.
	@Override
	public int compare(Vertice aVertice, Vertice aVertice2) {
		Double peso1 = aVertice.obterDistancia() + aVertice.obterDistanciaHeuristica(final_);
		Double peso2 = aVertice2.obterDistancia() + aVertice2.obterDistanciaHeuristica(final_);
		return peso1.compareTo(peso2);
	}

}
