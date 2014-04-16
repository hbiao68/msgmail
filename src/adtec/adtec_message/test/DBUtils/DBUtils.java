package adtec.adtec_message.test.DBUtils;

import java.sql.*;

public class DBUtils {

	private final static String driver="com.mysql.jdbc.Driver";
	private final static String url="jdbc:mysql://172.16.40.248/myspring";
	
	static
	{
		try
		{
			Class.forName(driver);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static Connection getConnection()throws Exception
	{
		Connection conn=DriverManager.getConnection(url, "root", "admin");
		return conn;
	}
	
	
	public static void close(ResultSet rs)
	{
		try
		{
			if(rs!=null)
			{
				rs.close();
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close(PreparedStatement pstm)
	{
	   try
	   {
		   if(pstm!=null)
		   {
			   pstm.close();
		   }
		   
	   }	
	   catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	}
	
	public static void close(Connection conn)
	{
		try
		{
			if(conn!=null)
			{
				//后销毁连接
				conn.close();
			}
			
		}
		catch(Exception ex)
		{
		}	
	}
	public static void main(String[] args) {
		try
		{
			Connection conn=DBUtils.getConnection();
			System.out.println(conn);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		

	}

}
