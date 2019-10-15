package br.com.sistemaControlePredial;

import java.util.Locale;
import java.util.ResourceBundle;

import br.com.sistemaControlePredial.view.MenuView;

public class Main {

	public static void main(String[] args) {

		System.setProperty("swing.aatext", "true");

		new MenuView(ResourceBundle.getBundle("br.com.sistemaControlePredial.sistemaControlePredial_pt_BR",
				new Locale("pt", "BR")), 0);

	}
}
