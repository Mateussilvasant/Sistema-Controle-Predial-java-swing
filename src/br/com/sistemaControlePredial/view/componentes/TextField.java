package br.com.sistemaControlePredial.view.componentes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;

@SuppressWarnings("serial")
public class TextField extends JTextField {

	public TextField(int coluna) {
		super(coluna);
		UIManager.put("TextField.inactiveBackground", new ColorUIResource(Color.WHITE));
		UIManager.put("TextField.inactiveForeground", new ColorUIResource(Color.BLACK));
		setFont(new Font("Segoe UI", 0, 14));
		setBorder(new LineBorder(new Color(149, 154, 161), 1));
	}

	public TextField(int coluna, boolean habilitado) {
		super(coluna);
		UIManager.put("TextField.inactiveBackground", new ColorUIResource(Color.WHITE));
		UIManager.put("TextField.inactiveForeground", new ColorUIResource(Color.BLACK));

		if (!habilitado) {
			setEnabled(false);
			setEditable(false);
		}

		setFont(new Font("Segoe UI", 0, 14));
		setBorder(new LineBorder(new Color(149, 154, 161), 1));
	}

}
