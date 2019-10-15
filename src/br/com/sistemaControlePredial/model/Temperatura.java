package br.com.sistemaControlePredial.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import br.com.sistemaControlePredial.services.CarregadorArquivo;

public class Temperatura {
	private Random temperaturaAtual;
	private ArrayList<Conjunto> array;

	// construtor
	public Temperatura() {
		temperaturaAtual = new Random();
	}

	// criar o arquivo SistemaAr.txt
	public File getArquivoTemperatura() {
		File novo = new File("SistemaAr.txt");

		if (novo.exists()) {
			novo = CarregadorArquivo.getFile("SistemaAr.txt");
		} else {
			limparTXT();
		}
		return novo;
	}

	// sorteia uma temperatura entre 10 e 40 para os ambientes dos conjuntos
	public int getTemperaturaArCondicionado() {
		// 10 <= temperatura <= 40
		return temperaturaAtual.nextInt(30) + 10;
	}

	// adiciona dados no arquivo SistemaAr.txt
	public void enviarTemperatura() throws Exception {

		FileWriter gravar = new FileWriter(getArquivoTemperatura());
		Conjunto c = new Conjunto();
		Iterator<Conjunto> iterator = c.getObjetoConjunto().iterator();
		String cnpj = "";
		int temperatura = 0;

		while (iterator.hasNext()) {
			Conjunto conjunto = iterator.next();
			if (cnpj.equals(conjunto.empresaCNPJ)) {

				String formato = String.format("%d %s %d %s", conjunto.getIdConjunto(), cnpj, temperatura, "Desligado");
				formato += System.lineSeparator();

				gravar.write(formato);
			} else {
				temperatura = getTemperaturaArCondicionado();
				cnpj = conjunto.empresaCNPJ;
				String formato = String.format("%d %s %d %s", conjunto.getIdConjunto(), cnpj, temperatura, "Desligado");
				formato += System.lineSeparator();
				gravar.write(formato);
			}
		}

		gravar.close();
	}

	public void atualizarArquivo(String cnpj) {

		try {
			FileWriter gravar = new FileWriter(getArquivoTemperatura());

			Conjunto conjunto = new Conjunto();
			Iterator<Conjunto> iterator = conjunto.getObjetoConjunto(cnpj).iterator();
			Conjunto conjuntoAux;

			carregarTemperatura();
			limparTXT();

			for (int i = 0; i < array.size(); i++) {
				Conjunto c = array.get(i);

				String formato = String.format("%d %s %d %s \n", c.getIdConjunto(), c.empresaCNPJ,
						c.getTemperaturaAtual(), c.getStatusArCondicionado());
				gravar.write(formato);

			}

			int temperatura = getTemperaturaArCondicionado();

			while (iterator.hasNext()) {
				conjuntoAux = iterator.next();
				String formato = String.format("%d %s %d %s \n", conjuntoAux.getIdConjunto(), conjuntoAux.empresaCNPJ,
						temperatura, "Desligado");
				gravar.write(formato);

			}

			gravar.close();

		} catch (IOException excecao) {
			excecao.printStackTrace();
		}

	}

	// liga o ar-condicionado nos conjuntos de uma determinada empresa
	public void ligarAr(String cnpj) throws IOException {
		carregarTemperatura();
		FileWriter gravar = new FileWriter(getArquivoTemperatura());

		int temp = getTemperaturaArCondicionado();

		for (int i = 0; i < array.size(); i++) {
			Conjunto c = array.get(i);

			if (c.empresaCNPJ.equals(cnpj)) {
				String formato = String.format("%d %s %d %s", c.getIdConjunto(), c.empresaCNPJ, temp, "Ligado");
				formato += System.lineSeparator();
				gravar.write(formato);
			} else {
				String formato = String.format("%d %s %d %s", c.getIdConjunto(), c.empresaCNPJ, c.getTemperaturaAtual(),
						c.getStatusArCondicionado());
				formato += System.lineSeparator();
				gravar.write(formato);
			}
		}

		gravar.close();
	}

	// desliga o ar-condicionado nos conjuntos de uma determinada empresa
	public void desligarAr(String cnpj) throws IOException {
		carregarTemperatura();
		FileWriter gravar = new FileWriter(getArquivoTemperatura());

		int temp = getTemperaturaArCondicionado();

		for (int i = 0; i < array.size(); i++) {
			Conjunto c = array.get(i);

			if (c.empresaCNPJ.equals(cnpj)) {
				String formato = String.format("%d %s %d %s", c.getIdConjunto(), c.empresaCNPJ, temp, "Desligado");
				formato += System.lineSeparator();
				gravar.write(formato);
			} else {
				String formato = String.format("%d %s %d %s", c.getIdConjunto(), c.empresaCNPJ, c.getTemperaturaAtual(),
						c.getStatusArCondicionado());
				formato += System.lineSeparator();
				gravar.write(formato);
			}
		}
		gravar.close();
	}

	public void carregarTemperatura() throws FileNotFoundException {
		array = new ArrayList<Conjunto>();

		@SuppressWarnings("resource")
		Scanner leitor = new Scanner(getArquivoTemperatura());

		try {
			while (leitor.hasNext()) {
				array.add(new Conjunto(leitor.nextInt(), leitor.next(), leitor.nextInt(), leitor.next()));
			}
		} catch (Exception e) {
			System.out.println("Erro ao ler registros");
		}

	}

	public int getTemperaturaAtualDeUmaEmpresa(String cnpj) {
		Conjunto c = array.get(0);

		for (int i = 1; !c.empresaCNPJ.equals(cnpj); i++)
			c = array.get(i);

		return c.getTemperaturaAtual();
	}

	public String getStatusAr(String cnpj) {
		Conjunto c = array.get(0);

		for (int i = 1; !c.empresaCNPJ.equals(cnpj); i++)
			c = array.get(i);

		return c.getStatusArCondicionado();
	}

	@SuppressWarnings("resource")
	private void limparTXT() {
		try {
			FileWriter arquivo = new FileWriter(new File("SistemaAr.txt"));
			PrintWriter gravador = new PrintWriter(arquivo);
			gravador.println("");
		} catch (IOException excecao) {
			excecao.printStackTrace();
		}

	}

}