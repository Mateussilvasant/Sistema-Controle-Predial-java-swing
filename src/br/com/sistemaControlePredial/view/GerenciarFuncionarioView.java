package br.com.sistemaControlePredial.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.text.ParseException;

import br.com.sistemaControlePredial.view.componentes.Button;
import br.com.sistemaControlePredial.view.componentes.Label;
import br.com.sistemaControlePredial.view.componentes.Panel;
import br.com.sistemaControlePredial.view.componentes.TextFieldFormatted;

public class GerenciarFuncionarioView {

	protected MenuView menuView;
	public Panel painelBusca, painelCadastrar;
	public Panel painelPrincipal;
	public Label rotuloBusca, rotuloNomeUsuario, rotuloNome;
	public TextFieldFormatted campoCPF;
	public Button botaoBuscar, botaoCadastrar, botaoConsultar, botaoAlterar, botaoExcluir;

	public GerenciarFuncionarioView(MenuView menu, Panel painel) {
		menuView = menu;
		painelPrincipal = painel;

		painel.removeAll();
		painel.setBorderPanel(menuView.getString(68));
		menuView.setTitle(menuView.getString(69));

		// sets painel da formulário
		painelBusca = new Panel(menuView.getString(70));
		painelBusca.setPreferredSize(new Dimension(menuView.getX() - 300, 70));
		painelCadastrar = new Panel(menuView.getString(73));
		painelCadastrar.setPreferredSize(new Dimension(menuView.getX() - 310, 80));

		// sets label do formulário
		rotuloBusca = new Label(menuView.getString(71));
		rotuloNome = new Label(menuView.getString(76), 18);
		rotuloNomeUsuario = new Label("João Oliveira");

		// sets campos do formulário
		try {
			campoCPF = new TextFieldFormatted("###.###.###-##", 18);
		} catch (ParseException excecao) {
			excecao.printStackTrace();
		}

		// sets botoes do formulário

		botaoBuscar = new Button(menuView.getString(72));
		botaoCadastrar = new Button(menuView.getString(74));
		botaoAlterar = new Button(menuView.getString(13), 130, 50);
		botaoConsultar = new Button(menuView.getString(12), 130, 50);
		botaoExcluir = new Button(menuView.getString(14), 130, 50);

		// adicionar componentes ao menu
		painelBusca.add(rotuloBusca);
		painelBusca.add(campoCPF);
		painelBusca.add(botaoBuscar);
		painelBusca.add(botaoCadastrar);

		painel.add(painelBusca);
		painel.repaint();
		painel.revalidate();

	}

	public void gerenciarView(Panel painel) {

		if (!menuView.buscaComponente(painel, painelCadastrar)) {
			painel.add(painelCadastrar);
			painel.repaint();
			painel.revalidate();
		}

		painelCadastrar.setBorderPanel(menuView.getString(93));
		painelCadastrar.setPreferredSize(new Dimension(menuView.getX() - 310, 60));
		painelCadastrar.remove(botaoCadastrar);
		painelCadastrar.add(rotuloNome);
		painelCadastrar.add(rotuloNomeUsuario);
		painelCadastrar.add(botaoConsultar);
		painelCadastrar.add(botaoAlterar);
		painelCadastrar.add(botaoExcluir);

		painelCadastrar.repaint();
		painelCadastrar.revalidate();

	}

	// Mateus, confira este método, por favor.
	// Teste
	public void gerenciarView2(Panel painel) {

		/*
		 * if (!menuView.buscaComponente(painel, painelCadastrar)) {
		 * painel.add(painelCadastrar); painel.repaint(); painel.revalidate(); }
		 */

		painelCadastrar.setBorderPanel(menuView.getString(93));
		painelCadastrar.setPreferredSize(new Dimension(menuView.getX() - 310, 60));

		// adicionar componentes ao menu
		painelBusca.add(rotuloBusca);
		painelBusca.add(campoCPF);
		painelBusca.add(botaoBuscar);
		painelBusca.add(botaoCadastrar);

		painel.add(painelBusca);
		painel.repaint();
		painel.revalidate();

		painelCadastrar.repaint();
		painelCadastrar.revalidate();
	}

	public void mudarEdicao(boolean confirmacao) {
		if (confirmacao)
			campoCPF.setEditable(false);
	}

	// altera o campo "rotuloNomeUsuario"
	public void setRotuloNomeUsuario(String nome) {
		rotuloNomeUsuario.setText(nome);
	}

	// retorna o CPF digitado. CPF, não NOME!!!
	public String getDadosCampoCPFField() {
		return campoCPF.getText();
	}

	public void addBotaoConsultarListener(ActionListener l) {
		botaoConsultar.addActionListener(l);
	}

	public void addBotaoAlterarListener(ActionListener l) {
		botaoAlterar.addActionListener(l);
	}

	public void addExcluirListener(ActionListener l) {
		botaoExcluir.addActionListener(l);
	}

	public void addBotaoBuscarListener(ActionListener l) {
		botaoBuscar.addActionListener(l);
	}

	public void addBotaoCadastrarListener(ActionListener l) {
		botaoCadastrar.addActionListener(l);
	}
}
