package br.com.sistemaControlePredial.view;

import java.awt.event.ActionListener;
import java.text.ParseException;

import br.com.sistemaControlePredial.control.CadastrarEmpresaControl;
import br.com.sistemaControlePredial.view.componentes.Button;
import br.com.sistemaControlePredial.view.componentes.Label;
import br.com.sistemaControlePredial.view.componentes.Panel;
import br.com.sistemaControlePredial.view.componentes.TextFieldFormatted;

public class AlterarEmpresaView {

	protected MenuView menuView;
	public CadastrarEmpresaControl cadastrarControl;
	public Label rotuloCNPJ;
	public TextFieldFormatted campoCNPJ;
	public Button botaoPesquisar;

	public AlterarEmpresaView(MenuView menu, Panel painel) {

		menuView = menu;
		// set titulo da janela
		menuView.setTitle(menuView.getString(57));

		// remove os componentes do menu anterior
		painel.removeAll();

		// set titulo do painel
		painel.setBorderPanel(menuView.getString(58));

		// sets label do formulario
		rotuloCNPJ = new Label(menuView.getString(53));

		// sets campos do formulario
		try {
			campoCNPJ = new TextFieldFormatted("##.###.###/####-##", 18);
		} catch (ParseException excecao) {
			excecao.printStackTrace();
		}

		// sets botao do formulario
		botaoPesquisar = new Button(menuView.getString(54));

		painel.add(rotuloCNPJ);
		painel.add(campoCNPJ);
		painel.add(botaoPesquisar);

		painel.repaint();
		painel.revalidate();

	}

	public void addBotaoPesquisarListener(ActionListener l) {
		botaoPesquisar.addActionListener(l);
	}

	public void alterarView(Panel painel) {
		cadastrarControl = new CadastrarEmpresaControl(menuView, painel, false);
		cadastrarControl.botaoCadastrarEmpresa.setText(menuView.getString(59));
		painel.repaint();
		painel.revalidate();

	}

	public void addSalvarAlteracoesListener(ActionListener l) {
		cadastrarControl.botaoCadastrarEmpresa.addActionListener(l);
	}

}