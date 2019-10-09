package ifsc.edu.br.eurotour.apirest.dataaccess;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import ifsc.edu.br.eurotour.apirest.model.grafo.Arco;
import ifsc.edu.br.eurotour.apirest.model.grafo.Grafo;
import ifsc.edu.br.eurotour.apirest.model.grafo.Vertice;
import ifsc.edu.br.eurotour.apirest.repository.DataRoutesRepository;

/**
 * Classe que representa o acesso concreto aos dados. Neste caso, serão feitas
 * operações sobre uma planilha Excel
 * 
 * @author Osmar
 *
 */
public class DataRoutesDataAccess implements DataRoutesRepository {

	private Grafo grafo = new Grafo();

	@Override
	@Bean
	public Grafo pegarArquivo() {
		try {
			// procura o arquivo a partir de seu caminho
			Resource resource = new ClassPathResource("plainha_paises_capitais_ordenadas.xlsx");
	        InputStream arquivo = resource.getInputStream();
			// le o arquivo excel para incluir os vertices e Arcos
			this.lerArquivoExcel(arquivo);
			return grafo;
		} catch (IOException e) {
			System.out.println("Erro ao tentar ler o arquivo Excel");
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Lê a planilha e a partir desta, gera os {@link Vertice}s e {@link Arco}s
	 * presentes no {@link Grafo}
	 * 
	 * @param arquivo url do caminho do arquivo excel
	 * @throws IOException erro de caso o caminho esteja incorreto
	 */
	@SuppressWarnings("unused")
	private void lerArquivoExcel(FileInputStream arquivo) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook(arquivo);
		converterWBParaGrafo(wb);
	}
	
	/**
	 * Lê a planilha e a partir desta, gera os {@link Vertice}s e {@link Arco}s
	 * presentes no {@link Grafo}
	 * 
	 * @param arquivo url do caminho do arquivo excel
	 * @throws IOException erro de caso o caminho esteja incorreto
	 */
	private void lerArquivoExcel(InputStream arquivo) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook(arquivo);
		converterWBParaGrafo(wb);
	}
	
	/**
	 * Lê a planilha e a partir desta, gera os {@link Vertice}s e {@link Arco}s
	 * presentes no {@link Grafo}
	 * 
	 * @param wb plainilha convertida conforme o parametro do método {@link lerArquivoExcel}
	 * @throws IOException 
	 */
	private void converterWBParaGrafo(XSSFWorkbook wb) throws IOException {
		XSSFSheet planilha = wb.getSheetAt(0);
		XSSFRow linha;
		XSSFCell celula;
		Vertice conecta = null;

		Iterator<Row> linhas = planilha.rowIterator();

		while (linhas.hasNext()) {
			linha = (XSSFRow) linhas.next();

			if (linha.getRowNum() > 1) {

				Iterator<Cell> celulas = linha.cellIterator();

				while (celulas.hasNext()) {
					celula = (XSSFCell) celulas.next();

					if (celula.getColumnIndex() == 0) {
						grafo.adicionarVertice(celula.getStringCellValue());
					} else {
						break;
					}
				}
			}
		}

		linhas = planilha.rowIterator();

		while (linhas.hasNext()) {
			linha = (XSSFRow) linhas.next();

			if (linha.getRowNum() > 1) {

				Iterator<Cell> celulas = linha.cellIterator();
				conecta = null;

				while (celulas.hasNext()) {
					celula = (XSSFCell) celulas.next();

					if (celula.getColumnIndex() == 0) {
						conecta = grafo.pesquisaVertice(celula.getStringCellValue());
					} else {
						if (celula.getCellType().toString().equals("NUMERIC")) {
							double peso = celula.getNumericCellValue();
							grafo.pesquisaVertice(
									planilha.getRow(1).getCell(celula.getColumnIndex()).getStringCellValue())
									.adicionarArco(conecta, peso);
						}
					}
				}
			}
		}

		planilha = wb.getSheetAt(1);
		linhas = planilha.rowIterator();

		while (linhas.hasNext()) {
			linha = (XSSFRow) linhas.next();

			if (linha.getRowNum() > 1) {

				Iterator<Cell> celulas = linha.cellIterator();
				conecta = null;

				while (celulas.hasNext()) {
					celula = (XSSFCell) celulas.next();

					if (celula.getColumnIndex() == 0) {
						conecta = grafo.pesquisaVertice(celula.getStringCellValue());
					} else {
						if (celula.getCellType().toString().equals("NUMERIC")) {
							double peso = celula.getNumericCellValue();
							grafo.pesquisaVertice(
									planilha.getRow(1).getCell(celula.getColumnIndex()).getStringCellValue())
									.adicionarArcoHeuristica(conecta, peso);
						}
					}
				}
			}
		}
		wb.close();
	}

}
