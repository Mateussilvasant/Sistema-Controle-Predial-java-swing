package br.com.sistemaControlePredial.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.text.ParseException;

import br.com.sistemaControlePredial.control.CadastrarUsuarioControl;
import br.com.sistemaControlePredial.view.componentes.Button;
import br.com.sistemaControlePredial.view.componentes.ComboBox;
import br.com.sistemaControlePredial.view.componentes.Label;
import br.com.sistemaControlePredial.view.componentes.Panel;
import br.com.sistemaControlePredial.view.componentes.TextField;
import br.com.sistemaControlePredial.view.componentes.TextFieldFormatted;

public class CadastrarUsuario {

	protected MenuView menuView;
	public Label rotuloNome, rotuloSobrenome, rotuloTelefone, rotuloCPF, rotuloUsuario, rotuloSenha,
			rotuloEscolherEmpresa, rotuloAcessoLivre, rotuloHorarioInicio, rotuloHorarioFim, rotuloTemperatura,
			rotuloAlterarTemperatura;
	public Panel painelPessoal, painelEmpresa, painelAcesso;
	public TextField campoSobrenome, campoSenha, campoUsuario, campoNome;
	public TextFieldFormatted campoTelefone, campoCPF, campoHorarioInicio, campoHorarioFim;
	public Button botaoCadastrar, VoltarMenu;
	public static ComboBox<String> escolherEmpresa;
	public static ComboBox<String> escolhaAlterarTemperatura;
	public Panel painel;

	public CadastrarUsuario(MenuView menu, Panel painel) {
		menuView = menu;
		this.painel = painel;
		menuView.setTitle(menuView.getString(75));
		painel.removeAll();
		painel.setBorderPanel(menuView.getString(30));

		painelPessoal = new Panel(menuView.getString(89));
		painelPessoal.setPreferredSize(new Dimension(menuView.getX() - 300, 65));
		painelEmpresa = new Panel(menuView.getString(90));
		painelEmpresa.setPreferredSize(new Dimension(menuView.getX() - 300, 65));
		painelAcesso = new Panel(menuView.getString(91));
		painelAcesso.setPreferredSize(new Dimension(menuView.getX() - 300, 110));

		botaoCadastrar = new Button(menuView.getString(92));
		VoltarMenu = new Button(menuView.getString(49));

		escolherEmpresa = new ComboBox<String>(CadastrarUsuarioControl.getEmpresa());
		escolhaAlterarTemperatura = new ComboBox<String>(getOpcoes());

		rotuloNome = new Label(menuView.getString(76));
		rotuloSobrenome = new Label(menuView.getString(77));
		rotuloTelefone = new Label(menuView.getString(78));
		rotuloCPF = new Label(menuView.getString(79));
		rotuloEscolherEmpresa = new Label(menuView.getString(80));
		rotuloUsuario = new Label(menuView.getString(81));
		rotuloSenha = new Label(menuView.getString(82));
		rotuloHorarioInicio = new Label(menuView.getString(86));
		rotuloHorarioFim = new Label(menuView.getString(87));
		rotuloAlterarTemperatura = new Label(menu.getString(88));

		campoNome = new TextField(12);
		campoSobrenome = new TextField(12);

		try {
			campoTelefone = new TextFieldFormatted("(##)####-####", 12);
			campoCPF = new TextFieldFormatted("###.###.###-##", 12);
			campoHorarioInicio = new TextFieldFormatted("##:##:00", 12);
			campoHorarioFim = new TextFieldFormatted("##:##:00", 12);

		} catch (ParseException excecao) {
			excecao.printStackTrace();
		}

		campoUsuario = new TextField(20);
		campoSenha = new TextField(20);

		painelPessoal.add(rotuloNome);
		painelPessoal.add(campoNome);
		painelPessoal.add(rotuloSobrenome);
		painelPessoal.add(campoSobrenome);
		painelPessoal.add(rotuloTelefone);
		painelPessoal.add(campoTelefone);
		painelPessoal.add(rotuloCPF);
		painelPessoal.add(campoCPF);

		painelEmpresa.add(rotuloEscolherEmpresa);
		painelEmpresa.add(escolherEmpresa);

		painelAcesso.add(rotuloUsuario);
		painelAcesso.add(campoUsuario);
		painelAcesso.add(rotuloSenha);
		painelAcesso.add(campoSenha);
		painelAcesso.add(rotuloHorarioInicio);
		painelAcesso.add(campoHorarioInicio);
		painelAcesso.add(rotuloHorarioFim);
		painelAcesso.add(campoHorarioFim);
		painelAcesso.add(rotuloAlterarTemperatura);
		painelAcesso.add(escolhaAlterarTemperatura);

		painel.add(painelPessoal);
		painel.add(painelEmpresa);
		painel.add(painelAcesso);
		painel.add(botaoCadastrar);
		painel.add(VoltarMenu);

		painel.repaint();
		painel.revalidate();

	}

	public MenuView getMenuView() {
		return menuView;
	}

	public String[] getOpcoes() {
		return new String[] { menuView.getString(84), menuView.getString(85) };
	}

	public void addBotaoVoltarListener(ActionListener l) {
		VoltarMenu.addActionListener(l);
	}

	public void addbotaoCadastrarListener(ActionListener l) {
		botaoCadastrar.addActionListener(l);
	}
}
