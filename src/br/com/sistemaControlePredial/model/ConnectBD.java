package br.com.sistemaControlePredial.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.sistemaControlePredial.services.CarregadorArquivo;

public class ConnectBD
{

    public static Connection conexao;
    public static String user = "";
    public static String password = "";

    public static void carregaDriver()
    {
	try
	{
	    Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e)
	{
	    throw new RuntimeException("Driver Mysql não encontrado!");
	}
    }

    public static Connection obtemConexao()
    {
	try
	{
	    carregaDriver();
	    setUserMysql();

	    conexao = DriverManager.getConnection(
		    "jdbc:mysql://localhost:3306/sistemapredial?useTimezone=true&useSSL=false&serverTimezone=America/Sao_Paulo",
		    user, password);

	} catch (SQLException e)
	{
	    e.printStackTrace();
	    throw new RuntimeException("Usuario e Senha Mysql incorreto, verifique config.txt!");
	}
	return conexao;

    }

    @SuppressWarnings("resource")
    public static void setUserMysql()
    {
	FileReader f = null;
	BufferedReader leitor = null;
	try
	{
	    f = new FileReader(CarregadorArquivo.getFile("config.txt"));

	    leitor = new BufferedReader(f);
	    int contador = 0;

	    while (leitor.ready())
	    {

		String linha = leitor.readLine();
		contador++;

		if (contador == 2)
		{
		    user = linha.split(" ")[1].trim();
		    password = linha.split(" ")[2].trim();
		}

	    }

	} catch (IOException e)
	{
	    throw new RuntimeException("O arquivo config.txt está corrompido!");
	}

    }

}
