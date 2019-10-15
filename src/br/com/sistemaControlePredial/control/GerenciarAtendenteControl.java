package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Iterator;

import br.com.sistemaControlePredial.model.Usuario;
import br.com.sistemaControlePredial.view.GerenciarAtendenteView;
import br.com.sistemaControlePredial.view.MensagemView;
import br.com.sistemaControlePredial.view.MenuView;
import br.com.sistemaControlePredial.view.componentes.Panel;
import br.com.sistemaControlePredial.view.componentes.TextFieldFormatted;

public class GerenciarAtendenteControl extends GerenciarAtendenteView {

	public CadastrarAtendenteControl cadastrarAtendente;
	public ConsultarAtendenteControl consultarAtendente;
	public AlterarAtendenteControl alterarAtendente;
	private Panel p;

	public GerenciarAtendenteControl(MenuView menu, Panel painel) {
		super(menu, painel);
		p = painel;
		addBotaoCadastrarListener(new BotaoCadastrarListener());
		addBotaoBuscarListener(new BotaoBuscarListener());
		addConsultarListener(new BotaoConsultarListener());
		addAlterarListener(new AlterarListener());
		addExcluirListener(new ExcluirListener());
	}

	public void voltarMenuPrincipal(Panel painel) {
		if (menuView.getUsuario().getTipo() == 'S') {
			new SindicoControl(menuView);
		}
		if (menuView.getUsuario().getTipo() == 'A') {
			new AtendenteControl(menuView);
		}
	}

	class ExcluirListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			MensagemView confirmacao = new MensagemView(menuView.getString(64), menuView.getString(97), menuView,
					MensagemView.CONFIRMACAO);

			if (confirmacao.getRespostaBotao() == 2) {
				new MensagemView(menuView.getString(66), menuView.getString(98), menuView, MensagemView.INFORMACAO);

				// Exclui o funcionario
				Usuario u = new Usuario();
				u.excluir(gerenciar.campoCPF.getLetras());
				voltarMenuPrincipal(p);

			} else {
				confirmacao.dispose();
			}
		}

	}

	class AlterarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			alterarAtendente = new AlterarAtendenteControl(menuView, painelFundo);

			// CONSULTA!!!
			try {
				String cpf = getCPF();
				Usuario usuario = new Usuario();
				Usuario usuarioAux;

				// "seta" o cpf para que seja possível a busca
				usuario.setCPF(cpf);

				Iterator<Usuario> objetoUsuario = usuario.getObjetoUsuario().iterator();

				usuarioAux = objetoUsuario.next();
				alterarAtendente.cadastrarAtendente.cadastrarUsuario.campoNome.setText(usuarioAux.getNome());
				alterarAtendente.cadastrarAtendente.cadastrarUsuario.campoSobrenome.setText(usuarioAux.getSobrenome());
				alterarAtendente.cadastrarAtendente.cadastrarUsuario.campoTelefone.setText(usuarioAux.getTelefone());
				alterarAtendente.cadastrarAtendente.cadastrarUsuario.campoCPF.setText(usuarioAux.getCPF());
				alterarAtendente.cadastrarAtendente.cadastrarUsuario.campoUsuario.setText(usuarioAux.getUsuario());
				alterarAtendente.cadastrarAtendente.cadastrarUsuario.campoSenha.setText(usuarioAux.getSenha());
			} catch (NullPointerException e) {
				new MensagemView(menuView.getString(148), menuView.getString(149), menuView, MensagemView.INFORMACAO);
			}
		}

	}

	class BotaoCadastrarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			cadastrarAtendente = new CadastrarAtendenteControl(menuView, painelFundo);
		}
	}

	class BotaoConsultarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			consultarAtendente = new ConsultarAtendenteControl(menuView, painelFundo);

			try {
				String cpf = getCPF();
				Usuario usuario = new Usuario();
				Usuario usuarioAux;

				// "seta" o cpf para que seja possível a busca
				usuario.setCPF(cpf);

				Iterator<Usuario> objetoUsuario = usuario.getObjetoUsuario().iterator();

				usuarioAux = objetoUsuario.next();
				consultarAtendente.cadastrarAtendenteView.cadastrarUsuario.campoNome.setText(usuarioAux.getNome());
				consultarAtendente.cadastrarAtendenteView.cadastrarUsuario.campoSobrenome
						.setText(usuarioAux.getSobrenome());
				consultarAtendente.cadastrarAtendenteView.cadastrarUsuario.campoTelefone
						.setText(usuarioAux.getTelefone());
				consultarAtendente.cadastrarAtendenteView.cadastrarUsuario.campoCPF.setText(usuarioAux.getCPF());
				consultarAtendente.cadastrarAtendenteView.cadastrarUsuario.campoUsuario
						.setText(usuarioAux.getUsuario());
				consultarAtendente.cadastrarAtendenteView.cadastrarUsuario.campoSenha.setText(usuarioAux.getSenha());
			} catch (NullPointerException e) {
				new MensagemView(menuView.getString(148), menuView.getString(149), menuView, MensagemView.INFORMACAO);
			}
		}

	}

	class BotaoBuscarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			TextFieldFormatted campoTeste = null;
			try {
				campoTeste = new TextFieldFormatted("###.###.###-##", 18);
			} catch (ParseException excecao) {
				excecao.printStackTrace();
			}

			if (getCPF().equals(campoTeste.getText())) {
				new MensagemView(menuView.getString(148), menuView.getString(150), menuView, MensagemView.INFORMACAO);

			} else {
				Usuario usuario = new Usuario();
				usuario.setCPF(getCPF());
				String nome = usuario.getNomeCorrespondenteAoCPF('A');
				if (nome != null) {
					setRotuloNomeUsuario(nome);
					gerenciar.mudarEdicao(true);
					gerenciar.gerenciarView(painelFundo);
				} else {
					new MensagemView(menuView.getString(148), menuView.getString(149), menuView,
							MensagemView.INFORMACAO);
				}
			}
		}
	}
}
