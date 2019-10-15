package br.com.sistemaControlePredial.view.componentes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;

@SuppressWarnings("serial")
public class PasswordField extends JPasswordField {
	public PasswordField(int colunas) {
		super(colunas);

		UIManager.put("PasswordField.inactiveBackground", new ColorUIResource(Color.WHITE));
		UIManager.put("PasswordField.inactiveForeground", new ColorUIResource(Color.BLACK));

		setFont(new Font("Segoe UI", 0, 14));
		setBorder(new LineBorder(new Color(149, 154, 161), 1));
	}
}
