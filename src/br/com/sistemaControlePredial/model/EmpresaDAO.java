package br.com.sistemaControlePredial.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

public class EmpresaDAO
{
    Connection conn;
    PreparedStatement prepared;
    ArrayList<String> conteudo;

    public EmpresaDAO()
    {
	conteudo = new ArrayList<String>();
	conn = ConnectBD.obtemConexao();
	prepared = null;
    }

    public boolean cadastrar(String cnpj, String razaoSocial, int temperatura, String horarioInicio, String horarioFim,
	    String horarioArInicio, String horarioArFim) throws RuntimeException
    {
	String sqlInsert = ("insert into empresa ( cnpj, " + "razaoSocial, temperatura, horaInicio, horaFim, "
		+ "horaArInicio, horaArFim ) values ( ?, ?, ?, ?, ?, ?, ? )");
	boolean sucesso = false;
	try
	{
	    prepared = conn.prepareStatement(sqlInsert);
	    prepared.setString(1, cnpj);
	    prepared.setString(2, razaoSocial);
	    prepared.setInt(3, temperatura);
	    prepared.setString(4, horarioInicio);
	    prepared.setString(5, horarioFim);
	    prepared.setString(6, horarioArInicio);
	    prepared.setString(7, horarioArFim);
	    prepared.execute();
	    sucesso = true;
	} catch (Exception e)
	{

	    if (e instanceof SQLIntegrityConstraintViolationException)
	    {
		throw new RuntimeException("" + 0); // 0 retorna quando CNPJ
						    // duplicado
	    }

	    if (e instanceof MysqlDataTruncation)
	    {
		throw new RuntimeException("" + 1); // 1 retorna quando um campo
						    // ta vazio
	    }
	    try
	    {
		conn.rollback();
	    } catch (SQLException sqlE)
	    {
		// System.out.print(sqlE.getLocalizedMessage());
	    }
	} finally
	{
	    if (prepared != null)
	    {
		try
		{
		    // fecha prepapredStatement
		    prepared.close();
		    // fecha conexão
		    conn.close();
		} catch (SQLException sqlE)
		{
		    System.out.print(sqlE.getLocalizedMessage());
		}
	    }
	}
	return sucesso;
    }

    public ArrayList<String> consultar(String cnpj)
    {
	String sqlRead = ("select * from empresa where cnpj = ?");
	ResultSet result = null;
	try
	{
	    prepared = conn.prepareStatement(sqlRead);
	    prepared.setString(1, cnpj);
	    result = prepared.executeQuery();
	    conteudo.add(result.getString("cnpj"));
	    conteudo.add(result.getString("razaoSocial"));
	    conteudo.add(result.getString("temperatura"));
	    conteudo.add(result.getString("horaInicio"));
	    conteudo.add(result.getString("horaFim"));
	    conteudo.add(result.getString("horaArInicio"));
	    conteudo.add(result.getString("horaArFim"));
	    result.close();
	} catch (Exception e)
	{
	    e.printStackTrace();
	    try
	    {
		conn.rollback();
	    } catch (SQLException sqlE)
	    {
		System.out.print(sqlE.getLocalizedMessage());
	    }
	} finally
	{
	    if (prepared != null)
	    {
		try
		{
		    prepared.close();
		    conn.close();
		} catch (SQLException sqlE)
		{
		    System.out.print(sqlE.getLocalizedMessage());
		}
	    }
	}

	return conteudo;
    }

    public String getHorarioArInicioDoBanco(String cnpj)
    {
	String sqlRead = "select horaArInicio from empresa where cnpj = ?", horaArInicio = "";
	ResultSet result = null;
	try
	{
	    prepared = conn.prepareStatement(sqlRead);
	    prepared.setString(1, cnpj);
	    result = prepared.executeQuery();

	    if (result.next())
		horaArInicio = result.getString("horaArInicio");

	    result.close();
	} catch (Exception e)
	{
	    e.printStackTrace();
	    try
	    {
		conn.rollback();
	    } catch (SQLException sqlE)
	    {
		System.out.print(sqlE.getLocalizedMessage());
	    }
	} finally
	{
	    if (prepared != null)
	    {
		try
		{
		    prepared.close();
		} catch (SQLException sqlE)
		{
		    System.out.print(sqlE.getLocalizedMessage());
		}
	    }
	}
	return horaArInicio;
    }

