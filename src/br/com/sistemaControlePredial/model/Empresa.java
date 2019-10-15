package br.com.sistemaControlePredial.model;

import java.util.ArrayList;

public class Empresa {
	private static String cnpj;
	private String razaoSocial;
	private int temperatura;
	private String horarioInicio;
	private String horarioFim;
	private String horarioArInicio;
	private String horarioArFim;
	private EmpresaDAO dao;
	private ArrayList<String> conteudo;

	public Empresa(String cnpj, String razaoSocial, int temperatura, String horarioInicio, String horarioFim,
			String horarioArInicio, String horarioArFim) {
		setCNPJ(cnpj);
		setRazaoSocial(razaoSocial);
		setTemperatura(temperatura);
		setHorarioInicio(horarioInicio);
		setHorarioFim(horarioFim);
		setHorarioArInicio(horarioArInicio);
		setHorarioArFim(horarioArFim);
		dao = new EmpresaDAO();
		conteudo = new ArrayList<String>();
	}

	public Empresa() {
		setCNPJ("");
		setRazaoSocial("");
		setTemperatura(0);
		setHorarioInicio("");
		setHorarioFim("");
		setHorarioArInicio("");
		setHorarioArFim("");
		dao = new EmpresaDAO();
		conteudo = new ArrayList<String>();
	}

	public void setCNPJ(String cnpj) {
		Empresa.cnpj = cnpj;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}

	public void setHorarioInicio(String horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public void setHorarioFim(String horarioFim) {
		this.horarioFim = horarioFim;
	}

	public void setHorarioArInicio(String horarioArInicio) {
		this.horarioArInicio = horarioArInicio;
	}

	public void setHorarioArFim(String horarioArFim) {
		this.horarioArFim = horarioArFim;
	}

	public static String getCNPJ() {
		return cnpj;
	}

	public String getCNPJNaoEstatico() {
		return cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public int getTemperatura() {
		return temperatura;
	}

	public String getHorarioInicio() {
		return horarioInicio;
	}

	public String getHorarioFim() {
		return horarioFim;
	}

	public String getHorarioArInicio() {
		return horarioArInicio;
	}

	public String getHorarioArFim() {
		return horarioArFim;
	}

	public boolean cadastrar() {
		return dao.cadastrar(getCNPJ(), getRazaoSocial(), getTemperatura(), getHorarioInicio(), getHorarioFim(),
				getHorarioArInicio(), getHorarioArFim());
	}

	public void consultar() {
		conteudo = dao.consultar(getCNPJ());
		setCNPJ(conteudo.get(0));
		setRazaoSocial(conteudo.get(1));
		setTemperatura(Integer.parseInt(conteudo.get(2)));
		setHorarioInicio(conteudo.get(3));
		setHorarioFim(conteudo.get(4));
		setHorarioArInicio(conteudo.get(5));
		setHorarioArFim(conteudo.get(6));
	}

	public ArrayList<Empresa> consultarEmpresas(String CNPJ) {
		return dao.getConsultar(CNPJ);
	}

	public ArrayList<Empresa> empresaCadastrar() {
		return dao.empresaCadastrar();
	}

	public ArrayList<Empresa> getNomeEmpresa(String cnpj) {
		return dao.getNomeEmpresa(cnpj);
	}

	public ArrayList<Empresa> getEmpresa(String nome) {
		return dao.getEmpresa(nome);
	}

	public int getTotalEmpresa() {
		return dao.getTotalEmpresa();
	}

	public boolean atualizar(String CNPJ, String razaoSocial, int temperatura, String horarioInicio, String horarioFim,
			String horarioArInicio, String horarioArFim) {
		return dao.atualizar(CNPJ, razaoSocial, temperatura, horarioInicio, horarioFim, horarioArInicio, horarioArFim);
	}

	public boolean atualizarTelaFuncionario(String CNPJ, int temP) {
		return dao.atualizarTelaFuncionario(CNPJ, temP);
	}

	public void deletar(String CNPJ) {
		dao.deletar(CNPJ);
	}

	public String getHorarioArInicioDoBanco(String cnpj) {
		return dao.getHorarioArInicioDoBanco(cnpj);
	}

	public String getHorarioArFimDoBanco(String cnpj) {
		return dao.getHorarioArFimDoBanco(cnpj);
	}
}
