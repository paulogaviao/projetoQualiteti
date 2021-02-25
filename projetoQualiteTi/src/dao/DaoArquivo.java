package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import bean.Arquivo;
import connection.SingleConnection;

public class DaoArquivo {

	private Connection connection;
	
	public DaoArquivo() {
		connection = SingleConnection.getConnection();
	}
    /*método para conectar e salvar os dados no banco de dados*/
	public void salvar(Arquivo arquivo) {
		try {
			String sql = "INSERT INTO arquivo(titulo1 , titulo2 )values(? ,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, arquivo.getTitulo1());
			statement.setString(2, arquivo.getTitulo2());
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	/*este metodo tem a funcionalidade de consultar o 
	 * banco de dados e colocar os dados consultados em uma lista*/
	
	public List<Arquivo>listaArquivo() throws Exception{
		List<Arquivo>listar = new ArrayList<Arquivo>();
		
		String sql="select * from arquivo";
		
		PreparedStatement statement =connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			Arquivo arquivo = new Arquivo();
			arquivo.setId(resultado.getLong("id"));
			arquivo.setTitulo1(resultado.getString("titulo1"));
			arquivo.setTitulo2(resultado.getString("titulo2"));
			listar.add(arquivo);
		}
		return listar;
	}
	
	
}
