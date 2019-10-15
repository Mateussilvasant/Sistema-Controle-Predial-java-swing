package br.com.sistemaControlePredial.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.text.ParseException;

import br.com.sistemaControlePredial.view.componentes.Button;
import br.com.sistemaControlePredial.view.componentes.Label;
import br.com.sistemaControlePredial.view.componentes.Panel;
import br.com.sistemaControlePredial.view.componentes.TextFieldFormatted;

public class AlterarEmpresaFuncionarioView {

	protected MenuView menuView;
	public Panel categoriaArCondicionado;
	public Label rotuloTemperaturaMax;
	public TextFieldFormatted campoTemperaturaMax;
	public Button botaoSalvarAlteracoes, botaoVoltarPrincipal;
	public Panel painel;

	public AlterarEmpresaFuncionarioView(MenuView menu, Panel painel) {
		this.painel = painel;
		menuView = menu;
		painel.removeAll();
		menuView.setTitle(menuView.getString(57));
		painel.setBorderPanel(menuView.getString(58));
		categoriaArCondicionado = new Panel(menuView.getString(34));
		categoriaArCondicionado.setPreferredSize(new Dimension(menuView.getX() - 300, 70));

		rotuloTemperaturaMax = new Label(menuView.getString(42));

		try {
			campoTemperaturaMax = new TextFieldFormatted("##°", 18);
		} catch (ParseException excecao) {
			excecao.printStackTrace();
		}

		botaoSalvarAlteracoes = new Button(menuView.getString(59));
		botaoVoltarPrincipal = new Button(menuView.getString(49));

		categoriaArCondicionado.add(rotuloTemperaturaMax);
		categoriaArCondicionado.add(campoTemperaturaMax);

		painel.add(categoriaArCondicionado);
		painel.add(botaoSalvarAlteracoes);
		painel.add(botaoVoltarPrincipal);

		painel.repaint();
		painel.revalidate();
	}

	public void addVoltarMenuListener(ActionListener l) {
		botaoVoltarPrincipal.addActionListener(l);
	}

	public void addSalvarAlteracoes(ActionListener l) {
		botaoSalvarAlteracoes.addActionListener(l);
	}
}
