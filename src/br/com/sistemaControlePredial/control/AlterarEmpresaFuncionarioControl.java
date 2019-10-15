package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import br.com.sistemaControlePredial.model.Empresa;
import br.com.sistemaControlePredial.view.AlterarEmpresaFuncionarioView;
import br.com.sistemaControlePredial.view.MensagemView;
import br.com.sistemaControlePredial.view.MenuView;
import br.com.sistemaControlePredial.view.componentes.Panel;

public class AlterarEmpresaFuncionarioControl extends AlterarEmpresaFuncionarioView {

	private String empresaCNPJ;
	private Empresa empresa;

	public AlterarEmpresaFuncionarioControl(MenuView menu, Panel painel) {
		super(menu, painel);
		if (autorizacaoAtualizar() == true) {
			consultar();
			addVoltarMenuListener(new VoltarListener());
			addSalvarAlteracoes(new SalvarAlteracoesListener());
		}
	}

	public void voltarMenuPrincipal(Panel painel) {
		new FuncionarioControl(menuView);
	}

	class VoltarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			voltarMenuPrincipal(painel);
		}

	}

	private boolean autorizacaoAtualizar() {
		boolean autorizacao = false;

		if (menuView.getUsuario().getPermissaoAlterarTemperatura() == true) {
			autorizacao = true;
		} else {
			voltarMenuPrincipal(painel);
			new MensagemView(menuView.getString(157), menuView.getString(173), menuView, MensagemView.INFORMACAO);
		}

		return autorizacao;
	}

	private void consultar() {
		empresaCNPJ = menuView.getUsuario().getCNPJCorrespondente();

		empresa = new Empresa();
		Iterator<Empresa> consulta = empresa.consultarEmpresas(empresaCNPJ).iterator();
		Empresa empre;

		System.out.print('a');

		if (consulta.hasNext() == true) {
			empre = consulta.next();
			campoTemperaturaMax.setText("" + empre.getTemperatura() + " °");
		} else {
			voltarMenuPrincipal(painel);
		}
	}

	private boolean atualizar() {
		empresa = new Empresa();
		int temperatura = Integer.parseInt(campoTemperaturaMax.getNumeros());
		return empresa.atualizarTelaFuncionario(empresaCNPJ, temperatura);
	}

	class SalvarAlteracoesListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				// Mensagens reutilizadas da classe AlterarEmpresaControl
				if (atualizar() == true) {
					voltarMenuPrincipal(painel);
					new MensagemView(menuView.getString(157), menuView.getString(160), menuView,
							MensagemView.INFORMACAO);
				} else {
					new MensagemView(menuView.getString(158), menuView.getString(161), menuView,
							MensagemView.INFORMACAO);
				}
			} catch (Exception erro) {
				// erro.printStackTrace();
				new MensagemView(menuView.getString(158), menuView.getString(161), menuView, MensagemView.INFORMACAO);
			}
		}

	}
}