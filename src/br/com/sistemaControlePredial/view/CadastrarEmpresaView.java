package br.com.sistemaControlePredial.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;

import br.com.sistemaControlePredial.view.componentes.Button;
import br.com.sistemaControlePredial.view.componentes.ComboBox;
import br.com.sistemaControlePredial.view.componentes.Label;
import br.com.sistemaControlePredial.view.componentes.List;
import br.com.sistemaControlePredial.view.componentes.Panel;
import br.com.sistemaControlePredial.view.componentes.TextField;
import br.com.sistemaControlePredial.view.componentes.TextFieldFormatted;

public class CadastrarEmpresaView {

	public Panel categoriaRazaoSocial, categoriaHorarios, categoriaArCondicionado, categoriaConjunto;
	public TextFieldFormatted campoCnpj, campoInicioEmpresa, campoFimEmpresa, campoArCondicionadoInicio,
			campoArCondicionadoFim, campoTemperaturaMax;
	public Label rotuloNomeEmpresa, rotuloCnpj, rotuloInicioEmpresa, rotuloFimEmpresa, rotuloArCondicionadoInicio,
			rotuloArCondicionadoFim, rotuloTemperaturaMin, rotuloTemperaturaMax, rotuloSelecionarConjunto;
	public Button botaoAddConjunto, botaoRemoverConjunto, botaoCadastrarEmpresa, botaoVoltarPrincipal;
	public JScrollPane conjuntosEscolhidosBarra;
	public TextField campoNomeEmpresa;
	protected MenuView menuView;
	public ComboBox<Object> listConjuntos;
	public DefaultComboBoxModel<Object> modeloConjunto;
	public DefaultListModel<Object> conjuntosItem;
	public List listaConjuntosEscolhidos;

