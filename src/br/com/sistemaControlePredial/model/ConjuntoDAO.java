package br.com.sistemaControlePredial.model;

import java.sql.*;
import java.util.ArrayList;

public class ConjuntoDAO 
{

   PreparedStatement st;
   Connection conn;

   public ConjuntoDAO() 
   {
      try 
      {
         conn = ConnectBD.obtemConexao();
      } 
      catch (Exception excecao) 
      {
         System.out.println("\n" + excecao.getLocalizedMessage());
      }
   }

   //Retorna o número total de conjuntos disponíveis 
   public int totalConjuntosVazios()
   {
	   int total = 0;
	   try
	   {
		   String sql = "select count(idConjunto) as 'total' from conjunto where Empresa_CNPJ is null;";
		   st = conn.prepareStatement(sql);
	       ResultSet resultSet = st.executeQuery();
	       
	       while (resultSet.next() == true) 
	       {
	    	   total = resultSet.getInt("Total");
	       }	   
	   }catch(SQLException e)
	   {
		   //e.printStackTrace();
		   total = 0;
	   }
	   
	   return total;
   }
   
   public int totalConjuntos(String CNPJ)
   {
	   int total = 0;
	   try
	   {
		   String sql = "select count(idConjunto) as 'total' from conjunto where Empresa_CNPJ = (?)";
		   st = conn.prepareStatement(sql);
		   st.setString(1, CNPJ);
	       ResultSet resultSet = st.executeQuery();
	       
	       while (resultSet.next() == true) 
	       {
	    	   total = resultSet.getInt("Total");
	       }	   
	   }catch(SQLException e)
	   {
		   //e.printStackTrace();
		   total = 0;
	   }
	   
	   return total;
   }
   
   //retorna idConjunto e a temperatura da empresa para o txt do Sistema de Ar-Condicionado
   public ArrayList<Conjunto> getObjetoConjunto()
   {
	   ArrayList<Conjunto> conjunto = new ArrayList<Conjunto>();
	   
	   try
	   {
		   String sqlRead = "select * from conjunto where Empresa_CNPJ != 'null'";
		   st = conn.prepareStatement( sqlRead );
		   ResultSet resultSet = st.executeQuery();
		   
		   while( resultSet.next() )
		   {
			   conjunto.add( new Conjunto( resultSet.getInt("andar"), resultSet.getInt("numero"), 
					   resultSet.getInt("idConjunto"), resultSet.getString("Empresa_CNPJ") ) );
		   }
		   
		   resultSet.close();
	   }
	   catch (Exception e) 
	   {
			e.printStackTrace();
			System.out.print(e.getLocalizedMessage());
			try 
			{
				conn.rollback();
			} catch (SQLException sqlE) 
			{
				System.out.print( sqlE.getLocalizedMessage() );
			}
		} 
	   finally 
	   {
			if (st != null) 
			{
				try 
				{
					st.close();
					conn.close();
				} catch (SQLException sqlE) 
				{
					System.out.print( sqlE.getLocalizedMessage() );
				}
			}
		}
	   return conjunto;
   }
   
   public ArrayList<Conjunto> getObjetoConjunto( String cnpj )
   {
	   ArrayList<Conjunto> conjunto = null;
	   
	   try
	   {
		   String sqlRead = "select * from conjunto where Empresa_CNPJ = ?";
		   st = conn.prepareStatement( sqlRead );
		   st.setString( 1, cnpj );
		   ResultSet resultSet = st.executeQuery();
		   
		   conjunto = new ArrayList<Conjunto>();
		   
		   while( resultSet.next() )
		   {
			   conjunto.add( new Conjunto( resultSet.getInt("andar"), resultSet.getInt("numero"), 
					   resultSet.getInt("idConjunto"), resultSet.getString("Empresa_CNPJ") ) );
		   }
		   
		   resultSet.close();
	   }
	   catch (Exception e) 
	   {
			e.printStackTrace();
			System.out.print(e.getLocalizedMessage());
			try 
			{
				conn.rollback();
			} catch (SQLException sqlE) 
			{
				System.out.print( sqlE.getLocalizedMessage() );
			}
		} 
	   finally 
	   {
			if (st != null) 
			{
				try 
				{
					st.close();
					conn.close();
				} catch (SQLException sqlE) 
				{
					System.out.print( sqlE.getLocalizedMessage() );
				}
			}
		}
	   return conjunto;
   }
   
