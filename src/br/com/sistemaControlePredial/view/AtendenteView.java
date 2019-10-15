package br.com.sistemaControlePredial.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import br.com.sistemaControlePredial.services.CarregadorArquivo;
import br.com.sistemaControlePredial.view.componentes.Button;
import br.com.sistemaControlePredial.view.componentes.Label;
import br.com.sistemaControlePredial.view.componentes.Panel;

public class AtendenteView {

	protected MenuView menuView;

	public Panel categoriaEmpresa;
	public Panel categoriaFuncionario;
	public Button cadastrarEmpresa;
	public Button consultarEmpresa;
	public Button alterarEmpresa;
	public Button excluirEmpresa;
	public Button gerenciarUsuarios;
	public Button botaoDeslogar;
	public Label rotuloBemVindo;
	public Label rotuloNomeUsuario;
	public Label rotuloNivelAcesso;
	public Label rotuloTipoAcesso;
	public Label rotuloPredio;
	public Label rotuloNomePredio;
	public ImageIcon icone;
	public Label logoSistema;
	public Panel menuLateral;
	public Label rotuloMenuLateral;
	public Panel menuCabecalho;
	public Panel menuPrincipal;

	public AtendenteView(MenuView menu) {
		menuView = menu;
		menuView.setTitle(menuView.getString(29));
		menuView.menuBarra.leste.remove(menuView.botaoSair);
		menuView.menuBarra.repaint();
		menuView.menuBarra.revalidate();

		menuCabecalho = new Panel("");
		menuCabecalho.removeBorder();
		menuCabecalho.addCorSecundaria();

		menuPrincipal = new Panel(menuView.getString(9));
		menuPrincipal.addCorSecundaria();
		menuLateral = new Panel("", new FlowLayout());
		menuLateral.removeBorder();
		menuLateral.addCorSecundaria();

		rotuloMenuLateral = new Label(menuView.getString(10), 21);
		rotuloBemVindo = new Label(menuView.getString(20), 16);
		rotuloNomeUsuario = new Label(menuView.getUsuario().getUsuario());
		rotuloNivelAcesso = new Label(menuView.getString(21), 16);
		rotuloTipoAcesso = new Label(menuView.getString(22));
		rotuloPredio = new Label(menuView.getString(23), 16);
		rotuloNomePredio = new Label("Alameda Santos");

		cadastrarEmpresa = new Button(menuView.getString(11), 200, 32);
		consultarEmpresa = new Button(menuView.getString(12), 200, 32);
		alterarEmpresa = new Button(menuView.getString(13), 200, 32);
		excluirEmpresa = new Button(menuView.getString(14), 200, 32);
		gerenciarUsuarios = new Button(menuView.getString(15), 200, 32);
		botaoDeslogar = new Button(menuView.getString(103), 90, 23);

		categoriaEmpresa = new Panel(menuView.getString(17), new FlowLayout());
		categoriaEmpresa.setPreferredSize(new Dimension(menuView.getX() / 5, menuView.getX() / 6));
		categoriaFuncionario = new Panel(menuView.getString(18), new FlowLayout());
		categoriaFuncionario.setPreferredSize(new Dimension(menuView.getX() / 5, menuView.getX() / 12));

		categoriaEmpresa.add(cadastrarEmpresa);
		categoriaEmpresa.add(consultarEmpresa);
		categoriaEmpresa.add(alterarEmpresa);
		categoriaEmpresa.add(excluirEmpresa);
		categoriaFuncionario.add(gerenciarUsuarios);

		menuLateral.setLayout(new FlowLayout());
		menuLateral.setPreferredSize(new Dimension(menuView.getX() / 5, menuLateral.getHeight()));
		menuLateral.add(categoriaEmpresa);
		menuLateral.add(categoriaFuncionario);

		menuCabecalho.add(rotuloBemVindo);
		menuCabecalho.add(rotuloNomeUsuario);
		menuCabecalho.add(rotuloNivelAcesso);
		menuCabecalho.add(rotuloTipoAcesso);
		menuCabecalho.add(rotuloPredio);
		menuCabecalho.add(rotuloNomePredio);
		menuCabecalho.add(botaoDeslogar);

		menuView.menuBarra.leste.add(menuCabecalho);
		menuView.menuBarra.repaint();
		menuView.menuBarra.revalidate();

		menuPrincipal.add(new Label(
				new ImageIcon(CarregadorArquivo.getURL(this, "br/com/sistemaControlePredial/view/images/logo.png"))));
		menuView.painelFundo.removeAll();
		menuLateral.setPreferredSize(new Dimension(((menuView.getX() / 4) - 45), menuLateral.getHeight()));
		menuCabecalho.setPreferredSize(new Dimension(menuCabecalho.getWidth(), menuView.getY() / 19));
		menuPrincipal.setPreferredSize(new Dimension(menuView.getX() / 10, menuView.getY() / 8));
		menuView.addPainel(menuView.painelFundo, menuCabecalho, BorderLayout.NORTH);
		menuView.addPainel(menuView.painelFundo, menuLateral, BorderLayout.WEST);
		menuView.addPainel(menuView.painelFundo, menuPrincipal, BorderLayout.CENTER);

	}

	public void addBotaoDeslogarListener(ActionListener l) {
		botaoDeslogar.addActionListener(l);
	}

	public void addExcluirEmpresaListener(ActionListener l) {
		excluirEmpresa.addActionListener(l);
	}

	public void addAlterarEmpresaListener(ActionListener l) {
		alterarEmpresa.addActionListener(l);
	}

	public void addCadastrarEmpresaListener(ActionListener l) {
		cadastrarEmpresa.addActionListener(l);
	}

	public void addConsultarEmpresaListener(ActionListener l) {
		consultarEmpresa.addActionListener(l);
	}

	public void addGerenciarFuncionarioListener(ActionListener l) {
		gerenciarUsuarios.addActionListener(l);
	}

}
