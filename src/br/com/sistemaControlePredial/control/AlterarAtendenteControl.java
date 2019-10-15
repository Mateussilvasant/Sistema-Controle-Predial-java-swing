package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import br.com.sistemaControlePredial.model.Usuario;
import br.com.sistemaControlePredial.view.AlterarAtendenteView;
import br.com.sistemaControlePredial.view.MensagemView;
import br.com.sistemaControlePredial.view.MenuView;
import br.com.sistemaControlePredial.view.componentes.Panel;
import br.com.sistemaControlePredial.view.componentes.TextFieldFormatted;

public class AlterarAtendenteControl extends AlterarAtendenteView {

	public AlterarAtendenteControl(MenuView menu, Panel painel) {
		super(menu, painel);
		addVoltarListener(new VoltarListener());
		addSalvarAlteracoesListener(new SalvarAlteracoesListener());
	}

	class VoltarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			GerenciarAtendenteControl gerenciar = new GerenciarAtendenteControl(menuView, painel);
			gerenciar.gerenciar.gerenciarView2(painel);
		}

	}

	class SalvarAlteracoesListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			TextFieldFormatted campoTelefone2 = null;
			try {
				campoTelefone2 = new TextFieldFormatted("(##)####-####", 12);
			} catch (ParseException excecao) {
				excecao.printStackTrace();
			}

			String cpf = cadastrarAtendente.cadastrarUsuario.campoCPF.getText();
			String nome = cadastrarAtendente.cadastrarUsuario.campoNome.getText();
			String sobrenome = cadastrarAtendente.cadastrarUsuario.campoSobrenome.getText();
			String telefone = cadastrarAtendente.cadastrarUsuario.campoTelefone.getText();
			String usu = cadastrarAtendente.cadastrarUsuario.campoUsuario.getText();
			String senha = cadastrarAtendente.cadastrarUsuario.campoSenha.getText();

			if (!nome.equals("") && !sobrenome.equals("") && !telefone.equals(campoTelefone2.getText())
					&& !usu.equals("") && !senha.equals("")) {
				Usuario usuario = new Usuario(cpf, nome, sobrenome, usu, senha, telefone, "00:00:00", "00:00:00", 'A',
						true);
				boolean conf = usuario.atualizar();

				if (conf)
					new MensagemView(menuView.getString(168), menuView.getString(169), menuView,
							MensagemView.INFORMACAO);
				else
					new MensagemView(menuView.getString(170), menuView.getString(171), menuView,
							MensagemView.INFORMACAO);

				new GerenciarAtendenteControl(menuView, cadastrarAtendente.cadastrarUsuario.painel);
				if (menuView.getUsuario().getTipo() == 0) {
					new SindicoControl(cadastrarAtendente.cadastrarUsuario.getMenuView());
				}

				if (menuView.getUsuario().getTipo() == 1) {
					new AtendenteControl(cadastrarAtendente.cadastrarUsuario.getMenuView());
				}
			} else {
				new MensagemView(menuView.getString(151), menuView.getString(154), menuView, MensagemView.INFORMACAO);
			}
		}

	}

}
