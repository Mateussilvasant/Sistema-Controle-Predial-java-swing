package br.com.sistemaControlePredial.view;

import java.awt.event.ActionListener;

import br.com.sistemaControlePredial.control.ConsultarEmpresaControl;
import br.com.sistemaControlePredial.view.componentes.Panel;

public class ConsultarEmpresaFuncionarioView {

	public ConsultarEmpresaControl consultarEmpresaControl;
	public MenuView menuView;

	public ConsultarEmpresaFuncionarioView(MenuView menu, Panel painel) {
		menuView = menu;
		consultarEmpresaControl = new ConsultarEmpresaControl(menu, painel);
		consultarEmpresaControl.informacoesView(painel);
		consultarEmpresaControl.painel.remove(consultarEmpresaControl.rotuloPesquisa);
		consultarEmpresaControl.painel.remove(consultarEmpresaControl.campoPesquisa);
		consultarEmpresaControl.painel.remove(consultarEmpresaControl.botaoPesquisa);
		consultarEmpresaControl.painel.repaint();
		consultarEmpresaControl.painel.revalidate();
	}

	public void addVoltarListener(ActionListener l) {
		consultarEmpresaControl.botaoVoltar.addActionListener(l);
	}
}