   //Retorna todos os objetos conjuntos cadastrados
   public ArrayList<Conjunto> consultar() 
   {
      ArrayList<Conjunto> consulta = new ArrayList<Conjunto>();
   
      try 
      {
         String sql = "SELECT * FROM Conjunto where Empresa_CNPJ is null";
         st = conn.prepareStatement(sql);
         ResultSet resultSet = st.executeQuery();
         while (resultSet.next() == true) 
         {
            consulta.add(new Conjunto(resultSet.getInt("andar"), resultSet.getInt("numero"),resultSet.getInt("idConjunto")));
         }
         st.close();
      } 
      catch (SQLException e) 
      {
         System.err.println("Ero ao consultar -> Classe conjunto");
         System.out.println("\n" + e.getLocalizedMessage());
      }
      return consulta;
   }

   //Adiciona uma empresa ao conjunto
   public void atualizar(String CNPJ, int idConjunto) 
   {
      String sql = "Update conjunto set Empresa_CNPJ = (?) where idConjunto = (?)";
      try 
      {
         st = conn.prepareStatement(sql);
         st.setString(1, CNPJ);
         st.setInt(2, idConjunto);
         st.executeUpdate();
         st.close();
      
      } 
      catch (SQLException e) 
      {
         //System.out.println("\n" + e.getLocalizedMessage());
    	  try
			{
				conn.rollback();
			}
			catch( SQLException sqlE)
			{
				System.out.print( sqlE.getLocalizedMessage());
			}
      }
   }
   
   //Remove o conjunto da empresa
   public void remover(String CNPJ)
   {
      String sql = "UPDATE conjunto set Empresa_CNPJ = null where Empresa_CNPJ = (?)";
      try
      {
         st = conn.prepareStatement(sql);
         st.setString(1, CNPJ);
         st.executeUpdate();
         st.close();
      }
      catch(SQLException e)
      {
         System.out.println("\n" + e.getLocalizedMessage());
      }
   }
   
   //Retorna apenas os conjunto da empresa selecionada
   public ArrayList<Conjunto> EmpresaConsultar(String CNPJ) 
   {
      ArrayList<Conjunto> consulta = new ArrayList<Conjunto>();
   
      try 
      {
         String sql = "select * from conjunto where Empresa_CNPJ = (?)";
         st = conn.prepareStatement(sql);
         st.setString(1, CNPJ);
         ResultSet resultSet = st.executeQuery();
         while (resultSet.next() == true) 
         {
            consulta.add(new Conjunto(resultSet.getInt("andar"), resultSet.getInt("numero"),resultSet.getInt("idConjunto")));
         }
         st.close();
      } 
      catch (SQLException e) 
      {
         System.err.println("Ero ao consultar -> Classe conjunto");
         System.out.println("\n" + e.getLocalizedMessage());
      }
      return consulta;
   }
   
   
   //Retorna os conjunto disponíveis e que pertencem a empresa
   public ArrayList<Conjunto> atualizarEmpresa(String CNPJ) 
   {
      ArrayList<Conjunto> consulta = new ArrayList<Conjunto>();
   
      try 
      {
         String sql = "select * from conjunto where empresa_CNPJ is null or Empresa_CNPJ = (?)";
         st = conn.prepareStatement(sql);
         st.setString(1, CNPJ);
         ResultSet resultSet = st.executeQuery();
         while (resultSet.next() == true) 
         {
            consulta.add(new Conjunto(resultSet.getInt("andar"), resultSet.getInt("numero"),resultSet.getInt("idConjunto")));
         }
         st.close();
      } 
      catch (SQLException e) 
      {
         System.err.println("Ero ao consultar -> Classe conjunto");
         System.out.println("\n" + e.getLocalizedMessage());
      }
      return consulta;
   }
   
   
}
