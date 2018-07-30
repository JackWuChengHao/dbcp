package xiaowulab.dbcp;

import dbcp.util.DbcpDataSource;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {	
    	
    	while(Boolean.TRUE) {
    		System.out.println(DbcpDataSource.getInstance().getConnection());	
    	}
    	
    }
}
