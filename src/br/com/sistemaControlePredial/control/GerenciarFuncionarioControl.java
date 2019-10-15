package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Iterator;

import br.com.sistemaControlePredial.model.Usuario;
import br.com.sistemaControlePredial.view.GerenciarFuncionarioView;
import br.com.sistemaControlePredial.view.MensagemView;
import br.com.sistemaControlePredial.view.MenuView;
import br.com.sistemaControlePredial.view.componentes.Panel;
import br.com.sistemaControlePredial.view.componentes.TextFieldFormatted;

public class GerenciarFuncionarioControl extends GerenciarFuncionarioView {

	public CadastrarUsuarioControl CadastrarUsuarioControl;
	public ConsultarUsuarioControl ConsultarUsuarioControl;
	public AlterarUsuarioControl AlterarUsuarioControl;
	public Panel painel;

	public GerenciarFuncionarioControl(MenuView menu, Panel painel) {
		super(menu, painel);
		this.painel = painel;
		addBotaoBuscarListener(new BuscarListener());
		addBotaoCadastrarListener(new CadastrarListener());
		addBotaoAlterarListener(new AlterarListener());
		addBotaoConsultarListener(new ConsultarListener());
		addExcluirListener(new ExcluirListener());
	}

	public MenuView getMenuView() {
		return menuView;
	}

	class CadastrarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			CadastrarUsuarioControl = new CadastrarUsuarioControl(menuView, painelPrincipal);
			// A AÇÃO DESTE BOTÃO ESTÁ DEFINIDA NA CLASSE
			// "CadastrarUsuarioControl"
		}

	}

	class ConsultarListener implements ActionListener {
		// método que dá ação ao botão "Consultar" localizado em "gerenciar
		// funcionário -> buscar funcionaário"
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent evento) {
			ConsultarUsuarioControl = new ConsultarUsuarioControl(menuView, painelPrincipal);

			try {
				Usuario usuario = new Usuario();
				// "seta" o cpf para que seja possível a busca
				usuario.setCPF(getDadosCampoCPFField());

				/*
				 * chama o metodo "getNomeEmpresaCorrespondente" da classe
				 * CadastrarUsuario, a fim de mudar a string inicial do combobox
				 * "escolher empresa"
				 */
				CadastrarUsuarioControl.selecionarStringPreferidaComboBoxEmpresa(usuario.getCNPJCorrespondente());

				// cuida da parte do combobox alterar temperatura
				CadastrarUsuarioControl.setSelecaoComboBoxAlterarTemperatura(usuario.getPermissaoCorrespondente());

				Iterator<Usuario> objetoUsuario = usuario.getObjetoUsuario().iterator();

				Usuario usuarioAux = objetoUsuario.next();
				ConsultarUsuarioControl.modeloBase.campoNome.setText(usuarioAux.getNome());
				ConsultarUsuarioControl.modeloBase.campoSobrenome.setText(usuarioAux.getSobrenome());
				ConsultarUsuarioControl.modeloBase.campoTelefone.setText(usuarioAux.getTelefone());
				ConsultarUsuarioControl.modeloBase.campoCPF.setText(usuarioAux.getCPF());
				ConsultarUsuarioControl.modeloBase.campoHorarioInicio.setText(usuarioAux.getHoraEntrada());
				ConsultarUsuarioControl.modeloBase.campoHorarioFim.setText(usuarioAux.getHoraSaida());
				ConsultarUsuarioControl.modeloBase.campoUsuario.setText(usuarioAux.getUsuario());
				ConsultarUsuarioControl.modeloBase.campoSenha.setText(usuarioAux.getSenha());
			} catch (NullPointerException e) {
				new MensagemView(menuView.getString(148), menuView.getString(149), menuView, MensagemView.INFORMACAO);
			}
		}
	}

	class BuscarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {

			TextFieldFormatted campoTeste = null;
			try {
				campoTeste = new TextFieldFormatted("###.###.###-##", 18);
			} catch (ParseException excecao) {
				excecao.printStackTrace();
			}

			if (getDadosCampoCPFField().equals(campoTeste.getText())) {
				new MensagemView(menuView.getString(148), menuView.getString(150), menuView, MensagemView.INFORMACAO);

			} else {
				Usuario usuario = new Usuario();
				usuario.setCPF(getDadosCampoCPFField());
				String nome = usuario.getNomeCorrespondenteAoCPF('F');
				if (nome != null) {
					setRotuloNomeUsuario(nome);
					mudarEdicao(true);
					gerenciarView(painelPrincipal);
				} else {
					new MensagemView(menuView.getString(148), menuView.getString(149), menuView,
							MensagemView.INFORMACAO);
				}
			}
		}

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
				u.excluir(campoCPF.getLetras());

				voltarMenuPrincipal(painel);
			} else {
				confirmacao.dispose();
			}
		}

	}

	class AlterarListener implements ActionListener {

		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent evento) {
			AlterarUsuarioControl = new AlterarUsuarioControl(menuView, painelPrincipal);

			// CONSULTA!!!
			try {
				Usuario usuario = new Usuario();
				// "seta" o cpf para que seja possível a busca
				usuario.setCPF(getDadosCampoCPFField());

				/*
				 * chama o metodo "getNomeEmpresaCorrespondente" da classe
				 * CadastrarUsuario, a fim de mudar a string inicial do combobox
				 * "escolher empresa"
				 */
				CadastrarUsuarioControl.selecionarStringPreferidaComboBoxEmpresa(usuario.getCNPJCorrespondente());

				CadastrarUsuarioControl.setSelecaoComboBoxAlterarTemperatura(usuario.getPermissaoCorrespondente());

				CadastrarUsuarioControl.mudarHabilitacaoComboBoxEscolherEmpresa(false);

				Iterator<Usuario> objetoUsuario = usuario.getObjetoUsuario().iterator();

				Usuario usuarioAux = objetoUsuario.next();
				AlterarUsuarioControl.cadastrarUsuario.campoNome.setText(usuarioAux.getNome());
				AlterarUsuarioControl.cadastrarUsuario.campoSobrenome.setText(usuarioAux.getSobrenome());
				AlterarUsuarioControl.cadastrarUsuario.campoTelefone.setText(usuarioAux.getTelefone());
				AlterarUsuarioControl.cadastrarUsuario.campoCPF.setText(usuarioAux.getCPF());
				AlterarUsuarioControl.cadastrarUsuario.campoHorarioInicio.setText(usuarioAux.getHoraEntrada());
				AlterarUsuarioControl.cadastrarUsuario.campoHorarioFim.setText(usuarioAux.getHoraSaida());
				AlterarUsuarioControl.cadastrarUsuario.campoUsuario.setText(usuarioAux.getUsuario());
				AlterarUsuarioControl.cadastrarUsuario.campoSenha.setText(usuarioAux.getSenha());
			} catch (NullPointerException e) {
				new MensagemView(menuView.getString(148), menuView.getString(149), menuView, MensagemView.INFORMACAO);
			}
		}
	}
}