	public CadastrarEmpresaView(MenuView menu, Panel painel, boolean x) {

		menuView = menu;
		// Remove os Elementos Anteriores
		if (x) {
			painel.removeAll();

			// Setando titulos da janela
			menuView.setTitle(menuView.getString(33));
			painel.setBorderPanel(menuView.getString(30));
		}
		// Sets dos paineis do Formulï¿½rio

		categoriaRazaoSocial = new Panel(menuView.getString(31));
		categoriaRazaoSocial.setPreferredSize(new Dimension(menuView.getX() - 300, 70));
		categoriaHorarios = new Panel(menuView.getString(32));
		categoriaHorarios.setPreferredSize(new Dimension(menuView.getX() - 300, 100));

		categoriaArCondicionado = new Panel(menuView.getString(34));
		categoriaArCondicionado.setPreferredSize(new Dimension(menuView.getX() - 300, 70));
		categoriaConjunto = new Panel(menuView.getString(35));
		categoriaConjunto.setPreferredSize(new Dimension(menuView.getX() - 300, 200));

		// Sets dos labels do Formulï¿½rio

		rotuloNomeEmpresa = new Label(menuView.getString(36));
		rotuloCnpj = new Label(menuView.getString(37));
		rotuloInicioEmpresa = new Label(menuView.getString(38));
		rotuloFimEmpresa = new Label(menuView.getString(39));
		rotuloArCondicionadoInicio = new Label(menuView.getString(40));
		rotuloArCondicionadoFim = new Label(menuView.getString(41));
		rotuloTemperaturaMax = new Label(menuView.getString(42));
		rotuloSelecionarConjunto = new Label(menuView.getString(47));

		// Sets dos textfields do Formulï¿½rio

		try {
			campoNomeEmpresa = new TextField(15);
			campoCnpj = new TextFieldFormatted("##.###.###/####-##", 18);
			campoInicioEmpresa = new TextFieldFormatted("##:##:00", 18);
			campoFimEmpresa = new TextFieldFormatted("##:##:00", 18);
			campoArCondicionadoInicio = new TextFieldFormatted("##:##:00", 18);
			campoArCondicionadoFim = new TextFieldFormatted("##:##:00", 18);
			campoTemperaturaMax = new TextFieldFormatted("##�", 18);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// sets dos buttons do Formulario
		botaoAddConjunto = new Button(menuView.getString(44));
		botaoRemoverConjunto = new Button(menuView.getString(45));
		botaoCadastrarEmpresa = new Button(menuView.getString(48));
		botaoVoltarPrincipal = new Button(menuView.getString(49));

		// sets da Lista
		conjuntosItem = new DefaultListModel<Object>();
		modeloConjunto = new DefaultComboBoxModel<Object>();

		listConjuntos = new ComboBox<Object>();

		listaConjuntosEscolhidos = new List(menuView.getString(46));
		listaConjuntosEscolhidos.setPreferredSize(new Dimension(menuView.getX() - 330, 130));
		conjuntosEscolhidosBarra = new JScrollPane();
		conjuntosEscolhidosBarra.setPreferredSize(new Dimension(menuView.getX() - 310, 130));
		conjuntosEscolhidosBarra.setAutoscrolls(true);

		// Adiciona componentes no painel Razao Social
		categoriaRazaoSocial.add(rotuloNomeEmpresa);
		categoriaRazaoSocial.add(campoNomeEmpresa);
		categoriaRazaoSocial.add(rotuloCnpj);
		categoriaRazaoSocial.add(campoCnpj);

		// Adiciona componentes no painel Horario Funcionamento

		categoriaHorarios.add(rotuloInicioEmpresa);
		categoriaHorarios.add(campoInicioEmpresa);
		categoriaHorarios.add(rotuloFimEmpresa);
		categoriaHorarios.add(campoFimEmpresa);
		categoriaHorarios.add(rotuloArCondicionadoInicio);
		categoriaHorarios.add(campoArCondicionadoInicio);
		categoriaHorarios.add(rotuloArCondicionadoFim);
		categoriaHorarios.add(campoArCondicionadoFim);

		// Adiciona componentes no painel Temperatura

		categoriaArCondicionado.add(rotuloTemperaturaMax);
		categoriaArCondicionado.add(campoTemperaturaMax);

		// Adiciona componentes no painel principal

		painel.add(categoriaRazaoSocial);
		painel.add(categoriaHorarios);
		painel.add(categoriaArCondicionado);
		painel.add(categoriaConjunto);
		painel.add(botaoCadastrarEmpresa);
		painel.add(botaoVoltarPrincipal);

		// Atualiza o painel para receber os novos componentes
		painel.revalidate();
		painel.repaint();

	}

	public void adicionarItemComboBox(Object elemento) {
		modeloConjunto.addElement(elemento);
		listConjuntos.setModel(modeloConjunto);
	}

	public void removerItemComboBox(Object elemento) {
		modeloConjunto.removeElement(elemento);
		listConjuntos.setModel(modeloConjunto);
	}

	public void configuraConjuntoCategoria(Panel painel) {
		categoriaConjunto.add(rotuloSelecionarConjunto);
		categoriaConjunto.add(listConjuntos);
		categoriaConjunto.add(botaoAddConjunto);
		categoriaConjunto.add(botaoRemoverConjunto);
		conjuntosEscolhidosBarra.setViewportView(listaConjuntosEscolhidos);
		categoriaConjunto.add(conjuntosEscolhidosBarra);

		painel.revalidate();
		painel.repaint();
	}

	public void addBotaoCadastrarListener(ActionListener l) {
		botaoCadastrarEmpresa.addActionListener(l);
	}

	public void addBotaoVoltarPrincipalListener(ActionListener l) {
		botaoVoltarPrincipal.addActionListener(l);
	}

	public void addlistConjuntosListener(ItemListener l) {
		listConjuntos.addItemListener(l);
	}

	public void addBotaoAdicionarConjuntoListener(ActionListener a) {
		botaoAddConjunto.addActionListener(a);
	}

	public void addBotaoRemoverConjuntoListener(ActionListener a) {
		botaoRemoverConjunto.addActionListener(a);
	}

	public void adicionarConjunto(Object item) {
		conjuntosItem.addElement(item);
	}

	public void removerConjunto(Object objeto) {
		conjuntosItem.removeElement(objeto);
	}

	public String getNomeEmpresa() {
		return campoNomeEmpresa.getText();
	}

	public String getCNPJ() {
		return campoCnpj.getNumerosLetras();
	}

	public String getEmpresaHorarioInicio() {
		return campoInicioEmpresa.getText();
	}

	public String getEmpresaHorarioFim() {
		return campoFimEmpresa.getText();
	}

	public String getArCondicionadoInicio() {
		return campoArCondicionadoInicio.getText();
	}

	public String getArCondicionadoFim() {
		return campoArCondicionadoFim.getText();
	}

	public int getTemperaturaMaxima() {
		return new Integer(campoTemperaturaMax.getNumeros());
	}

}
