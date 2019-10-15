package br.com.sistemaControlePredial.view;

import java.awt.event.ActionListener;

import br.com.sistemaControlePredial.control.CadastrarUsuarioControl;
import br.com.sistemaControlePredial.view.componentes.Panel;

public class ConsultarUsuario {

	protected MenuView menuView;
	public CadastrarUsuario modeloBase;
	public Panel painel;

	public ConsultarUsuario(MenuView menu, Panel panel) {
		menuView = menu;
		painel = panel;
		modeloBase = new CadastrarUsuario(menuView, panel);
		panel.setBorderPanel(menuView.getString(94));
		menuView.setTitle(menuView.getString(95));

		modeloBase.rotuloEscolherEmpresa.setText(menuView.getString(90) + ":");

		modeloBase.campoNome.setEditable(false);
		modeloBase.campoSobrenome.setEditable(false);
		modeloBase.campoCPF.setEditable(false);
		modeloBase.campoHorarioFim.setEditable(false);
		modeloBase.campoHorarioInicio.setEditable(false);
		modeloBase.campoTelefone.setEditable(false);
		modeloBase.campoUsuario.setEditable(false);
		modeloBase.campoSenha.setEditable(false);

		CadastrarUsuarioControl.mudarHabilitacaoComboBoxAlterarTemperatura(false);
		CadastrarUsuarioControl.mudarHabilitacaoComboBoxEscolherEmpresa(false);

		panel.remove(modeloBase.botaoCadastrar);
		panel.repaint();
		panel.revalidate();

	}

	public void addBotaoVoltarListener(ActionListener l) {
		modeloBase.VoltarMenu.addActionListener(l);
	}
}
