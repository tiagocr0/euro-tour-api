package ifsc.edu.br.eurotour.apirest.algorithms;

import java.util.ArrayList;

import ifsc.edu.br.eurotour.apirest.model.estruturadados.Fila;
import ifsc.edu.br.eurotour.apirest.model.grafo.Arco;
import ifsc.edu.br.eurotour.apirest.model.grafo.Grafo;
import ifsc.edu.br.eurotour.apirest.model.grafo.Vertice;
import ifsc.edu.br.eurotour.apirest.model.mapeamento.Caminho;
import ifsc.edu.br.eurotour.apirest.repository.BuscaAprofundamentoIterativoRepository;

/**
 * Realiza a Busca de Aprofundamento Iterativo através do método de mesmo nome
 * 
 * @author equipe.mapa
 *
 */
public class BuscaAprofundamentoIterativo implements BuscaAprofundamentoIterativoRepository {

	private boolean encontrou_caminho = false;
	private ArrayList<Arco> lista_filhos = new ArrayList<>();
	private int nosExplorados;
	private int nosGerados;

	@Override
	public Caminho buscaAprofundamentoIterativo(Grafo g, Vertice inicial, Vertice destino) {
		long tempoInicio = System.nanoTime();
		encontrou_caminho = false;
		Vertice vertice_final = new Vertice();
		int limite = 0;
		while (!encontrou_caminho) {
			nosGerados = 1;
			nosExplorados = 0;
			vertice_final = buscaProfundidadeLimitada(g, inicial, destino, limite);
			limite++;
		}
		return Caminho.converter(g, destino, vertice_final.obterDistancia(), nosGerados, nosExplorados,
				Caminho.gerarTempoProcessamento(tempoInicio));
	}

	/**
	 * Realiza a busca em profundidade de um certo grafo, a partir de um vertice
	 * inicial
	 * 
	 * @param g       Grafo com as origens, destinos e pesos
	 * @param inicial Vertice inicial (raiz) a começar a busca
	 * @return A lista com os vertices que podem ser alcançados
	 */

	private Vertice buscaProfundidadeLimitada(Grafo g, Vertice inicial, Vertice destino, int limite) {
		int cont = 0;
		Grafo.reiniciarGrafo(g);
		inicial.visitar();
		inicial.definirDistancia(0);
		inicial.setCaminho(null);
		Fila fila = new Fila();
		fila.push(inicial);
		while (!fila.estaVazia()) {
			Vertice atual = fila.getInicio().getInfo();
			fila.pop();
			nosExplorados++;
			if (cont <= limite) {
				if (atual.equals(destino)) {
					encontrou_caminho = true;
					return atual;
				} else {
					lista_filhos = atual.obterArcos();
					for (Arco arco : lista_filhos) {
						Vertice filho = arco.getDestino();
						if (filho.obterVisitado() == 0) {
							filho.visitar();
							filho.definirDistancia(atual.obterDistancia() + arco.getPeso());
							filho.setCaminho(atual.getCaminho());
							fila.push(filho);
							nosGerados++;
							if (filho.equals(destino)) {
								encontrou_caminho = true;
								return filho;
							}
						}
						atual.visitar();
					}
				}
			}
			cont++;
		}
		return destino;
	}

}
