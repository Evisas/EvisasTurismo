package br.com.evisas.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class QueryUtil {

	private static final String PATH_ARQUIVO_QUERIES = "src/main/resources/data/sql/queries.xml";
	
	private static Map<String, String> queryBuffer = null;

	/**
	 * Obtém uma query pelo seu nome.
	 * 
	 * @param nome String
	 * @return String Query
	 */
	public static String getQueryByName(String nome) {
		String query = getQueryBuffer().get(nome);
		if (query == null) {
			throw new IllegalArgumentException("Query '" + nome + "' não encontrada.");
		}
		return query;
	}

	private static Map<String, String> getQueryBuffer() {
		if (queryBuffer == null) {
			queryBuffer = obterQueriesDoXml();
			System.out.println("Leu arquivo de queries"); // TODO: Usar log4j ao invés de sisout
		}
		return queryBuffer;
	}

	private static Map<String, String> obterQueriesDoXml() {
		try {
			Document domXml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(PATH_ARQUIVO_QUERIES));
			
//			domXml.getDocumentElement().normalize(); // Normaliza o documento (não necessário neste caso)

			NodeList nodeListQueries = domXml.getElementsByTagName("query");

			Map<String, String> queries = new HashMap<String, String>();
			
			for (int i = 0; i < nodeListQueries.getLength(); i++) {
				Node nodeQuery = nodeListQueries.item(i);
				String nomeDaQuery = nodeQuery.getAttributes().getNamedItem("name").getNodeValue();
				String query = tratarQuery(nodeQuery.getTextContent());
				
				queries.put(nomeDaQuery, query);
			}

			return queries;

		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Erro ao encontrar o arquivo " + PATH_ARQUIVO_QUERIES, e);
		} catch (SAXException e) {
			throw new RuntimeException("Erro ao fazer o parse do arquivo " + PATH_ARQUIVO_QUERIES, e);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao ler o arquivo " + PATH_ARQUIVO_QUERIES, e);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException("Erro ao abrir o builder de arquivo xml (para ler o arquivo '" + PATH_ARQUIVO_QUERIES + "')", e);
		}
	}

	private static String tratarQuery(String query) {
		return query.replaceAll("\t", "")	// retira a tabulação
					.replaceAll("\r", "")	// retira o 'carrie return' (alguns servidores colocam, outros não)
					.replaceAll("\n", " ")	// substitui a quebra de linha por um espaço
					.trim();				// remove espaços, tabulação e quebras de linhas antes e depois da query
	}
}
