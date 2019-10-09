package ifsc.edu.br.eurotour.apirest.services;

import java.net.URISyntaxException;

import ifsc.edu.br.eurotour.apirest.dataaccess.DataRoutesDataAccess;
import ifsc.edu.br.eurotour.apirest.model.grafo.Grafo;
import ifsc.edu.br.eurotour.apirest.repository.DataRoutesRepository;

/**
 * Camada de Serviço para o acesso a base de dados (Excel). <br>
 * Delega funções a camada de acesso a dados: {@link DataRoutesRepository}
 * 
 * @author Osmar
 *
 */
public class DataRoutesService {

	private DataRoutesRepository rep;

	public DataRoutesService() {
		rep = new DataRoutesDataAccess();
	}

	/**
	 * Acessa e lê o arquivo (neste caso, um Excel) para obter os dados e gerar um
	 * {@link Grafo}.
	 * 
	 * @return {@link Grafo} baseado na planilha Excel
	 * @throws URISyntaxException caso não encontre o caminho correto do arquivo
	 */
	public Grafo pegarArquivo() throws URISyntaxException {
		return rep.pegarArquivo();
	}
}
