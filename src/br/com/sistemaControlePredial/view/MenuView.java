package br.com.sistemaControlePredial.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.com.sistemaControlePredial.control.LoginControl;
import br.com.sistemaControlePredial.model.ConnectBD;
import br.com.sistemaControlePredial.model.Temperatura;
import br.com.sistemaControlePredial.model.Usuario;
import br.com.sistemaControlePredial.view.componentes.Button;
import br.com.sistemaControlePredial.view.componentes.ComboBox;
import br.com.sistemaControlePredial.view.componentes.Label;
import br.com.sistemaControlePredial.view.componentes.MenuBar;
import br.com.sistemaControlePredial.view.componentes.Panel;

@SuppressWarnings("serial")
public class MenuView extends JFrame {

	public Panel painelFundo;
	public MenuBar menuBarra;
	public Button botaoSair;
	public Button botaoSairPredio;

	private ArrayList<String> textosApp;
	private ComboBox<String> escolhaIdioma;

	private Label rotuloIdioma;
	private Container container;
	private ResourceBundle resourceBundle;

	// Objeto usuario logado
	private Usuario usuario;

	// Eixos x = largura e y = Altura
	private int x;
	private int y;

	// constantes
	public boolean ACESSAR_PREDIO = false;

	public MenuView(ResourceBundle resource, int idiomaEscolhido) {
		setResourceBundle(resource);
		setText(new ArrayList<String>());
		addText();

		botaoSair = new Button(getString(100));
		botaoSairPredio = new Button(getString(168));

		setContainer(getContentPane());
		setPainelFundo(new Panel(""));
		setMenuBarra(new MenuBar());
		getPainelFundo().setLayout(new BorderLayout());
		getContainerJanela().add(getPainelFundo());
		setX(getScreenWidth(170));
		setY(getScreenHeight(100));
		setEscolhaIdioma(new ComboBox<String>(getRotulosIdiomas()));
		getEscolhaIdioma().setSelectedIndex(idiomaEscolhido);
		setRotuloIdioma(new Label(getString(24)));
		getRotuloIdioma().setCorAlternativa();
		getMenuBarra().addComponente(getRotuloIdioma());
		getMenuBarra().addComponente(getEscolhaIdioma(), 100, 28);
		getEscolhaIdioma().addItemListener(new idiomaListener());
		botaoSairPredio.addActionListener(new BotaoSairListener());

		new LoginControl(this);

		inicializaView();
		inicializarSistemaTemperatura();
	}

	public void inicializaView() {
		setJMenuBar(getMenuBarra());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(getX(), getY());
		setLocationRelativeTo(getContainerJanela());
		setResizable(false);
		setVisible(true);
	}

	public void inicializarSistemaTemperatura() {
		Temperatura temperatura = new Temperatura();
		try {

			ConnectBD.obtemConexao(); // testando conexao
			temperatura.enviarTemperatura();

		} catch (Exception excecao) {
			this.dispose();
			MensagemView mensagem = new MensagemView(getString(181), excecao.getLocalizedMessage(), this,
					MensagemView.INFORMACAO);
			if (mensagem.getRespostaBotao() == 0) {
				System.exit(0);
			}
		}

	}

