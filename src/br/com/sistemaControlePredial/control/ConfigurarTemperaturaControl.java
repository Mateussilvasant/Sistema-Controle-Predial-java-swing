package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import br.com.sistemaControlePredial.model.Conjunto;
import br.com.sistemaControlePredial.model.Empresa;
import br.com.sistemaControlePredial.model.Temperatura;
import br.com.sistemaControlePredial.view.ConfigurarTemperaturaView;
import br.com.sistemaControlePredial.view.MensagemView;
import br.com.sistemaControlePredial.view.MenuView;
import br.com.sistemaControlePredial.view.componentes.ButtonToggle;
import br.com.sistemaControlePredial.view.componentes.Panel;

public class ConfigurarTemperaturaControl extends ConfigurarTemperaturaView {

	String cnpj;

	public ConfigurarTemperaturaControl(MenuView menu, Panel painel) {
		super(menu, painel);
		addBuscarListener(new BotaoPesquisarListener());
		addBotaoTrocaListener(new BotaoStatusArListener());
	}

	// Carrega e adiciona os dados da empresa nos textfields correspondentes
	private void mostraDados() throws IOException {
		Empresa empresa = new Empresa();
		cnpj = consultarEmpresa.campoPesquisa.getLetras();
		Iterator<Empresa> consulta = empresa.consultarEmpresas(cnpj).iterator();

		Empresa empre = consulta.next();
		consultarEmpresa.campoNomeEmpresa.setText(empre.getRazaoSocial());
		consultarEmpresa.campoNomeEmpresa.setEditable(false);
		consultarEmpresa.campoTemperaturaMax.setText("" + empre.getTemperatura() + "�");
		consultarEmpresa.campoTemperaturaMax.setEditable(false);

		Temperatura t = new Temperatura();
		t.carregarTemperatura();
		;
		campoTempAtual.setText("" + t.getTemperaturaAtualDeUmaEmpresa(empre.getCNPJNaoEstatico()) + "�");
		campoTempAtual.setEditable(false);
		campoStatusAr.setText("" + t.getStatusAr(empre.getCNPJNaoEstatico()));
		campoStatusAr.setEditable(false);

		setEstiloBotaoTroca(Empresa.getCNPJ());
		geraRandoms(Empresa.getCNPJ());
	}

	public void geraRandoms(String cnpj) throws IOException {
		Temperatura t = new Temperatura();
		t.carregarTemperatura();
		String statusAr = t.getStatusAr(cnpj);

		if (statusAr.equals("Desligado"))
			t.desligarAr(cnpj);
		else
			t.ligarAr(cnpj);
	}

	// muda o estilo do botao dependendo do status que o sistemaAr.txt retornar
	private void setEstiloBotaoTroca(String cnpj) throws FileNotFoundException {
		Temperatura t = new Temperatura();
		t.carregarTemperatura();
		String statusAr = t.getStatusAr(cnpj);

		if (statusAr.equals("Desligado"))
			botaoTroca.setSelecaoBoolean(true);
		else
			botaoTroca.setSelecaoBoolean(false);

		botaoTroca.defineStyle();

	}

	/*
	 * verifica se o ar-condionado deve ser ligado ou não. O ar-condicionado será
	 * ligado se a temperatura ambiente atual for maior que cadastrada caso
	 * contrario ele deve ser desligado
	 */
	private boolean validarTemperatura() {
		// temperatura atual >= temperatura cadastrada
		if (Integer.parseInt(campoTempAtual.getText().substring(0, 2)) >= Integer
				.parseInt(consultarEmpresa.campoTemperaturaMax.getText().substring(0, 2)))
			return true;
		return false;
	}

	/*
	 * verifica se podemos ligar o ar-condicionado. Só podemos ligar ou desligar o
	 * ar, entre outras situações, se o horario da maquina estiver entre o seu
	 * horario de funcinamento
	 */
	public boolean getHorarioFuncionamentoAr() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String horarioAtual = sdf.format(cal.getTime());

		Empresa e = new Empresa();
		String horarioArInicio = e.getHorarioArInicioDoBanco(cnpj);
		String horarioArFim = e.getHorarioArFimDoBanco(cnpj);

