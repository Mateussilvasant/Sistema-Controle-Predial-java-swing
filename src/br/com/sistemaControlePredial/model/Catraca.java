package br.com.sistemaControlePredial.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JOptionPane;

import br.com.sistemaControlePredial.services.CarregadorArquivo;
import br.com.sistemaControlePredial.services.Criptografia;
import br.com.sistemaControlePredial.services.DataFormat;

public class Catraca extends Criptografia {

	public Usuario usuario;

	public Catraca() {
		super();
		usuario = new Usuario();
	}

	public Catraca(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	public File getUsuarioArquivo() {

		File novo = new File("usuario.txt");

		if (novo.exists()) {
			novo = CarregadorArquivo.getFile("usuario.txt");
		} else {
			limparTXT();
			atualizarListaAcessos();
		}

		return novo;
	}

	public boolean acessarPredio(String usuario, String senha, char tipo) throws RuntimeException {
		ArrayList<Usuario> usuarios = null;
		boolean login = false;

		try {
			usuarios = carregarUsuarios();
			ordenarUsuarios(usuarios);
			login = buscaUsuario(usuarios, usuario, senha, tipo);
		} catch (Exception e) {
			atualizarListaAcessos();
		}
		return login;
	}

	public ArrayList<Usuario> carregarUsuarios() {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		try {

			BufferedReader arquivo = new BufferedReader(new FileReader(getUsuarioArquivo()));
			while (arquivo.ready()) {

				String linha = arquivo.readLine();
				String usuario = linha.split("<espaco>")[1].trim();
				String senha = linha.split("<espaco>")[2].trim();
				String cpf = linha.split("<espaco>")[3].trim();
				String tipo = linha.split("<espaco>")[4].trim();
				String horaEntrada = linha.split("<espaco>")[5].trim();
				String horaSaida = linha.split("<espaco>")[6].trim();

				Usuario u = new Usuario();
				u.setUsuario(getDecifra(usuario));
				u.setSenha(getDecifra(senha));
				u.setCPF(getDecifra(cpf));
				u.setTipo(getDecifra(tipo).charAt(0));
				u.setHoraEntrada(getDecifra(horaEntrada));
				u.setHoraSaida(getDecifra(horaSaida));
				usuarios.add(u);

			}

			arquivo.close();

		} catch (FileNotFoundException excecao) {
			System.out.println(excecao.getLocalizedMessage());
		} catch (IOException excecao) {
			System.out.println(excecao.getLocalizedMessage());
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			Throwable erros = e.initCause(e.getCause());

			JOptionPane.showMessageDialog(null, e.toString() + " " + e.getCause() + " " + erros.toString());
			
			
		}

		return usuarios;
	}

	public void enviarUsuarioTXT(Usuario usuario) {
		FileWriter arquivo = null;

		try {
			arquivo = new FileWriter(getUsuarioArquivo(), true);

			String u = String.format("<espaco>%s<espaco>%s<espaco>%s<espaco>%s<espaco>%s<espaco>%s<espaco>",
					getCifra(usuario.getUsuario()), getCifra(usuario.getSenha()), getCifra(usuario.getCPF()),
					getCifra("" + usuario.getTipo()), getCifra(usuario.getHoraEntrada()),
					getCifra(usuario.getHoraSaida()));

			u += System.lineSeparator();
			arquivo.write(u);

			arquivo.close();

		} catch (IOException | ClassNotFoundException excecao) {
			System.out.println(excecao.getLocalizedMessage());
		}
	}

	public boolean atualizarListaAcessos() {

		boolean saida = false;
		limparTXT();

		try {
			ArrayList<Usuario> user = new ArrayList<Usuario>();
			user = usuario.usuariosAcessos(user);
			Iterator<Usuario> iterator = user.iterator();

			while (iterator.hasNext()) {
				Usuario us = iterator.next();
				enviarUsuarioTXT(us);
				saida = true;
			}

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

		return saida;

	}

	private boolean buscaUsuario(ArrayList<Usuario> usuario, String user, String senha, char tipo) {

		boolean resultado = false;
		int in = 0;
		int fi = usuario.size() - 1;
		int meio;

		while (in <= fi) {
			meio = (in + fi) / 2;

			if (usuario.get(meio).getUsuario().compareTo(user) == 0) {

				boolean senhaValidacao = usuario.get(meio).getSenha().equals(senha);
				boolean tipoValidacao = usuario.get(meio).getTipo() == tipo;
				boolean autorizacaoValidacao = autorizarEntrada(usuario.get(meio));

				if (senhaValidacao && tipoValidacao && autorizacaoValidacao) {
					this.usuario = usuario.get(meio);
					resultado = true;
				}

				break;

			} else {
				if (usuario.get(meio).getUsuario().compareTo(user) > 0) {
					fi = meio - 1;
				} else {
					in = meio + 1;
				}
			}
		}

		return resultado;
	}

	public boolean autorizarEntrada(Usuario usuario) {

		boolean s = false;

		if (usuario.getTipo() == 'S' || usuario.getTipo() == 'A') {
			s = true;
		} else {
			s = DataFormat.compareToHora(usuario.getHoraEntrada(), usuario.getHoraSaida());
		}

		return s;
	}

	private void ordenarUsuarios(ArrayList<Usuario> lista) {
		Collections.sort(lista);
	}

	@SuppressWarnings("resource")
	private void limparTXT() {
		try {
			FileWriter arquivo = new FileWriter(new File("usuario.txt"));
			PrintWriter gravador = new PrintWriter(arquivo);
			gravador.println("");
		} catch (IOException excecao) {
			excecao.printStackTrace();
		}

	}

}