    public String getHorarioArFimDoBanco(String cnpj)
    {
	String sqlRead = "select horaArFim from empresa where cnpj = ?", horaArFim = "";
	ResultSet result = null;
	try
	{
	    prepared = conn.prepareStatement(sqlRead);
	    prepared.setString(1, cnpj);
	    result = prepared.executeQuery();

	    if (result.next())
		horaArFim = result.getString("horaArFim");

	    result.close();
	} catch (Exception e)
	{
	    e.printStackTrace();
	    try
	    {
		conn.rollback();
	    } catch (SQLException sqlE)
	    {
		System.out.print(sqlE.getLocalizedMessage());
	    }
	} finally
	{
	    if (prepared != null)
	    {
		try
		{
		    prepared.close();
		    conn.close();
		} catch (SQLException sqlE)
		{
		    System.out.print(sqlE.getLocalizedMessage());
		}
	    }
	}
	return horaArFim;
    }

    public ArrayList<Empresa> getConsultar(String CNPJ)
    {
	String sqlRead = ("select * from empresa where cnpj = ?");
	ResultSet result = null;
	ArrayList<Empresa> arrayDeEmpresa = new ArrayList<Empresa>();
	try
	{
	    prepared = conn.prepareStatement(sqlRead);
	    prepared.setString(1, CNPJ);
	    result = prepared.executeQuery();

	    while (result.next() == true)
	    {
		arrayDeEmpresa.add(new Empresa(result.getString("cnpj"), result.getString("razaoSocial"),
			result.getInt("temperatura"), result.getString("horaInicio"), result.getString("horaFim"),
			result.getString("horaArInicio"), result.getString("horaArFim")));
	    }

	} catch (SQLException e)
	{
	    e.printStackTrace();
	}

	return arrayDeEmpresa;
    }

    public ArrayList<Empresa> getEmpresa(String nome)
    {
	String sqlConsultar = "select * from empresa where razaoSocial = (?)";
	ResultSet result = null;
	ArrayList<Empresa> arrayDeEmpresa = new ArrayList<Empresa>();
	try
	{
	    prepared = conn.prepareStatement(sqlConsultar);
	    prepared.setString(1, nome);
	    result = prepared.executeQuery();

	    while (result.next())
	    {
		if (nome.equals(result.getString("razaoSocial")))
		    arrayDeEmpresa.add(new Empresa(result.getString("cnpj"), result.getString("razaoSocial"),
			    result.getInt("temperatura"), result.getString("horaInicio"), result.getString("horaFim"),
			    result.getString("horaArInicio"), result.getString("horaArFim")));
	    }
	    result.close();
	} catch (Exception e)
	{
	    e.printStackTrace();
	    try
	    {
		conn.rollback();
	    } catch (SQLException sqlE)
	    {
		System.out.print(sqlE.getLocalizedMessage());
	    }
	} finally
	{
	    if (prepared != null)
	    {
		try
		{
		    prepared.close();
		    conn.close();
		} catch (SQLException sqlE)
		{
		    System.out.print(sqlE.getLocalizedMessage());
		}
	    }
	}

	return arrayDeEmpresa;
    }

    public ArrayList<Empresa> getNomeEmpresa(String cnpj)
    {
	String sqlConsultar = "select * from empresa where CNPJ = (?)";
	ResultSet result = null;
	ArrayList<Empresa> arrayDeEmpresa = new ArrayList<Empresa>();
	try
	{
	    prepared = conn.prepareStatement(sqlConsultar);
	    prepared.setString(1, cnpj);
	    result = prepared.executeQuery();

	    while (result.next())
	    {
		if (cnpj.equals(result.getString("cnpj")))
		    arrayDeEmpresa.add(new Empresa(result.getString("cnpj"), result.getString("razaoSocial"),
			    result.getInt("temperatura"), result.getString("horaInicio"), result.getString("horaFim"),
			    result.getString("horaArInicio"), result.getString("horaArFim")));
	    }
	    result.close();
	} catch (Exception e)
	{
	    e.printStackTrace();
	    try
	    {
		conn.rollback();
	    } catch (SQLException sqlE)
	    {
		System.out.print(sqlE.getLocalizedMessage());
	    }
	} finally
	{
	    if (prepared != null)
	    {
		try
		{
		    prepared.close();
		    conn.close();
		} catch (SQLException sqlE)
		{
		    System.out.print(sqlE.getLocalizedMessage());
		}
	    }
	}

	return arrayDeEmpresa;
    }

