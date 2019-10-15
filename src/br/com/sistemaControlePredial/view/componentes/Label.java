package br.com.sistemaControlePredial.view.componentes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Label extends JLabel {
	public Label(String texto) {
		super(texto);
		setFont(new Font("Segoe UI", 0, 15));
	}

	public Label(String texto, int tamanho) {
		super(texto);
		setFont(new Font("Segoe UI", Font.BOLD, tamanho));
	}

	public Label(ImageIcon image) {
		super(image);
	}

	public void setCorAlternativa() {
		setForeground(new Color(232, 232, 232));
	}
}
