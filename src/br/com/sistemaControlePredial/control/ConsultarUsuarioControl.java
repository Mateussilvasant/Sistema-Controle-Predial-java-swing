package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.com.sistemaControlePredial.view.ConsultarUsuario;
import br.com.sistemaControlePredial.view.MenuView;
import br.com.sistemaControlePredial.view.componentes.Panel;

public class ConsultarUsuarioControl extends ConsultarUsuario {

	public ConsultarUsuarioControl(MenuView menu, Panel panel) {
		super(menu, panel);
		addBotaoVoltarListener(new VoltarListener());
	}

	class VoltarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {

			GerenciarFuncionarioControl gerenciar = new GerenciarFuncionarioControl(menuView, painel);
			// gerenciar.gerenciarView(painel);
			gerenciar.gerenciarView2(painel);
		}

	}

}
