package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.com.sistemaControlePredial.view.ConsultarAtendenteView;
import br.com.sistemaControlePredial.view.MenuView;
import br.com.sistemaControlePredial.view.componentes.Panel;

public class ConsultarAtendenteControl extends ConsultarAtendenteView {

	public ConsultarAtendenteControl(MenuView menu, Panel panel) {
		super(menu, panel);
		addVoltarListener(new VoltarListener());
	}

	class VoltarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			GerenciarAtendenteControl gerenciar = new GerenciarAtendenteControl(menuView, painel);
			gerenciar.gerenciar.gerenciarView2(painel);
		}

	}

}
