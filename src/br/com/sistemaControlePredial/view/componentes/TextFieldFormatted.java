package br.com.sistemaControlePredial.view.componentes;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.MaskFormatter;

@SuppressWarnings("serial")
public class TextFieldFormatted extends JFormattedTextField {

	public TextFieldFormatted(String formato, int colunas) throws ParseException {
		super(new MaskFormatter(formato));
		UIManager.put("FormattedTextField.inactiveBackground", new ColorUIResource(Color.WHITE));
		UIManager.put("FormattedTextField.inactiveForeground", new ColorUIResource(Color.BLACK));

		setFont(new Font("Segoe UI", 0, 14));
		setColumns(colunas);
		setBorder(new LineBorder(new Color(149, 154, 161), 1));
	}

	public String getNumeros() {
		return getText().replaceAll("[^0-9]", "");
	}

	public String getLetras() {
		return getText().replaceAll("[a-zA-Z]", "");
	}

	public String getNumerosLetras() {
		return getText();
	}

}
