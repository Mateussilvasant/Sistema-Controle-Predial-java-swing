package br.com.sistemaControlePredial.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import br.com.sistemaControlePredial.view.componentes.Button;
import br.com.sistemaControlePredial.view.componentes.Label;
import br.com.sistemaControlePredial.view.componentes.Panel;

@SuppressWarnings("serial")
public class MensagemView extends JDialog {

	private Container container;
	private Panel painelFundo;
	private Panel painelCabecalho;
	private Panel painelRodape;
	private Button botaoOk;
	private Label rotuloMensagem;
	private Button botaoNao;
	private Button botaoSim;
	public static final int INFORMACAO = 0;
	public static final int CONFIRMACAO = 1;
	public int OPCOES = 0;
	private int respostaBotao;

	public MensagemView(String titulo, String mensagem, MenuView menu, int tipo) {

		super(menu, titulo, true);
		setContainer(getContentPane());
		setPainelFundo(new Panel(""));
		getPainelFundo().setLayout(new BorderLayout());
		getPainelFundo().setBorder(null);
		setPainelCabecalho(new Panel(""));
		setPainelRodape(new Panel(""));
		setRotuloMensagem(new Label(mensagem));

		respostaBotao = -1;

		if (tipo == INFORMACAO) {

			setBotaoOk(new Button(menu.getString(52)));
			getPainelRodape().add(getBotaoOk());
			addBotaoOkListener();

		} else if (tipo == CONFIRMACAO) {
			setBotaoSim(new Button(menu.getString(51)));
			setBotaoNao(new Button(menu.getString(50)));

			addBotaoNaoListener();
			addBotaoSimListener();

			getPainelRodape().add(getBotaoSim());
			getPainelRodape().add(getBotaoNao());

		}

		getPainelCabecalho().add(getRotuloMensagem());
		getContaine().add(getPainelFundo());
		getPainelFundo().add(getPainelCabecalho(), BorderLayout.NORTH);
		getPainelFundo().add(getPainelRodape(), BorderLayout.CENTER);

		InicializaView(menu);

	}

	public void InicializaView(JFrame frame) {
		setResizable(false);
		setUndecorated(false);
		setSize(frame.getWidth() / 3, frame.getHeight() / 6);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(getContaine());
		setVisible(true);
	}

	public void addBotaoOkListener() {
		getBotaoOk().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evento) {
				respostaBotao = 0;
				getJDialog().dispose();
			}

		});
	}

	public void addBotaoNaoListener() {
		getBotaoNao().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evento) {
				respostaBotao = 1;
				getJDialog().dispose();
			}

		});
	}

	public void addBotaoSimListener() {
		getBotaoSim().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evento) {
				respostaBotao = 2;
				getJDialog().dispose();
			}
		});
	}

	public int getRespostaBotao() {
		return respostaBotao;
	}

	public void setRotuloMensagem(Label rotuloMensagem) {
		this.rotuloMensagem = rotuloMensagem;
	}

	public Label getRotuloMensagem() {
		return rotuloMensagem;
	}

	public void setBotaoOk(Button botaoOk) {
		this.botaoOk = botaoOk;
	}

	public Button getBotaoOk() {
		return botaoOk;
	}

	public void setPainelRodape(Panel painelRodape) {
		this.painelRodape = painelRodape;
	}

	public Panel getPainelRodape() {
		return painelRodape;
	}

	public void setPainelCabecalho(Panel painelCabecalho) {
		this.painelCabecalho = painelCabecalho;
	}

	public Panel getPainelCabecalho() {
		return painelCabecalho;
	}

	public void setPainelFundo(Panel painelFundo) {
		this.painelFundo = painelFundo;
	}

	public Panel getPainelFundo() {
		return painelFundo;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public Container getContaine() {
		return container;
	}

	public JDialog getJDialog() {
		return this;
	}

	public Button getBotaoNao() {
		return botaoNao;
	}

	public void setBotaoNao(Button botaoNao) {
		this.botaoNao = botaoNao;
	}

	public Button getBotaoSim() {
		return botaoSim;
	}

	public void setBotaoSim(Button botaoSim) {
		this.botaoSim = botaoSim;
	}
}
