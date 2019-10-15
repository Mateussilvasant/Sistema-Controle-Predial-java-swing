package br.com.sistemaControlePredial.model;

import java.util.ArrayList;

public class Usuario implements Comparable<Usuario> {
	private String cpf, nome, sobrenome, usuario, senha, telefone, horaEntrada, horaSaida;
	private boolean permissaoAlterarTemperatura;
	private char tipo;
	private ArrayList<String> conteudo;
	public UsuarioDAO dao;

	public Usuario(String cpf, String nome, String sobrenome, String usuario, String senha, String telefone,
			String horaEntrada, String horaSaida, char tipo, boolean permissaoAlterarTemperatura) {
		setCPF(cpf);
		setNome(nome);
		setSobrenome(sobrenome);
		setUsuario(usuario);
		setSenha(senha);
		setTelefone(telefone);
		setHoraEntrada(horaEntrada);
		setHoraSaida(horaSaida);
		setTipo(tipo);
		setPermissaoAlterarTemperatura(permissaoAlterarTemperatura);
		dao = new UsuarioDAO();
		conteudo = new ArrayList<String>();
	}

	public Usuario(String usuario, String senha, String cpf, String horaentrada, String horasaida, char tipo) {
		setUsuario(usuario);
		setSenha(senha);
		setCPF(cpf);
		setHoraEntrada(horaentrada);
		setHoraSaida(horasaida);
		setTipo(tipo);
	}

	public Usuario() {
		setCPF("");
		setNome("");
		setSobrenome("");
		setUsuario("");
		setSenha("");
		setTelefone("");
		setHoraEntrada("");
		setHoraSaida("");
		setTipo('n');
		setPermissaoAlterarTemperatura(false);
		dao = new UsuarioDAO();
		conteudo = new ArrayList<String>();
	}

	public void setCPF(String cpf) {
		this.cpf = cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public void setPermissaoAlterarTemperatura(boolean permissaoAlterarTemperatura) {
		this.permissaoAlterarTemperatura = permissaoAlterarTemperatura;
	}

	public String getCPF() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public String getHoraSaida() {
		return horaSaida;
	}

	public char getTipo() {
		return tipo;
	}

	public boolean getPermissaoAlterarTemperatura() {
		return permissaoAlterarTemperatura;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	// Outros métodos
	public boolean cadastrar(String EmpresaCNPJ) {
		return dao.cadastrar(getCPF(), getNome(), getSobrenome(), getUsuario(), getSenha(), getTelefone(),
				getHoraEntrada(), getHoraSaida(), getTipo(), getPermissaoAlterarTemperatura(), EmpresaCNPJ);
	}

	public void consultar() {
		conteudo = dao.consultar(getCPF());
		setCPF(conteudo.get(0));
		setNome(conteudo.get(1));
		setSobrenome(conteudo.get(2));
		setTelefone(conteudo.get(3));
		setHoraEntrada(conteudo.get(4));
		setHoraSaida(conteudo.get(5));
		String x = conteudo.get(6);
		setTipo(x.charAt(0));
	}

	public String getNomeCorrespondenteAoCPF(char tipoUsuario) {
		return dao.getNomeCorrespondenteAoCPF(getCPF(), tipoUsuario);
	}

	public ArrayList<Usuario> getObjetoUsuario() {
		return dao.getObjetoUsuario(getCPF());
	}

	// retorna o cnpj da empresa que o funcionario trabalha cpf em questão
	public String getCNPJCorrespondente() {
		return dao.getCNPJCorrespondente(getCPF());
	}

	public String getPermissaoCorrespondente() {
		if (dao.getPermissaoCorrespondente(getCPF()))
			return "Sim";
		else
			return "Não";
	}

	public boolean atualizar() {
		return dao.atualizar(getCPF(), getNome(), getSobrenome(), getUsuario(), getSenha(), getTelefone(),
				getHoraEntrada(), getHoraSaida(), getPermissaoAlterarTemperatura());
	}

	public void excluir(String CPF) {
		dao.excluir(CPF);
	}

	public ArrayList<Usuario> usuariosAcessos(ArrayList<Usuario> usuarios) {
		return dao.dadosAcesso(usuarios);
	}

	public Usuario getUsuario(String usuario, String senha, char tipo) {
		return dao.getUsuario(usuario, senha, tipo);
	}

	public int compareTo(Usuario usuario) {
		if (this.usuario.compareTo(usuario.getUsuario()) < 0) {
			return -1;
		} else if (this.usuario.compareTo(usuario.getUsuario()) > 0) {
			return 1;
		}
		return 0;
	}

	public void registrarEntrada(String horarioE, String CPF) {
		dao.registrarEntrada(horarioE, CPF);
	}

	public void registrarSaida(String horaEntrada, String horarioSaida, String cpf) {
		dao.registrarSaida(horaEntrada, horarioSaida, cpf);
	}

	public Object[][] consultarAcessos(String CNPJEmpresa, String DataInicio, String DataFim, char tipoUsuario) {
		return dao.consultarAcessos(CNPJEmpresa, DataInicio, DataFim, tipoUsuario);
	}

	public Object[][] consultarAcessos(String DataInicio, String DataFim, char tipoUsuario) {
		return dao.consultarAcessos(DataInicio, DataFim, tipoUsuario);
	}

	public Object[][] consultarAcessos() {
		return dao.consultarAcessos();
	}

}
