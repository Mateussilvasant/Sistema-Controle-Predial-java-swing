package br.com.sistemaControlePredial.services;

import java.io.File;
import java.net.URL;

public class CarregadorArquivo {

	public static URL getURL(Object classe, String url) throws RuntimeException {
		URL caminho = null;

		try {
			caminho = classe.getClass().getClassLoader().getResource(url);
		} catch (Exception e) {
			new RuntimeException("Erro ao abrir a imagem URL incorreto!");
		}

		return caminho;
	}

	public static File getFile(String url) throws RuntimeException {
		File arquivo = null;

		try {

			arquivo = new File(url);

		} catch (Exception excecao) {
			new RuntimeException("Erro ao abrir arquivos da AplicaÃ§Ã£o");
		}
		return arquivo;
	}
}
