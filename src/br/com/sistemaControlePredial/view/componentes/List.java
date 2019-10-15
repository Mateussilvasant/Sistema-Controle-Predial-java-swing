package br.com.sistemaControlePredial.view.componentes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings({ "serial", "rawtypes" })
public class List extends JList {

	public List(String titulo) {
		setSelectionMode(JList.VERTICAL_WRAP);
		setBackground(Color.WHITE);
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setForeground(Color.BLACK);
		setBorder(new TitledBorder(new LineBorder(new Color(233, 233, 233), 1), titulo,
				TitledBorder.DEFAULT_JUSTIFICATION, 0, new Font("Segoe UI", Font.PLAIN + Font.ITALIC, 16)));
		setSelectionForeground(new Color(247, 247, 247));
		setSelectionBackground(new Color(43, 43, 43));
		setFocusable(false);
	}

}
