package exercicio2;


	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

	   public class DAO2 {
		private Connection conexao;
		
		public DAO2() {
			conexao = null;
		}
		
		public boolean conectar() {
			String driverName = "org.postgresql.Driver";                    
			String serverName = "localhost";
			String mydatabase = "teste";
			int porta = 5432;
			String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
			String username = "ti2cc";
			String password = "ti@cc";
			boolean status = false;

			try {
				Class.forName(driverName);
				conexao = DriverManager.getConnection(url, username, password);
				status = (conexao == null);
				System.out.println("Conexão efetuada com o postgres!");
			} catch (ClassNotFoundException e) { 
				System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
			} catch (SQLException e) {
				System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
			}

			return status;
		}
		
		public boolean close() {
			boolean status = false;
			
			try {
				conexao.close();
				status = true;
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
			return status;
		}
		
		public boolean inserirCarros(Carros carros) {
	        boolean status = false;
	        try (PreparedStatement ps = conexao.prepareStatement("INSERT INTO carros (marca, cor, placa, ano) VALUES (?, ?, ?, ?)")) {
	            ps.setString(1, carros.getMarca());
	            ps.setString(2, carros.getCor());
	            ps.setString(3, carros.getPlaca());
	            ps.setInt(4, carros.getAno());
	            int rowsAffected = ps.executeUpdate();
	            status = (rowsAffected > 0);
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	        return status;
	    }

		public boolean atualizarCarros(Carros carros) {
		    boolean status = false;
		    try (PreparedStatement ps = conexao.prepareStatement("UPDATE carros SET marca = ?, cor = ?, placa = ? WHERE ano = ?")) {
		        ps.setString(1, carros.getMarca());
		        ps.setString(2, carros.getCor());
		        ps.setString(3, carros.getPlaca());
		        ps.setInt(4, carros.getAno());
		        int rowsAffected = ps.executeUpdate();
		        status = (rowsAffected > 0);
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    }
		    return status;
		}

	    public boolean excluirCarros(int ano) {
	        boolean status = false;
	        try (PreparedStatement ps = conexao.prepareStatement("DELETE FROM carros WHERE ano = ?")) {
	            ps.setInt(1, ano);
	            int rowsAffected = ps.executeUpdate();
	            status = (rowsAffected > 0);
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	        return status;
	    }

	    public Carros[] getCarross() {
	        Carros[] carross = null;
	        try (Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	             ResultSet rs = st.executeQuery("SELECT * FROM carros")) {
	            
	       
	            rs.last();
	            int numRows = rs.getRow();
	            rs.beforeFirst();
	            
	         
	            carross = new Carros[numRows];
	            
	        
	            int i = 0;
	            while (rs.next()) {
	                int ano = rs.getInt("ano");
	                String marca = rs.getString("marca");
	                String cor = rs.getString("cor");
	                String placa = rs.getString("placa");
	                
	                carross[i] = new Carros(marca, cor, placa, ano);
	                i++;
	            }
	        } catch (SQLException e) {
	            System.err.println("Erro ao buscar carros: " + e.getMessage());
	        }
	        return carross;
	    }

	    public Carros[] getCarrossBefore2015() {
	        Carros[] carross = null;
	        try (PreparedStatement ps = conexao.prepareStatement("SELECT * FROM carros WHERE ano < 2015",
	                                                               ResultSet.TYPE_SCROLL_INSENSITIVE,
	                                                               ResultSet.CONCUR_READ_ONLY);
	             ResultSet rs = ps.executeQuery()) {

	           
	            rs.last();
	            int numRows = rs.getRow();
	            rs.beforeFirst();

	          
	            carross = new Carros[numRows];

	           
	            int i = 0;
	            while (rs.next()) {
	                int ano = rs.getInt("ano");
	                String marca = rs.getString("marca");
	                String cor = rs.getString("cor");
	                String placa = rs.getString("placa");

	                carross[i] = new Carros(marca, cor, placa, ano);
	                i++;
	            }
	        } catch (SQLException e) {
	            System.err.println("Erro ao buscar carros anteriores a 2015: " + e.getMessage());
	        }
	        return carross;
	    }


}
