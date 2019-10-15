package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import br.com.sistemaControlePredial.model.Usuario;
import br.com.sistemaControlePredial.view.CadastrarAtendenteView;
import br.com.sistemaControlePredial.view.MensagemView;
import br.com.sistemaControlePredial.view.MenuView;
import br.com.sistemaControlePredial.view.componentes.Panel;
import br.com.sistemaControlePredial.view.componentes.TextFieldFormatted;

public class CadastrarAtendenteControl extends CadastrarAtendenteView {

	public CadastrarAtendenteControl(MenuView menu, Panel painel) {
		super(menu, painel);
		addVoltarListener(new VoltarListener());
		addConcluirCadastroListener(new CadastrarListener());
	}

	class VoltarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			GerenciarAtendenteControl gerenciar = new GerenciarAtendenteControl(menuView, painel);
			gerenciar.gerenciar.gerenciarView2(painel);
		}
	}

	class CadastrarListener implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			TextFieldFormatted campoTelefone2 = null;
			TextFieldFormatted campoCPF2 = null;
			try {
				campoTelefone2 = new TextFieldFormatted("(##)####-####", 12);
				campoCPF2 = new TextFieldFormatted("###.###.###-##", 12);
			} catch (ParseException excecao) {
				excecao.printStackTrace();
			}

			String cpf = cadastrarUsuario.campoCPF.getText();
			String nome = cadastrarUsuario.campoNome.getText();
			String sobrenome = cadastrarUsuario.campoSobrenome.getText();
			String telefone = cadastrarUsuario.campoTelefone.getText();
			String usu = cadastrarUsuario.campoUsuario.getText();
			String senha = cadastrarUsuario.campoSenha.getText();

			if (!cpf.equals(campoCPF2.getText()) && !nome.equals("") && !sobrenome.equals("")
					&& !telefone.equals(campoTelefone2.getText()) && !usu.equals("") && !senha.equals("")) {
				Usuario usuario = new Usuario(cpf, nome, sobrenome, usu, senha, telefone, "00:00:00", "00:00:00", 'A',
						true);
				boolean confirmacao = usuario.cadastrar("85.664.579/6954-25");

				if (confirmacao == false)
					new MensagemView(menuView.getString(151), menuView.getString(152), menuView,
							MensagemView.INFORMACAO);
				else {
					new MensagemView(menuView.getString(166), menuView.getString(167), menuView,
							MensagemView.INFORMACAO);
					new GerenciarAtendenteControl(menuView, cadastrarUsuario.painel);
					if (menuView.getUsuario().getTipo() == 0) {
						new SindicoControl(cadastrarUsuario.getMenuView());
					}

					if (menuView.getUsuario().getTipo() == 1) {
						new AtendenteControl(cadastrarUsuario.getMenuView());
					}
				}
			} else {
				new MensagemView(menuView.getString(151), menuView.getString(154), menuView, MensagemView.INFORMACAO);
			}
		}
	}
}
