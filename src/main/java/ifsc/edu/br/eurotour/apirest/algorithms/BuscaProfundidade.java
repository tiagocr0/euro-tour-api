package ifsc.edu.br.eurotour.apirest.algorithms;

import java.util.ArrayList;

import ifsc.edu.br.eurotour.apirest.model.estruturadados.Pilha;
import ifsc.edu.br.eurotour.apirest.model.grafo.Arco;
import ifsc.edu.br.eurotour.apirest.model.grafo.Grafo;
import ifsc.edu.br.eurotour.apirest.model.grafo.Vertice;
import ifsc.edu.br.eurotour.apirest.model.mapeamento.Caminho;
import ifsc.edu.br.eurotour.apirest.repository.BuscaProfundidadeRepository;

/**
 * Classe que representa uma busca em profundidade em um {@link Grafo}.
 * 
 * @author equipe.mapa
 *
 */
public class BuscaProfundidade implements BuscaProfundidadeRepository {

	/**
	 * Realiza a busca em profundidade de um certo grafo, a partir de um vertice
	 * inicial
	 * 
	 * @param g       Grafo com as origens, destinos e pesos
	 * @param inicial Vertice inicial (raiz) a começar a busca
	 * @return A lista com os vertices que podem ser alcançados
	 */
	@Override
	public Caminho buscaProfundidade(Grafo g, Vertice inicial, Vertice destino) {
		int nosGerados = 1;
		int nosExplorados = 0;
		long tempoInicio = System.nanoTime();
		ArrayList<Vertice> vertices_grafo = g.obterVertices();
		for (int i = 0; i < vertices_grafo.size(); i++) {
			vertices_grafo.get(i).zerarVisitas();
			vertices_grafo.get(i).zerarDistancia();
			vertices_grafo.get(i).setCaminho(null);
		}
		inicial.visitar();
		inicial.definirDistancia(0);
		inicial.setCaminho(null);
		Pilha pilha = new Pilha();
		pilha.push(inicial);
		while (!pilha.estaVazia()) {
			Vertice atual = pilha.getTopo().getInfo();
			pilha.pop();
			nosExplorados++;
			if (atual.equals(destino)) {
				return Caminho.converter(g, destino, atual.obterDistancia(), nosGerados, nosExplorados,
						Caminho.gerarTempoProcessamento(tempoInicio));
			} else {
				ArrayList<Arco> lista_filhos = atual.obterArcos();
				for (Arco arco : lista_filhos) {
					Vertice filho = arco.getDestino();
					if (filho.obterVisitado() == 0) {
						filho.visitar();
						filho.definirDistancia(atual.obterDistancia() + arco.getPeso());
						filho.setCaminho(atual.getCaminho());
						pilha.push(filho);
						nosGerados++;
						if (filho.equals(destino)) {
							return Caminho.converter(g, destino, filho.obterDistancia(), nosGerados, nosExplorados,
									Caminho.gerarTempoProcessamento(tempoInicio));
						}
					}
					atual.visitar();
				}
			}
		}
		return Caminho.converter(g, destino, 0d, nosGerados, nosExplorados,
				Caminho.gerarTempoProcessamento(tempoInicio));
	}
}
