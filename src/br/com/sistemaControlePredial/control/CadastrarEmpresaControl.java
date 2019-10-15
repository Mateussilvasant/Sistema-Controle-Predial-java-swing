package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import br.com.sistemaControlePredial.model.Conjunto;
import br.com.sistemaControlePredial.model.Empresa;
import br.com.sistemaControlePredial.model.Temperatura;
import br.com.sistemaControlePredial.view.CadastrarEmpresaView;
import br.com.sistemaControlePredial.view.MensagemView;
import br.com.sistemaControlePredial.view.MenuView;
import br.com.sistemaControlePredial.view.componentes.Panel;
import java.util.*;

public class CadastrarEmpresaControl extends CadastrarEmpresaView {

	public Panel panel;
	public int indiceSelecionado;
	private Conjunto conjunto = new Conjunto();
	private Iterator<Conjunto> dados;

	public CadastrarEmpresaControl(MenuView menu, Panel painel, boolean remover) {
		super(menu, painel, remover);
		panel = painel;

		configuraConjuntoCategoria(painel);
		setConjuntos();
		addlistConjuntosListener(new ConjuntoListener());
		addBotaoAdicionarConjuntoListener(new BotaoAddConjuntoListener());
		addBotaoRemoverConjuntoListener(new BotaoRemoveConjuntoListener());
		addBotaoVoltarPrincipalListener(new VoltarListener());

		
		if (remover) {
			addBotaoCadastrarListener(new CadastrarEmpresaListener());
		}
		
	}

	public void voltarMenuPrincipal(Panel painel) {
		if (menuView.getUsuario().getTipo() == 'S') {
			new SindicoControl(menuView);
		}
		if (menuView.getUsuario().getTipo() == 'A') {
			new AtendenteControl(menuView);
		}
	}

	// Exibe o nome dos conjunto dispoíveis.
	private void setConjuntos() {
		dados = conjunto.consultar().iterator();
		Conjunto cj;

		while (dados.hasNext() == true) {
			cj = dados.next();
			adicionarItemComboBox(menuView.getString(155) + ": " + cj.getAndar() + " | " + menuView.getString(156)
					+ ": " + cj.getNumero());
		}
	}

	public int getIndiceSelecionado() {
		return indiceSelecionado;
	}

	public void setIndiceSelecionado(int indiceSelecionado) {
		this.indiceSelecionado = indiceSelecionado;
	}

	class ConjuntoListener implements ItemListener {

		@SuppressWarnings("static-access")
		public void itemStateChanged(ItemEvent evento) {
			if (evento.getStateChange() == evento.SELECTED) {
				for (int d = 0; d < listConjuntos.getItemCount(); d++) {
					if (listConjuntos.getSelectedIndex() == d) {
						setIndiceSelecionado(d);
					}
				}
			}
		}
	}