		int horaAtual = Integer.parseInt(horarioAtual.substring(0, 2));
		int horaArInicio = Integer.parseInt(horarioArInicio.substring(0, 2));
		int horaArFim = Integer.parseInt(horarioArFim.substring(0, 2));

		if (horaArInicio <= horaAtual && horaAtual <= horaArFim)
			return true;
		return false;

	}

	// Carrega os conjuntos para a lista
	private void getConjuntosEmpresa() {
		Conjunto conjunto = new Conjunto();
		Iterator<Conjunto> consultaConjunto = conjunto.EmpresaConsultar(cnpj).iterator();
		Conjunto conj;

		while (consultaConjunto.hasNext() == true) {
			conj = consultaConjunto.next();
			consultarEmpresa.adicionarElemento(consultarEmpresa.menuView.getString(155) + ": " + conj.getAndar() + " | "
					+ consultarEmpresa.menuView.getString(156) + ": " + conj.getNumero());
		}
	}

	// classe responsável pelo botão "On/Off" do ar-condicionado
	class BotaoStatusArListener implements ActionListener {

		public void actionPerformed(ActionEvent evento1) {

			try {

				if (getHorarioFuncionamentoAr()) {
					if (botaoTroca.getSelecaoBoolean() == ButtonToggle.OFF) {

						Temperatura t = new Temperatura();

						if (validarTemperatura()) {
							t.ligarAr(cnpj);

							new MensagemView(consultarEmpresa.menuView.getString(187),
									consultarEmpresa.menuView.getString(188), consultarEmpresa.menuView,
									MensagemView.INFORMACAO);

							campoStatusAr.setText("Ligado");
						} else {
							new MensagemView(consultarEmpresa.menuView.getString(190),
									consultarEmpresa.menuView.getString(191), consultarEmpresa.menuView,
									MensagemView.INFORMACAO);

							// t.desligarAr(cnpj);

							consultarEmpresa.voltarMenuPrincipal(painel);
						}
					} else {
						if (botaoTroca.getSelecaoBoolean() == ButtonToggle.ON) {

							Temperatura t = new Temperatura();

							if (!validarTemperatura()) {
								t.desligarAr(cnpj);

								new MensagemView(consultarEmpresa.menuView.getString(187),
										consultarEmpresa.menuView.getString(189), consultarEmpresa.menuView,
										MensagemView.INFORMACAO);

								campoStatusAr.setText("Desligado");
							} else {
								new MensagemView(consultarEmpresa.menuView.getString(190),
										consultarEmpresa.menuView.getString(192), consultarEmpresa.menuView,
										MensagemView.INFORMACAO);

								// t.ligarAr(cnpj);

								consultarEmpresa.voltarMenuPrincipal(painel);
							}
						}
					}

					if (botaoTroca.getSelecaoBoolean() && validarTemperatura()) {
						botaoTroca.setSelecaoBoolean(false);
						botaoTroca.defineStyle();
					} else {
						if (!botaoTroca.getSelecaoBoolean() && !validarTemperatura()) {
							botaoTroca.setSelecaoBoolean(true);
							botaoTroca.defineStyle();
						}
					}
				} else {
					new MensagemView(consultarEmpresa.menuView.getString(183), consultarEmpresa.menuView.getString(184),
							consultarEmpresa.menuView, MensagemView.INFORMACAO);
					consultarEmpresa.voltarMenuPrincipal(painel);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// classe responsavel pela acao do botao "buscar empresa"
	class BotaoPesquisarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento1) {
			consultarEmpresa.removerElemento();
			try {
				// Dados da empresa
				mostraDados();
				// Dados do conjunto
				getConjuntosEmpresa();

				informacoesView(painel);

			} catch (Exception e) {
				new MensagemView(consultarEmpresa.menuView.getString(144), consultarEmpresa.menuView.getString(145),
						consultarEmpresa.menuView, MensagemView.INFORMACAO);

				consultarEmpresa.voltarMenuPrincipal(painel);
			}
		}

	}

}