	public void addText() {

		getText().add("LoginView.border.painelLogin"); // 0
		getText().add("LoginView.label.rotuloUsuario"); // 1
		getText().add("LoginView.label.rotuloSenha"); // 2
		getText().add("LoginView.label.rotuloTipoUsuario"); // 3
		getText().add("LoginView.combobox.escolhaUsuario.sindico"); // 4
		getText().add("LoginView.combobox.escolhaUsuario.atendente"); // 5
		getText().add("LoginView.combobox.escolhaUsuario.funcionario"); // 6
		getText().add("LoginView.button.botaoLogar"); // 7
		getText().add("LoginView.button.botaoAcessarPredio"); // 8
		getText().add("AtendenteView.border.menuCabecalho.inicio"); // 9
		getText().add("AtendenteView.border.menuLateral"); // 10
		getText().add("AtendenteView.button.cadastrarEmpresa"); // 11
		getText().add("AtendenteView.button.consultarEmpresa"); // 12
		getText().add("AtendenteView.button.alterarEmpresa"); // 13
		getText().add("AtendenteView.button.excluirEmpresa"); // 14
		getText().add("AtendenteView.button.gerenciarFuncionario"); // 15
		getText().add("AtendenteView.button.enviarTXTAcesso"); // 16
		getText().add("AtendenteView.border.categoriaEmpresa"); // 17
		getText().add("AtendenteView.border.categoriaFuncionario"); // 18
		getText().add("AtendenteView.border.categoriaAcesso"); // 19
		getText().add("AtendenteView.label.rotuloBemVindo"); // 20
		getText().add("AtendenteView.label.rotuloNivelAcesso"); // 21
		getText().add("AtendenteView.label.rotuloTipoAcesso"); // 22
		getText().add("AtendenteView.label.rotuloPredio"); // 23
		getText().add("MenuView.menubar.menuIdioma"); // 24
		getText().add("MenuView.menubar.combobox.escolhaIdioma.portugues"); // 25
		getText().add("MenuView.menubar.combobox.escolhaIdioma.ingles"); // 26
		getText().add("MenuView.menubar.combobox.escolhaIdioma.espanhol"); // 27
		getText().add("Window.menuLogar.titulo");// 28
		getText().add("Window.menuPrincipal.titulo"); // 29
		getText().add("CadastrarEmpresaView.border.titulo");// 30
		getText().add("CadastrarEmpresaView.border.categoriaRazaoSocial"); // 31
		getText().add("CadastrarEmpresaView.border.categoriaHorarios"); // 32
		getText().add("Window.menuCadastrar.titulo"); // 33
		getText().add("CadastrarEmpresaView.border.categoriaArCondicionado"); // 34
		getText().add("CadastrarEmpresaView.border.categoriaConjunto"); // 35
		getText().add("CadastrarEmpresaView.label.rotuloNomeEmpresa"); // 36
		getText().add("CadastrarEmpresaView.label.rotuloCNPJEmpresa"); // 37
		getText().add("CadastrarEmpresaView.label.rotuloHorarioInicio"); // 38
		getText().add("CadastrarEmpresaView.label.rotuloHorariofim"); // 39
		getText().add("CadastrarEmpresaView.label.rotuloArcondicionadoInicio"); // 40
		getText().add("CadastrarEmpresaView.label.rotuloArcondicionadoFim"); // 41
		getText().add("CadastrarEmpresaView.label.temperaturaMaxima"); // 42
		getText().add("CadastrarEmpresaView.label.temperaturaMinima"); // 43
		getText().add("CadastrarEmpresaView.button.botaoAdicionar"); // 44
		getText().add("CadastrarEmpresaView.button.botaoRemoverConjunto"); // 45
		getText().add("CadastrarEmpresaView.list.border.listaConjuntos"); // 46
		getText().add("CadastrarEmpresaView.rotulo.selecionarConjuntos"); // 47
		getText().add("CadastrarEmpresaView.button.botaoCadastrarEmpresa"); // 48
		getText().add("CadastrarEmpresaView.button.botaoVoltarMenu"); // 49
		getText().add("MensagemView.button.botaoNao"); // 50
		getText().add("MensagemView.button.botaoSim"); // 51
		getText().add("MensagemView.button.ok"); // 52
		getText().add("ConsultarEmpresaView.rotulo.cnpj"); // 53
		getText().add("ConsultarEmpresaView.botao.pesquisar"); // 54
		getText().add("ConsultarEmpresaView.border.painel"); // 55
		getText().add("Window.menuConsultarEmpresa.titulo"); // 56
		getText().add("Window.menuAlterarEmpresa.titulo"); // 57
		getText().add("AlterarEmpresaView.border.tituloPanel"); // 58
		getText().add("AlterarEmpresaView.button.alterarEmpresa"); // 59
		getText().add("Window.menuExcluirEmpresa.titulo"); // 60
		getText().add("ExcluirEmpresaView.border.tituloPainel"); // 61
		getText().add("ExcluirEmpresaView.border.painelEmpresa"); // 62
		getText().add("ExcluirEmpresaView.button.botaoExcluir"); // 63
		getText().add("ExcluirEmpresaView.mensagemView.confirmacao.titulo"); // 64
		getText().add("ExcluirEmpresaView.mensagemView.confirmacao.mensagem"); // 65
		getText().add("ExcluirEmpresaView.mensagemView.informacao.titulo"); // 66
		getText().add("ExcluirEmpresaView.mensagemView.informacao.mensagem"); // 67
		getText().add("GerenciarFuncionario.border.tituloPainel"); // 68
		getText().add("Window.menuGerenciarFuncionario.titulo"); // 69
		getText().add("GerenciarFuncionario.border.painelBusca"); // 70
		getText().add("GerenciarFuncionario.label.rotuloBusca"); // 71
		getText().add("GerenciarFuncionario.button.botaoBusca"); // 72
		getText().add("GerenciarFuncionario.border.painelCadastrar"); // 73
		getText().add("GerenciarFuncionario.button.botaoCadastrar"); // 74
		getText().add("Window.menuGerenciar.cadastrarUsuario.titulo"); // 75
		getText().add("CadastrarUsuario.label.rotuloNome"); // 76
		getText().add("CadastrarUsuario.label.rotuloSobrenome"); // 77
		getText().add("CadastrarUsuario.label.rotuloTelefone"); // 78
		getText().add("CadastrarUsuario.label.rotuloCPF"); // 79
		getText().add("CadastrarUsuario.label.rotuloEscolherEmpresa"); // 80
		getText().add("CadastrarUsuario.label.rotuloUsuario"); // 81
		getText().add("CadastrarUsuario.label.rotuloSenha"); // 82
		getText().add("CadastrarUsuario.label.rotuloAcessoLivre"); // 83
		getText().add("CadastrarUsuario.label.rotuloSim"); // 84
		getText().add("CadastrarUsuario.label.rotuloNao"); // 85
		getText().add("CadastrarUsuario.label.rotuloHorarioInicio"); // 86
		getText().add("CadastrarUsuario.label.rotuloHorarioFim"); // 87
		getText().add("CadastrarUsuario.label.rotuloalterarTemperatura"); // 88
		getText().add("CadastrarUsuario.border.painelPessoal"); // 89
		getText().add("CadastrarUsuario.border.painelEmpresa"); // 90
		getText().add("CadastrarUsuario.border.painelAcesso"); // 91
		getText().add("CadastrarUsuario.button.cadastrarUsuario"); // 92
		getText().add("GerenciarFuncionario.border.painelgerenciar");// 93
		getText().add("ConsultarUsuario.border.painelTitulo"); // 94
		getText().add("Window.menuGerenciar.consultarUsuario.titulo"); // 95
		getText().add("Window.menuGerenciar.AlterarUsurio.titulo"); // 96
		getText().add("ExcluirUsuario.mensagemView.confirmacao.mensagem");// 97
		getText().add("ExcluirUsuario.mensagemView.informacao.mensagem"); // 98
		getText().add("FuncionarioView.label.rotuloNivelAcesso"); // 99
		getText().add("Window.menuBar.botao.sair"); // 100
		getText().add("MensagemView.confirmacao.titulo"); // 101
		getText().add("MensagemView.confirmacao.mensagem.sair"); // 102
		getText().add("AtendenteView.button.deslogar"); // 103
		getText().add("SindicoView.button.gerenciarFuncionario"); // 104
		getText().add("SindicoView.button.gerenciarAtendente"); // 105
		getText().add("SindicoView.panel.acessoBorder"); // 106
		getText().add("SindicoView.button.consultarAcessos"); // 107
		getText().add("SindicoView.button.enviarAcessos"); // 108
		getText().add("SindicoView.panel.temperaturaBorder"); // 109
		getText().add("SindicoView.button.enviarTemperatura"); // 110
		getText().add("GerenciarAtendente.border.painelBusca"); // 111
		getText().add("GerenciarAtendente.button.botaoBusca"); // 112
		getText().add("GerenciarAtendente.border.painelCadastrar"); // 113
		getText().add("GerenciarAtendente.label.rotuloBusca"); // 114
		getText().add("Window.menuGerenciarAtendente.titulo"); // 115
		getText().add("ConsultarAcessos.border.tituloPesquisa"); // 116
		getText().add("ConsultarAcessos.label.pesquisa"); // 117
		getText().add("ConsultarAcessos.border.tituloPeriodo"); // 118
		getText().add("ConsultarAcessos.label.rotuloDe"); // 119
		getText().add("ConsultarAcessos.label.rotuloAte"); // 120
		getText().add("ConsultarAcessos.button.pesquisar"); // 121
		getText().add("ConsultarAcessos.table.coluna1"); // 122
		getText().add("ConsultarAcessos.table.coluna2"); // 123
		getText().add("ConsultarAcessos.table.coluna3"); // 124
		getText().add("ConsultarAcessos.table.coluna4"); // 125
		getText().add("ConsultarAcessos.table.coluna5"); // 126
		getText().add("ConsultarAcesso.combobox.opcaoTodos"); // 127
		getText().add("ConsultarAcesso.border.filtroUsuario"); // 128
		getText().add("ConsultarAcesso.textfield.cpf"); // 129
		getText().add("Window.menuConsultarAcessos.titulo"); // 130
		getText().add("EnviarTemperatura.mensagemView.titulo"); // 131
		getText().add("EnviarTemperatura.mensagemView.mensagem"); // 132
		getText().add("EnviarAcessos.mensagemView.titulo"); // 133
		getText().add("EnviarAcessos.mensagemView.mensagem"); // 134
		getText().add("TrocaIdioma.mensagemView.confirmacao.titulo"); // 135
		getText().add("TrocaIdioma.mensagemView.confirmacao.mensagem"); // 136
		getText().add("TrocaIdioma.mensagemView.informacao.titulo"); // 137
		getText().add("TrocaIdioma.mensagemView.informacao.mensagem"); // 138
		getText().add("CadastrarEmpresaView.mensagem.informacao.titulo1"); // 139
		getText().add("CadastrarEmpresaView.mensagem.informacao.mensagem1"); // 140
		getText().add("CadastrarEmpresaView.mensagem.informacao.titulo2"); // 141
		getText().add("CadastrarEmpresaView.mensagem.informacao.mensagem2"); // 142
		getText().add("CadastrarEmpresaView.mensagem.informacao.mensagem3"); // 143
		getText().add("ExcluirEmpresaView.mensagemView.informacao.titulo1"); // 144
		getText().add("ExcluirEmpresaView.mensagemView.informacao.mensagem1"); // 145
		getText().add("LoginView.titulo.mensagem"); // 146
		getText().add("LoginView.Mensagem"); // 147
		getText().add("GerenciarFuncionario.titulo.CPFErro"); // 148
		getText().add("GerenciarFuncionario.mensagem.CPFErro"); // 149
		getText().add("GerenciarFuncionario.mensagem2.CPFErro"); // 150
		getText().add("CadastrarUsuario.titulo.alerta"); // 151
		getText().add("CadastrarUsuario.mensagem1"); // 152
		getText().add("CadastrarUsuario.mensagem2"); // 153
		getText().add("CadastrarUsuario.mensagem3"); // 154
		getText().add("outros.andar"); // 155
		getText().add("outros.numero"); // 156
		getText().add("AlterarEmpresaView.mensagem.titulo1"); // 157
		getText().add("AlterarEmpresaView.mensagem.titulo2"); // 158
		getText().add("AlterarEmpresaView.mensagem.titulo3"); // 159
		getText().add("AlterarEmpresaView.mensagem1"); // 160
		getText().add("AlterarEmpresaView.mensagem2"); // 161
		getText().add("AlterarEmpresaView.mensagem3"); // 162
		getText().add("ExcluirEmpresaView.mensagemView.informacao.mensagem2"); // 163
		getText().add("CadastrarEmpresaView.mensagem.informacao.mensagem4"); // 164
		getText().add("CadastrarEmpresaView.mensagem.informacao.mensagem5"); // 165
		getText().add("CadastrarUsuario.titulo.cadastroComSucesso"); // 166
		getText().add("CadastrarUsuario.cadastroComSucesso"); // 167
		getText().add("LoginView.button.botaoSairPredio"); // 168
		getText().add("AlterarUsuario.mensagem.alteracaoComSucesso"); // 169
		getText().add("AlterarUsuario.titulo.erro.alteracao"); // 170
		getText().add("AlterarUsuario.mensagem.erro.alteracao"); // 171
		getText().add("CadastrarEmpresaView.mensagem.informacao.mensagem6"); // 172
		getText().add("AlterarEmpresaView.mensagem4"); // 173
		getText().add("catraca.titulo"); // 174
		getText().add("catraca.mensagem1"); // 175
		getText().add("catraca.mensagem2"); // 176
		getText().add("outros.comboBox.selecionar"); // 177
		getText().add("consutarAcesso.mensagem1"); // 178
		getText().add("consutarAcesso.mensagem2"); // 179
		getText().add("consutarAcesso.mensagem3"); // 180
		getText().add("consutarAcesso.titulo1"); // 181
		getText().add("consutarAcesso.titulo2"); // 182
		getText().add("ar.foraDoIntervalo.mensagem.titulo"); // 183
		getText().add("ar.foraDoIntervalo.mensagem"); // 184
		getText().add("ar.tituloPainel");// 185
		getText().add("consultarAcessoTituloPainel");// 186
		getText().add("ar.arCondicionadoStatus.titulo");// 187
		getText().add("ar.arCondicionadoLigado.mensagem");// 188
		getText().add("ar.arCondicionadoDesligado.mensagem");// 189
		getText().add("ar.erroAoAlterarStatusArCondicionado.titulo");// 190
		getText().add("ar.erroAoLigarArCondicionado.mensagem");// 191
		getText().add("ar.erroAoDesligarArCondicionado.mensagem");// 192
		getText().add("Window.SistemaArCondicionado.titulo");// 193
		getText().add("ar.rotuloTempAtual"); // 194
		getText().add("ar.statusArCondicionado"); // 195
	}

