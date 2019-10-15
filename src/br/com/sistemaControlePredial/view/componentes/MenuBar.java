package br.com.sistemaControlePredial.view.componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {

	public Panel leste;

	public MenuBar() {
		leste = new Panel("");
		leste.setPreferredSize(new Dimension(550, 40));
		leste.setBorder(null);
		leste.setBackground(new Color(79, 79, 79));
		setBackground(new Color(79, 79, 79));
		setLayout(new BorderLayout());
		add(leste, BorderLayout.EAST);
	}

	public void addMenu(String texto) {
		JMenu menu = new JMenu(texto);
		menu.setForeground(new Color(232, 232, 232));
		menu.setFont(new Font("Segoe UI", Font.ITALIC, 17));
		leste.add(menu);
		leste.repaint();
		leste.revalidate();
	}

	public void addComponente(JComponent componente, int x, int y) {
		componente.setForeground(new Color(79, 79, 79));
		componente.setFont(new Font("Segoe UI", 0, 14));
		componente.setPreferredSize(new Dimension(x, y));
		leste.add(componente);
		leste.repaint();
		leste.revalidate();
	}

	public void addComponente(Component componente) {
		componente.setForeground(new Color(232, 232, 232));
		componente.setFont(new Font("Segoe UI", Font.ITALIC, 17));
		leste.add(componente);
		leste.repaint();
		leste.revalidate();
	}

	public void addButton(Button botao) {
		leste.add(botao);
		leste.repaint();
		leste.revalidate();
	}

	public void removeButton(Button c) {
		leste.remove(c);
		leste.repaint();
		leste.revalidate();
	}

}
