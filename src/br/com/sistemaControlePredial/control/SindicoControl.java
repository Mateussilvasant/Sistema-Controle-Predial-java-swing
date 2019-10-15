package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.com.sistemaControlePredial.model.Catraca;
import br.com.sistemaControlePredial.model.Usuario;
import br.com.sistemaControlePredial.view.MensagemView;
import br.com.sistemaControlePredial.view.MenuView;
import br.com.sistemaControlePredial.view.SindicoView;

public class SindicoControl extends SindicoView {

	public GerenciarAtendenteControl gerenciarAtendenteControl;

	public SindicoControl(MenuView menu) {
		super(menu);

		addbotaoConsultarAcessosListener(new ConsultarAcessoListener());
		addbotaoEnviarListaListener(new EnviarListaListener());
		addbotaoEnviarTemperaturaListener(new EnviarTemperaturaListener());
		addbotaoGerenciarAtendenteListener(new GerenciarAtendenteListener());
	}

	public SindicoView getGerenciar() {
		return this;
	}

	class GerenciarAtendenteListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			new GerenciarAtendenteControl(menuView, atendenteControl.menuPrincipal);
		}

	}

	class EnviarTemperaturaListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			new ConfigurarTemperaturaControl(menuView, atendenteControl.menuPrincipal);
		}

	}

	class ConsultarAcessoListener implements ActionListener {

		public void actionPerformed(ActionEvent etoven) {
			new ConsultarAcessosControl(menuView, atendenteControl.menuPrincipal);
		}

	}

	class EnviarListaListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {

			Catraca catraca = new Catraca(new Usuario());
			if (catraca.atualizarListaAcessos()) {
				new MensagemView(menuView.getString(133), menuView.getString(134), menuView, MensagemView.INFORMACAO);
			}
		}
	}
}