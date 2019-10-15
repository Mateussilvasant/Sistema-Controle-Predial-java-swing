package br.com.sistemaControlePredial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Iterator;

import br.com.sistemaControlePredial.model.Empresa;
import br.com.sistemaControlePredial.model.Usuario;
import br.com.sistemaControlePredial.view.CadastrarUsuario;
import br.com.sistemaControlePredial.view.MensagemView;
import br.com.sistemaControlePredial.view.MenuView;
import br.com.sistemaControlePredial.view.componentes.Panel;
import br.com.sistemaControlePredial.view.componentes.TextFieldFormatted;

public class CadastrarUsuarioControl extends CadastrarUsuario {

	public CadastrarUsuarioControl(MenuView menu, Panel painel) {
		super(menu, painel);
		addBotaoVoltarListener(new VoltarListener());
		addbotaoCadastrarListener(new CadastrarListener());
	}

	class VoltarListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			new GerenciarFuncionarioControl(menuView, painel);
		}

	}

	class CadastrarListener implements ActionListener {
		public void actionPerformed(ActionEvent evento) {

			boolean confirmacao;

			TextFieldFormatted campoTelefone2 = null;
			TextFieldFormatted campoCPF2 = null;
			TextFieldFormatted campoHorarioInicio2 = null;
			TextFieldFormatted campoHorarioFim2 = null;
			try {
				campoTelefone2 = new TextFieldFormatted("(##)####-####", 12);
				campoCPF2 = new TextFieldFormatted("###.###.###-##", 12);
				campoHorarioInicio2 = new TextFieldFormatted("##:##:00", 12);
				campoHorarioFim2 = new TextFieldFormatted("##:##:00", 12);

			} catch (ParseException excecao) {
				excecao.printStackTrace();
			}

			String cpf = campoCPF.getText();
			String nome = campoNome.getText();
			String sobrenome = campoSobrenome.getText();
			String telefone = campoTelefone.getText();
			String horaEntrada = campoHorarioInicio.getText();
			String horaSaida = campoHorarioFim.getText();
			String usu = campoUsuario.getText();
			String senha = campoSenha.getText();

			if (!cpf.equals(campoCPF2.getText()) && !nome.equals("") && !sobrenome.equals("")
					&& !telefone.equals(campoTelefone2.getText()) && !horaEntrada.equals(campoHorarioInicio2.getText())
					&& !horaSaida.equals(campoHorarioFim2.getText()) && !usu.equals("") && !senha.equals("")) {
				int horaMinima = Integer.parseInt(horaEntrada.substring(0, 2));
				int horaMaxima = Integer.parseInt(horaSaida.substring(0, 2));
				int minutoMinimo = Integer.parseInt(horaEntrada.substring(3, 5));
				int minutoMaximo = Integer.parseInt(horaSaida.substring(3, 5));
				if (horaMinima <= 24 && horaMinima >= 00 && horaMaxima <= 24 && horaMaxima >= 00 && minutoMinimo <= 60
						&& minutoMinimo >= 00 && minutoMaximo <= 60 && minutoMaximo >= 00) {
					Usuario usuario = new Usuario(cpf, nome, sobrenome, usu, senha, telefone, horaEntrada, horaSaida,
							'F', getSelecaoAlterarTemperaturaComboBox());
					String nomeEmpresa = getNomeEmpresaSelecionada().toString();
					confirmacao = usuario.cadastrar(getCNPJEmpresaSelecionada(nomeEmpresa));
					if (confirmacao == false)
						new MensagemView(menuView.getString(151), menuView.getString(152), menuView,
								MensagemView.INFORMACAO);
					else {
						new MensagemView(menuView.getString(166), menuView.getString(167), menuView,
								MensagemView.INFORMACAO);
						new GerenciarFuncionarioControl(menuView, painel);
						if (menuView.getUsuario().getTipo() == 0) {
							new SindicoControl(getMenuView());
						}

						if (menuView.getUsuario().getTipo() == 1) {
							new AtendenteControl(getMenuView());
						}
					}
				} else
					new MensagemView(menuView.getString(151), menuView.getString(153), menuView,
							MensagemView.INFORMACAO);
			} else {
				new MensagemView(menuView.getString(151), menuView.getString(154), menuView, MensagemView.INFORMACAO);
			}
		}
	}

	// retorna o nome da empresa que está selecionada no comboBox
	public Object getNomeEmpresaSelecionada() {
		return escolherEmpresa.getSelectedItem();
	}

	// retorna o que esta selecionado no combobox "escolhaAlterarTemperatura"
	// true para sim e false para não
	public static boolean getSelecaoAlterarTemperaturaComboBox() {
		if (escolhaAlterarTemperatura.getSelectedIndex() == 0)
			return true;
		else
			return false;
	}

	public static void setSelecaoComboBoxAlterarTemperatura(String permissao) {
		escolhaAlterarTemperatura.setSelectedItem(permissao);
	}

	public static void mudarHabilitacaoComboBoxEscolherEmpresa(boolean conf) {
		escolherEmpresa.setEnabled(conf);
	}

	public static void mudarHabilitacaoComboBoxAlterarTemperatura(boolean conf) {
		escolhaAlterarTemperatura.setEnabled(conf);
	}

	// Exibe o nome das empresas no ComboBox "escolher empresa"
	public static String[] getEmpresa() {
		Empresa e = new Empresa();

		String nomes[] = new String[e.getTotalEmpresa()];
		Iterator<Empresa> dados = e.empresaCadastrar().iterator();
		Empresa empresa;
		int controle = 0;

		while (dados.hasNext() == true) {
			empresa = dados.next();
			nomes[controle] = empresa.getRazaoSocial();
			controle++;
		}

		return nomes;
	}

	// usa o nome para retornar o CNPJ da empresa correspondente
	public String getCNPJEmpresaSelecionada(String nome) {
		Empresa e = new Empresa();
		Empresa empresaAux;
		Iterator<Empresa> objetoEmpresa = e.getEmpresa(nome).iterator();
		String cnpj = "";

		while (objetoEmpresa.hasNext()) {
			empresaAux = objetoEmpresa.next();
			cnpj = empresaAux.getCNPJNaoEstatico();
		}

		return cnpj;
	}

	// muda a string inicial que aparece no combobox "escolher empresa"
	public static void selecionarStringPreferidaComboBoxEmpresa(String cnpj) {
		Empresa e = new Empresa();
		Empresa empresaAux;
		Iterator<Empresa> objetoEmpresa = e.getNomeEmpresa(cnpj).iterator();
		String nome = "";

		while (objetoEmpresa.hasNext()) {
			empresaAux = objetoEmpresa.next();
			nome = empresaAux.getRazaoSocial();

		}

		escolherEmpresa.setSelectedItem(nome);
	}

}