package xiaowulab.dbcp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dbcp.util.DbcpDataSource;
import xiaowulab.entity.User;

/**
 * Hello world!
 *
 */
public class App 
{	
	public static void main( String[] args )  
	{	
		Connection connection = null;
		List<User> listuser = new ArrayList<User>();

		PreparedStatement prePAStatement = null;
		
		PreparedStatement insertPAStatement = null;
		
		
	   	String inserttestuser = "update user set username =? , age = ? where id = 3 ";

		String queryuser = "select * from user";
		try {

			connection  = DbcpDataSource.getInstance().getConnection();

			prePAStatement = connection.prepareStatement(queryuser);
			
			insertPAStatement = connection.prepareStatement(inserttestuser);
			
			insertPAStatement.setString(1,"xiaohuang");
			insertPAStatement.setInt(2,30);
			insertPAStatement.executeUpdate();

			ResultSet rs = prePAStatement.executeQuery();

			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("ID"));
				user.setUsername(rs.getString("USERNAME"));
				user.setAge(rs.getInt("AGE"));
				listuser.add(user);
			}

			for(User tmpuser : listuser) {
				System.out.println(tmpuser.getUsername());
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(null != prePAStatement)
				DbcpDataSource.closeStmt(prePAStatement);
			if(null != connection)
				DbcpDataSource.closeConnection(connection);
		}

	}
}
