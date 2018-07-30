package dbcp.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.log4j.Logger;

/**
 * 用于获取Dbcp连接
 * @author Administrator
 *
 */
public class DbcpDataSource {

	private final static Logger logger = Logger.getLogger(DbcpDataSource.class);

	private DbcpDataSource() {};

	private static DataSource datasource = null;

	private final static DbcpDataSource dbcpDataSource = new DbcpDataSource();

	public static DbcpDataSource getInstance() {
		return dbcpDataSource;
	}

	{
		InputStream fis = DbcpDataSource.class.getClassLoader().getResourceAsStream("data.properties");
		
		Properties prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		try {
			datasource = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = datasource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("the connection is get failed");
			logger.error(e.getMessage(), e);
		}
		return connection;
	}
	
	
}
