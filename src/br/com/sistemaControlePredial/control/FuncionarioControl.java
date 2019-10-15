package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.com.sistemaControlePredial.view.ConsultarEmpresaFuncionarioView;
import br.com.sistemaControlePredial.view.FuncionarioView;
import br.com.sistemaControlePredial.view.MensagemView;
import br.com.sistemaControlePredial.view.MenuView;

public class FuncionarioControl extends FuncionarioView {

	public AlterarEmpresaFuncionarioControl alterarEmpresaFuncionarioControl;
	public ConsultarEmpresaFuncionarioView consultarEmpresaFuncionarioView;

	public FuncionarioControl(MenuView menu) {
		super(menu);
		addConsultarEmpresaListener(new ConsultarListener());
		addAlterarEmpresaListener(new AlterarListener());
		addBotaoDeslogarListener(new DeslogarListener());

	}

	class DeslogarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			final MensagemView confirmacao = new MensagemView(menuView.getString(101), menuView.getString(102), menuView,
					MensagemView.CONFIRMACAO);

			if (confirmacao.getRespostaBotao() == 2) {
				menuView.painelFundo.removeAll();
				new LoginControl(menuView);
				menuView.painelFundo.repaint();
				menuView.painelFundo.revalidate();
				confirmacao.dispose();
			}

			confirmacao.getBotaoNao().addActionListener(ActionEvent -> {
				confirmacao.dispose();
			});

		}

	}

	class ConsultarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			consultarEmpresaFuncionarioView = new ConsultarEmpresaFuncionarioControl(menuView,
					atendenteView.menuPrincipal);

		}

	}

	class AlterarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			alterarEmpresaFuncionarioControl = new AlterarEmpresaFuncionarioControl(menuView,
					atendenteView.menuPrincipal);
		}

	}

}
