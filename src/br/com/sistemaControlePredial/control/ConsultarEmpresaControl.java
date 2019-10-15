package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import br.com.sistemaControlePredial.model.Conjunto;
import br.com.sistemaControlePredial.model.Empresa;
import br.com.sistemaControlePredial.view.ConsultarEmpresaView;
import br.com.sistemaControlePredial.view.MensagemView;
import br.com.sistemaControlePredial.view.MenuView;
import br.com.sistemaControlePredial.view.componentes.Panel;

public class ConsultarEmpresaControl extends ConsultarEmpresaView {

	public Panel painel;
	public PesquisarEmpresaListener pesquisarListener;
	private Empresa empresa;

	public ConsultarEmpresaControl(MenuView menu, Panel panel) {
		super(panel, menu);
		painel = panel;
		addPesquisarEmpresaListener(pesquisarListener = new PesquisarEmpresaListener());
		addBotaoVoltarListener(new VoltarMenuListener());
	}

	public void voltarMenuPrincipal(Panel painel) {
		if (menuView.getUsuario().getTipo() == 'S') {
			new SindicoControl(menuView);
		}

		if (menuView.getUsuario().getTipo() == 'A') {
			new AtendenteControl(menuView);
		}
	}

	// Carrega e adiciona os dados da empresa nos textfilds correspondentes
	private void mostraDados() {
		empresa = new Empresa();
		Iterator<Empresa> consulta = empresa.consultarEmpresas(campoPesquisa.getLetras()).iterator();
		Empresa empre;

		empre = consulta.next();
		campoNomeEmpresa.setText(empre.getRazaoSocial());
		campoCnpj.setText(Empresa.getCNPJ());
		campoCnpj.setEditable(false);
		campoInicioEmpresa.setText(empre.getHorarioInicio());
		campoFimEmpresa.setText(empre.getHorarioFim());
		campoArCondicionadoInicio.setText(empre.getHorarioArInicio());
		campoArCondicionadoFim.setText(empre.getHorarioArFim());
		campoTemperaturaMax.setText("" + empre.getTemperatura() + "°");
	}

	// Carrega os conjuntos para a lista
	private void getConjuntosEmpresa() {
		Conjunto conjunto = new Conjunto();
		Iterator<Conjunto> consultaConjunto = conjunto.EmpresaConsultar(campoPesquisa.getLetras()).iterator();
		Conjunto conj;

		while (consultaConjunto.hasNext() == true) {
			conj = consultaConjunto.next();
			adicionarElemento(menuView.getString(155) + ": " + conj.getAndar() + " | " + menuView.getString(156) + ": "
					+ conj.getNumero());
		}
	}

	class PesquisarEmpresaListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			informacoesView(painel);

			// Limpa a lista
			removerElemento();

			try {
				// Dados da empresa
				mostraDados();
				// Dados do conjunto
				getConjuntosEmpresa();

			} catch (Exception e) {
				voltarMenuPrincipal(painel);
				new MensagemView(menuView.getString(144), menuView.getString(145), menuView, MensagemView.INFORMACAO);
			}
		}
	}

	class VoltarMenuListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			voltarMenuPrincipal(painel);
		}
	}
}
