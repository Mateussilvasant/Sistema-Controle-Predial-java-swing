package br.com.sistemaControlePredial.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;

import br.com.sistemaControlePredial.view.componentes.Button;
import br.com.sistemaControlePredial.view.componentes.Label;
import br.com.sistemaControlePredial.view.componentes.List;
import br.com.sistemaControlePredial.view.componentes.Panel;
import br.com.sistemaControlePredial.view.componentes.TextField;
import br.com.sistemaControlePredial.view.componentes.TextFieldFormatted;

public class ConsultarEmpresaView {

	public Panel painelRazaoSocial, painelFuncionamento, painelArCondicionado, painelConjuntos;

	public TextField campoNomeEmpresa, campoCnpj, campoInicioEmpresa, campoFimEmpresa, campoArCondicionadoInicio,
			campoArCondicionadoFim, campoTemperaturaMax;

	public Label rotuloNomeEmpresa, rotuloCnpj, rotuloInicioEmpresa, rotuloFimEmpresa, rotuloArCondicionadoInicio,
			rotuloArCondicionadoFim, rotuloTemperaturaMax, rotuloSelecionarConjunto;
	public Label rotuloPesquisa;

	public Button botaoVoltar;
	public Button botaoPesquisa;

	public DefaultListModel<Object> conjuntosItem;
	public List listaConjuntos;
	public JScrollPane listaConjuntosScroll;
	public TextFieldFormatted campoPesquisa;
	public MenuView menuView;

	public ConsultarEmpresaView(Panel painel, MenuView menu) {
		// set menu principal
		menuView = menu;
		menuView.setTitle(menuView.getString(56));

		// remove os componentes do menu anterior
		painel.removeAll();
		painel.setBorderPanel(menuView.getString(55));

		// sets painel do formul�rio
		painelRazaoSocial = new Panel(menuView.getString(31));
		painelRazaoSocial.setPreferredSize(new Dimension(menuView.getX() - 300, 70));
		painelFuncionamento = new Panel(menuView.getString(32));
		painelFuncionamento.setPreferredSize(new Dimension(menuView.getX() - 300, 100));
		painelArCondicionado = new Panel(menuView.getString(34));
		painelArCondicionado.setPreferredSize(new Dimension(menuView.getX() - 300, 70));
		painelConjuntos = new Panel(menuView.getString(35));

		// sets labels do formul�rio
		rotuloPesquisa = new Label(menuView.getString(53));
		rotuloNomeEmpresa = new Label(menuView.getString(36));
		rotuloCnpj = new Label(menuView.getString(37));
		rotuloInicioEmpresa = new Label(menuView.getString(38));
		rotuloFimEmpresa = new Label(menuView.getString(39));
		rotuloArCondicionadoInicio = new Label(menuView.getString(40));
		rotuloArCondicionadoFim = new Label(menuView.getString(41));
		rotuloTemperaturaMax = new Label(menuView.getString(42));

		// sets textfields do formul�rio
		try {
			campoPesquisa = new TextFieldFormatted("##.###.###/####-##", 18);
		} catch (ParseException excecao) {
			System.out.println("\n" + excecao.getLocalizedMessage());
		}

		// sets da lista de conjuntos
		conjuntosItem = new DefaultListModel<Object>();
		listaConjuntos = new List(menuView.getString(46));
		listaConjuntos.setPreferredSize(new Dimension(menuView.getX() - 330, 130));
		listaConjuntosScroll = new JScrollPane();
		listaConjuntosScroll.setPreferredSize(new Dimension(menuView.getX() - 310, 130));
		listaConjuntosScroll.setAutoscrolls(true);
		listaConjuntosScroll.setViewportView(listaConjuntos);

		// sets textfiels do formul�rio
		campoNomeEmpresa = new TextField(15, false);
		campoCnpj = new TextField(18, false);
		campoInicioEmpresa = new TextField(18, false);
		campoFimEmpresa = new TextField(18, false);
		campoArCondicionadoInicio = new TextField(18, false);
		campoArCondicionadoFim = new TextField(18, false);
		campoTemperaturaMax = new TextField(18, false);

		// sets buttons do formul�rio
		botaoPesquisa = new Button(menuView.getString(54));
		botaoVoltar = new Button(menuView.getString(49));

		// adiciona os componentes no menu principal
		painel.add(rotuloPesquisa);
		painel.add(campoPesquisa);
		painel.add(botaoPesquisa);

		painel.repaint();
		painel.revalidate();

	}

	public void informacoesView(Panel painel) {
		// Adiciona componentes no painel razao social
		painelRazaoSocial.add(rotuloNomeEmpresa);
		painelRazaoSocial.add(campoNomeEmpresa);
		painelRazaoSocial.add(rotuloCnpj);
		painelRazaoSocial.add(campoCnpj);

		// adiciona componentes no painel horario funcionamento
		painelFuncionamento.add(rotuloInicioEmpresa);
		painelFuncionamento.add(campoInicioEmpresa);
		painelFuncionamento.add(rotuloFimEmpresa);
		painelFuncionamento.add(campoFimEmpresa);
		painelFuncionamento.add(rotuloArCondicionadoInicio);
		painelFuncionamento.add(campoArCondicionadoInicio);
		painelFuncionamento.add(rotuloArCondicionadoFim);
		painelFuncionamento.add(campoArCondicionadoFim);

		// adiciona componentes no painel temperatura
		painelArCondicionado.add(rotuloTemperaturaMax);
		painelArCondicionado.add(campoTemperaturaMax);
		painelConjuntos.add(listaConjuntosScroll);

		painel.add(painelRazaoSocial);
		painel.add(painelFuncionamento);
		painel.add(painelArCondicionado);
		painel.add(painelConjuntos);
		painel.add(botaoVoltar);

		painel.repaint();
		painel.revalidate();

	}

	@SuppressWarnings("unchecked")
	public void adicionarElemento(String objeto) {
		conjuntosItem.addElement(objeto);
		listaConjuntos.setModel(conjuntosItem);
	}

	@SuppressWarnings("unchecked")
	public void removerElemento() {
		conjuntosItem.removeAllElements();
		listaConjuntos.setModel(conjuntosItem);
	}

	public void addBotaoVoltarListener(ActionListener l) {
		botaoVoltar.addActionListener(l);
	}

	public void addPesquisarEmpresaListener(ActionListener l) {
		botaoPesquisa.addActionListener(l);
	}
}