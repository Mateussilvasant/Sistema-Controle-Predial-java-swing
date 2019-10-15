package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;

import br.com.sistemaControlePredial.model.Empresa;
import br.com.sistemaControlePredial.model.Usuario;
import br.com.sistemaControlePredial.view.ConsultarAcessosView;
import br.com.sistemaControlePredial.view.MensagemView;
import br.com.sistemaControlePredial.view.MenuView;
import br.com.sistemaControlePredial.view.componentes.Panel;

public class ConsultarAcessosControl extends ConsultarAcessosView {

	// constantes
	public static final int SINDICO = 0;
	public static final int ATENDENTE = 1;
	public static final int FUNCIONARIO = 2;
	public static final int TODOS = 3;

	// Datas no formato para pesquiso
	private String dataInicio, dataFim;

	public ConsultarAcessosControl(MenuView menu, Panel painel) {
		super(menu, painel);
		addTipoUsuarioListener(new TipoUsuarioListener());
		addVoltarListener(new VoltarMenu());
		addPesquisarListener(new PesquisarListener());
	}

	public void consultarAcessos() {
		if (tipoUsuario.getSelectedIndex() == SINDICO) {
			if (validaData() == true)
				consultarAcessos(dataInicio, dataFim, 'S');

		}
		if (tipoUsuario.getSelectedIndex() == ATENDENTE) {
			if (validaData() == true)
				consultarAcessos(dataInicio, dataFim, 'A');
		}
		if (tipoUsuario.getSelectedIndex() == FUNCIONARIO) {
			if (validaData() == true && verificaCnpj() == true)
				consultarAcessos(getCNPJ(), dataInicio, dataFim, 'F');
		}
		if (tipoUsuario.getSelectedIndex() == TODOS) {
			consultarTodosAcessos();
		}
	}

	private void consultarTodosAcessos() {
		Usuario usuario = new Usuario();
		tabelaAcessos.resetModel();
		Object[] acessos = usuario.consultarAcessos();
		tabelaAcessos.setLinhaSize(acessos.length);
		tabelaAcessos.setColunaSize(4);

		for (int d = 0; d < acessos.length; d++) {
			tabelaAcessos.adiciona(acessos[d]);
		}

		tabelaAcessos.setModel(tabelaAcessos.modelo);
	}

	private void consultarAcessos(String CNPJ, String horarioEntrada, String horarioSaida, char tipo) {
		Usuario usuario = new Usuario();
		tabelaAcessos.resetModel();
		Object[] acessos = usuario.consultarAcessos(CNPJ, horarioEntrada, horarioSaida, tipo);
		tabelaAcessos.setLinhaSize(acessos.length);
		tabelaAcessos.setColunaSize(4);

		for (int d = 0; d < acessos.length; d++) {
			tabelaAcessos.adiciona(acessos[d]);
		}

		tabelaAcessos.setModel(tabelaAcessos.modelo);
	}

	private void consultarAcessos(String horarioEntrada, String horarioSaida, char tipo) {
		Usuario usuario = new Usuario();
		tabelaAcessos.resetModel();
		Object[] acessos = usuario.consultarAcessos(horarioEntrada, horarioSaida, tipo);

		if (acessos.length == 0) {
			new MensagemView(menuView.getString(181), menuView.getString(178), menuView, MensagemView.INFORMACAO);
		}
		tabelaAcessos.setLinhaSize(acessos.length);
		tabelaAcessos.setColunaSize(4);

		for (int d = 0; d < acessos.length; d++) {
			tabelaAcessos.adiciona(acessos[d]);
		}

		tabelaAcessos.setModel(tabelaAcessos.modelo);
	}

	class PesquisarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			consultarAcessos();
		}

	}

	class VoltarMenu implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			new SindicoControl(menuView);
		}

	}

	class TipoUsuarioListener implements ItemListener {

		@SuppressWarnings("static-access")
		public void itemStateChanged(ItemEvent evento) {
			if (evento.getStateChange() == evento.SELECTED) {
				if (tipoUsuario.getSelectedIndex() == SINDICO) {
					consultarAcessosSemCNPJ(painel);
				}

				if (tipoUsuario.getSelectedIndex() == ATENDENTE) {
					consultarAcessosSemCNPJ(painel);
				}

				if (tipoUsuario.getSelectedIndex() == FUNCIONARIO) {
					consultarAcessosComCNPJ(painel);
				}

				if (tipoUsuario.getSelectedIndex() == TODOS) {
					consultarAcessosTodos(painel);
					consultarTodosAcessos();
				}
			}
		}

	}

	private boolean validaData() {
		try {
			int anoInicio = Integer.parseInt(campoDe.getLetras().substring(6, 10));
			int mesInicio = Integer.parseInt(campoDe.getLetras().substring(3, 5));
			int anoFim = Integer.parseInt(campoAte.getLetras().substring(6, 10));
			int mesFim = Integer.parseInt(campoAte.getLetras().substring(3, 5));

			dataInicio = anoInicio + "-" + getHorarioEntrada().substring(3, 5) + "-"
					+ campoDe.getLetras().substring(0, 2);
			dataFim = anoFim + "-" + getHorarioSaida().substring(3, 5) + "-" + campoAte.getLetras().substring(0, 2);

			if (anoInicio == anoFim) {
				return true;
			} else if (mesInicio >= mesFim && (anoInicio + 1) >= anoFim) {
				return true;
			} else {
				new MensagemView(menuView.getString(182), menuView.getString(179), menuView, MensagemView.INFORMACAO);
			}
		} catch (Exception e) {
			new MensagemView(menuView.getString(182), menuView.getString(180), menuView, MensagemView.INFORMACAO);
		}
		return false;
	}

	private boolean verificaCnpj() {
		Empresa empresa = new Empresa();
		Iterator<Empresa> consulta = empresa.consultarEmpresas(campoCNPJ.getLetras()).iterator();
		if (consulta.hasNext() == true) {
			return true;
		} else {
			// mensagem reaprovaidata de consultar empresa
			new MensagemView(menuView.getString(144), menuView.getString(145), menuView, MensagemView.INFORMACAO);
		}
		return false;
	}
}
