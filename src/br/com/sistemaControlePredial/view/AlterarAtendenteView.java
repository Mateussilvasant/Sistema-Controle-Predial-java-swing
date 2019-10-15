package br.com.sistemaControlePredial.view;

import java.awt.event.ActionListener;

import br.com.sistemaControlePredial.view.componentes.Panel;

public class AlterarAtendenteView {

	protected MenuView menuView;
	public CadastrarAtendenteView cadastrarAtendente;
	public Panel painel;

	public AlterarAtendenteView(MenuView menu, Panel pane) {
		menuView = menu;
		painel = pane;
		cadastrarAtendente = new CadastrarAtendenteView(menu, pane);
		cadastrarAtendente.cadastrarUsuario.painel.setBorderPanel(menuView.getString(58));
		cadastrarAtendente.cadastrarUsuario.menuView.setTitle(menuView.getString(96));
		cadastrarAtendente.cadastrarUsuario.campoCPF.setEditable(false);
		cadastrarAtendente.cadastrarUsuario.campoCPF.setEditable(false);
		cadastrarAtendente.cadastrarUsuario.botaoCadastrar.setText(menuView.getString(59));
	}

	public void addVoltarListener(ActionListener l) {
		cadastrarAtendente.cadastrarUsuario.VoltarMenu.addActionListener(l);
	}

	public void addSalvarAlteracoesListener(ActionListener l) {
		cadastrarAtendente.cadastrarUsuario.botaoCadastrar.addActionListener(l);
	}
}
