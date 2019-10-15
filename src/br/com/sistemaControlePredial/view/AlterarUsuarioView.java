package br.com.sistemaControlePredial.view;

import java.awt.event.ActionListener;

import br.com.sistemaControlePredial.view.componentes.Panel;

public class AlterarUsuarioView {

	protected MenuView menuView;
	public CadastrarUsuario cadastrarUsuario;
	public Panel painel;

	public AlterarUsuarioView(MenuView menu, Panel panel) {
		painel = panel;
		menuView = menu;
		cadastrarUsuario = new CadastrarUsuario(menuView, panel);
		panel.setBorderPanel(menuView.getString(58));
		menuView.setTitle(menuView.getString(96));
		cadastrarUsuario.botaoCadastrar.setText(menuView.getString(59));
		cadastrarUsuario.campoCPF.setEditable(false);

		panel.repaint();
		panel.revalidate();
	}

	public void addbotaoVoltarListener(ActionListener l) {
		cadastrarUsuario.VoltarMenu.addActionListener(l);
	}

	public void addSalvarAlteracoesListener(ActionListener l) {
		cadastrarUsuario.botaoCadastrar.addActionListener(l);
	}

}
