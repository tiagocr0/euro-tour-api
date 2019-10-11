package ifsc.edu.br.eurotour.apirest.algorithms;

import java.util.ArrayList;
import java.util.List;

import ifsc.edu.br.eurotour.apirest.model.grafo.Arco;
import ifsc.edu.br.eurotour.apirest.model.grafo.Grafo;
import ifsc.edu.br.eurotour.apirest.model.grafo.Vertice;
import ifsc.edu.br.eurotour.apirest.model.mapeamento.Caminho;
import ifsc.edu.br.eurotour.apirest.repository.BuscaBidirecionalRepository;

/**
 * Classe que representa a Busca Bidirecional através de um Grafo
 * 
 * @author equipe.mapa
 * 
 */
public class BuscaBidirecional implements BuscaBidirecionalRepository {

	private int lGerados = 1;
	private int lExplorados = 0;

	/**
	 * 
	 * Através do caminho do vertice inicial e do vertice final, monta um possível
	 * caminho entre eles.
	 * 
	 * @param aGrafo   - Parametro utilizado para rezetar as informações do grafo.
	 * @param aInicial - Ponto inicial, vertice que inicia a busca pelo ponto de
	 *                 partida.
	 * @param aFinal   - Objetivo, vertice que inicia a busca pelo objetivo.
	 * 
	 * @param Caminho  para ser exibido na parte gráfica.
	 * 
	 */
	@Override
	public Caminho buscaBidirecional(Grafo aGrafo, Vertice aInicial, Vertice aFinal) {
		long lTempoInicio = System.nanoTime();
		if (!aInicial.equals(aFinal)) {
			Grafo.reiniciarGrafo(aGrafo);

			lGerados = 1;
			lExplorados = 0;

//			Listas de vertices que guardarão os vertices que serão explorados.
			List<Vertice> lVerticesAbertorA = new ArrayList<>();
			List<Vertice> lVerticesAbertorB = new ArrayList<>();

//	        Vertices que serão resposavel por verificar se existe um caminho.
			List<Vertice> lVerticesExpandidosA = new ArrayList<>();
			List<Vertice> lVerticesExpandidosB = new ArrayList<>();

//	        Adicionando pontos de partida para realizar as buscas.
			aInicial.definirDistancia(0);
			lVerticesExpandidosA.add(aInicial);

			aFinal.definirDistancia(0);
			lVerticesExpandidosB.add(aFinal);

			lVerticesAbertorA.add(aInicial);
			lVerticesAbertorB.add(aFinal);

//	        Realiza as verificações para ver se existe um caminho entre o vertice final com o vertice inicial.
			while (!lVerticesAbertorA.isEmpty() || !lVerticesAbertorB.isEmpty()) {
				Vertice lVerticeAAB = existeCaminho(lVerticesAbertorA, lVerticesExpandidosA, lVerticesExpandidosB);
				if (lVerticeAAB != null) {
					lGerados = lVerticesExpandidosA.size() + lVerticesExpandidosB.size();
					return Caminho.converter(aGrafo, gerarCaminho(lVerticeAAB), lVerticeAAB.obterDistancia(), lGerados,
							lExplorados, Caminho.gerarTempoProcessamento(lTempoInicio));
				}
				Vertice lVerticeBBA = existeCaminho(lVerticesAbertorB, lVerticesExpandidosB, lVerticesExpandidosA);
				if (lVerticeBBA != null) {
					lGerados = lVerticesExpandidosA.size() + lVerticesExpandidosB.size();
					return Caminho.converter(aGrafo, gerarCaminho(lVerticeBBA), lVerticeBBA.obterDistancia(), lGerados,
							lExplorados, Caminho.gerarTempoProcessamento(lTempoInicio));
				}
			}
		}
		return Caminho.converter(aGrafo, aFinal, aFinal.obterDistancia(), lGerados, lExplorados,
				Caminho.gerarTempoProcessamento(lTempoInicio));
	}

	/**
	 * 
	 * Verifica se existe um caminho entre, caso exista uma interseção retorna o
	 * vertice central para criar o caminho e mostrar na tela.
	 * 
	 * @param aVerticesAbertos     - Lista de vertices que ainda vão ser explorados.
	 * @param aVerticesExpandidosA - Lista de vertices já explorados do caminho A ou
	 *                             B.
	 * @param aVerticesExpandidosB - Lista de vertices já explorados do caminho A ou
	 *                             B.
	 * 
	 * @return Vertice que faz interseção entre os dois caminhos.
	 * 
	 */
	private Vertice existeCaminho(List<Vertice> aVerticesAbertos, List<Vertice> aVerticesExpandidosA,
			List<Vertice> aVerticesExpandidosB) {

		if (!aVerticesAbertos.isEmpty()) {
			Vertice next = aVerticesAbertos.remove(0);
			List<Arco> adjacentNodes = next.obterArcos();
			for (Arco arcosAdjacent : adjacentNodes) {
				Vertice adjacent = arcosAdjacent.getDestino();
				double lDistanciaPorPeso = arcosAdjacent.getPeso();
				if (aVerticesExpandidosB.contains(adjacent)) {
					adjacent.setCaminhoInverso(next.getCaminho());
					double totalDistancia = lDistanciaPorPeso + adjacent.obterDistancia() + next.obterDistancia();
					adjacent.definirDistancia(totalDistancia);
					return adjacent;
				} else if (!aVerticesExpandidosA.contains(adjacent)) {
					adjacent.definirDistancia(lDistanciaPorPeso + next.obterDistancia());
					adjacent.setCaminho(next.getCaminho());
					aVerticesExpandidosA.add(adjacent);
					aVerticesAbertos.add(adjacent);
					lExplorados++;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * Cria o caminho através do vertice central em que os dois caminhos se
	 * encontraram
	 * 
	 * @param lVerticeCentral - Vertice em que os caminhos se encontram.
	 * 
	 * @return Caminho formatado para gerar o objeto que será enviado para a tela
	 * 
	 */
	private static String gerarCaminho(Vertice aVerticeCentral) {
		String lTextCaminhoI = aVerticeCentral.getCaminho().replace(" / " + aVerticeCentral.toString(), "")
				.replace(aVerticeCentral.toString(), "");
		;
		String lTextCaminhoII = aVerticeCentral.getCaminhoInverso();

		String[] lCaminho = revertArray(lTextCaminhoI.split(" / "));
		for (int indice = 0; indice < lCaminho.length; indice++) {
			lTextCaminhoII += " / " + lCaminho[indice];
		}
		return lTextCaminhoII;
	}

	/**
	 * 
	 * Inverte um vetor do tipo String, deixando os itens da últimas posições nas
	 * primeira e os primeiros nas últimas.
	 * 
	 * ARRAY[0] = últimoItem, ARRAY[1] = penúltimoItem, ARRAY[2] = antepenúltimoItem
	 * ...
	 * 
	 * @param caminho - caminho para ser invertido.
	 * 
	 * @return Array com os itens invertidos.
	 * 
	 */
	private static String[] revertArray(String[] aCaminho) {
		String[] novo = new String[aCaminho.length];
		int j = aCaminho.length;
		for (int i = 0; i < aCaminho.length; i++) {
			novo[j - 1] = aCaminho[i];
			j = j - 1;
		}
		return novo;
	}
}
