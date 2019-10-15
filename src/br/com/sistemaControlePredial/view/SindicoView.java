package br.com.sistemaControlePredial.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import br.com.sistemaControlePredial.control.AtendenteControl;
import br.com.sistemaControlePredial.view.componentes.Button;
import br.com.sistemaControlePredial.view.componentes.Panel;

public class SindicoView {

	protected MenuView menuView;
	public AtendenteControl atendenteControl;
	public Button botaoGerenciarAtendente;
	public Button botaoConsultarAcessos;
	public Button botaoEnviarLista;
	public Button botaoEnviarTemperatura;
	public Panel painelAcesso;
	public Panel painelTemperatura;

	public SindicoView(MenuView menu) {
		menuView = menu;

		painelAcesso = new Panel(menuView.getString(106));
		painelAcesso.setPreferredSize(new Dimension(menuView.getX() / 5, menuView.getX() / 10));

		painelTemperatura = new Panel(menuView.getString(109));
		painelTemperatura.setPreferredSize(new Dimension(menuView.getX() / 5, menuView.getX() / 15));

		botaoEnviarLista = new Button(menuView.getString(108), 200, 32);
		botaoConsultarAcessos = new Button(menuView.getString(107), 200, 32);
		botaoEnviarTemperatura = new Button(menuView.getString(110), 200, 32);

		atendenteControl = new AtendenteControl(menuView);
		atendenteControl.rotuloTipoAcesso.setText(menuView.getString(4));
		atendenteControl.categoriaFuncionario.setBorderPanel(menuView.getString(1));
		atendenteControl.gerenciarUsuarios.setText(menuView.getString(104));
		botaoGerenciarAtendente = new Button(menuView.getString(105), 200, 32);

		painelAcesso.add(botaoConsultarAcessos);
		painelAcesso.add(botaoEnviarLista);

		painelTemperatura.add(botaoEnviarTemperatura);

		atendenteControl.categoriaFuncionario.add(botaoGerenciarAtendente);
		atendenteControl.categoriaFuncionario
				.setPreferredSize(new Dimension(menuView.getX() / 5, menuView.getX() / 10));

		atendenteControl.menuLateral.add(painelAcesso);
		atendenteControl.menuLateral.add(painelTemperatura);

	}

	public void addbotaoGerenciarAtendenteListener(ActionListener l) {
		botaoGerenciarAtendente.addActionListener(l);
	}

	public void addbotaoConsultarAcessosListener(ActionListener l) {
		botaoConsultarAcessos.addActionListener(l);
	}

	public void addbotaoEnviarListaListener(ActionListener l) {
		botaoEnviarLista.addActionListener(l);
	}

	public void addbotaoEnviarTemperaturaListener(ActionListener l) {
		botaoEnviarTemperatura.addActionListener(l);
	}
}
