package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.com.sistemaControlePredial.view.AtendenteView;
import br.com.sistemaControlePredial.view.MensagemView;
import br.com.sistemaControlePredial.view.MenuView;

public class AtendenteControl extends AtendenteView {

	public CadastrarEmpresaControl cadastrarEmpresaControl;
	public ConsultarEmpresaControl consultarEmpresaControl;
	public AlterarEmpresaControl alterarEmpresaControl;
	public ExcluirEmpresaControl excluirEmpresaControl;
	public GerenciarFuncionarioControl gerenciarFuncionarioControl;

	public AtendenteControl(MenuView menu) {
		super(menu);
		addCadastrarEmpresaListener(new CadastrarEmpresaListener());
		addConsultarEmpresaListener(new ConsultarEmpresaListener());
		addAlterarEmpresaListener(new AlterarEmpresaListener());
		addExcluirEmpresaListener(new ExcluirEmpresaListener());
		addGerenciarFuncionarioListener(new GerenciarFuncionarioListener());
		addBotaoDeslogarListener(new DeslogarListener());
	}

	class DeslogarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			MensagemView confirmacao = new MensagemView(menuView.getString(101), menuView.getString(102), menuView,
					MensagemView.CONFIRMACAO);

			if (confirmacao.getRespostaBotao() == 2) {
				menuView.painelFundo.removeAll();
				new LoginControl(menuView);
				menuView.painelFundo.repaint();
				menuView.painelFundo.revalidate();
				confirmacao.dispose();
			}

		}

	}

	class CadastrarEmpresaListener implements ActionListener {

		public void actionPerformed(ActionEvent cadastrar) {
			cadastrarEmpresaControl = new CadastrarEmpresaControl(menuView, menuPrincipal, true);
		}

	}

	class ConsultarEmpresaListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			consultarEmpresaControl = new ConsultarEmpresaControl(menuView, menuPrincipal);
		}

	}

	class AlterarEmpresaListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			alterarEmpresaControl = new AlterarEmpresaControl(menuView, menuPrincipal);
		}

	}

	class ExcluirEmpresaListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			excluirEmpresaControl = new ExcluirEmpresaControl(menuView, menuPrincipal);
		}

	}

	class GerenciarFuncionarioListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			gerenciarFuncionarioControl = new GerenciarFuncionarioControl(menuView, menuPrincipal);
		}
	}
}