    public ArrayList<Empresa> empresaCadastrar()
    {
	String sqlConsultar = "select * from empresa where CNPJ != '85.664.579/6954-25'";
	ResultSet result = null;
	ArrayList<Empresa> arrayDeEmpresa = new ArrayList<Empresa>();
	try
	{
	    prepared = conn.prepareStatement(sqlConsultar);
	    result = prepared.executeQuery();

	    while (result.next())
	    {
		arrayDeEmpresa.add(new Empresa(result.getString("cnpj"), result.getString("razaoSocial"),
			result.getInt("temperatura"), result.getString("horaInicio"), result.getString("horaFim"),
			result.getString("horaArInicio"), result.getString("horaArFim")));
	    }
	    result.close();
	} catch (Exception e)
	{
	    e.printStackTrace();
	    try
	    {
		conn.rollback();
	    } catch (SQLException sqlE)
	    {
		System.out.print(sqlE.getLocalizedMessage());
	    }
	} finally
	{
	    if (prepared != null)
	    {
		try
		{
		    prepared.close();
		    conn.close();
		} catch (SQLException sqlE)
		{
		    System.out.print(sqlE.getLocalizedMessage());
		}
	    }
	}

	return arrayDeEmpresa;
    }

    public int getTotalEmpresa()
    {
	String sqlConsultar = "select * from empresa where CNPJ != '85.664.579/6954-25'";
	ResultSet result = null;
	int total = 0;
	try
	{
	    prepared = conn.prepareStatement(sqlConsultar);
	    result = prepared.executeQuery();

	    while (result.next())
		total++;

	    result.close();
	} catch (Exception e)
	{
	    e.printStackTrace();
	    try
	    {
		conn.rollback();
	    } catch (SQLException sqlE)
	    {
		System.out.print(sqlE.getLocalizedMessage());
	    }
	} finally
	{
	    if (prepared != null)
	    {
		try
		{
		    prepared.close();
		} catch (SQLException sqlE)
		{
		    System.out.print(sqlE.getLocalizedMessage());
		}
	    }
	}

	return total;
    }

    public boolean atualizar(String CNPJ, String razSocial, int temP, String horIni, String horFim, String horArIni,
	    String horArFim)
    {
	String sqlUpdate = ("update empresa set razaoSocial = (?), "
		+ "temperatura = (?), horaInicio = (?), horaFim = (?), "
		+ "horaArInicio = (?), horaArFim = (?) where cnpj = (?)");
	boolean saida = false;

	try
	{
	    prepared = conn.prepareStatement(sqlUpdate);
	    prepared.setString(1, razSocial);
	    prepared.setInt(2, temP);
	    prepared.setString(3, horIni);
	    prepared.setString(4, horFim);
	    prepared.setString(5, horArIni);
	    prepared.setString(6, horArFim);
	    prepared.setString(7, CNPJ);
	    prepared.execute();
	    saida = true;
	} catch (Exception e)
	{
	    e.printStackTrace();
	    try
	    {
		conn.rollback();
		saida = false;
	    } catch (SQLException sqlE)
	    {
		System.out.print(sqlE.getLocalizedMessage());
	    }
	} finally
	{
	    if (prepared != null)
	    {
		try
		{
		    prepared.close();
		    conn.close();
		} catch (SQLException sqlE)
		{
		    System.out.print(sqlE.getLocalizedMessage());
		}
	    }
	}

	return saida;
    }

    public boolean atualizarTelaFuncionario(String CNPJ, int temP)
    {
	String sqlUpdate = ("UPDATE empresa set temperatura = (?) where cnpj = (?);");
	boolean saida = false;

	try
	{
	    prepared = conn.prepareStatement(sqlUpdate);
	    prepared.setInt(1, temP);
	    prepared.setString(2, CNPJ);
	    prepared.execute();
	    saida = true;
	} catch (Exception e)
	{
	    e.printStackTrace();
	    try
	    {
		conn.rollback();
		saida = false;
	    } catch (SQLException sqlE)
	    {
		System.out.print(sqlE.getLocalizedMessage());
	    }
	} finally
	{
	    if (prepared != null)
	    {
		try
		{
		    prepared.close();
		    conn.close();
		} catch (SQLException sqlE)
		{
		    System.out.print(sqlE.getLocalizedMessage());
		}
	    }
	}

	return saida;
    }

    public void deletar(String cnpj)
    {
	String sqlDelete = ("delete from empresa where cnpj = ?");

	try
	{
	    prepared = conn.prepareStatement(sqlDelete);
	    prepared.setString(1, cnpj);
	    prepared.execute();
	} catch (Exception e)
	{
	    e.printStackTrace();
	    try
	    {
		conn.rollback();
	    } catch (SQLException sqlE)
	    {
		System.out.print(sqlE.getLocalizedMessage());
	    }
	} finally
	{
	    if (prepared != null)
	    {
		try
		{
		    prepared.close();
		    conn.close();
		} catch (SQLException sqlE)
		{
		    System.out.print(sqlE.getLocalizedMessage());
		}
	    }
	}
    }
}
