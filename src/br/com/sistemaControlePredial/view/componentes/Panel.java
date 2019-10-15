package br.com.sistemaControlePredial.view.componentes;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class Panel extends JPanel {

	public TitledBorder borda;

	public Panel(String titulo) {
		super(new FlowLayout());
		setBackground(new Color(233, 233, 233));
		borda = new TitledBorder(new LineBorder(new Color(131, 131, 131), 1), titulo, TitledBorder.LEFT, 0,
				new Font("Segoe UI", Font.CENTER_BASELINE, 14));
		setBorder(borda);
	}

	public Panel(String titulo, LayoutManager layout) {
		super(layout);
		setBackground(new Color(233, 233, 233));
		borda = new TitledBorder(new LineBorder(new Color(131, 131, 131), 1), titulo, TitledBorder.LEFT, 0,
				new Font("Segoe UI", Font.CENTER_BASELINE, 14));
		setBorder(borda);
	}

	public void setBorderPanel(String titulo) {
		borda.setTitle(titulo);
		setBorder(borda);
	}

	public String getBorderPanel() {
		return borda.getTitle();
	}

	public void removeBorder() {
		setBorder(null);
	}

	public void addCorSecundaria() {
		setBackground(new Color(250, 250, 250));
	}
}
