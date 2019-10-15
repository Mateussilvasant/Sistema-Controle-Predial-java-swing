package br.com.sistemaControlePredial.view;

import java.awt.event.ActionListener;

public class FuncionarioView {

	protected MenuView menuView;
	public AtendenteView atendenteView;

	public FuncionarioView(MenuView menu) {
		menuView = menu;
		menuView.painelFundo.removeAll();
		atendenteView = new AtendenteView(menu);
		atendenteView.rotuloTipoAcesso.setText(menuView.getString(99));
		atendenteView.categoriaEmpresa.remove(atendenteView.cadastrarEmpresa);
		atendenteView.categoriaEmpresa.remove(atendenteView.excluirEmpresa);
		atendenteView.menuLateral.remove(atendenteView.categoriaFuncionario);
		menuView.painelFundo.repaint();
		menuView.painelFundo.revalidate();

	}

	public void addBotaoDeslogarListener(ActionListener l) {
		atendenteView.botaoDeslogar.addActionListener(l);
	}

	public void addConsultarEmpresaListener(ActionListener l) {
		atendenteView.consultarEmpresa.addActionListener(l);
	}

	public void addAlterarEmpresaListener(ActionListener l) {
		atendenteView.alterarEmpresa.addActionListener(l);

	}
}