	class BotaoSairListener implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			usuario = new Usuario();
			Date horaSaida = null;
			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				horaSaida = new Date(System.currentTimeMillis());

				usuario.registrarSaida(LoginControl.usuarioAcesso.getHoraEntrada(), dateFormat.format(horaSaida),
						LoginControl.usuarioAcesso.getCPF());

				new MensagemView(getString(174), getString(176), getMenuView(), MensagemView.INFORMACAO);
			} catch (Exception e) {
				e.printStackTrace();
			}

			saiuPredio();
			removeBotaoSairPredio();
		}

	}

	class idiomaListener implements ItemListener {

		public void itemStateChanged(ItemEvent evento) {
			if (evento.getStateChange() == ItemEvent.SELECTED) {
				if (getEscolhaIdioma().getSelectedIndex() == 0) {

					reconfigurarIdioma(PropertyResourceBundle.getBundle(
							"br.com.sistemaControlePredial.sistemaControlePredial_pt_BR", new Locale("pt", "BR")), 0);

				}
				if (getEscolhaIdioma().getSelectedIndex() == 1) {

					reconfigurarIdioma(PropertyResourceBundle.getBundle(
							"br.com.sistemaControlePredial.sistemaControlePredial_en_US", new Locale("en", "US")), 1);

				}

				if (getEscolhaIdioma().getSelectedIndex() == 2) {
					reconfigurarIdioma(PropertyResourceBundle.getBundle(
							"br.com.sistemaControlePredial.sistemaControlePredial_es_ES", new Locale("es", "ES")), 2);

				}
			}
		}
	}

	public void reconfigurarIdioma(ResourceBundle resource, int indice) {
		MensagemView confirmacao = new MensagemView(getString(135), getString(136), this, MensagemView.CONFIRMACAO);

		if (confirmacao.getRespostaBotao() == 2) {
			MensagemView info = new MensagemView(getString(137), getString(138), this, MensagemView.INFORMACAO);
			if (info.getRespostaBotao() == 0) {
				dispose();
				try {
					new MenuView(resource, indice);
				} catch (Exception excecao) {
					excecao.printStackTrace();
				}
			}
		} else if (confirmacao.getRespostaBotao() == 1) {
			confirmacao.dispose();
		}
	}

	public boolean buscaComponente(Panel painel, Component componente) {

		boolean saida = false;

		for (int d = 0; d < painel.getComponentCount(); d++) {
			if (painel.getComponent(d) == componente) {
				saida = true;
				break;
			}
		}

		return saida;
	}

	public int buscaIndiceComponente(Panel painel, Component componente) {

		int saida = 0;

		for (int d = 0; d < painel.getComponentCount(); d++) {
			if (painel.getComponent(d) == componente) {
				saida = d;
				break;
			}
		}

		return saida;
	}

	public void addPainel(JPanel painel, JPanel novoPainel, String posicao) {
		painel.add(novoPainel, posicao);
		painel.revalidate();
		painel.repaint();
	}

	public int getScreenHeight(int fator) {
		return (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - fator;
	}

	public int getScreenWidth(int fator) {
		return (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - fator;
	}

	public String getString(int indice) {
		return getResourceBundle().getString(getText().get(indice));
	}

	public String[] getRotulosIdiomas() {
		return new String[] { getString(25), getString(26), getString(27) };
	}

	public void addBotaoSairListener(ActionListener l) {
		botaoSair.addActionListener(l);
	}

	public void removeBotaoSairListener(ActionListener l) {
		botaoSair.removeActionListener(l);
	}

	public void setText(ArrayList<String> text) {
		this.textosApp = text;
	}

	public ArrayList<String> getText() {
		return textosApp;
	}

	public void setRotuloIdioma(Label rotuloIdioma) {
		this.rotuloIdioma = rotuloIdioma;
	}

	public Label getRotuloIdioma() {
		return rotuloIdioma;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public Container getContainerJanela() {
		return container;
	}

	public void setPainelFundo(Panel painelFundo) {
		this.painelFundo = painelFundo;
	}

	public JPanel getPainelFundo() {
		return painelFundo;
	}

	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public void setMenuBarra(MenuBar menuBarra) {
		this.menuBarra = menuBarra;
	}

	public MenuBar getMenuBarra() {
		return menuBarra;
	}

	public ComboBox<String> getEscolhaIdioma() {
		return escolhaIdioma;
	}

	public void setEscolhaIdioma(ComboBox<String> escolhaIdioma) {
		this.escolhaIdioma = escolhaIdioma;
	}

	public MenuView getMenuView() {
		return this;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void entrouPredio() {
		ACESSAR_PREDIO = true;
	}

	public void saiuPredio() {
		ACESSAR_PREDIO = false;
	}

	public boolean getStatusAcesso() {
		return ACESSAR_PREDIO;
	}

	public void addBotaoSairPredio() {
		menuBarra.addButton(botaoSairPredio);
		painelFundo.repaint();
		painelFundo.revalidate();
	}

	public void removeBotaoSairPredio() {
		LoginControl.botaoAcessarPredio.setEnabled(true);
		menuBarra.removeButton(botaoSairPredio);
		painelFundo.repaint();
		painelFundo.revalidate();
	}
}