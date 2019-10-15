package br.com.sistemaControlePredial.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.text.ParseException;

import br.com.sistemaControlePredial.view.componentes.Button;
import br.com.sistemaControlePredial.view.componentes.Label;
import br.com.sistemaControlePredial.view.componentes.Panel;
import br.com.sistemaControlePredial.view.componentes.TextFieldFormatted;

public class ExcluirEmpresaView {

	protected MenuView menuView;
	public Label rotuloCNPJ;
	public TextFieldFormatted campoCNPJ;
	public Button botaoPesquisar;
	public Panel painelExcluir;
	public Label rotuloEmpresa;
	public Label rotuloNomeEmpresa;
	public Button botaoExcluir;

	public ExcluirEmpresaView(MenuView menu, Panel painel) {
		menuView = menu;
		menuView.setTitle(menuView.getString(60));

		painel.removeAll();
		painel.setBorderPanel(menuView.getString(61));

		// sets painel do formulário
		painelExcluir = new Panel(menuView.getString(62));
		painelExcluir.setPreferredSize(new Dimension(400, 70));

		// sets label do formulário
		rotuloCNPJ = new Label(menuView.getString(53));
		rotuloEmpresa = new Label(menuView.getString(36), 18);
		rotuloNomeEmpresa = new Label("Portland INC");

		// sets campos do formulário
		try {
			campoCNPJ = new TextFieldFormatted("##.###.###/####-##", 18);
		} catch (ParseException excecao) {
			excecao.printStackTrace();
		}

		// sets botao do formulário
		botaoPesquisar = new Button(menuView.getString(54));
		botaoExcluir = new Button(menuView.getString(63));

		painelExcluir.add(rotuloEmpresa);
		painelExcluir.add(rotuloNomeEmpresa);
		painelExcluir.add(botaoExcluir);

		painel.add(rotuloCNPJ);
		painel.add(campoCNPJ);
		painel.add(botaoPesquisar);

		painel.repaint();
		painel.revalidate();

	}

	public void addPainelExcluir(Panel painel) {
		painel.add(painelExcluir);
		painel.repaint();
		painel.revalidate();
	}

	public void addBotaoExcluirListener(ActionListener l) {
		botaoExcluir.addActionListener(l);
	}

	public void addPesquisarListener(ActionListener l) {
		botaoPesquisar.addActionListener(l);
	}

}
