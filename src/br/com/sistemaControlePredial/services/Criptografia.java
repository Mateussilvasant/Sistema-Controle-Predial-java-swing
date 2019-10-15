package br.com.sistemaControlePredial.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Criptografia {

	public File chave;

	public Criptografia(File chave) {
		this.chave = chave;
	}

	public Criptografia() {
	}

	public void setChave(File arquivo) {
		chave = arquivo;
	}

	public void geraChave(File arquivo) throws IOException {
		int chave = (int) (Math.random() * 500);
		ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream(arquivo));
		ob.writeObject(chave);
		ob.close();
	}

	public File getChavePrivada() {

		File arquivo = new File("chave.dummy");
		File novo = null;

		if (arquivo.exists()) {
			novo = CarregadorArquivo.getFile("chave.dummy");
		} else {
			try {
				geraChave(new File("chave.dummy"));
				novo = CarregadorArquivo.getFile("chave.dummy");
			} catch (IOException excecao) {
				excecao.printStackTrace();
			}
		}
		return novo;
	}

	public String getDecifra(String texto) throws IOException, ClassNotFoundException {
		byte[] textoDescripto = texto.getBytes("ISO-8859-1");

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getChavePrivada()));
		int iDummy = (Integer) ois.readObject();
		ois.close();

		for (int i = 0; i < textoDescripto.length; i++) {
			textoDescripto[i] = (byte) (textoDescripto[i] - i - iDummy);
		}

		return new String(textoDescripto, "ISO-8859-1");
	}

	public String getCifra(String texto) throws IOException, ClassNotFoundException {

		byte[] textoCripto = texto.getBytes("ISO-8859-1");

		ObjectInputStream ob = new ObjectInputStream(new FileInputStream(getChavePrivada()));
		int dummy = (Integer) ob.readObject();
		ob.close();

		for (int i = 0; i < textoCripto.length; i++) {
			textoCripto[i] = (byte) (textoCripto[i] + i + dummy);
		}

		String cifrado = new String(textoCripto, "ISO-8859-1");

		return cifrado;
	}

	public void verificaDelimitador(String texto) throws ClassNotFoundException, IOException {

		char caracteres[] = texto.toCharArray();

		for (int d = 0; d < caracteres.length; d++) {

			while (caracteres[d] == '<' || caracteres[d] == '>') {
				String cifrado = getCifra(texto);
				caracteres = cifrado.toCharArray();
			}

		}

	}
}