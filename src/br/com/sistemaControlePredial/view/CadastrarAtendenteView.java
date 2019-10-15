package br.com.sistemaControlePredial.view;

import java.awt.event.ActionListener;

import br.com.sistemaControlePredial.control.CadastrarUsuarioControl;
import br.com.sistemaControlePredial.view.componentes.Panel;

public class CadastrarAtendenteView {

	public CadastrarUsuario cadastrarUsuario;
	protected MenuView menuView;
	protected Panel painel;

	public CadastrarAtendenteView(MenuView menu, Panel pane) {
		menuView = menu;
		painel = pane;
		cadastrarUsuario = new CadastrarUsuario(menu, pane);
		cadastrarUsuario.painel.remove(cadastrarUsuario.painelEmpresa);
		cadastrarUsuario.painelAcesso.remove(cadastrarUsuario.campoHorarioFim);
		cadastrarUsuario.painelAcesso.remove(cadastrarUsuario.campoHorarioInicio);
		cadastrarUsuario.painelAcesso.remove(cadastrarUsuario.rotuloHorarioFim);
		cadastrarUsuario.painelAcesso.remove(cadastrarUsuario.rotuloHorarioInicio);
		cadastrarUsuario.painelAcesso.remove(cadastrarUsuario.rotuloAlterarTemperatura);
		cadastrarUsuario.painelAcesso.remove(CadastrarUsuarioControl.escolhaAlterarTemperatura);
	}

	public void addVoltarListener(ActionListener l) {
		cadastrarUsuario.VoltarMenu.addActionListener(l);
	}

	public void addConcluirCadastroListener(ActionListener l) {
		cadastrarUsuario.botaoCadastrar.addActionListener(l);
	}

}
