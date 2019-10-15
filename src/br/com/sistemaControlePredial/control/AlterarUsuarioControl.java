package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import br.com.sistemaControlePredial.model.Usuario;
import br.com.sistemaControlePredial.view.AlterarUsuarioView;
import br.com.sistemaControlePredial.view.MensagemView;
import br.com.sistemaControlePredial.view.MenuView;
import br.com.sistemaControlePredial.view.componentes.Panel;
import br.com.sistemaControlePredial.view.componentes.TextFieldFormatted;

public class AlterarUsuarioControl extends AlterarUsuarioView {

	public AlterarUsuarioControl(MenuView menu, Panel painel) {
		super(menu, painel);
		addbotaoVoltarListener(new voltarMenuListener());
		addSalvarAlteracoesListener(new SalvarAlteracoesListener());
	}

	class voltarMenuListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {

			GerenciarFuncionarioControl gerenciar = new GerenciarFuncionarioControl(menuView, painel);
			gerenciar.gerenciarView2(painel);

		}

	}

	class SalvarAlteracoesListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			// ATUALIZA!!!
			TextFieldFormatted campoTelefone2 = null;
			TextFieldFormatted campoHorarioInicio2 = null;
			TextFieldFormatted campoHorarioFim2 = null;
			try {
				campoTelefone2 = new TextFieldFormatted("(##)####-####", 12);
				campoHorarioInicio2 = new TextFieldFormatted("##:##:00", 12);
				campoHorarioFim2 = new TextFieldFormatted("##:##:00", 12);

			} catch (ParseException excecao) {
				excecao.printStackTrace();
			}

			String cpf = cadastrarUsuario.campoCPF.getText();
			String nome = cadastrarUsuario.campoNome.getText();
			String sobrenome = cadastrarUsuario.campoSobrenome.getText();
			String telefone = cadastrarUsuario.campoTelefone.getText();
			String horaEntrada = cadastrarUsuario.campoHorarioInicio.getText();
			String horaSaida = cadastrarUsuario.campoHorarioFim.getText();
			String usu = cadastrarUsuario.campoUsuario.getText();
			String senha = cadastrarUsuario.campoSenha.getText();

			if (!nome.equals("") && !sobrenome.equals("") && !telefone.equals(campoTelefone2.getText())
					&& !horaEntrada.equals(campoHorarioInicio2.getText())
					&& !horaSaida.equals(campoHorarioFim2.getText()) && !usu.equals("") && !senha.equals("")) {
				int horaMinima = Integer.parseInt(horaEntrada.substring(0, 2));
				int horaMaxima = Integer.parseInt(horaSaida.substring(0, 2));
				int minutoMinimo = Integer.parseInt(horaEntrada.substring(3, 5));
				int minutoMaximo = Integer.parseInt(horaSaida.substring(3, 5));
				if (horaMinima <= 24 && horaMinima >= 00 && horaMaxima <= 24 && horaMaxima >= 00 && minutoMinimo <= 60
						&& minutoMinimo >= 00 && minutoMaximo <= 60 && minutoMaximo >= 00) {
					Usuario usuario = new Usuario(cpf, nome, sobrenome, usu, senha, telefone, horaEntrada, horaSaida,
							'F', CadastrarUsuarioControl.getSelecaoAlterarTemperaturaComboBox());
					boolean conf = usuario.atualizar();

					if (conf)
						new MensagemView(menuView.getString(168), menuView.getString(169), menuView,
								MensagemView.INFORMACAO);
					else
						new MensagemView(menuView.getString(170), menuView.getString(171), menuView,
								MensagemView.INFORMACAO);

					new GerenciarFuncionarioControl(menuView, painel);
					if (menuView.getUsuario().getTipo() == 0) {
						new SindicoControl(cadastrarUsuario.getMenuView());
					}

					if (menuView.getUsuario().getTipo() == 1) {
						new AtendenteControl(cadastrarUsuario.getMenuView());
					}
				} else
					new MensagemView(menuView.getString(151), menuView.getString(153), menuView,
							MensagemView.INFORMACAO);
			} else {
				new MensagemView(menuView.getString(151), menuView.getString(154), menuView, MensagemView.INFORMACAO);
			}
		}

	}

}
