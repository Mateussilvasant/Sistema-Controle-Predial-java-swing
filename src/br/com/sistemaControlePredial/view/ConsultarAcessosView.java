package br.com.sistemaControlePredial.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.ParseException;

import javax.swing.JScrollPane;

import br.com.sistemaControlePredial.view.componentes.Button;
import br.com.sistemaControlePredial.view.componentes.ComboBox;
import br.com.sistemaControlePredial.view.componentes.Label;
import br.com.sistemaControlePredial.view.componentes.Panel;
import br.com.sistemaControlePredial.view.componentes.Table;
import br.com.sistemaControlePredial.view.componentes.TextFieldFormatted;

public class ConsultarAcessosView {

	protected MenuView menuView;
	public Panel painel;
	public Panel painelPesquisa;
	public Panel filtroUsuario;
	public ComboBox<String> tipoUsuario;
	public Label rotuloPesquisa;
	public Panel painelPeriodo;
	public Label periodoDe;
	public TextFieldFormatted campoDe;
	public Label rotuloAte;
	public TextFieldFormatted campoAte;
	public Button pesquisar;
	public Table tabelaAcessos;
	public JScrollPane scroll;
	public TextFieldFormatted campoCNPJ;
	public Label rotuloCNPJ;
	public Button voltarMenu;

	public ConsultarAcessosView(MenuView menu, Panel panel) {
		panel.removeAll();
		painel = panel;
		menuView = menu;

		menuView.setTitle(menuView.getString(130));

		painelPesquisa = new Panel(menuView.getString(116));
		painelPesquisa.setPreferredSize(new Dimension(menuView.getX() - 300, 70));

		filtroUsuario = new Panel(menuView.getString(128));
		filtroUsuario.setPreferredSize(new Dimension(menuView.getX() - 300, 70));

		tipoUsuario = new ComboBox<String>(getLabelsUsuarios());
		tipoUsuario.setSelectedIndex(4);
		rotuloPesquisa = new Label(menuView.getString(117));

		painelPeriodo = new Panel(menuView.getString(118));
		painelPeriodo.setPreferredSize(new Dimension(menuView.getX() - 300, 70));
		rotuloCNPJ = new Label(menuView.getString(129));
		painel.setBorderPanel(menuView.getString(186));
		periodoDe = new Label(menuView.getString(119));
		rotuloAte = new Label(menuView.getString(120));

		pesquisar = new Button(menuView.getString(121));

		voltarMenu = new Button(menuView.getString(49));

		tabelaAcessos = new Table(getCabecalho());
		tabelaAcessos.setEnabled(false);
		tabelaAcessos.setPreferredSize(new Dimension(menuView.getX() - 300, 220));
		scroll = new JScrollPane(tabelaAcessos);
		scroll.setPreferredSize(new Dimension(menuView.getX() - 300, 220));

		try {
			campoDe = new TextFieldFormatted("##/##/####", 18);
			campoAte = new TextFieldFormatted("##/##/####", 18);
			campoCNPJ = new TextFieldFormatted("##.###.###/####-##", 18);

		} catch (ParseException excecao) {
			excecao.printStackTrace();
		}

		painelPesquisa.add(rotuloPesquisa);
		painelPesquisa.add(tipoUsuario);

		painel.add(painelPesquisa);
		painel.repaint();
		painel.revalidate();
	}

	public void consultarAcessosTodos(Panel painel) {

		painel.removeAll();
		tabelaAcessos.resetModel();
		painel.add(painelPesquisa);
		painel.add(scroll);
		painel.add(voltarMenu);
		painel.repaint();
		painel.revalidate();

	}

	public void consultarAcessosComCNPJ(Panel painel) {

		painel.removeAll();
		tabelaAcessos.resetModel();
		painel.add(painelPesquisa);
		painelPeriodo.add(periodoDe);
		painelPeriodo.add(campoDe);
		painelPeriodo.add(rotuloAte);
		painelPeriodo.add(campoAte);

		filtroUsuario.add(rotuloCNPJ);
		filtroUsuario.add(campoCNPJ);

		painel.add(painelPeriodo);
		painel.add(filtroUsuario);
		painel.add(scroll);
		painel.add(pesquisar);
		painel.add(voltarMenu);

		painel.repaint();
		painel.revalidate();

	}

	public void consultarAcessosSemCNPJ(Panel painel) {

		painel.removeAll();
		tabelaAcessos.resetModel();
		painel.add(painelPesquisa);
		painelPeriodo.add(periodoDe);
		painelPeriodo.add(campoDe);
		painelPeriodo.add(rotuloAte);
		painelPeriodo.add(campoAte);
		painel.add(painelPeriodo);
		painel.add(scroll);
		painel.add(pesquisar);
		painel.add(voltarMenu);

		painel.repaint();
		painel.revalidate();

	}

	public String getCNPJ() {
		return campoCNPJ.getNumerosLetras();
	}

	public String getHorarioSaida() {
		return campoAte.getNumerosLetras();
	}

	public String getHorarioEntrada() {
		return campoDe.getNumerosLetras();
	}

	public void addVoltarListener(ActionListener l) {
		voltarMenu.addActionListener(l);
	}

	public void addTipoUsuarioListener(ItemListener l) {
		tipoUsuario.addItemListener(l);
	}

	public String[] getCabecalho() {
		return new String[] { menuView.getString(123), menuView.getString(122), menuView.getString(125),
				menuView.getString(126) };
	}

	public String[] getLabelsUsuarios() {
		return new String[] { menuView.getString(4), menuView.getString(5), menuView.getString(6),
				menuView.getString(127), menuView.getString(177) };
	}

	public void addPesquisarListener(ActionListener l) {
		pesquisar.addActionListener(l);
	}

}
