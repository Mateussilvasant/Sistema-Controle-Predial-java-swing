package br.com.sistemaControlePredial.view;

import java.awt.event.ActionListener;
import br.com.sistemaControlePredial.control.ConsultarEmpresaControl;
import br.com.sistemaControlePredial.view.componentes.Button;
import br.com.sistemaControlePredial.view.componentes.ButtonToggle;
import br.com.sistemaControlePredial.view.componentes.Label;
import br.com.sistemaControlePredial.view.componentes.Panel;
import br.com.sistemaControlePredial.view.componentes.TextField;

public class ConfigurarTemperaturaView {

	public Panel painel;
	public ConsultarEmpresaControl consultarEmpresa;
	public ButtonToggle botaoTroca;
	public Label rotuloTempAtual, rotuloStatusAr;
	public TextField campoTempAtual, campoStatusAr;
	public MenuView menuView;
	

	public ConfigurarTemperaturaView(MenuView menu, Panel panel) {
		painel = panel;
		menuView = menu;
		consultarEmpresa = new ConsultarEmpresaControl(menu, painel);
		consultarEmpresa.painel.setBorderPanel(consultarEmpresa.menuView.getString(185));
		consultarEmpresa.menuView.setTitle(consultarEmpresa.menuView.getString(193));
		consultarEmpresa.botaoPesquisa.removeActionListener(consultarEmpresa.pesquisarListener);
		rotuloTempAtual = new Label(menuView.getString(194));
		rotuloStatusAr = new Label(menuView.getString(195));
		campoStatusAr = new TextField(18, false);
		campoTempAtual = new TextField(18, false);
		botaoTroca = new ButtonToggle("On", "Off", false);

		painel.revalidate();
		painel.repaint();
	}

	public void informacoesView(Panel painel) {
		consultarEmpresa.informacoesView(painel);
		consultarEmpresa.painel.setBorderPanel(consultarEmpresa.menuView.getString(185));
		consultarEmpresa.painelArCondicionado.add(rotuloTempAtual);
		consultarEmpresa.painelArCondicionado.add(campoTempAtual);
		consultarEmpresa.painelRazaoSocial.remove(consultarEmpresa.campoCnpj);
		consultarEmpresa.painelRazaoSocial.add(campoStatusAr);
		consultarEmpresa.rotuloCnpj.setText(menuView.getString(195));
		painel.remove(consultarEmpresa.painelFuncionamento);
		Button aux = (Button) painel.getComponent(painel.getComponentCount() - 1);
		painel.remove(painel.getComponent(painel.getComponentCount() - 1));
		painel.add(botaoTroca);
		painel.add(aux);

	}

	public void addBuscarListener(ActionListener l) {
		consultarEmpresa.botaoPesquisa.addActionListener(l);
	}

	public void addBotaoTrocaListener(ActionListener l) {
		botaoTroca.addActionListener(l);
	}

}
