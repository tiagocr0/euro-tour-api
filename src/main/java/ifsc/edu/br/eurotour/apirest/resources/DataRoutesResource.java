package ifsc.edu.br.eurotour.apirest.resources;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ifsc.edu.br.eurotour.apirest.model.grafo.Arco;
import ifsc.edu.br.eurotour.apirest.model.grafo.Grafo;
import ifsc.edu.br.eurotour.apirest.model.grafo.Vertice;
import ifsc.edu.br.eurotour.apirest.model.mapeamento.Caminho;
import ifsc.edu.br.eurotour.apirest.model.mapeamento.Front;
import ifsc.edu.br.eurotour.apirest.model.mapeamento.FrontToBack;
import ifsc.edu.br.eurotour.apirest.services.BuscaAEstrelaService;
import ifsc.edu.br.eurotour.apirest.services.BuscaAprofundamentoIterativoService;
import ifsc.edu.br.eurotour.apirest.services.BuscaBidirecionalService;
import ifsc.edu.br.eurotour.apirest.services.BuscaCustoUniformeService;
import ifsc.edu.br.eurotour.apirest.services.BuscaProfundidadeService;
import ifsc.edu.br.eurotour.apirest.services.DataRoutesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Camada que provê funções para comunicação com o front-end que é chamado
 * através da requisição http://localhost:8081/api/busca
 * 
 * @author Tiago
 * 
 *         Alteração para mostrar o swagger da aplicação
 * @author wilsonfcj
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Euro Tour")
public class DataRoutesResource {

	private BuscaAprofundamentoIterativoService aprofundamentoIterativo;
	private BuscaBidirecionalService bidirecional;
	private BuscaCustoUniformeService custoUniforme;
	private BuscaProfundidadeService profundidade;
	private BuscaAEstrelaService aEstrela;
	private Caminho caminho;

	private Grafo grafo = new Grafo();

	private DataRoutesService dataRoutes;

	/**
	 * Realiza a leitura do arquivo Planilha de Países-Capitais Ordenada.xlsx e
	 * chama método {@link DataRoutesService#pegarArquivo} para pegar o arquivo
	 * 
	 * @return {@link Grafo} que contém os {@link Vertice}s e os {@link Arco}s
	 * @throws Exceção no caso de erro de leitura do arquivo
	 */

	public DataRoutesResource() {
		dataRoutes = new DataRoutesService();
		try {
			grafo = dataRoutes.pegarArquivo();
		} catch (URISyntaxException e) {
			System.err.print("Erro ao tentar ler o arquivo");
			e.printStackTrace();
		}
	}

	/**
	 * Método para retornar um Json de exemplo de requisição para o método de busca
	 * 
	 * @return {@link Front} que contém o exemplo que será enviado ao frontend
	 */
	@ApiOperation(value = "Retorna um exemplo de requisição para o método de busca")
	@GetMapping("/requisicaoExemplo")
	public ResponseEntity<Front> descricao() {
		FrontToBack lFrontToBack = new FrontToBack();
		lFrontToBack.setAlgoritmo(3);
		lFrontToBack.setOrigem("Grécia – Atenas");
		lFrontToBack.setDestino("Noruega – Oslo");
		Front lFront = new Front();
		lFront.setFront(lFrontToBack);
		return new ResponseEntity<Front>(lFront, HttpStatus.OK);
	}

	/**
	 * Realiza a comunicação com o front-end recebendo como parâmetro um objeto no
	 * formato {@link Front} e realizando o tratamento de qual busca será realizada
	 * 
	 * @param front {@link Front} que é o formato que será mandado pelo front
	 * @return {@link Caminho} que contém o caminho que deve ser percorrido para
	 *         chegar de {@link Vertice} origem até um {@link Vertice} destino
	 * @throws Exceção no caso de algoritmo inválido
	 */
	@ApiOperation(value = "Gera um caminho conforme o algoritmo selecionado")
	@RequestMapping(value = "/busca", method = RequestMethod.POST)
	public ResponseEntity<Caminho> busca(@RequestBody @Valid Front front) {
		try {
			int algoritmo = front.getFront().getAlgoritmo();
			Vertice origem = grafo.pesquisaVertice(front.getFront().getOrigem());
			Vertice destino = grafo.pesquisaVertice(front.getFront().getDestino());

			switch (algoritmo) {
			case 0:
				// Realiza a busca de buscaProfundidade
				profundidade = new BuscaProfundidadeService();
				caminho = profundidade.buscaProfundidade(grafo, origem, destino);
				return new ResponseEntity<Caminho>(caminho, HttpStatus.OK);
			case 1:
				// Realiza a busca de Aprofundamento Iterativo
				aprofundamentoIterativo = new BuscaAprofundamentoIterativoService();
				caminho = aprofundamentoIterativo.buscaAprofundamentoIterativo(grafo, origem, destino);
				return new ResponseEntity<Caminho>(caminho, HttpStatus.OK);
			case 2:
				// Realiza a busca de Bidirecional
				bidirecional = new BuscaBidirecionalService();
				caminho = bidirecional.buscaBidirecional(grafo, origem, destino);
				return new ResponseEntity<Caminho>(caminho, HttpStatus.OK);
			case 3:
				// Realiza a busca de Custo Uniforme
				custoUniforme = new BuscaCustoUniformeService();
				caminho = custoUniforme.buscaCustoUniforme(grafo, origem, destino);
				return new ResponseEntity<Caminho>(caminho, HttpStatus.OK);
			case 4:
				// Realiza a busca de A*
				aEstrela = new BuscaAEstrelaService();
				caminho = aEstrela.buscaAEstrela(grafo, origem, destino);
				return new ResponseEntity<Caminho>(caminho, HttpStatus.OK);
			default:
				System.out.println("Algoritmo incorreto");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
