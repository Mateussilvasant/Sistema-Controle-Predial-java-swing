package br.com.sistemaControlePredial.view;

import java.awt.event.ActionListener;

import br.com.sistemaControlePredial.view.componentes.Panel;

public class ConsultarAtendenteView {

	protected MenuView menuView;
	public CadastrarAtendenteView cadastrarAtendenteView;
	public Panel painel;

	public ConsultarAtendenteView(MenuView menu, Panel panel) {
		menuView = menu;
		painel = panel;
		cadastrarAtendenteView = new CadastrarAtendenteView(menuView, painel);
		cadastrarAtendenteView.cadastrarUsuario.painel.setBorderPanel(menuView.getString(94));
		cadastrarAtendenteView.cadastrarUsuario.menuView.setTitle(menuView.getString(95));
		cadastrarAtendenteView.cadastrarUsuario.campoNome.setEditable(false);
		cadastrarAtendenteView.cadastrarUsuario.campoCPF.setEditable(false);
		cadastrarAtendenteView.cadastrarUsuario.campoTelefone.setEditable(false);
		cadastrarAtendenteView.cadastrarUsuario.campoSobrenome.setEditable(false);
		cadastrarAtendenteView.cadastrarUsuario.campoUsuario.setEditable(false);
		cadastrarAtendenteView.cadastrarUsuario.campoSenha.setEditable(false);
		panel.remove(cadastrarAtendenteView.cadastrarUsuario.botaoCadastrar);
	}

	public void addVoltarListener(ActionListener l) {
		cadastrarAtendenteView.cadastrarUsuario.VoltarMenu.addActionListener(l);
	}
}
