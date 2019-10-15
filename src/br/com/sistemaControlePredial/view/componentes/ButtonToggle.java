package br.com.sistemaControlePredial.view.componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ButtonToggle extends JButton implements ActionListener {

	private boolean TROCA = false;
	public static final boolean ON = false;
	public static final boolean OFF = true;
	private String textoON;
	private String textoOff;

	public ButtonToggle(String t1, String t2, boolean selecao) {
		super("");
		setTextoON(t1);
		setTextoOff(t2);
		setSelecaoBoolean(selecao);
		setFont(new Font("Segoe UI", 0, 14));
		setPreferredSize(new Dimension(160, 30));
		setFocusPainted(false);
		addActionListener(this);
		defineStyle();
	}

	public void setSelecaoBoolean(boolean status) {
		TROCA = status;
	}

	public boolean getSelecaoBoolean() {
		return TROCA;
	}

	public void defineStyle() {
		if (getSelecaoBoolean()) {
			setText(getTextoON());
			setBackground(new Color(124, 252, 0));
			setForeground(new Color(255, 255, 255));
			setBorder(new LineBorder(new Color(50, 205, 50), 1));
			setFocusable(false);
			setBorderPainted(false);

		} else {
			setText(getTextoOff());
			setBackground(new Color(255, 0, 0));
			setForeground(new Color(255, 255, 255));
			setBorder(new LineBorder(new Color(255, 69, 0), 1));
			setFocusable(false);
			setBorderPainted(false);

		}
	}

	public void actionPerformed(ActionEvent evento) {
		/*
		 * if (getSelecaoBoolean()) { setSelecaoBoolean(false); defineStyle(); }
		 * else { setSelecaoBoolean(true); defineStyle(); }
		 */
	}

	public String getTextoON() {
		return textoON;
	}

	public void setTextoON(String textoON) {
		this.textoON = textoON;
	}

	public String getTextoOff() {
		return textoOff;
	}

	public void setTextoOff(String textoOff) {
		this.textoOff = textoOff;
	}
}
