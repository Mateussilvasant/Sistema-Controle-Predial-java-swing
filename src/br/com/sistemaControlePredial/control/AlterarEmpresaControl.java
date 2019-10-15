package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.NoSuchElementException;

import br.com.sistemaControlePredial.model.Conjunto;
import br.com.sistemaControlePredial.model.Empresa;
import br.com.sistemaControlePredial.view.AlterarEmpresaView;
import br.com.sistemaControlePredial.view.MensagemView;
import br.com.sistemaControlePredial.view.MenuView;
import br.com.sistemaControlePredial.view.componentes.Panel;

public class AlterarEmpresaControl extends AlterarEmpresaView {

	public Panel painel;
	private Conjunto conjunto;
	private Empresa empresa;
	private Iterator<Conjunto> dados;

	public AlterarEmpresaControl(MenuView menu, Panel panel) {
		super(menu, panel);
		painel = panel;
		menuView = menu;
		addBotaoPesquisarListener(new PesquisarListener());

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
		Iterator<Empresa> consulta = empresa.consultarEmpresas(campoCNPJ.getLetras()).iterator();
		Empresa empre;

		if (consulta.hasNext() == true) {
			empre = consulta.next();

			cadastrarControl.campoNomeEmpresa.setText(empre.getRazaoSocial());
			cadastrarControl.campoCnpj.setText(Empresa.getCNPJ());
			cadastrarControl.campoCnpj.setEditable(false);
			cadastrarControl.campoInicioEmpresa.setText(empre.getHorarioInicio());
			cadastrarControl.campoFimEmpresa.setText(empre.getHorarioFim());
			cadastrarControl.campoArCondicionadoInicio.setText(empre.getHorarioArInicio());
			cadastrarControl.campoArCondicionadoFim.setText(empre.getHorarioArFim());
			cadastrarControl.campoTemperaturaMax.setText("" + empre.getTemperatura());
			cadastrarControl.panel.repaint();
			cadastrarControl.panel.revalidate();

		} else {
			new MensagemView(menuView.getString(144), menuView.getString(145), menuView, MensagemView.INFORMACAO);
			voltarMenuPrincipal(painel);
		}
	}

	private void setConjuntos() {
		cadastrarControl.modeloConjunto.removeAllElements();
		dados = conjunto.atualizarEmpresa(campoCNPJ.getLetras()).iterator();
		Conjunto cj;

		while (dados.hasNext() == true) {
			cj = dados.next();
			cadastrarControl.adicionarItemComboBox(menuView.getString(155) + ": " + cj.getAndar() + " | "
					+ menuView.getString(156) + ": " + cj.getNumero());
		}
	}

	// Atualiza os dados da empresa
	private boolean atualizarDadosEmpresa() {
		Empresa e = new Empresa();
		return e.atualizar(cadastrarControl.campoCnpj.getLetras(), cadastrarControl.campoNomeEmpresa.getText(),
				Integer.parseInt(cadastrarControl.campoTemperaturaMax.getNumeros()),
				cadastrarControl.campoInicioEmpresa.getLetras(), cadastrarControl.campoFimEmpresa.getLetras(),
				cadastrarControl.campoArCondicionadoInicio.getLetras(),
				cadastrarControl.campoArCondicionadoFim.getLetras());
	}

	// Faz a atualizacao dos conjuntos
	private void atualizaConjuntos() {
		// Remove todos os conjuntos
		conjunto.remover(campoCNPJ.getLetras());

		// Localiza os conjuntos para serem cadastrados
		Conjunto cj;
		int controle = 0;

		while (controle < cadastrarControl.conjuntosItem.size()) {
			dados = conjunto.consultar().iterator();
			while (dados.hasNext() == true) {
				cj = dados.next();
				String comparar = menuView.getString(155) + ": " + cj.getAndar() + " | " + menuView.getString(156)
						+ ": " + cj.getNumero();
				if (cadastrarControl.conjuntosItem.get(controle).equals(comparar)) {
					conjunto.cadastrar(campoCNPJ.getLetras(), cj.getIdConjunto());
				}

			}
			controle++;
		}
		;
	}

	// Carrega os conjuntos para a lista
	private String[] getConjuntosEmpresa() {

		Iterator<Conjunto> consultaConjunto = conjunto.EmpresaConsultar(campoCNPJ.getLetras()).iterator();
		Conjunto conj;
		String saida[] = new String[conjunto.totalConjuntos(campoCNPJ.getLetras())];
		int posi = 0;

		while (consultaConjunto.hasNext() == true) {
			conj = consultaConjunto.next();
			saida[posi] = menuView.getString(155) + ": " + conj.getAndar() + " | " + menuView.getString(156) + ": "
					+ conj.getNumero();
			posi++;
		}

		return saida;
	}

	@SuppressWarnings("unchecked")
	public void configuraListaConjuntos() {

		cadastrarControl.conjuntosItem.removeAllElements();

		Object[] conjuntos = getConjuntosEmpresa();
		for (int d = 0; d < conjuntos.length; d++) {

			cadastrarControl.adicionarConjunto(conjuntos[d]);
		}

		cadastrarControl.listaConjuntosEscolhidos.setModel(cadastrarControl.conjuntosItem);
	}

	class SalvarAlteracoesListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			try {
				// Verifica se ha pelo menos um conjunto na empresa
				if (cadastrarControl.conjuntosItem.size() != 0) {

					if (cadastrarControl.validarHora() == true && cadastrarControl.validaTemperatura() == true) {
						// retorna true se a empresa foi atualizado com sucesso
						if (atualizarDadosEmpresa() == true) {
							// atualiza os conjuntos
							atualizaConjuntos();
							new MensagemView(menuView.getString(157), menuView.getString(160), menuView,
									MensagemView.INFORMACAO);
							voltarMenuPrincipal(painel);
						} else {
							new MensagemView(menuView.getString(158), menuView.getString(161), menuView,
									MensagemView.INFORMACAO);
						}
					}
				} else {
					new MensagemView(menuView.getString(159), menuView.getString(162), menuView,
							MensagemView.INFORMACAO);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}

	}

	class PesquisarListener implements ActionListener {

		boolean atualizar = false;

		public void actionPerformed(ActionEvent evento) {

			if (atualizar == false) {
				alterarView(painel);
				atualizar = true;
			}
			addSalvarAlteracoesListener(new SalvarAlteracoesListener());
			conjunto = new Conjunto();
			configuraListaConjuntos();
			setConjuntos();
			try {
				// Mostra os dados da empresa
				mostraDados();

			} catch (NoSuchElementException e) {
				new MensagemView(menuView.getString(144), menuView.getString(145), menuView, MensagemView.INFORMACAO);
				voltarMenuPrincipal(painel);
			}

		}
	}
}
