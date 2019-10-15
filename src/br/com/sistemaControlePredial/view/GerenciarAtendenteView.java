package br.com.sistemaControlePredial.view;

import java.awt.event.ActionListener;

import br.com.sistemaControlePredial.view.componentes.Panel;

public class GerenciarAtendenteView {

	protected MenuView menuView;
	public GerenciarFuncionarioView gerenciar;
	public Panel painelFundo;

	public GerenciarAtendenteView(MenuView menu, Panel painel) {
		menuView = menu;
		painelFundo = painel;
		gerenciar = new GerenciarFuncionarioView(menu, painel);
		menuView.setTitle(menuView.getString(115));

		gerenciar.rotuloBusca.setText(menuView.getString(114));
		gerenciar.painelBusca.setBorderPanel(menuView.getString(111));
		gerenciar.botaoBuscar.setText(menuView.getString(112));
		gerenciar.painelCadastrar.setBorderPanel(menuView.getString(113));
	}

	public String getCPF() {
		return gerenciar.getDadosCampoCPFField();
	}

	public void setRotuloNomeUsuario(String nome) {
		gerenciar.setRotuloNomeUsuario(nome);
	}

	public void addExcluirListener(ActionListener l) {
		gerenciar.botaoExcluir.addActionListener(l);
	}

	public void addAlterarListener(ActionListener l) {
		gerenciar.botaoAlterar.addActionListener(l);
	}

	public void addConsultarListener(ActionListener l) {
		gerenciar.botaoConsultar.addActionListener(l);
	}

	public void addBotaoCadastrarListener(ActionListener l) {
		gerenciar.botaoCadastrar.addActionListener(l);
	}

	public void addBotaoBuscarListener(ActionListener l) {
		gerenciar.botaoBuscar.addActionListener(l);
	}

}
