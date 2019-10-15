package br.com.sistemaControlePredial.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {
	Connection conn;
	PreparedStatement prepared;
	ArrayList<String> conteudo;

	public UsuarioDAO() {
		conteudo = new ArrayList<String>();
		conn = ConnectBD.obtemConexao();
		prepared = null;
	}

	public boolean cadastrar(String cpf, String nome, String sobrenome, String usuario, String senha, String telefone,
			String horaEntrada, String horaSaida, char tipo, boolean permissaoAlterarTemperatura, String EmpresaCNPJ) {
		String sqlInsert = ("insert into usuario (cpf,nome,sobrenome,usuario,senha,telefone,horaEntrada, horaSaida, tipo, "
				+ "alterarTemperatura, Empresa_CNPJ) values (?,?,?,?,?,?,?,?,?,?,?)");
		boolean confirmacao = false;
		try {
			prepared = conn.prepareStatement(sqlInsert);
			prepared.setString(1, cpf);
			prepared.setString(2, nome);
			prepared.setString(3, sobrenome);
			prepared.setString(4, usuario);
			prepared.setString(5, senha);
			prepared.setString(6, telefone);
			prepared.setString(7, horaEntrada);
			prepared.setString(8, horaSaida);
			prepared.setString(9, "" + tipo);
			prepared.setBoolean(10, permissaoAlterarTemperatura);
			prepared.setString(11, EmpresaCNPJ);
			prepared.execute();
			confirmacao = true;
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.print(e.getLocalizedMessage());
			try {
				conn.rollback();
			} catch (SQLException sqlE) {
				// System.out.print( sqlE.getLocalizedMessage() );
			}
		} finally {
			if (prepared != null) {
				try {
					prepared.close();
					conn.close();
				} catch (SQLException sqlE) {
					// System.out.print( sqlE.getLocalizedMessage() );
				}
			}
		}

		return confirmacao;
	}

	public ArrayList<String> consultar(String cpf) {
		String sqlRead = ("select * from usuario where cpf = ?");
		ResultSet result = null;
		try {
			prepared = conn.prepareStatement(sqlRead);
			prepared.setString(1, cpf);
			result = prepared.executeQuery();
			conteudo.add(result.getString("cpf"));
			conteudo.add(result.getString("nome"));
			conteudo.add(result.getString("sobrenome"));
			conteudo.add(result.getString("telefone"));
			conteudo.add(result.getString("horaEntrada"));
			conteudo.add(result.getString("horaSaida"));
			conteudo.add(result.getString("tipo"));
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqlE) {
				System.out.print(sqlE.getLocalizedMessage());
			}
		} finally {
			if (prepared != null) {
				try {
					prepared.close();
					conn.close();
				} catch (SQLException sqlE) {
					System.out.print(sqlE.getLocalizedMessage());
				}
			}
		}

		return conteudo;
	}

	public Usuario getUsuario(String usuario, String senha, char tipo) {
		Usuario u = null;
		String sql = "select * from usuario where usuario = ? and senha = ? and tipo = ?";
		ResultSet result = null;

		try {

			prepared = conn.prepareStatement(sql);
			prepared.setString(1, usuario);
			prepared.setString(2, senha);
			prepared.setString(3, tipo + "");
			result = prepared.executeQuery();

			if (result.next()) {

				char aux = result.getString("tipo").charAt(0);

				u = new Usuario(result.getString("cpf"), result.getString("nome"), result.getString("sobrenome"),
						result.getString("usuario"), result.getString("senha"), result.getString("telefone"),
						result.getString("horaEntrada"), result.getString("horaSaida"), aux,
						result.getBoolean("alterarTemperatura"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqlE) {
				System.out.print(sqlE.getLocalizedMessage());
			}
		} finally {
			if (prepared != null) {
				try {
					prepared.close();
					conn.close();
				} catch (SQLException sqlE) {
					System.out.print(sqlE.getLocalizedMessage());
				}
			}
		}

		return u;

	}

	public String getNomeCorrespondenteAoCPF(String cpf, char tipoUsuario) {
		String nome = null;
		String sql = ("select nome from usuario where cpf = ? and tipo = ?");
		ResultSet set = null;

		try {
			prepared = conn.prepareStatement(sql);
			prepared.setString(1, cpf);
			prepared.setString(2, "" + tipoUsuario);
			set = prepared.executeQuery();

			if (set.next())
				nome = set.getString("nome");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqlE) {
				System.out.print(sqlE.getLocalizedMessage());
			}
		} finally {
			if (prepared != null) {
				try {
					prepared.close();
					conn.close();
				} catch (SQLException sqlE) {
					System.out.print(sqlE.getLocalizedMessage());
				}
			}
		}

		return nome;
	}

	// retorna dados num array do tipo usuario
	public ArrayList<Usuario> getObjetoUsuario(String cpf) {
		String sqlRead = ("select * from usuario where cpf = ?");
		ResultSet result = null;
		ArrayList<Usuario> conteudo = null;

		try {
			prepared = conn.prepareStatement(sqlRead);
			prepared.setString(1, cpf);
			result = prepared.executeQuery();

			// transforma a string "tipo de usuario", resgatada do banco, em
			// char
			if (result.next()) {
				char aux = result.getString("tipo").charAt(0);
				conteudo = new ArrayList<Usuario>();

				conteudo.add(new Usuario(result.getString("cpf"), result.getString("nome"),
						result.getString("sobrenome"), result.getString("usuario"), result.getString("senha"),
						result.getString("telefone"), result.getString("horaEntrada"), result.getString("horaSaida"),
						aux, result.getBoolean("alterarTemperatura")));

			}
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqlE) {
				System.out.print(sqlE.getLocalizedMessage());
			}
		} finally {
			if (prepared != null) {
				try {
					prepared.close();
					conn.close();
				} catch (SQLException sqlE) {
					System.out.print(sqlE.getLocalizedMessage());
				}
			}
		}

		return conteudo;
	}

	public String getCNPJCorrespondente(String cpf) {
		String sqlRead = ("select * from usuario where cpf = ?");
		ResultSet result = null;
		String cnpj = "";
		try {
			prepared = conn.prepareStatement(sqlRead);
			prepared.setString(1, cpf);
			result = prepared.executeQuery();

			if (result.next()) {
				cnpj = result.getString("Empresa_CNPJ");
			}
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqlE) {
				System.out.print(sqlE.getLocalizedMessage());
			}
		} finally {
			if (prepared != null) {
				try {
					prepared.close();
				} catch (SQLException sqlE) {
					System.out.print(sqlE.getLocalizedMessage());
				}
			}
		}

		return cnpj;
	}

	public boolean getPermissaoCorrespondente(String cpf) {
		String sqlRead = ("select * from usuario where cpf = ?");
		ResultSet result = null;
		boolean permissao = false;
		try {
			prepared = conn.prepareStatement(sqlRead);
			prepared.setString(1, cpf);
			result = prepared.executeQuery();

			if (result.next()) {
				permissao = result.getBoolean("alterarTemperatura");
			}
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqlE) {
				System.out.print(sqlE.getLocalizedMessage());
			}
		} finally {
			if (prepared != null) {
				try {
					prepared.close();
				} catch (SQLException sqlE) {
					System.out.print(sqlE.getLocalizedMessage());
				}
			}
		}

		return permissao;
	}

	public boolean atualizar(String cpf, String nome, String sobrenome, String usuario, String senha, String telefone,
			String horaEntrada, String horaSaida, boolean permissaoAlterarTemperatura) {
		String sqlUpdate = ("update usuario set nome = (?), sobrenome = (?), usuario = (?), senha = (?), telefone = (?),"
				+ "horaEntrada = (?), horaSaida = (?), alterarTemperatura = (?) where cpf = (?)");
		boolean conf = false;
		try {
			prepared = conn.prepareStatement(sqlUpdate);
			prepared.setString(1, nome);
			prepared.setString(2, sobrenome);
			prepared.setString(3, usuario);
			prepared.setString(4, senha);
			prepared.setString(5, telefone);
			prepared.setString(6, horaEntrada);
			prepared.setString(7, horaSaida);
			prepared.setBoolean(8, permissaoAlterarTemperatura);
			prepared.setString(9, cpf);
			prepared.execute();
			conf = true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqlE) {
				System.out.print(sqlE.getLocalizedMessage());
			}
		} finally {
			if (prepared != null) {
				try {
					prepared.close();
					conn.close();
				} catch (SQLException sqlE) {
					System.out.print(sqlE.getLocalizedMessage());
				}
			}
		}
		return conf;
	}

	public void excluir(String cpf) {
		String sqlDelete = ("delete from usuario where cpf = ?");

		try {
			prepared = conn.prepareStatement(sqlDelete);
			prepared.setString(1, cpf);
			prepared.execute();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqlE) {
				System.out.print(sqlE.getLocalizedMessage());
			}
		} finally {
			if (prepared != null) {
				try {
					// fecha preparedStatement
					prepared.close();
					// fecha conexão
					conn.close();
				} catch (SQLException sqlE) {
					System.out.print(sqlE.getLocalizedMessage());
				}
			}
		}
	}

	// Recupera os dados de acesso do sistema
	public ArrayList<Usuario> dadosAcesso(ArrayList<Usuario> conteudo) {
		String sql = "select CPF, usuario, senha, nome, sobrenome, HoraEntrada, HoraSaida, tipo, alterarTemperatura  from usuario";
		ResultSet result = null;
		try {
			prepared = conn.prepareStatement(sql);
			result = prepared.executeQuery();

			while (result.next() == true) {

				char aux = result.getString("tipo").charAt(0);
				conteudo.add(new Usuario(result.getString("CPF"), result.getString("nome"),
						result.getString("sobrenome"), result.getString("usuario"), result.getString("senha"), "",
						result.getString("horaEntrada"), result.getString("horaSaida"), aux,
						result.getBoolean("alterarTemperatura")));
			}
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqlE) {
				System.out.print(sqlE.getLocalizedMessage());
			}
		} finally {
			if (prepared != null) {
				try {
					prepared.close();
				} catch (SQLException sqlE) {
					System.out.print(sqlE.getLocalizedMessage());
				}
			}
		}

		return conteudo;
	}

	public void registrarSaida(String horaEntrada, String horarioSaida, String cpf) {
		String sql = "insert into acesso values (?,?,?)";
		try {
			prepared = conn.prepareStatement(sql);
			prepared.setString(1, horaEntrada);
			prepared.setString(2, horarioSaida);
			prepared.setString(3, cpf);
			prepared.executeUpdate();

		} catch (Exception excecao) {
			excecao.printStackTrace();
		}
	}

	public void registrarEntrada(String horarioEntrada, String cpf) {
		String sql = "insert into acesso value(?,?,?)";
		try {
			prepared = conn.prepareStatement(sql);
			prepared.setString(0, horarioEntrada);
			prepared.setString(1, "00:00:00");
			prepared.setString(2, cpf);
			prepared.execute();

		} catch (SQLException excecao) {
			excecao.printStackTrace();
		}
	}

	public int getTotalAcessos(String CNPJEmpresa, String DataInicio, String DataFim, char tipoUsuario) {
		String sql = "select count(acesso.usuarioCPF) as total from acesso inner join usuario on acesso.usuarioCPF = usuario.CPF where usuario.Empresa_CNPJ = (?) and date(acesso.horaEntrada) >= (?) and date(acesso.horaEntrada) <= (?) and usuario.Tipo = (?)";
		ResultSet resultado = null;
		int saida = 0;
		try {
			prepared = conn.prepareStatement(sql);
			prepared.setString(1, CNPJEmpresa);
			prepared.setString(2, DataInicio);
			prepared.setString(3, DataFim);
			prepared.setString(4, "" + tipoUsuario);
			resultado = prepared.executeQuery();
			while (resultado.next()) {
				saida = resultado.getInt("total");
			}

		} catch (SQLException excecao) {
			excecao.printStackTrace();
		}
		return saida;
	}

	public int getTotalAcessos() {
		String sql = "select count(acesso.usuarioCPF) as total from acesso inner join usuario on acesso.usuarioCPF = usuario.CPF";

		ResultSet resultado = null;
		int saida = 0;
		try {
			prepared = conn.prepareStatement(sql);
			resultado = prepared.executeQuery();
			while (resultado.next()) {
				saida = resultado.getInt("total");
			}

		} catch (SQLException excecao) {
			excecao.printStackTrace();
		}
		return saida;
	}

	public int getTotalAcessos(String DataInicio, String DataFim, char tipoUsuario) {
		String sql = "select count(acesso.usuarioCPF) as total from acesso inner join usuario on acesso.usuarioCPF = usuario.CPF where date(acesso.horaEntrada) >= (?) and date(acesso.horaEntrada) <= (?) and usuario.Tipo = (?)";

		ResultSet resultado = null;
		int saida = 0;
		try {
			prepared = conn.prepareStatement(sql);
			prepared.setString(1, DataInicio);
			prepared.setString(2, DataFim);
			prepared.setString(3, "" + tipoUsuario);
			resultado = prepared.executeQuery();
			while (resultado.next()) {
				saida = resultado.getInt("total");
			}

		} catch (SQLException excecao) {
			excecao.printStackTrace();
		}
		return saida;
	}

	public Object[][] consultarAcessos(String CNPJEmpresa, String DataInicio, String DataFim, char tipoUsuario) {
		String sql = "select acesso.usuarioCPF , usuario.Nome, acesso.horaEntrada, acesso.horaSaida from acesso inner join usuario on acesso.usuarioCPF = usuario.CPF where usuario.Empresa_CNPJ = (?) and date(acesso.horaEntrada) >= (?) and date(acesso.horaEntrada) <= (?) and usuario.Tipo = (?) order by day(acesso.horaEntrada), (acesso.horaSaida) desc";

		Object[][] objetos = null;
		ResultSet resultado = null;
		try {

			prepared = conn.prepareStatement(sql);
			prepared.setString(1, CNPJEmpresa);
			prepared.setString(2, DataInicio);
			prepared.setString(3, DataFim);
			prepared.setString(4, "" + tipoUsuario);
			resultado = prepared.executeQuery();
			objetos = new Object[getTotalAcessos(CNPJEmpresa, DataInicio, DataFim, tipoUsuario)][4];

			for (int d = 0; resultado.next(); d++) {
				objetos[d][0] = resultado.getString("acesso.usuarioCPF");
				objetos[d][1] = resultado.getString("usuario.Nome");
				objetos[d][2] = resultado.getString("acesso.horaEntrada");
				objetos[d][3] = resultado.getString("acesso.horaSaida");
			}

		} catch (SQLException excecao) {
			excecao.printStackTrace();
		}

		return objetos;
	}

	public Object[][] consultarAcessos() {
		String sql = "select  acesso.usuarioCPF , usuario.Nome, acesso.horaEntrada, acesso.horaSaida, usuario.Empresa_CNPJ from acesso inner join usuario on acesso.usuarioCPF = usuario.CPF order by day(acesso.horaEntrada),acesso.horaSaida desc";
		Object[][] objetos = null;
		ResultSet resultado = null;
		try {

			prepared = conn.prepareStatement(sql);
			resultado = prepared.executeQuery();
			objetos = new Object[getTotalAcessos()][4];

			for (int d = 0; resultado.next(); d++) {
				objetos[d][0] = resultado.getString("acesso.usuarioCPF");
				objetos[d][1] = resultado.getString("usuario.Nome");
				objetos[d][2] = resultado.getString("acesso.horaEntrada");
				objetos[d][3] = resultado.getString("acesso.horaSaida");
			}

		} catch (SQLException excecao) {
			excecao.printStackTrace();
		}

		return objetos;
	}

	public Object[][] consultarAcessos(String DataInicio, String DataFim, char tipoUsuario) {
		String sql = "select acesso.usuarioCPF , usuario.Nome, acesso.horaEntrada, acesso.horaSaida from acesso inner join usuario on acesso.usuarioCPF = usuario.CPF where date(acesso.horaEntrada) >= ? and date(acesso.horaEntrada) <= ? and usuario.Tipo = ? order by day(acesso.horaEntrada), (acesso.horaSaida) desc";
		Object[][] objetos = null;
		ResultSet resultado = null;
		try {

			prepared = conn.prepareStatement(sql);
			prepared.setString(1, DataInicio);
			prepared.setString(2, DataFim);
			prepared.setString(3, "" + tipoUsuario);
			resultado = prepared.executeQuery();
			objetos = new Object[getTotalAcessos(DataInicio, DataFim, tipoUsuario)][4];

			for (int d = 0; resultado.next(); d++) {
				objetos[d][0] = resultado.getString("acesso.usuarioCPF");
				objetos[d][1] = resultado.getString("usuario.Nome");
				objetos[d][2] = resultado.getString("acesso.horaEntrada");
				objetos[d][3] = resultado.getString("acesso.horaSaida");
			}

		} catch (SQLException excecao) {
			excecao.printStackTrace();
		}

		return objetos;
	}
}