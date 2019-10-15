package br.com.sistemaControlePredial.services;

import java.util.Calendar;

public class DataFormat {

	public static boolean compareToHora(String horaEntrada, String horaSaida) {
		boolean saida = false;

		String horarioAtualDoSistema = getHoraSistema();

		// Hora e minuto do sistema
		int horaSistema = Integer.parseInt(horarioAtualDoSistema.substring(0, 2));
		int minutoSistema = Integer.parseInt(horarioAtualDoSistema.substring(3, 5));

		// Hora e minuto do usuario
		int horaEntradaUsuario = Integer.parseInt(horaEntrada.substring(0, 2));
		int minutoEntradaUsuario = Integer.parseInt(horaEntrada.substring(3, 5));
		int horaSaidaUsuario = Integer.parseInt(horaSaida.substring(0, 2));
		int minutoSaidaUsuario = Integer.parseInt(horaSaida.substring(3, 5));

		int horaSistemaSoma = horaSistema + minutoSistema;
		int horaEntradaSoma = horaEntradaUsuario + minutoEntradaUsuario;
		int horaSaidaSoma = horaSaidaUsuario + minutoSaidaUsuario;

		if (horaSistemaSoma >= horaEntradaSoma && horaSistemaSoma <= horaSaidaSoma) {
			saida = true;
		}

		return saida;
	}

	public static String getHoraSistema() {
		String saida = "";
		saida = String.format("%1$tH:%1$tM:%1$tS\n", Calendar.getInstance());
		return saida;
	}

}