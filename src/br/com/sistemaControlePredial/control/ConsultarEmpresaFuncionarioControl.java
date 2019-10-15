package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import br.com.sistemaControlePredial.model.Conjunto;
import br.com.sistemaControlePredial.model.Empresa;
import br.com.sistemaControlePredial.view.ConsultarEmpresaFuncionarioView;
import br.com.sistemaControlePredial.view.MenuView;
import br.com.sistemaControlePredial.view.componentes.Panel;

public class ConsultarEmpresaFuncionarioControl extends ConsultarEmpresaFuncionarioView {

	String empresaCNPJ;

	public ConsultarEmpresaFuncionarioControl(MenuView menu, Panel painel) {
		super(menu, painel);
		consultar();
		addVoltarListener(new VoltarListener());
	}

	public void consultar() {
		empresaCNPJ = menuView.getUsuario().getCNPJCorrespondente();

		Empresa empresa = new Empresa();
		Iterator<Empresa> consulta = empresa.consultarEmpresas(empresaCNPJ).iterator();
		Empresa empre;

		while (consulta.hasNext()) {
			empre = consulta.next();
			consultarEmpresaControl.campoNomeEmpresa.setText(empre.getRazaoSocial());
			consultarEmpresaControl.campoCnpj.setText(Empresa.getCNPJ());
			consultarEmpresaControl.campoInicioEmpresa.setText(empre.getHorarioInicio());
			consultarEmpresaControl.campoFimEmpresa.setText(empre.getHorarioFim());
			consultarEmpresaControl.campoArCondicionadoInicio.setText(empre.getHorarioArInicio());
			consultarEmpresaControl.campoArCondicionadoFim.setText(empre.getHorarioArFim());
			consultarEmpresaControl.campoTemperaturaMax.setText("" + empre.getTemperatura() + " °");
		}
		// Carrega os conjuntos para a lista
		Conjunto conjunto = new Conjunto();
		Iterator<Conjunto> consultaConjunto = conjunto.EmpresaConsultar(empresaCNPJ).iterator();
		Conjunto conj;

		while (consultaConjunto.hasNext() == true) {
			conj = consultaConjunto.next();
			consultarEmpresaControl.adicionarElemento(menuView.getString(155) + ": " + conj.getAndar() + " | "
					+ menuView.getString(156) + ": " + conj.getNumero());
		}
	}

	class VoltarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			new FuncionarioControl(menuView);
		}
	}
}