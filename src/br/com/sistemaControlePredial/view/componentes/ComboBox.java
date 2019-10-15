package br.com.sistemaControlePredial.view.componentes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

@SuppressWarnings("serial")
public class ComboBox<T> extends JComboBox<T> {
	public ComboBox(T vet[]) {
		super(vet);

		UIManager.put("ComboBox.background", new ColorUIResource(Color.WHITE));
		UIManager.put("ComboBox.foreground", new ColorUIResource(new Color(79, 79, 79)));

		UIManager.put("ComboBox.disabledBackground", new ColorUIResource(Color.WHITE));
		UIManager.put("ComboBox.disabledForeground", new ColorUIResource(new Color(79, 79, 79)));

		UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color(224, 224, 224)));
		UIManager.put("ComboBox.selectionForeground", new ColorUIResource(new Color(66, 66, 66)));

		setMaximumRowCount(vet.length);
		setFont(new Font("Segoe UI", 0, 14));
	}

	public ComboBox() {
		setFont(new Font("Segoe UI", 0, 14));
	}
}