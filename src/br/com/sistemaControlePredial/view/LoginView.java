package br.com.sistemaControlePredial.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;

import br.com.sistemaControlePredial.services.CarregadorArquivo;
import br.com.sistemaControlePredial.view.componentes.Button;
import br.com.sistemaControlePredial.view.componentes.ComboBox;
import br.com.sistemaControlePredial.view.componentes.Label;
import br.com.sistemaControlePredial.view.componentes.Panel;
import br.com.sistemaControlePredial.view.componentes.PasswordField;
import br.com.sistemaControlePredial.view.componentes.TextField;

public class LoginView {

	public MenuView menuView;
	public int usuarioEscolhido;
	public ComboBox<String> escolhaUsuario;
	public Panel painelLogin;
	public Label rotuloUsuario;
	public Label rotuloSenha;
	public PasswordField campoSenha;
	public Label rotuloTipoUsuario;
	public Button botaoLogar;
	public static TextField campoUsuario;
	public static Button botaoAcessarPredio;

	public LoginView(MenuView menu) {

		menuView = menu;
		menuView.setTitle(menuView.getString(28)); // 28
		menuView.menuBarra.leste.add(menuView.botaoSair);
		menuView.menuBarra.repaint();
		menuView.menuBarra.revalidate();

		painelLogin = new Panel(menuView.getString(0));
		rotuloUsuario = new Label(menuView.getString(1));
		campoUsuario = new TextField(20);
		rotuloSenha = new Label(menuView.getString(2));
		campoSenha = new PasswordField(20);
		rotuloTipoUsuario = new Label(menuView.getString(3));
		escolhaUsuario = new ComboBox<String>(getLabelsUsuarios());
		botaoLogar = new Button(menuView.getString(7));
		botaoAcessarPredio = new Button(menuView.getString(8));

		painelLogin.add(rotuloUsuario);
		painelLogin.add(campoUsuario);
		painelLogin.add(rotuloSenha);
		painelLogin.add(campoSenha);
		painelLogin.add(rotuloTipoUsuario);
		painelLogin.add(escolhaUsuario);
		painelLogin.add(botaoLogar);
		painelLogin.add(botaoAcessarPredio);

		menuView.getPainelFundo().add(new Label(
				new ImageIcon(CarregadorArquivo.getURL(this, "br/com/sistemaControlePredial/view/images/logo.png"))));
		menuView.addPainel(menuView.getPainelFundo(), painelLogin, BorderLayout.NORTH);

	}

	public LoginView() {

	}

	public String getUsuario() {
		return campoUsuario.getText();
	}

	public String getSenha() {
		return new String(campoSenha.getPassword());
	}

	public void addbotaoLogarListener(ActionListener l) {
		botaoLogar.addActionListener(l);
	}

	public void addbotaoAcessarPredioListener(ActionListener l) {
		botaoAcessarPredio.addActionListener(l);
	}

	public void addEscolhaUsuarioListener(ItemListener l) {
		escolhaUsuario.addItemListener(l);
	}

	public String[] getLabelsUsuarios() {
		return new String[] { menuView.getString(4), menuView.getString(5), menuView.getString(6) };
	}

}
