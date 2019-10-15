package br.com.sistemaControlePredial.view.componentes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class Button extends JButton {

	private final Color BACKGROUND_COLOR = new Color(79, 79, 79);
	private final Color BACKGROUND_ENTERED = new Color(28, 28, 28);
	private final Color FOREGROUND_COLOR = new Color(232, 232, 232);
	private final Cursor CURSOR_ENTERED = new Cursor(Cursor.HAND_CURSOR);
	private final Cursor CURSOR_DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);

	public Button(String nome) {
		super(nome);
		setFont(new Font("Segoe UI", 0, 14));
		setPreferredSize(new Dimension(160, 30));
		addMouseListener(new FocoButtonListener());
		setStyle();
	}

	public Button(String nome, int x, int y) {
		super(nome);
		setFont(new Font("Segoe UI", 0, 14));
		setPreferredSize(new Dimension(x, y));
		addMouseListener(new FocoButtonListener());
		setStyle();
	}

	public void setStyle() {
		UIManager.put("Button.select", BACKGROUND_COLOR);
		setBorder(new LineBorder(new Color(67, 69, 71), 1));
		setBackground(BACKGROUND_COLOR);
		setForeground(FOREGROUND_COLOR);
		setFocusPainted(false);
	}

	public void setCorAlternativa() {
		setBackground(FOREGROUND_COLOR);
		setForeground(BACKGROUND_COLOR);
	}

	class FocoButtonListener implements MouseListener {

		public void mouseEntered(MouseEvent mouseEncostado) {
			setBackground(BACKGROUND_ENTERED);
			setCursor(CURSOR_ENTERED);
		}

		public void mouseExited(MouseEvent mouseRetirado) {
			setBackground(BACKGROUND_COLOR);
			setCursor(CURSOR_DEFAULT);
		}

		public void mousePressed(MouseEvent mousePressionado) {
		}

		public void mouseReleased(MouseEvent mouseSolto) {
		}

		public void mouseClicked(MouseEvent mouseClicado) {
		}

	}
}
