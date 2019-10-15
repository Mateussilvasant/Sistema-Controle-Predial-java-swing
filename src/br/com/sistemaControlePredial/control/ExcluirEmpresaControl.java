package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import br.com.sistemaControlePredial.model.Conjunto;
import br.com.sistemaControlePredial.model.Empresa;
import br.com.sistemaControlePredial.model.Usuario;
import br.com.sistemaControlePredial.view.ExcluirEmpresaView;
import br.com.sistemaControlePredial.view.MensagemView;
import br.com.sistemaControlePredial.view.MenuView;
import br.com.sistemaControlePredial.view.componentes.Panel;

public class ExcluirEmpresaControl extends ExcluirEmpresaView {

	public Panel painel;

	public ExcluirEmpresaControl(MenuView menu, Panel panel) {
		super(menu, panel);
		painel = panel;
		addPesquisarListener(new PesquisarListener());
		addBotaoExcluirListener(new ExcluirEmpresaListener());
	}

	public void voltarMenuPrincipal(Panel painel) {

		if (menuView.getUsuario().getTipo() == 'S') {
			new SindicoControl(menuView);
		}

		if (menuView.getUsuario().getTipo() == 'A') {
			new AtendenteControl(menuView);
		}
	}

	private boolean pesquisar() {
		Empresa e = new Empresa();
		Iterator<Empresa> dados = e.consultarEmpresas(campoCNPJ.getLetras()).iterator();
		Empresa empresa;
		String comparar = "";
		boolean controle = false;

		while (dados.hasNext() == true) {
			empresa = dados.next();
			rotuloNomeEmpresa.setText(empresa.getRazaoSocial());
			comparar = Empresa.getCNPJ();
			controle = true;
		}
		if (comparar.equals("85.664.579/6954-25")) {
			new MensagemView(menuView.getString(144), menuView.getString(163), menuView, MensagemView.INFORMACAO);
			voltarMenuPrincipal(painel);
		}

		return controle;
	}

	private void excluir() {
		// Exclui todos os usuarios cadastrados no CNPJ informado
		Usuario usuario = new Usuario();
		usuario.excluir(campoCNPJ.getLetras());
		// Remove o conjunto da empresa do CNPJ informado
		Conjunto conjunto = new Conjunto();
		conjunto.remover(campoCNPJ.getLetras());
		// Exclui a empresa do CNPJ informado
		Empresa empresa = new Empresa();
		empresa.deletar(campoCNPJ.getLetras());
	}

	class ExcluirEmpresaListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {

			MensagemView confirmacao = new MensagemView(menuView.getString(64), menuView.getString(65), menuView,
					MensagemView.CONFIRMACAO);

			if (confirmacao.getRespostaBotao() == 2) {
				new MensagemView(menuView.getString(66), menuView.getString(67), menuView, MensagemView.INFORMACAO);
				excluir();
				confirmacao.dispose();
				voltarMenuPrincipal(painel);
			} else if (confirmacao.getRespostaBotao() == 1) {
				confirmacao.dispose();
				voltarMenuPrincipal(painel);
			}
		}
	}

	class PesquisarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			addPainelExcluir(painel);
			if (pesquisar() == false) {
				voltarMenuPrincipal(painel);
				// Mensagem caso o CNPJ não seja localizado.
				new MensagemView(menuView.getString(144), menuView.getString(145), menuView, MensagemView.INFORMACAO);
				voltarMenuPrincipal(painel);
			}
		}
	}
}
