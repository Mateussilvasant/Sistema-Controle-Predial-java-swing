package br.com.sistemaControlePredial.view.componentes;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Table extends JTable {

	public DefaultTableModel modelo;
	public Object[][] matriz;
	public Object[] cabecalho;
	public int coluna;
	public int linha;
	public int controle;

	public Table(Object[][] objetos, Object[] cabecalhos) {
		super(objetos, cabecalhos);
	}

	public Table(Object[] colunas) {
		modelo = new DefaultTableModel(matriz, colunas);
		matriz = null;
		cabecalho = colunas;
		controle = 0;
		linha = 0;
		coluna = 0;
		this.setModel(modelo);
	}

	public void resetModel() {
		modelo = new DefaultTableModel(matriz, cabecalho);
		matriz = null;
		controle = 0;
		linha = 0;
		coluna = 0;
		this.setModel(modelo);
	}

	public void setLinhaSize(int l) {
		linha = l;
	}

	public void setColunaSize(int c) {
		coluna = c;
	}

	public void adiciona(Object objeto) {

		Object[] obj = (Object[]) objeto;

		if (matriz != null) {
			modelo.insertRow(controle, obj);
			controle++;
		} else {
			matriz = new Object[linha][coluna];
			modelo.insertRow(controle, obj);
			controle++;
		}

	}

}