	class BotaoRemoveConjuntoListener implements ActionListener {

		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent evento) {
			Object objeto = listConjuntos.getItemAt(getIndiceSelecionado());
			removerConjunto(objeto);
			listaConjuntosEscolhidos.setModel(conjuntosItem);
		}

	}

	class BotaoAddConjuntoListener implements ActionListener {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {

			Object objeto = listConjuntos.getItemAt(getIndiceSelecionado());
			boolean existe = false;			
			for (int d = 0; d < conjuntosItem.getSize(); d++) {
				if (objeto.toString().equals(conjuntosItem.getElementAt(d).toString())) {
					existe = true;
					break;
				}
			}
			
			if (!existe) {
				adicionarConjunto(objeto);
				listaConjuntosEscolhidos.setModel(conjuntosItem);
			}
		}
	}

	private void atualizarTXTSistemaAr( String cnpj )
	{
		Temperatura t = new Temperatura();
		t.atualizarArquivo( cnpj );
	}
	
	private boolean cadastrar() throws Exception {

		boolean sucesso = false;
		if (conjuntosItem.size() != 0) {

			// Faz o cadastro da empresa.
			Empresa empresa = new Empresa(getCNPJ(), getNomeEmpresa(), getTemperaturaMaxima(),
					getEmpresaHorarioInicio(), getEmpresaHorarioFim(), getArCondicionadoInicio(),
					getArCondicionadoFim());
			sucesso = empresa.cadastrar();
			
			// Localiza os conjuntos para serem cadastrados
			Conjunto cj;
			int controle = 0;

			while (controle < conjuntosItem.size()) {
				dados = conjunto.consultar().iterator();
				while (dados.hasNext() == true) {
					cj = dados.next();
					String comparar = menuView.getString(155) + ": " + cj.getAndar() + " | " + menuView.getString(156)
							+ ": " + cj.getNumero();
					if (conjuntosItem.get(controle).equals(comparar)) {
						conjunto.cadastrar(getCNPJ(), cj.getIdConjunto());
					}
				}
				controle++;
			}
		}

		if( sucesso == true )
			atualizarTXTSistemaAr( getCNPJ() );
		return sucesso;
	}
	
	//Verifica se a hora informada é válida
	protected boolean validarHora()
	{
		boolean saida = false;
		int hEntradaEmpre = Integer.parseInt(getEmpresaHorarioInicio().substring(0,2));
		int mEntradaEmpre = Integer.parseInt(getEmpresaHorarioInicio().substring(3,5));
		int hSaidaEmpre =   Integer.parseInt(getEmpresaHorarioFim().substring(0,2));
		int mSaidaEmpre = Integer.parseInt(getEmpresaHorarioFim().substring(3,5));
		int hEntradaAr = Integer.parseInt( getArCondicionadoInicio().substring(0,2));
		int mEntradaAr = Integer.parseInt( getArCondicionadoInicio().substring(3,5));
		int hSaidaAr = Integer.parseInt(getArCondicionadoFim().substring(0,2));
		int mSaidaAr = Integer.parseInt(getArCondicionadoFim().substring(3,5));
		
		
		if(hEntradaEmpre <= 24 && hEntradaEmpre >= 00 && mEntradaEmpre <= 60 && mEntradaEmpre >= 00
				&& hSaidaEmpre <= 24 && hSaidaEmpre >= 00 && mSaidaEmpre <= 60 && mSaidaEmpre >= 00)
		{
			saida = true;
		}else
		{
			new MensagemView(menuView.getString(141),menuView.getString(164), menuView, 0);
			return false;
		}
		
		if(hEntradaAr <= 24 && hEntradaAr >= 00 && mEntradaAr <= 60 && mEntradaAr >= 00
				&& hSaidaAr <= 24 && hSaidaAr >= 00 && mSaidaAr <= 60 && mSaidaAr >= 00)
		{
			saida = true;
		}else
		{
			new MensagemView(menuView.getString(141),menuView.getString(165), menuView, 0);
			return false;
		}
		
		return saida;
	}
	
	//Valida a temperatura Informada
	protected boolean validaTemperatura()
	{
		boolean saida = false;
		
		if(getTemperaturaMaxima() >= 10 && getTemperaturaMaxima() <= 30)
		{
			saida = true;
		}else
		{
			new MensagemView(menuView.getString(141),menuView.getString(172), menuView, 0);
		}
		
		return saida;
	}

	// Faz o cadastor da empresa
	class CadastrarEmpresaListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent evento) 
		{
			try 
			{
				
				if(validarHora() == true && validaTemperatura() == true)
				{
					if (cadastrar() == true) 
					{
						new MensagemView(menuView.getString(139), menuView.getString(140), menuView,
								MensagemView.INFORMACAO);
						voltarMenuPrincipal(panel);
					} else 
					{
						new MensagemView(menuView.getString(141), menuView.getString(143), menuView, 0);
					}
				}	
				
			} catch (Exception excecao) 
			{
				if (excecao.getLocalizedMessage() == "" + 0) 
				{
					new MensagemView(menuView.getString(141), menuView.getString(142), menuView, 0);
				} else if (excecao.getLocalizedMessage() == "" + 1) 
				{
					new MensagemView(menuView.getString(141), menuView.getString(143), menuView, 0);
				}else
				{
					new MensagemView(menuView.getString(141), menuView.getString(143), menuView, 0);
				}
				//System.out.print("\n" + excecao.getLocalizedMessage());
			}
		}
	}

	class VoltarListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent evento) 
		{
			voltarMenuPrincipal(panel);
		}
	}
}