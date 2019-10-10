package ifsc.edu.br.eurotour.apirest.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ifsc.edu.br.eurotour.apirest.model.grafo.Arco;
import ifsc.edu.br.eurotour.apirest.model.grafo.Grafo;
import ifsc.edu.br.eurotour.apirest.model.grafo.Vertice;
import ifsc.edu.br.eurotour.apirest.model.mapeamento.Caminho;
import ifsc.edu.br.eurotour.apirest.repository.BuscaCustoUniformeRepository;



/**
 * Realiza a busca de custo uniforme ao chamar o método buscaCustoUniforme
 * 
 * 
 * @author equipe.mapa
 *
 */
public class BuscaDeCustoUniforme implements BuscaCustoUniformeRepository, Comparator<Vertice> {

	public static final int VERTICE_COM_MENOR_DISTANCIA = 0;

	private List<Vertice> lVerticesAbertos;
	private List<Vertice> lVerticesExpandidos;

	/**
	 * 
	 * Cria o caminho com o menor custo partindo do vertice inicial.
	 * 
	 * @param aGrafo - Parametro utilizado para reiniciar as informações do grafo.
	 * @param aInicial - Ponto de partida para a busca de custo uniforme 
	 * @param aFinal - Objetivo, utilizado para verificar se já chegamos ao destino.
	 * 
	 * @param Caminho para ser exibido na parte gráfica.
	 * 
	 * */
	@Override
	public Caminho buscaCustoUniforme(Grafo aGrafo, Vertice aInicial, Vertice aFinal) {
		long lTempoInicio = System.nanoTime();
		lVerticesAbertos = new ArrayList<>();
		lVerticesExpandidos = new ArrayList<>();
	
		Grafo.reiniciarGrafo(aGrafo);
		aInicial.definirDistancia(0);
		lVerticesAbertos.add(aInicial);
		
		int lGerados = 1;
		int lExplorados = 0;

		lVerticesAbertos.addAll(aGrafo.obterVertices());
		while (lVerticesAbertos.size() > 0) {
//        	Ordena os vertices para deixar o com menor distancia na posição 0
			ordernar(lVerticesAbertos);
//          Remove o vertice com menor distancia
//			Para melhorar podemos utlilizar uma queue, deixa o código limpo sem a necessidade de criar uma estrutura de dados
//			usando as as ferramentas que o próprio java nos fornece.
			Vertice lVerticeOrigem = lVerticesAbertos.remove(VERTICE_COM_MENOR_DISTANCIA);
//          Adiconar o vertice na lista dos vertices expandidos
			if(!lVerticesExpandidos.contains(lVerticeOrigem))
				lVerticesExpandidos.add(lVerticeOrigem);

			if (!lVerticeOrigem.equals(aFinal)) {
				for (Arco lArco : lVerticeOrigem.obterArcos()) {
					Vertice lVerticeDestino = lArco.getDestino();
					double lDistanciaPorPeso = lArco.getPeso();
					if (lVerticeDestino.obterDistancia() > lVerticeOrigem.obterDistancia() + lDistanciaPorPeso) {
						lVerticeDestino.definirDistancia(lDistanciaPorPeso + lVerticeOrigem.obterDistancia());
						lVerticeDestino.setCaminho(lVerticeOrigem.getCaminho());
						lGerados++;
					}
				}
			} else {
				lExplorados = lVerticesExpandidos.size();
				return Caminho.converter(aGrafo, aFinal, lVerticeOrigem.obterDistancia(), lGerados, lExplorados, Caminho.gerarTempoProcessamento(lTempoInicio));
			}
		}
		return Caminho.converter(aGrafo, aFinal, aFinal.obterDistancia(), lGerados, lExplorados,  Caminho.gerarTempoProcessamento(lTempoInicio));
	}
	

	/** 
	 * Chama a collections e o método sort para realizar a ordenação conforme nossa implementação do compare.
	 * 
	 * @param aArcos - Lista que deseja ordenar.
	 * 
	 * */
	private static void ordernar(List<Vertice> aArcos) {
		Collections.sort(aArcos, new BuscaDeCustoUniforme());
	}

	/** 
	 * Faz comparações e realiza as ordenações de acordo com o peso de cada vertice.
	 * 
	 * @param aVerticeI - vertice que será comparado com o VerticeII.
	 * @param aVerticeII - vertice que será comparado com o VerticeI
	 * 
	 * */
	@Override
	public int compare(Vertice aVerticeI, Vertice aVerticeII) {
		Double pesoI = aVerticeI.obterDistancia();
		Double pesoII = aVerticeII.obterDistancia();
		return pesoI.compareTo(pesoII);
	}
	
}
