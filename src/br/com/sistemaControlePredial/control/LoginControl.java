package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.sistemaControlePredial.model.Catraca;
import br.com.sistemaControlePredial.model.Usuario;
import br.com.sistemaControlePredial.view.LoginView;
import br.com.sistemaControlePredial.view.MensagemView;
import br.com.sistemaControlePredial.view.MenuView;

@SuppressWarnings("unused")
public class LoginControl extends LoginView {

	private MenuView menuView;
	private AtendenteControl atendenteControl;
	private FuncionarioControl funcionarioControl;
	private SindicoControl sindicoControl;
	public static Usuario usuarioAcesso;

	// constantes
	public static final int SINDICO = 0;
	public static final int ATENDENTE = 1;
	public static final int FUNCIONARIO = 2;

	public LoginControl(MenuView menu) {
		super(menu);
		menuView = menu;
		addbotaoAcessarPredioListener(new AcessarPredioListener());
		addbotaoLogarListener(new LogarListener());
		addEscolhaUsuarioListener(new EscolhaUsuarioListener());
		menuView.addBotaoSairListener(new SairListener());
	}

	public boolean efetuarLogin() {
		Usuario u = new Usuario();
		boolean resposta = false;

		if (usuarioEscolhido == SINDICO) {

			Usuario sindico = u.getUsuario(this.getUsuario(), getSenha(), 'S');

			if (sindico != null) {
				menuView.setUsuario(sindico);
				sindicoControl = new SindicoControl(menuView);
				resposta = true;
			}
		}
		if (usuarioEscolhido == ATENDENTE) {

			Usuario atendente = u.getUsuario(this.getUsuario(), getSenha(), 'A');

			if (atendente != null) {
				menuView.setUsuario(atendente);
				atendenteControl = new AtendenteControl(menuView);
				resposta = true;
			}
		}
		if (usuarioEscolhido == FUNCIONARIO) {

			Usuario funcionario = u.getUsuario(this.getUsuario(), getSenha(), 'F');

			if (funcionario != null) {
				menuView.setUsuario(funcionario);
				funcionarioControl = new FuncionarioControl(menuView);
				resposta = true;
			}
		}

		return resposta;
	}

	private void registraEntradaNaMemoria(Catraca catraca) {
		try {
			Date horaEntrada = null;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			horaEntrada = new Date(System.currentTimeMillis());

			usuarioAcesso = new Usuario();
			usuarioAcesso.setHoraEntrada(dateFormat.format(horaEntrada));
			usuarioAcesso.setCPF(catraca.usuario.getCPF());
			new MensagemView(menuView.getString(174), menuView.getString(175), menuView, MensagemView.INFORMACAO);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean acessarPredio() {
		Catraca catraca = new Catraca();
		boolean saida = false;

		if (usuarioEscolhido == SINDICO) {

			if (catraca.acessarPredio(this.getUsuario(), getSenha(), 'S')) {
				menuView.addBotaoSairPredio();
				menuView.entrouPredio();
				saida = true;
				registraEntradaNaMemoria(catraca);

			}

		}
		if (usuarioEscolhido == ATENDENTE) {
			if (catraca.acessarPredio(this.getUsuario(), getSenha(), 'A')) {
				menuView.addBotaoSairPredio();
				menuView.entrouPredio();
				saida = true;
				registraEntradaNaMemoria(catraca);
			}
		}
		if (usuarioEscolhido == FUNCIONARIO) {

			if (catraca.acessarPredio(this.getUsuario(), getSenha(), 'F')) {
				menuView.addBotaoSairPredio();
				menuView.entrouPredio();
				saida = true;
				registraEntradaNaMemoria(catraca);
				registraEntradaNaMemoria(catraca);
				painelLogin.remove(botaoAcessarPredio);
				painelLogin.revalidate();
				painelLogin.repaint();
			}
		}

		return saida;
	}

	class LogarListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!efetuarLogin()) {
				new MensagemView(menuView.getString(146), menuView.getString(147), menuView, MensagemView.INFORMACAO);
			}
		}
	}

	class SairListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {

			MensagemView confirmacao = new MensagemView(menuView.getString(101), menuView.getString(102), menuView,
					MensagemView.CONFIRMACAO);
			if (confirmacao.getRespostaBotao() == 2) {
				System.exit(0);
				confirmacao.dispose();
			}
		}
	}

	class AcessarPredioListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!acessarPredio()) {
				new MensagemView(menuView.getString(146), menuView.getString(147), menuView, MensagemView.INFORMACAO);
			}

		}

	}

	class EscolhaUsuarioListener implements ItemListener {
		public void itemStateChanged(ItemEvent evento) {
			if (evento.getStateChange() == ItemEvent.SELECTED) {
				if (escolhaUsuario.getSelectedIndex() == 0) {
					usuarioEscolhido = SINDICO;
				}
				if (escolhaUsuario.getSelectedIndex() == 1) {
					usuarioEscolhido = ATENDENTE;
				}

				if (escolhaUsuario.getSelectedIndex() == 2) {
					usuarioEscolhido = FUNCIONARIO;
				}
			}
		}
	}

}
